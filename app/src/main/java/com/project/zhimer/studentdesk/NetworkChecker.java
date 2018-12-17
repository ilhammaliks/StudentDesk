package com.project.zhimer.studentdesk;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkChecker {

    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;
    private Context context;

    public NetworkChecker(NetworkInfo networkInfo) {
        this.connectivityManager = connectivityManager;
        this.networkInfo = networkInfo;
        this.context = context;
    }

    public boolean StatInetUser() {

        return networkInfo != null && networkInfo.isConnected();
    }


}


