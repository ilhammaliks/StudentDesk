package com.project.zhimer.studentdesk.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.project.zhimer.studentdesk.ForceUpdateChecker;
import com.project.zhimer.studentdesk.MainActivity;
import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;

public class SplashScreen extends AppCompatActivity implements ForceUpdateChecker.OnUpdateNeededListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //ilangin bar title di splashscreen
        getSupportActionBar().hide();

        final SessionManager sessionManager = new SessionManager(this);
        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    if (!sessionManager.isLogin()) {
                        startActivity(new Intent(SplashScreen.this, Login.class));
                        finish();
                    } else {
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                        finish();
                    }
                }
            }
        };
        thread.start();

        Log.d("Datas", sessionManager.getNim());
        ForceUpdateChecker.with(this).onUpdateNeeded(this).check();
    }

    @Override
    public void onUpdateNeeded(final String updateUrl) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("New version available")
                .setMessage("Please, update app to new version to continue.")
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        redirectStore(updateUrl);
                    }
                }).setNegativeButton("No, Thanks", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).create();
    }

    private void redirectStore(String updateUrl) {
        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(updateUrl));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
