package com.project.zhimer.studentdesk.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.project.zhimer.studentdesk.MainActivity;
import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;

public class Login extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        SessionManager sessionManager = new SessionManager(this);

        final EditText etNim = (EditText)findViewById(R.id.etNim);
        final EditText etPassword = (EditText)findViewById(R.id.etPassword);
        final CardView blogin = (CardView)findViewById(R.id.bLogin);

        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if(i == EditorInfo.IME_ACTION_GO);
                {
                    Intent login = new Intent(Login.this, MainActivity.class);
                    Login.this.startActivity(login);
                    finish();
                }
                return handled;
            }
        });

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(Login.this, MainActivity.class);
                Login.this.startActivity(login);
                finish();
            }
        });
    }
}
