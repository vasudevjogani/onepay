package com.example.hb.invest.invest.utiles.views;

import com.example.hb.invest.invest.models.OrderListResponse;
import com.example.hb.invest.invest.models.ViewBillResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 */
public class WSOrderList extends WSUtils {
    @Override
    protected void enqueueWebService(Map<String, String> params, Map<String, RequestBody> fileUploadParams, Callback callback) {
        Call<OrderListResponse> orderListResponseCall = webServices.requestOrder(params);
        orderListResponseCall.enqueue(callback);
    }
}
