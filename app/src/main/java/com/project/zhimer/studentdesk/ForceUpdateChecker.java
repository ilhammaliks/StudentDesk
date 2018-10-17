package com.project.zhimer.studentdesk;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class ForceUpdateChecker {

    public static final String KEY_UPDATE_REQUIRED = "force_update_required";
    public static final String KEY_CURRENT_VERSION = "force_update_current_version";
    public static final String KEY_UPDATE_URL = "force_update_store_url";
    public static final String KEY_MENU_KRS = "isiKRS";

    public static final String KEY_MENU_KRS_SP = "showKrsSP";
    public static final String KEY_MENU_PERKULIAHAN_SP = "showPerkuliahanSP";

    public static final String KEY_MENU_DAFTAR_SIDANG = "showDaftarSidang";
    public static final String KEY_MENU_DAFTAR_WISUDA = "showDaftarWisuda";

    private OnRemoteConfigListener onRemoteConfigListener;
    private Context context;

    public interface OnRemoteConfigListener {
        void onUpdateNeeded(String updateUrl);

        void onSetMenuKrs();

        void onSetMenuKrsSP();
        void onSetMenuPerkuliahanSp();

        void onSetMenuDaftarSidang();
        void onSetMenuDaftarWisuda();
    }

    public static Builder with(@NonNull Context context) {
        return new Builder(context);
    }

    public ForceUpdateChecker(@NonNull Context context, OnRemoteConfigListener onRemoteConfigListener) {
        this.context = context;
        this.onRemoteConfigListener = onRemoteConfigListener;
    }

    public void Check() {
        final FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();

        if (remoteConfig.getBoolean(KEY_UPDATE_REQUIRED)) {
            String currentViersion = remoteConfig.getString(KEY_CURRENT_VERSION); //untuk check value yang ada di firebase
            String appVersion = getAppVersion(context); // check value yang ada di apps
            String updateUrl = remoteConfig.getString(KEY_UPDATE_URL);

            if (!TextUtils.equals(currentViersion, appVersion) && onRemoteConfigListener != null) {
                onRemoteConfigListener.onUpdateNeeded(updateUrl);
            }
        }

        if (!remoteConfig.getBoolean(KEY_MENU_KRS)) {
            onRemoteConfigListener.onSetMenuKrs();
        }

        if (!remoteConfig.getBoolean(KEY_MENU_KRS_SP)){
            onRemoteConfigListener.onSetMenuKrsSP();
        }

        if (!remoteConfig.getBoolean(KEY_MENU_PERKULIAHAN_SP)){
            onRemoteConfigListener.onSetMenuPerkuliahanSp();
        }

        if (!remoteConfig.getBoolean(KEY_MENU_DAFTAR_SIDANG)){
            onRemoteConfigListener.onSetMenuDaftarSidang();
        }

        if (!remoteConfig.getBoolean(KEY_MENU_DAFTAR_WISUDA)){
            onRemoteConfigListener.onSetMenuDaftarWisuda();
        }
    }

    private String getAppVersion(Context context) {
        String result = "";

        try {
            result = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0)
                    .versionName;

            result = result.replaceAll("[a-z A-Z]|-", "");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static class Builder {
        private Context context;
        private OnRemoteConfigListener onRemoteConfigListener;

        Builder(Context context) {
            this.context = context;
        }

        public Builder onUpdateNeeded(OnRemoteConfigListener onRemoteConfigListener) {
            this.onRemoteConfigListener = onRemoteConfigListener;
            return this;
        }

        ForceUpdateChecker build() {
            return new ForceUpdateChecker(context, onRemoteConfigListener);
        }

        public ForceUpdateChecker check() {
            ForceUpdateChecker forceUpdateChecker = build();
            forceUpdateChecker.Check();

            return forceUpdateChecker;
        }
    }
}
