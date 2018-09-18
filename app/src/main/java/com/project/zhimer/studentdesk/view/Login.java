package com.project.zhimer.studentdesk.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.zhimer.studentdesk.MainActivity;
import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;

public class Login extends AppCompatActivity {

    String nim, password;

    EditText etNim, etPassword;
    Button toastFail, blogin;

    Animation fadein, fadeout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        final SessionManager sessionManager = new SessionManager(this);

        etNim = findViewById(R.id.etNim);
        etPassword = findViewById(R.id.etPassword);
        blogin = findViewById(R.id.bLogin);

        //animation toast
        toastFail = findViewById(R.id.toast_fail);
        toastFail.setAlpha(0);

        //set animation
        //animation
        fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
        fadeout = AnimationUtils.loadAnimation(this, R.anim.fadeout);

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean checkNim = false, checkPassword = false;

                nim = etNim.getText().toString();
                password = etPassword.getText().toString();

                //set condition if user do not input data
                if (nim.isEmpty()) {
                    etNim.setError("Nim tidak boleh kosong");
                } else {
                    checkNim = true;
                }

                if (password.isEmpty()) {
                    etPassword.setError("Password tidak boleh kosong");
                } else {
                    checkPassword = true;
                }

                if (checkNim && checkPassword) {

                    //save data nim dan passowrd di session
                    sessionManager.setNim(nim);
                    sessionManager.setPassword(password);
                    sessionManager.setLogin(true);

                    Intent login = new Intent(Login.this, MainActivity.class);
                    Login.this.startActivity(login);

                    finish();
                } else {
                    toastFail.setAlpha(1);
                    toastFail.startAnimation(fadein);
                    new BackgroundTask().execute();
                }
            }
        });
    }

    //asynchronus condition
    private class BackgroundTask extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            toastFail.startAnimation(fadeout);
            toastFail.setAlpha(0);
            etNim.setError(null);
            etPassword.setError(null);
        }
    }
}
