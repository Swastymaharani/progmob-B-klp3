package com.example.pemesanansalon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MulaiActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulai);
        new Handler().postDelayed(() -> {// splash screen 1500 ms, jadi splashscreennya cuma 1,5 detik
            startActivity(new Intent(MulaiActivity.this, Login.class));
            finish();
        }, 1500L);
    }
}