package com.example.pemesanansalon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MulaiActivity extends AppCompatActivity {
    //1. Activity untuk splash screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulai);
        new Handler().postDelayed(() -> {// splash screen 1500 ms, jadi splashscreennya cuma 1,5 detik
            startActivity(new Intent(MulaiActivity.this, Login.class));
            //Intent ke main menu yaitu main activity
            finish();// finish agar screen ini gk bakal bisa di back jadi org gbs ke splash screen lagi
        }, 1500L);// 1,5 detik
    }
}