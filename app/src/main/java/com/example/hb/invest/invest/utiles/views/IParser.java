package com.example.hb.invest.invest.utiles.views;

/**
 */
public interface IParser<T> {
    void successResponse(int requestCode, T response);

    void errorResponse(int requestCode, Throwable t);

    void noInternetConnection(int requestCode);

}
