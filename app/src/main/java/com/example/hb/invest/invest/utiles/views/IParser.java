package com.example.hb.invest.invest.utiles.views;

/**
 * Created by hb on 20-May-16.
 */
public interface IParser<T> {
    void successResponse(int requestCode, T response);

    void errorResponse(int requestCode, Throwable t);

    void noInternetConnection(int requestCode);

}
