package com.project.zhimer.studentdesk.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.project.zhimer.studentdesk.MainActivity;
import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;

public class SplashScreen extends AppCompatActivity {

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
    }
}
