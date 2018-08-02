package com.example.hb.invest.invest.utiles.views;

import com.example.hb.invest.invest.models.PaymentInfoResponse;
import com.example.hb.invest.invest.models.PriceWithTax;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 */
public class WSPaymentInfo extends WSUtils {
    @Override
    protected void enqueueWebService(Map<String, String> params, Map<String, RequestBody> fileUploadParams, Callback callback) {
        Call<PaymentInfoResponse> infoResponseCall = webServices.requestPaymentInfo(params);
        infoResponseCall.enqueue(callback);
    }
}
