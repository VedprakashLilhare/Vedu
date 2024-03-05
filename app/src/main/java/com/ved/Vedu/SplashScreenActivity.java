package com.ved.Vedu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.ved.mysafety.R;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar();
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreenActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        },1000);
    }
}