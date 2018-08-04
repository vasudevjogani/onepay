package com.example.hb.invest.invest.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.hb.invest.R;
import com.example.hb.invest.databinding.ActivityBasicInfoBinding;
import com.example.hb.invest.invest.models.PriceWithTax;
import com.example.hb.invest.invest.models.SuccessResponse;
import com.example.hb.invest.invest.utiles.views.IParser;
import com.example.hb.invest.invest.utiles.views.LoadingUtils;
import com.example.hb.invest.invest.utiles.views.UserDetail;
import com.example.hb.invest.invest.utiles.views.WSFactory;
import com.example.hb.invest.invest.utiles.views.WSResponse;
import com.example.hb.invest.invest.utiles.views.WSUtils;

import java.util.HashMap;

public class BasicInfoActivity extends AppCompatActivity implements IParser<WSResponse> {

    ActivityBasicInfoBinding binding;
    private Toolbar actionBarToolbar;
    private ActionBar ab;
    private String operator = "", amount = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_basic_info);
        setupToolbar();

        if (getIntent() != null) {
            operator = getIntent().getStringExtra("operator");
            amount = getIntent().getStringExtra("amount");
            requestForPriceTaxWs(operator, amount);
        }

        binding.tvContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void setupToolbar() {
        ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.btn_back);
        ab.setTitle("Basic Information");
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

    private void requestForPriceTaxWs(String operator, String amount) {
        LoadingUtils.getInstance(this).showLoading();
        WSFactory wsFactory = new WSFactory();
        WSUtils wsUtils = wsFactory.getWsUtils(WSFactory.WSType.WS_PRICE_WITH_TAX);
        HashMap<String, String> params = new HashMap<>();
        params.put("operator", operator);
        params.put("amount", amount);
        wsUtils.WSRequest(this, params, null, WSUtils.REQ_PRICE_WITH_TAX, this);
    }

    @Override
    public void successResponse(int requestCode, WSResponse response) {
        switch (requestCode) {
            case WSUtils.REQ_PRICE_WITH_TAX:
                parseSuccessWs((PriceWithTax) response);
                break;
            default:
                break;
        }
    }

    private void parseSuccessWs(PriceWithTax response) {
        if (response != null && response.getStatus() == 200) {
            PriceWithTax.AmountList amountList = response.getAmountList();

            binding.tvName.setText(UserDetail.getInstance(this).getFullname());
            binding.tvEmail.setText(UserDetail.getInstance(this).getUserName());
            binding.tvMoNumber.setText(UserDetail.getInstance(this).getMobile());
            binding.tvOperator.setText("");

            binding.tvAmount.setText(amountList.getAmount());
            binding.tvTax.setText(amountList.getTaxAmount());

            try {

                double amount = Double.parseDouble(amountList.getAmount());
                double tax = Double.parseDouble(amountList.getTaxAmount());

//                double deductAmount = amount + tax;
                Toast.makeText(this, amount+"" , Toast.LENGTH_SHORT).show();


//                binding.tvDeductAmount.setText(deductAmount + "");
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, e.toString() , Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Invalid data.", Toast.LENGTH_SHORT).show();
        }
        LoadingUtils.getInstance(this).hideLoading();
    }

    @Override
    public void errorResponse(int requestCode, Throwable t) {
        LoadingUtils.getInstance(this).hideLoading();
        Toast.makeText(this, t.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noInternetConnection(int requestCode) {
        LoadingUtils.getInstance(this).hideLoading();
        Toast.makeText(this, R.string.internet_not_available, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
