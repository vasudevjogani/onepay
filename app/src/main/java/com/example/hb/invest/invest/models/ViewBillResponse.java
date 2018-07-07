package com.example.hb.invest.invest.models;

import com.example.hb.invest.invest.utiles.views.WSResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewBillResponse implements WSResponse {

    @SerializedName("data_caption")
    @Expose
    private String dataCaption;
    @SerializedName("data_type")
    @Expose
    private String dataType;
    @SerializedName("data_entry_type")
    @Expose
    private String dataEntryType;
    @SerializedName("response_code")
    @Expose
    private String responseCode;
    @SerializedName("balance")
    @Expose
    private String balance;
    @SerializedName("data_reference_number")
    @Expose
    private String dataReferenceNumber;

    public String getDataCaption() {
        return dataCaption;
    }

    public void setDataCaption(String dataCaption) {
        this.dataCaption = dataCaption;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataEntryType() {
        return dataEntryType;
    }

    public void setDataEntryType(String dataEntryType) {
        this.dataEntryType = dataEntryType;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getDataReferenceNumber() {
        return dataReferenceNumber;
    }

    public void setDataReferenceNumber(String dataReferenceNumber) {
        this.dataReferenceNumber = dataReferenceNumber;
    }

}