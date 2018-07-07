package com.example.hb.invest.invest.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.hb.invest.R;
import com.example.hb.invest.databinding.ActivityManualPaymentBinding;

public class ManualPaymentActivity extends AppCompatActivity {

    ActivityManualPaymentBinding binding;
    private Toolbar actionBarToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manual_payment);
        setupToolbar();
        initComponent();
    }

    private void setupToolbar() {
        ActionBar ab = getActionBarToolbar();
//        ab.setHomeAsUpIndicator(R.drawable.btn_back);
        ab.setTitle("Manual Payment");
        ab.setDisplayHomeAsUpEnabled(false);
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

 /*   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/

    private void initComponent() {
        if (getIntent() != null) {
            String accNo = getIntent().getStringExtra("accountNo");
            String orderId = getIntent().getStringExtra("orderId");
            String label = getIntent().getStringExtra("label");

            binding.tvTitle.setText(label);
            binding.tvAccNo.setText(accNo);
            binding.tvOrderId.setText(orderId);
        }

        binding.tvGoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManualPaymentActivity.this,MainActivity.class);
                intent.putExtra("isFromWeb", true);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
