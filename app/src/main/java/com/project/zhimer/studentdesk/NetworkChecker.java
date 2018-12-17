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


        if (networkInfo != null && networkInfo.isConnected()) {


            return true;

        } else {
            Toast.makeText(context, "Koneksi Anda Bermasalah\nSilahkan Coba Beberapa Saat Lagi", Toast.LENGTH_LONG).show();
            return false;
        }
    }


}


