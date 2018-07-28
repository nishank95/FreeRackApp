package com.example.dell.freerackapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import butterknife.OnClick;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @OnClick(R.id.signup_btn)
    public void onSignUpClick(View v){
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        this.startActivity(intent);
    }

    @OnClick(R.id.login_btn)
    public void onLoginClick(View v){
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        this.startActivity(intent);
    }


}
