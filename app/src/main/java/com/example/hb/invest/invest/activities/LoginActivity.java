package com.example.hb.invest.invest.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.hb.invest.R;
import com.example.hb.invest.databinding.ActivityLoginBinding;
import com.example.hb.invest.invest.models.LoginResponse;
import com.example.hb.invest.invest.utiles.views.CommonUtils;
import com.example.hb.invest.invest.utiles.views.Constant;
import com.example.hb.invest.invest.utiles.views.IParser;
import com.example.hb.invest.invest.utiles.views.LoadingUtils;
import com.example.hb.invest.invest.utiles.views.UserDetail;
import com.example.hb.invest.invest.utiles.views.WSFactory;
import com.example.hb.invest.invest.utiles.views.WSResponse;
import com.example.hb.invest.invest.utiles.views.WSUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements IParser<WSResponse> {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        initComponent();
    }

    private void initComponent() {
        binding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestForLoginWs();
            }
        });

        binding.tvForgotPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPinActivity.class));
            }
        });
    }

    private void requestForLoginWs() {
        if (TextUtils.isEmpty(binding.etEmail.getText().toString())) {
            Toast.makeText(LoginActivity.this, "Please enter your email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.getText().toString()).matches()) {
            Toast.makeText(LoginActivity.this, "Please enter your valid email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(binding.etPassword.getText().toString())) {
            Toast.makeText(LoginActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }

        LoadingUtils.getInstance(LoginActivity.this).showLoading();
        WSFactory wsFactory = new WSFactory();
        WSUtils wsUtils = wsFactory.getWsUtils(WSFactory.WSType.WS_LOGIN);
        HashMap<String, String> params = new HashMap<>();
        params.put(Constant.USER_NAME, binding.etEmail.getText().toString());
        params.put(Constant.PASSWORD, binding.etPassword.getText().toString());
        wsUtils.WSRequest(this, params, null, WSUtils.REQ_LOGIN, this);
    }

    @Override
    public void successResponse(int requestCode, WSResponse response) {
        switch (requestCode) {
            case WSUtils.REQ_LOGIN:
                parseLoginWs((LoginResponse) response);
                break;
            default:
                break;
        }
    }

    private void parseLoginWs(LoginResponse response) {
        if (response != null && response.getStatus() == 200) {
            UserDetail.getInstance(this).setUserId(response.getCustomerNo());
            UserDetail.getInstance(this).setUserName(response.getUserName());
            UserDetail.getInstance(this).setFullname(response.getFullName());
            UserDetail.getInstance(this).setToken(response.getToken());
            UserDetail.getInstance(this).setMobile(response.getMobile());
            UserDetail.getInstance(this).setAutoLogin(true);
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Invalid email and password", Toast.LENGTH_SHORT).show();
        }
        LoadingUtils.getInstance(LoginActivity.this).hideLoading();
    }

    @Override
    public void errorResponse(int requestCode, Throwable t) {
        LoadingUtils.getInstance(LoginActivity.this).hideLoading();
        Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noInternetConnection(int requestCode) {
        LoadingUtils.getInstance(LoginActivity.this).hideLoading();
        Toast.makeText(LoginActivity.this, R.string.internet_not_available, Toast.LENGTH_SHORT).show();
    }
}
