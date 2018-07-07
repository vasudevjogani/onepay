package com.example.hb.invest.invest.utiles.views;

import com.example.hb.invest.invest.models.CommonResponse;
import com.example.hb.invest.invest.models.StaticPageResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by hb on 10/6/16.
 */
public class WSSupport extends WSUtils {
    @Override
    protected void enqueueWebService(Map<String, String> params, Map<String, RequestBody> fileUploadParams, Callback callback) {
        Call<CommonResponse> commonResponseCall = webServices.requestSupport(params);
        commonResponseCall.enqueue(callback);
    }
}
