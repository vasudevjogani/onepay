package com.example.hb.invest.invest.utiles.views;

import com.example.hb.invest.invest.models.StaticPageResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 */
public class WSSitemap extends WSUtils {
    @Override
    protected void enqueueWebService(Map<String, String> params, Map<String, RequestBody> fileUploadParams, Callback callback) {
        Call<StaticPageResponse> staticPageResponseCall = webServices.requestSitemap(params);
        staticPageResponseCall.enqueue(callback);
    }
}
