package com.project.zhimer.studentdesk.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseInstanceService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

        String tokenRefreshed = FirebaseInstanceId.getInstance().getToken();

        Log.d("TOKEN", tokenRefreshed);
//        super.onTokenRefresh();
    }
}
