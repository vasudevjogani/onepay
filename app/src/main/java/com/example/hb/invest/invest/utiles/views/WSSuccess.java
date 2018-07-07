package com.example.hb.invest.invest.utiles.views;

import com.example.hb.invest.invest.models.CommonResponse;
import com.example.hb.invest.invest.models.SuccessResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by SHREEJI on 23-Mar-18.
 */

public class WSSuccess extends WSUtils {
    @Override
    protected void enqueueWebService(Map<String, String> params, Map<String, RequestBody> fileUploadParams, Callback callback) {
        Call<SuccessResponse> successResponseCall = webServices.requestSuccess(params);
        successResponseCall.enqueue(callback);
    }
}
