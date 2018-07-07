package com.example.hb.invest.invest.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.hb.invest.R;
import com.example.hb.invest.databinding.ActivityRegistrationBinding;
import com.example.hb.invest.invest.models.LoginResponse;
import com.example.hb.invest.invest.models.RegistrationResponse;
import com.example.hb.invest.invest.utiles.views.CommonUtils;
import com.example.hb.invest.invest.utiles.views.Constant;
import com.example.hb.invest.invest.utiles.views.IParser;
import com.example.hb.invest.invest.utiles.views.LoadingUtils;
import com.example.hb.invest.invest.utiles.views.WSFactory;
import com.example.hb.invest.invest.utiles.views.WSResponse;
import com.example.hb.invest.invest.utiles.views.WSUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegistrationActivity extends AppCompatActivity implements IParser<WSResponse> {

    ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration);
        initComponent();
    }

    private void initComponent() {
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.prefix_spinner));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(dataAdapter);

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.gender_spinner));
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerGender.setAdapter(genderAdapter);

        binding.tvDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_YEAR);
                int month = calendar.get(Calendar.DAY_OF_MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog mDate = new DatePickerDialog(RegistrationActivity.this, date, year, month, day);
                mDate.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                mDate.show();
            }
        });

        binding.tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestForRegistrationWs();
            }
        });
    }

    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            view.setMinDate(System.currentTimeMillis() - 1000);
        }
    };

    private void requestForRegistrationWs() {
        if (TextUtils.isEmpty(binding.etName.getText().toString())) {
            Toast.makeText(RegistrationActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(binding.etEmail.getText().toString())) {
            Toast.makeText(RegistrationActivity.this, "Please enter your email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.getText().toString()).matches()) {
            Toast.makeText(RegistrationActivity.this, "Please enter your valid email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(binding.etMobileNumber.getText().toString())) {
            Toast.makeText(RegistrationActivity.this, "Please enter your mobile number", Toast.LENGTH_SHORT).show();
            return;
        }

        if (binding.spinnerGender.getSelectedItem().equals("")) {
            Toast.makeText(RegistrationActivity.this, "Please select gender option.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(binding.tvDOB.getText().toString())) {
            Toast.makeText(RegistrationActivity.this, "Please select your DOB.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(binding.etPassword.getText().toString())) {
            Toast.makeText(RegistrationActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(binding.etAddress1.getText().toString())) {
            Toast.makeText(RegistrationActivity.this, "Please enter your address.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(binding.etTown.getText().toString())) {
            Toast.makeText(RegistrationActivity.this, "Please enter your town..", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(binding.etCity.getText().toString())) {
            Toast.makeText(RegistrationActivity.this, "Please enter your city.", Toast.LENGTH_SHORT).show();
            return;
        }

        LoadingUtils.getInstance(RegistrationActivity.this).showLoading();
        WSFactory wsFactory = new WSFactory();
        WSUtils wsUtils = wsFactory.getWsUtils(WSFactory.WSType.WS_REGISTRATION);
        HashMap<String, String> params = new HashMap<>();
        params.put(Constant.PREFIX, binding.spinner.getSelectedItem().toString());
        params.put(Constant.FULL_NAME, binding.etName.getText().toString());
        params.put(Constant.USER_NAME, binding.etEmail.getText().toString());
        params.put(Constant.MOBILE, binding.etMobileNumber.getText().toString());
        params.put(Constant.PASSWORD, binding.etPassword.getText().toString());

        params.put(Constant.ADDRESS_LINE1, binding.etAddress1.getText().toString());
        params.put(Constant.ADDRESS_LINE2, binding.etAddress2.getText().toString());
        params.put(Constant.TOWN, binding.etTown.getText().toString());
        params.put(Constant.CITY, binding.etCity.getText().toString());
        params.put(Constant.DATE_OF_BIRTH, binding.tvDOB.getText().toString());
        params.put(Constant.GENDER, binding.spinnerGender.getSelectedItem().toString());

        wsUtils.WSRequest(this, params, null, WSUtils.REQ_REGISTRATION, this);
    }

    @Override
    public void successResponse(int requestCode, WSResponse response) {
        switch (requestCode) {
            case WSUtils.REQ_REGISTRATION:
                parseRegistrationWs((RegistrationResponse) response);
                break;
            default:
                break;
        }
    }

    private void parseRegistrationWs(RegistrationResponse response) {
        if (response != null && response.getStatus() == 200) {
            Intent intent = new Intent(RegistrationActivity.this, EmailVerifyActivity.class);
            intent.putExtra("username", binding.etEmail.getText().toString());
            intent.putExtra("code", response.getVerification_code());
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, response != null ? response.getMessage() : "", Toast.LENGTH_SHORT).show();
        }
        LoadingUtils.getInstance(RegistrationActivity.this).hideLoading();
    }

    @Override
    public void errorResponse(int requestCode, Throwable t) {
        LoadingUtils.getInstance(RegistrationActivity.this).hideLoading();
        Toast.makeText(RegistrationActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noInternetConnection(int requestCode) {
        LoadingUtils.getInstance(RegistrationActivity.this).hideLoading();
        Toast.makeText(RegistrationActivity.this, R.string.internet_not_available, Toast.LENGTH_SHORT).show();
    }
}
