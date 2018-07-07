package com.example.hb.invest.invest.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hb.invest.R;
import com.example.hb.invest.databinding.ActivityLandingBinding;

public class LandingActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLandingBinding binding;
    private long backPressed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_landing);
        initComponent();
    }

    private void initComponent() {
        binding.tvLogin.setOnClickListener(this);
        binding.tvRegister.setOnClickListener(this);
        binding.llExchangeRate.setOnClickListener(this);
        binding.llContactUs.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvLogin:
                startActivity(new Intent(LandingActivity.this, LoginActivity.class));
                break;
            case R.id.tvRegister:
                startActivity(new Intent(LandingActivity.this, RegistrationActivity.class));
                break;
            case R.id.llExchangeRate:
                startActivity(new Intent(LandingActivity.this, ExchangeRateActivity.class));
                break;
            case R.id.llContactUs:
                startActivity(new Intent(LandingActivity.this, ContactUsActivity.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (backPressed + 2000 > System.currentTimeMillis()) {
            finish();
        } else {
            Toast.makeText(this, getString(R.string.press_once_again_to_exit_), Toast.LENGTH_LONG).show();
        }
        backPressed = System.currentTimeMillis();
    }
}
