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
import com.example.hb.invest.databinding.ActivitySucessBinding;
import com.example.hb.invest.invest.models.LoginResponse;
import com.example.hb.invest.invest.models.SuccessResponse;
import com.example.hb.invest.invest.utiles.views.IParser;
import com.example.hb.invest.invest.utiles.views.LoadingUtils;
import com.example.hb.invest.invest.utiles.views.UserDetail;
import com.example.hb.invest.invest.utiles.views.WSFactory;
import com.example.hb.invest.invest.utiles.views.WSResponse;
import com.example.hb.invest.invest.utiles.views.WSUtils;

import java.util.HashMap;

public class SucessActivity extends AppCompatActivity implements IParser<WSResponse> {

    ActivitySucessBinding binding;
    private Toolbar actionBarToolbar;
    private ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sucess);
        setupToolbar();
        if (getIntent() != null) {
            String id = getIntent().getStringExtra("id");
            requestForSuccessWs(id);
        }
    }

    private void setupToolbar() {
        ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.btn_back);
        ab.setTitle("Order Detail");
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
                Intent intent = new Intent(SucessActivity.this, MainActivity.class);
                intent.putExtra("isFromWeb", true);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void requestForSuccessWs(String id) {
        LoadingUtils.getInstance(this).showLoading();
        WSFactory wsFactory = new WSFactory();
        WSUtils wsUtils = wsFactory.getWsUtils(WSFactory.WSType.WS_SUCCESS);
        HashMap<String, String> params = new HashMap<>();
        params.put("access_token", UserDetail.getInstance(this).getToken());
        params.put("order_id", id);
        wsUtils.WSRequest(this, params, null, WSUtils.REQ_SUCCESS, this);
    }

    @Override
    public void successResponse(int requestCode, WSResponse response) {
        switch (requestCode) {
            case WSUtils.REQ_SUCCESS:
                parseSuccessWs((SuccessResponse) response);
                break;
            default:
                break;
        }
    }

    private void parseSuccessWs(SuccessResponse response) {
        if (response != null && response.getStatus() == 200) {
            SuccessResponse.OrderList order = response.getOrderList().get(0);
            if (order != null) {
                if (order.getType().equalsIgnoreCase("electricity")) {
                    binding.tvOrderType.setText("Electricity");
                } else if (order.getType().equalsIgnoreCase("mobile")) {
                    binding.tvOrderType.setText("Mobile");
                } else if (order.getType().equalsIgnoreCase("paytv")) {
                    binding.tvOrderType.setText("PayTv");
                } else if (order.getType().equalsIgnoreCase("council_bill")) {
                    binding.tvOrderType.setText("Council Bill");
                }

                binding.tvOrderId.setText(order.getOrderId());

                if (!TextUtils.isEmpty(order.getAmount())) {
                    binding.tvAmount.setText(order.getAmount());
                }

                if (order.getType().equalsIgnoreCase("electricity") && !TextUtils.isEmpty(order.getMeterNumber())) {
                    binding.tvNumberTitle.setText("Meter Number : ");
                    binding.tvNumber.setText(order.getMeterNumber());
                    binding.llMobile.setVisibility(View.VISIBLE);
                } else if (order.getType().equalsIgnoreCase("mobile") && !TextUtils.isEmpty(order.getMobileNumber())) {
                    binding.tvNumberTitle.setText("Mobile Number : ");
                    binding.tvNumber.setText(order.getMobileNumber());
                    binding.llMobile.setVisibility(View.VISIBLE);
                } else {
                    binding.llMobile.setVisibility(View.GONE);
                }

                if (order.getType().equalsIgnoreCase("paytv") && !TextUtils.isEmpty(order.getAccountNumber())) {
                    binding.tvAccountTitle.setText("Account Number : ");
                    binding.tvAccNumber.setText(order.getAccountNumber());
                    binding.llAccount.setVisibility(View.VISIBLE);
                } else if (order.getType().equalsIgnoreCase("council_bill") && !TextUtils.isEmpty(order.getPlotNumber())) {
                    binding.tvAccountTitle.setText("Plot Number : ");
                    binding.tvAccNumber.setText(order.getPlotNumber());
                    binding.llAccount.setVisibility(View.VISIBLE);
                } else {
                    binding.llAccount.setVisibility(View.GONE);
                }

                binding.tvOrderStatus.setText(order.getStatus());

                if (order.getStatus().equalsIgnoreCase("completed")) {
                    binding.tvOrderStatus.setTextColor(getResources().getColor(R.color.green));
                } else if (order.getStatus().equalsIgnoreCase("initiated")) {
                    binding.tvOrderStatus.setTextColor(getResources().getColor(R.color.white));
                } else if (order.getStatus().equalsIgnoreCase("")) {
                    binding.tvOrderStatus.setText("canceled");
                    binding.tvOrderStatus.setTextColor(getResources().getColor(R.color.red));
                }

                binding.tvDate.setText(order.getCreatedDate());
//                binding.tvDetails.setText(Html.fromHtml(order.getConfirmResponse()));
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
        Intent intent = new Intent(SucessActivity.this, MainActivity.class);
        intent.putExtra("isFromWeb", true);
        startActivity(intent);
        finish();
    }
}
