package com.example.hb.invest.invest.utiles.views;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hb on 28-May-16.
 */
public abstract class WSUtils {
    private final String LOCAL_URL = "https://www.onepay.co.zm/";


    private final String BASE_URL = LOCAL_URL;
    public Webservice webServices;
    public static final int REQ_LOGIN = 101;
    public static final int REQ_REGISTRATION = 102;
    public static final int REQ_STATIC_PAGE = 103;
    public static final int REQ_PAYMENT = 104;
    public static final int REQ_SUPPORT = 105;
    public static final int REQ_FEEDBACK = 106;
    public static final int REQ_HOME = 107;
    public static final int REQ_VIEW_BILL = 108;
    public static final int REQ_ORDER_LIST = 109;
    public static final int REQ_VERIFY_CODE = 110;
    public static final int REQ_SUCCESS = 111;
    public static final int REQ_PRICE_WITH_TAX = 112;

    public WSUtils() {
        initRetrofit(BASE_URL);
    }

    public WSUtils(String customBaseUrl) {
        initRetrofit(customBaseUrl);
    }

    private void initRetrofit(String baseURl) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        /*Uncomment following line to enable logging of WS*/
        httpClient.addInterceptor(logging);
        httpClient.connectTimeout(1, TimeUnit.MINUTES);
        httpClient.readTimeout(1, TimeUnit.MINUTES);
        httpClient.writeTimeout(1, TimeUnit.MINUTES);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        webServices = retrofit.create(Webservice.class);
        httpClient.addInterceptor(logging);
    }

    protected abstract void enqueueWebService(Map<String, String> params, Map<String, RequestBody> fileUploadParams, Callback callback);

    public void WSRequest(final Context context, Map<String, String> params, Map<String, RequestBody> fileUploadParams,
                          final int requestCode, final IParser<WSResponse> iParser) {
        if (!isNetworkAvailable(context)) {
            LoadingUtils.getInstance(context).hideLoading();
            iParser.noInternetConnection(requestCode);
        } else {
            Callback<WSResponse> callback = new Callback<WSResponse>() {
                @Override
                public void onResponse(Call<WSResponse> call, Response<WSResponse> response) {
                    if (context instanceof Activity && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && ((Activity) context).isDestroyed()) {
                        return;
                    }
                    iParser.successResponse(requestCode, response.body());
                }

                @Override
                public void onFailure(Call<WSResponse> call, Throwable t) {
                    LoadingUtils.getInstance(context).hideLoading();
                    if (context instanceof Activity && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && ((Activity) context).isDestroyed()) {
                        return;
                    }
                    iParser.errorResponse(requestCode, t);
                }
            };
            enqueueWebService(params, fileUploadParams, callback);
        }
    }

    private boolean isNetworkAvailable(Context mContext) {
        boolean connection = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                NetworkInfo net_info = cm.getActiveNetworkInfo();
                if (net_info != null && net_info.isConnected()) {
                    connection = true;
                    int type = net_info.getType();
                    switch (type) {
                        case ConnectivityManager.TYPE_MOBILE:
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
