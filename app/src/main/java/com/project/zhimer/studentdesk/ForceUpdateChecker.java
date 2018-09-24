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

    private OnUpdateNeededListener onUpdateNeededListener;
    private Context context;

    public interface OnUpdateNeededListener
    {
        void onUpdateNeeded(String updateUrl);
    }

    public static Builder with(@NonNull Context context)
    {
        return new Builder(context);
    }

    public ForceUpdateChecker(@NonNull Context context, OnUpdateNeededListener onUpdateNeededListener)
    {
        this.context = context;
        this.onUpdateNeededListener = onUpdateNeededListener;
    }

    public void Check()
    {
        final FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();

        if (remoteConfig.getBoolean(KEY_UPDATE_REQUIRED))
        {
            String currentViersion = remoteConfig.getString(KEY_CURRENT_VERSION);
            String appVersion = getAppVersion(context);
            String updateUrl = remoteConfig.getString(KEY_UPDATE_URL);

            if (!TextUtils.equals(currentViersion, appVersion) && onUpdateNeededListener != null)
            {
                onUpdateNeededListener.onUpdateNeeded(updateUrl);
            }
        }
    }

    private String getAppVersion(Context context) {
        String result = "";

        try{
            result = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0)
                    .versionName;

            result = result.replaceAll("[a - z A - Z]|-", "");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static class Builder
    {
        private Context context;
        private OnUpdateNeededListener onUpdateNeededListener;

        public Builder(Context context)
        {
            this.context = context;
        }

        public Builder onUpdateNeeded(OnUpdateNeededListener onUpdateNeededListener)
        {
            this.onUpdateNeededListener = onUpdateNeededListener;
            return this;
        }

        public ForceUpdateChecker build()
        {
            return new ForceUpdateChecker(context, onUpdateNeededListener);
        }

        public ForceUpdateChecker check()
        {
            ForceUpdateChecker forceUpdateChecker = build();
            forceUpdateChecker.Check();

            return forceUpdateChecker;
        }
    }
}
