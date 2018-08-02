package com.example.hb.invest.invest.utiles.views;

import com.example.hb.invest.invest.models.LoginResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 */
public class WSLogin extends WSUtils {
    @Override
    protected void enqueueWebService(Map<String, String> params, Map<String, RequestBody> fileUploadParams, Callback callback) {
        Call<LoginResponse> loginModelCall = webServices.requestLogin(params);
        loginModelCall.enqueue(callback);
    }
}
