package com.example.hb.invest.invest.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hb.invest.R;
import com.example.hb.invest.databinding.ActivityWaterBinding;
import com.example.hb.invest.invest.models.HomeResponse;
import com.example.hb.invest.invest.models.PaymentResponse;
import com.example.hb.invest.invest.utiles.views.Constant;
import com.example.hb.invest.invest.utiles.views.IParser;
import com.example.hb.invest.invest.utiles.views.LoadingUtils;
import com.example.hb.invest.invest.utiles.views.UserDetail;
import com.example.hb.invest.invest.utiles.views.WSFactory;
import com.example.hb.invest.invest.utiles.views.WSResponse;
import com.example.hb.invest.invest.utiles.views.WSUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class WaterActivity extends AppCompatActivity implements IParser<WSResponse> {

    ActivityWaterBinding binding;
    private Toolbar actionBarToolbar;
    private ActionBar ab;
    private ArrayList<HomeResponse.WaterBill> waterBillList = new ArrayList<>();
    private ArrayList<String> operatorList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_water);
        setupToolbar();
        initComponent();
    }

    private void setupToolbar() {
        ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.btn_back);
        ab.setTitle("Water");
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

        Intent intent = getIntent();
        if (intent != null) {

            Bundle args = intent.getBundleExtra("BUNDLE");
            waterBillList = (ArrayList<HomeResponse.WaterBill>) args.getSerializable("Water");

            for (HomeResponse.WaterBill waterBill : waterBillList) {
                operatorList.add(waterBill.getUtilityName());
            }
            operatorList.add(0, "Select operator");

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, operatorList);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinner.setAdapter(dataAdapter);

            binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0) {
                        ((TextView) view).setTextColor(ContextCompat.getColor(WaterActivity.this, R.color.light_gray));
                    } else {
                        ((TextView) view).setTextColor(ContextCompat.getColor(WaterActivity.this, R.color.colorPrimaryDark));
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            binding.tvPayBill.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!binding.spinner.getSelectedItem().equals("Select operator")) {
//                        requestForPaymentWs();
                    }
                }
            });
        }
    }

    private void requestForPaymentWs() {
        LoadingUtils.getInstance(WaterActivity.this).showLoading();
        WSFactory wsFactory = new WSFactory();
        WSUtils wsUtils = wsFactory.getWsUtils(WSFactory.WSType.WS_PAYMENT);
        HashMap<String, String> params = new HashMap<>();
        params.put(Constant.AMOUNT, "10");
        params.put(Constant.TYPE, "10");
        params.put(Constant.MOBILE_NUMBER, "10");
        params.put(Constant.METER_NUMBER, "10");
        params.put(Constant.OPERATOR, "10");
        params.put(Constant.ACCOUNT_NUMBER, "10");
        params.put(Constant.USER_ID, UserDetail.getInstance(this).getUserId());
        wsUtils.WSRequest(this, params, null, WSUtils.REQ_PAYMENT, this);
    }

    @Override
    public void successResponse(int requestCode, WSResponse response) {
        switch (requestCode) {
            case WSUtils.REQ_PAYMENT:
                parsePaymentWs((PaymentResponse) response);
                LoadingUtils.getInstance(WaterActivity.this).hideLoading();
                break;
            default:
                break;
        }
    }

    private void parsePaymentWs(PaymentResponse response) {
        if (response != null && response.getStatus()==200) {
            String url = response.getUrl();
            Intent intent = new Intent(WaterActivity.this, WebActivity.class);
            intent.putExtra("url", url);
            startActivity(intent);
        }else {
            Toast.makeText(this, response != null ? response.getMessage() : "", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void errorResponse(int requestCode, Throwable t) {
//        Toast.makeText(WaterActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noInternetConnection(int requestCode) {
        Toast.makeText(WaterActivity.this, R.string.internet_not_available, Toast.LENGTH_SHORT).show();
    }
}
