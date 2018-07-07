package com.example.hb.invest.invest.models;

import com.example.hb.invest.invest.utiles.views.WSResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SHREEJI on 10-Jun-18.
 */

public class PriceWithTax implements WSResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("amount_list")
    @Expose
    private AmountList amountList;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AmountList getAmountList() {
        return amountList;
    }

    public void setAmountList(AmountList amountList) {
        this.amountList = amountList;
    }

    public class AmountList {

        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("tax_amount")
        @Expose
        private String taxAmount;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getTaxAmount() {
            return taxAmount;
        }

        public void setTaxAmount(String taxAmount) {
            this.taxAmount = taxAmount;
        }

    }

}
