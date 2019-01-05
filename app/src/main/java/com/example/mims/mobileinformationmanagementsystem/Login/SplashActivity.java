package com.example.mims.mobileinformationmanagementsystem.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.mims.mobileinformationmanagementsystem.R;

public class SplashActivity extends Activity {
    int SPLASH_DISPLAY_LENGHT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_DISPLAY_LENGHT);
    }
}
