package com.example.hb.invest.invest.utiles.views;

import com.example.hb.invest.invest.models.CommonResponse;
import com.example.hb.invest.invest.models.LoginResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by SHREEJI on 23-Mar-18.
 */

public class WSEmailVerify extends WSUtils {
    @Override
    protected void enqueueWebService(Map<String, String> params, Map<String, RequestBody> fileUploadParams, Callback callback) {
        Call<CommonResponse> commonResponseCall = webServices.requestEmailVerify(params);
        commonResponseCall.enqueue(callback);
    }
}
