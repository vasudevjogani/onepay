package com.example.hb.invest.invest.utiles.views;

import com.example.hb.invest.invest.models.HomeResponse;
import com.example.hb.invest.invest.models.ViewBillResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 */
public class WSViewBill extends WSUtils {
    @Override
    protected void enqueueWebService(Map<String, String> params, Map<String, RequestBody> fileUploadParams, Callback callback) {
        Call<ViewBillResponse> viewBillResponseCall = webServices.requestViewBill(params);
        viewBillResponseCall.enqueue(callback);
    }
}
