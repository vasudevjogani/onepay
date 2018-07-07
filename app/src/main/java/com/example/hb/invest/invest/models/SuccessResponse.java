package com.example.hb.invest.invest.models;

import com.example.hb.invest.invest.utiles.views.WSResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SuccessResponse implements WSResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("order_list")
    @Expose
    private List<OrderList> orderList = null;

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

    public List<OrderList> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderList> orderList) {
        this.orderList = orderList;
    }

    public class OrderList {

        @SerializedName("order_id")
        @Expose
        private String orderId;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("mobile_number")
        @Expose
        private String mobileNumber;
        @SerializedName("meter_number")
        @Expose
        private String meterNumber;
        @SerializedName("account_number")
        @Expose
        private String accountNumber;
        @SerializedName("payment_method")
        @Expose
        private String paymentMethod;
        @SerializedName("response_data")
        @Expose
        private String responseData;
        @SerializedName("confirm_response")
        @Expose
        private String confirmResponse;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("created_date")
        @Expose
        private String createdDate;
        @SerializedName("transaction_reference")
        @Expose
        private Integer transactionReference;
        @SerializedName("response_message")
        @Expose
        private String responseMessage;
        @SerializedName("plot_number")
        @Expose
        private String plotNumber;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getMeterNumber() {
            return meterNumber;
        }

        public void setMeterNumber(String meterNumber) {
            this.meterNumber = meterNumber;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public String getResponseData() {
            return responseData;
        }

        public void setResponseData(String responseData) {
            this.responseData = responseData;
        }

        public String getConfirmResponse() {
            return confirmResponse;
        }

        public void setConfirmResponse(String confirmResponse) {
            this.confirmResponse = confirmResponse;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public Integer getTransactionReference() {
            return transactionReference;
        }

        public void setTransactionReference(Integer transactionReference) {
            this.transactionReference = transactionReference;
        }

        public String getResponseMessage() {
            return responseMessage;
        }

        public void setResponseMessage(String responseMessage) {
            this.responseMessage = responseMessage;
        }

        public String getPlotNumber() {
            return plotNumber;
        }

        public void setPlotNumber(String plotNumber) {
            this.plotNumber = plotNumber;
        }
    }
}

