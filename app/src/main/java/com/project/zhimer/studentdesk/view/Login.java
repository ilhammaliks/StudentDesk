package com.project.zhimer.studentdesk.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.zhimer.studentdesk.MainActivity;
import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;

public class Login extends AppCompatActivity {

    String nim, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        final SessionManager sessionManager = new SessionManager(this);

        final EditText etNim = findViewById(R.id.etNim);
        final EditText etPassword = findViewById(R.id.etPassword);
        final Button blogin = findViewById(R.id.bLogin);

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
                }
            }
        });
    }
}
