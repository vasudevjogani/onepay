package com.example.hb.invest.invest.utiles.views;

import com.example.hb.invest.invest.models.CommonResponse;
import com.example.hb.invest.invest.models.HomeResponse;
import com.example.hb.invest.invest.models.LoginResponse;
import com.example.hb.invest.invest.models.OrderListResponse;
import com.example.hb.invest.invest.models.PaymentResponse;
import com.example.hb.invest.invest.models.PriceWithTax;
import com.example.hb.invest.invest.models.RegistrationResponse;
import com.example.hb.invest.invest.models.StaticPageResponse;
import com.example.hb.invest.invest.models.SuccessResponse;
import com.example.hb.invest.invest.models.ViewBillResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by hb on 28-May-16.
 */
public interface Webservice {

    @FormUrlEncoded
    @POST("api/auth/login")
    Call<LoginResponse> requestLogin(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("api/auth/register")
    Call<RegistrationResponse> requestRegister(@FieldMap Map<String, String> params);

    @GET("api/content/terms_condition")
    Call<StaticPageResponse> requestTerms(@QueryMap Map<String, String> params);

    @GET("api/content/security_policy")
    Call<StaticPageResponse> requestPrivacy(@QueryMap Map<String, String> params);

    @GET("api/content/faq")
    Call<StaticPageResponse> requestFaq(@QueryMap Map<String, String> params);

    @GET("api/content/about")
    Call<StaticPageResponse> requestAbout(@QueryMap Map<String, String> params);

    @GET("api/content/sitemap")
    Call<StaticPageResponse> requestSitemap(@QueryMap Map<String, String> params);

    @POST("api/content/create_ticket")
    Call<CommonResponse> requestSupport(@QueryMap Map<String, String> params);

    @POST("api/content/create_feedback")
    Call<CommonResponse> requestFeedback(@QueryMap Map<String, String> params);

    @GET("api/utility/utility_list")
    Call<HomeResponse> requestHome(@QueryMap Map<String, String> params);

    @POST("checkout/payment")
    Call<PaymentResponse> requestPayment(@QueryMap Map<String, String> params);

    @POST("home/getdstvbill")
    Call<ViewBillResponse> requestViewBill(@QueryMap Map<String, String> params);

    @GET("api/orders/list")
    Call<OrderListResponse> requestOrder(@QueryMap Map<String, String> params);

    @FormUrlEncoded
    @POST("api/auth/verify_email")
    Call<CommonResponse> requestEmailVerify(@FieldMap Map<String, String> params);

    @GET("api/orders/order_databy_orderid")
    Call<SuccessResponse> requestSuccess(@QueryMap Map<String, String> params);

    @FormUrlEncoded
    @POST("api/orders/price_with_tax")
    Call<PriceWithTax> requestPriceWithTax(@FieldMap Map<String, String> params);
}
