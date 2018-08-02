package com.example.hb.invest.invest.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.hb.invest.R;
import com.example.hb.invest.databinding.ActivityMtnpaymentBinding;
import com.example.hb.invest.invest.models.PaymentInfoResponse;
import com.example.hb.invest.invest.models.PaymentResponse;
import com.example.hb.invest.invest.models.PriceWithTax;
import com.example.hb.invest.invest.utiles.views.IParser;
import com.example.hb.invest.invest.utiles.views.LoadingUtils;
import com.example.hb.invest.invest.utiles.views.WSFactory;
import com.example.hb.invest.invest.utiles.views.WSResponse;
import com.example.hb.invest.invest.utiles.views.WSUtils;

import java.util.HashMap;

public class MTNPaymentActivity extends AppCompatActivity implements IParser<WSResponse> {

    ActivityMtnpaymentBinding binding;
    private Toolbar actionBarToolbar;
    private ActionBar ab;
    private String orderId = "", mobileNumber = "";
    private int count = 0;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mtnpayment);
        setupToolbar();
        initComponent();
    }

    private void setupToolbar() {
        ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.btn_back);
        ab.setTitle("MTN Money Mobile");
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

    private void initComponent() {
        handler = new Handler();
        if (getIntent() != null) {
            orderId = getIntent().getStringExtra("orderId");
            mobileNumber = getIntent().getStringExtra("mobile");

            binding.tvText.setText("Please approve the transaction from your phone using your MTN Mobile Money PIN. We email you your transaction details and an SMS Confirmation on (" + mobileNumber + ") once the transaction is completed.");

            requestForPaymentInfoWs();
        }

        binding.tvGoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MTNPaymentActivity.this, MainActivity.class);
                intent.putExtra("isFromWeb", true);
                startActivity(intent);
                finish();
            }
        });
    }

    private void requestForPaymentInfoWs() {
        LoadingUtils.getInstance(this).showLoading();
        WSFactory wsFactory = new WSFactory();
        WSUtils wsUtils = wsFactory.getWsUtils(WSFactory.WSType.WS_PAYMENT_INFO);
        HashMap<String, String> params = new HashMap<>();
        params.put("order_id", orderId);
        wsUtils.WSRequest(this, params, null, WSUtils.REQ_PAYMENT_INFO, this);
    }

    @Override
    public void successResponse(int requestCode, WSResponse response) {
        switch (requestCode) {
            case WSUtils.REQ_PAYMENT_INFO:
                parsePaymentInfoWs((PaymentInfoResponse) response);
                break;
            default:
                break;
        }
    }

    private void parsePaymentInfoWs(PaymentInfoResponse response) {
        if (response != null) {
            if (response.getSuccess().equalsIgnoreCase("1")) {
                Intent intent = new Intent(MTNPaymentActivity.this, SucessActivity.class);
                intent.putExtra("id", orderId);
                startActivity(intent);
                finish();
            } else if (response.getSuccess().equalsIgnoreCase("2")) {
                Intent intent = new Intent(MTNPaymentActivity.this, MainActivity.class);
                intent.putExtra("isFromWeb", true);
                startActivity(intent);
                finish();
            } else if (response.getSuccess().equalsIgnoreCase("0")) {
                if (count == 5) {
                    Intent intent = new Intent(MTNPaymentActivity.this, MainActivity.class);
                    intent.putExtra("isFromWeb", true);
                    startActivity(intent);
                    finish();
                } else {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            count++;
                            requestForPaymentInfoWs();
                        }
                    }, 60000);
                }
            }
            LoadingUtils.getInstance(this).hideLoading();
        }

    }

    @Override
    public void errorResponse(int requestCode, Throwable t) {
        Toast.makeText(MTNPaymentActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noInternetConnection(int requestCode) {
        Toast.makeText(MTNPaymentActivity.this, R.string.internet_not_available, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {

    }
}
