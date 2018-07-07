package com.example.hb.invest.invest.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.hb.invest.R;
import com.example.hb.invest.databinding.ActivityEmailVerifyBinding;
import com.example.hb.invest.invest.models.CommonResponse;
import com.example.hb.invest.invest.models.RegistrationResponse;
import com.example.hb.invest.invest.utiles.views.Constant;
import com.example.hb.invest.invest.utiles.views.IParser;
import com.example.hb.invest.invest.utiles.views.LoadingUtils;
import com.example.hb.invest.invest.utiles.views.WSFactory;
import com.example.hb.invest.invest.utiles.views.WSResponse;
import com.example.hb.invest.invest.utiles.views.WSUtils;

import java.util.HashMap;

public class EmailVerifyActivity extends AppCompatActivity implements IParser<WSResponse> {

    ActivityEmailVerifyBinding binding;
    String username = "", code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_email_verify);
        initComponent();
    }

    private void initComponent() {
        if (getIntent() != null) {
            username = getIntent().getStringExtra("username");
            code = getIntent().getStringExtra("code");

            binding.tvSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    requestForEmailVerifyWs();
                }
            });
        }
    }

    private void requestForEmailVerifyWs() {
        if (TextUtils.isEmpty(binding.etVerificationCode.getText().toString())) {
            Toast.makeText(EmailVerifyActivity.this, "Please enter verification code.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!binding.etVerificationCode.getText().toString().equalsIgnoreCase(code)) {
            Toast.makeText(EmailVerifyActivity.this, "Please enter valid verification code", Toast.LENGTH_SHORT).show();
            return;
        }

        LoadingUtils.getInstance(EmailVerifyActivity.this).showLoading();
        WSFactory wsFactory = new WSFactory();
        WSUtils wsUtils = wsFactory.getWsUtils(WSFactory.WSType.WS_EMAIL_VERIFY);
        HashMap<String, String> params = new HashMap<>();
        params.put(Constant.USER_NAME, username);
        params.put(Constant.VERIFICATION_CODE, binding.etVerificationCode.getText().toString());
        wsUtils.WSRequest(this, params, null, WSUtils.REQ_VERIFY_CODE, this);
    }

    @Override
    public void successResponse(int requestCode, WSResponse response) {
        switch (requestCode) {
            case WSUtils.REQ_VERIFY_CODE:
                parseRegistrationWs((CommonResponse) response);
                break;
            default:
                break;
        }
    }

    private void parseRegistrationWs(CommonResponse response) {
        if (response != null && response.getStatus() == 200) {
            startActivity(new Intent(EmailVerifyActivity.this, LoginActivity.class));
            finish();
        } else {
            Toast.makeText(this, response != null ? response.getMessage() : "", Toast.LENGTH_SHORT).show();
        }
        LoadingUtils.getInstance(EmailVerifyActivity.this).hideLoading();
    }

    @Override
    public void errorResponse(int requestCode, Throwable t) {
        LoadingUtils.getInstance(EmailVerifyActivity.this).hideLoading();
        Toast.makeText(EmailVerifyActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noInternetConnection(int requestCode) {
        LoadingUtils.getInstance(EmailVerifyActivity.this).hideLoading();
        Toast.makeText(EmailVerifyActivity.this, R.string.internet_not_available, Toast.LENGTH_SHORT).show();
    }
}
