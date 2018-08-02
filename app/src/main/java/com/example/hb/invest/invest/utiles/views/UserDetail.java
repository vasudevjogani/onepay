package com.example.hb.invest.invest.utiles.views;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 */

public class UserDetail {
    public static final String PREF_NAME = "user_data";
    private static UserDetail instance;
    private SharedPreferences sh;
    private SharedPreferences.Editor edit;

    public UserDetail(Context mContext) {
        sh = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        edit = sh.edit();
    }

    public static synchronized UserDetail getInstance(Context context) {
        if (instance == null) {
            instance = new UserDetail(context);
        }
        return instance;
    }

    public void setDeviceToken(String deviceToken) {
        edit.putString(Constant.DEVICE_TOKEN, deviceToken).commit();
    }

    public String getDeviceToken() {
        return sh.getString(Constant.DEVICE_TOKEN, "");
    }

    public void setAppVersion(String deviceToken) {
        edit.putString("app_version", deviceToken).commit();
    }

    public String getAppVersion() {
        return sh.getString("app_version", "");
    }

    public void setDevice(String device) {
        edit.putString("Device", device).commit();
    }

    public String getDevice() {
        return sh.getString("Device", "");
    }

    public void setOSVersion(String osVersion) {
        edit.putString("OSVersion", osVersion).commit();
    }

    public String getOSVersion() {
        return sh.getString("OSVersion", "");
    }

    public void setUserId(String user_id) {
        edit.putString("user_id", user_id).commit();
    }

    public String getUserId() {
        return sh.getString("user_id", "");
    }

    public void setUserName(String userName) {
        edit.putString("userName", userName).commit();
    }

    public String getUserName() {
        return sh.getString("userName", "");
    }

    public String getPassword() {
        return sh.getString("Password", "");
    }

    public void setPassword(String Password) {
        edit.putString("Password", Password).commit();
    }

    public String getFullname() {
        return sh.getString("Fullname", "");
    }

    public void setFullname(String Fullname) {
        edit.putString("Fullname", Fullname).commit();
    }

    public String getToken() {
        return sh.getString("Token", "");
    }

    public void setToken(String Token) {
        edit.putString("Token", Token).commit();
    }

    public String getMobile() {
        return sh.getString("Mobile", "");
    }

    public void setMobile(String Mobile) {
        edit.putString("Mobile", Mobile).commit();
    }

    public boolean isAutoLogin() {
        return sh.getBoolean("AutoLogin", false);
    }

    public void setAutoLogin(boolean isAutoLogin) {
        edit.putBoolean("AutoLogin", isAutoLogin).commit();
    }

    public void clearAll() {
        setUserId("");
        setToken("");
        setUserName("");
        setFullname("");
        setMobile("");
        setAutoLogin(false);
    }
}
