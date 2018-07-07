package com.example.hb.invest.invest.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.hb.invest.R;
import com.example.hb.invest.databinding.ActivityExchangeRateBinding;

public class ExchangeRateActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityExchangeRateBinding binding;
    private Toolbar actionBarToolbar;
    private ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_exchange_rate);
        setupToolbar();
        initComponent();
    }

    private void setupToolbar() {
        ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.btn_back);
        ab.setTitle("Exchange Rate");
        ab.setDisplayHomeAsUpEnabled(true);
    }

    protected ActionBar getActionBarToolbar() {
        if (actionBarToolbar == null) {
            actionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (actionBarToolbar != null) {
                actionBarToolbar.setTitleTextColor(getResources().getColor(R.color.white));
                setSupportActionBar(actionBarToolbar);
            }
        }
        return getSupportActionBar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initComponent() {
        binding.tvZMW.setOnClickListener(this);
        binding.tvGBP.setOnClickListener(this);
        binding.tvZAR.setOnClickListener(this);
        binding.tvEUR.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvZMW:
                if (binding.llZMWView.getVisibility()==View.VISIBLE) {
                    binding.llZMWView.setVisibility(View.GONE);
                    binding.llGBP.setVisibility(View.VISIBLE);
                    binding.llZAR.setVisibility(View.VISIBLE);
                    binding.llEUR.setVisibility(View.VISIBLE);
                } else {
                    binding.llZMWView.setVisibility(View.VISIBLE);
                    binding.llGBP.setVisibility(View.GONE);
                    binding.llZAR.setVisibility(View.GONE);
                    binding.llEUR.setVisibility(View.GONE);
                }
                break;

            case R.id.tvGBP:
                if (binding.llGBPView.getVisibility()==View.VISIBLE) {
                    binding.llGBPView.setVisibility(View.GONE);
                    binding.llZMW.setVisibility(View.VISIBLE);
                    binding.llZAR.setVisibility(View.VISIBLE);
                    binding.llEUR.setVisibility(View.VISIBLE);
                } else {
                    binding.llGBPView.setVisibility(View.VISIBLE);
                    binding.llZMW.setVisibility(View.GONE);
                    binding.llZAR.setVisibility(View.GONE);
                    binding.llEUR.setVisibility(View.GONE);
                }
                break;

            case R.id.tvZAR:
                if (binding.llZARView.getVisibility()==View.VISIBLE) {
                    binding.llZARView.setVisibility(View.GONE);
                    binding.llGBP.setVisibility(View.VISIBLE);
                    binding.llZMW.setVisibility(View.VISIBLE);
                    binding.llEUR.setVisibility(View.VISIBLE);
                } else {
                    binding.llZARView.setVisibility(View.VISIBLE);
                    binding.llGBP.setVisibility(View.GONE);
                    binding.llZMW.setVisibility(View.GONE);
                    binding.llEUR.setVisibility(View.GONE);
                }
                break;

            case R.id.tvEUR:
                if (binding.llEURView.getVisibility()==View.VISIBLE) {
                    binding.llEURView.setVisibility(View.GONE);
                    binding.llGBP.setVisibility(View.VISIBLE);
                    binding.llZAR.setVisibility(View.VISIBLE);
                    binding.llZMW.setVisibility(View.VISIBLE);
                } else {
                    binding.llEURView.setVisibility(View.VISIBLE);
                    binding.llGBP.setVisibility(View.GONE);
                    binding.llZAR.setVisibility(View.GONE);
                    binding.llZMW.setVisibility(View.GONE);
                }
                break;
        }
    }
}
