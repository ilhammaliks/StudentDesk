package com.project.zhimer.studentdesk;

import android.app.Application;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.HashMap;
import java.util.Map;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        final FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();

        Map<String, Object> remoteConfigDefaults = new HashMap<>();
        remoteConfigDefaults.put(ForceUpdateChecker.KEY_UPDATE_REQUIRED, false);
        remoteConfigDefaults.put(ForceUpdateChecker.KEY_CURRENT_VERSION, "1.0.0");
        remoteConfigDefaults.put(ForceUpdateChecker.KEY_UPDATE_URL, "https://www.google.com/");

        remoteConfigDefaults.put(ForceUpdateChecker.KEY_MENU_KRS, true);

        remoteConfigDefaults.put(ForceUpdateChecker.KEY_MENU_KRS_SP, true);
        remoteConfigDefaults.put(ForceUpdateChecker.KEY_MENU_PERKULIAHAN_SP, true);

        remoteConfigDefaults.put(ForceUpdateChecker.KEY_MENU_DAFTAR_SIDANG, true);
        remoteConfigDefaults.put(ForceUpdateChecker.KEY_MENU_DAFTAR_WISUDA, true);

        remoteConfig.setDefaults(remoteConfigDefaults);
        remoteConfig.fetch(60)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            remoteConfig.activateFetched();
                        }
                    }
                });
    }
}
