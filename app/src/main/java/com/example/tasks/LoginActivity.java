package com.example.tasks;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LinearLayout errorWindow = (LinearLayout) findViewById(R.id.ERROR_WINDOW);
        errorWindow.setVisibility(View.GONE);
        TextView errorText = (TextView) findViewById(R.id.ERROR_TEXT);
        AppCompatButton btnLogin = (AppCompatButton) findViewById(R.id.BTN_LOGIN);
        AppCompatButton btnRegister = (AppCompatButton) findViewById(R.id.BTN_REGISTER);
        CheckBox cbRememberMe = (CheckBox) findViewById(R.id.CHB_REMEMBER);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
