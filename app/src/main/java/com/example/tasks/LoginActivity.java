package com.example.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class LoginActivity extends AppCompatActivity {

    LoginManager loginManager;
    PrefsManager prefsManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginManager = new LoginManager(getApplicationContext());
        prefsManager = new PrefsManager(getApplicationContext());


        LinearLayout errorWindow = (LinearLayout) findViewById(R.id.ERROR_WINDOW);
        errorWindow.setVisibility(View.GONE);
        TextView errorText = (TextView) findViewById(R.id.ERROR_TEXT);
        AppCompatButton btnLogin = (AppCompatButton) findViewById(R.id.BTN_LOGIN);
        AppCompatButton btnRegister = (AppCompatButton) findViewById(R.id.BTN_REGISTER);
        CheckBox cbRememberMe = (CheckBox) findViewById(R.id.CHB_REMEMBER);
        cbRememberMe.setChecked(prefsManager.GetAutoLogin());
        cbRememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d("STATE", String.valueOf(b));
            }
        });


        if(prefsManager.GetAutoLogin()){
            if(loginManager.Login()){
                Intent intent = new Intent(LoginActivity.this,
                        HomeActivity.class);
                intent.putExtra("Text","User data");
                startActivity(intent);
                LoginActivity.this.finish();
            }else{
                // display error login
            }
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loginManager.Login()){
                    Intent intent = new Intent(LoginActivity.this,
                            HomeActivity.class);
                    intent.putExtra("Text","User data");
                    startActivity(intent);
                    LoginActivity.this.finish();
                }else{
                    // display error login
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // register form
            }
        });
    }

}
