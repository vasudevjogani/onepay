package com.example.hb.invest.invest.models;

import com.example.hb.invest.invest.utiles.views.WSResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StaticPageResponse implements WSResponse {

    @SerializedName("html")
    @Expose
    private String html;

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

}