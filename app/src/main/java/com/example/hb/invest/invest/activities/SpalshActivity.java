package com.example.hb.invest.invest.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hb.invest.R;
import com.example.hb.invest.invest.utiles.views.UserDetail;

public class SpalshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        splashThread();
    }

    protected void splashThread() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (UserDetail.getInstance(SpalshActivity.this).isAutoLogin()) {
                    startActivity(new Intent(SpalshActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(SpalshActivity.this, LandingActivity.class));
                }
                finish();
            }
        }, 2000);
    }
}
