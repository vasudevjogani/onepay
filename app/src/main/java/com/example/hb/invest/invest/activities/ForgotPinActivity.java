package com.example.hb.invest.invest.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.hb.invest.R;
import com.example.hb.invest.databinding.ActivityForgotPinBinding;

public class ForgotPinActivity extends AppCompatActivity {

    ActivityForgotPinBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_pin);
        initComponent();
    }

    private void initComponent() {
        binding.tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
