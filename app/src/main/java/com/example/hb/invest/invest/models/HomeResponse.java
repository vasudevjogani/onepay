package com.example.hb.invest.invest.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.hb.invest.invest.utiles.views.WSResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SHREEJI on 11-Feb-18.
 */

public class HomeResponse implements WSResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("utility_list")
    @Expose
    private UtilityList utilityList;

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

    public UtilityList getUtilityList() {
        return utilityList;
    }

    public void setUtilityList(UtilityList utilityList) {
        this.utilityList = utilityList;
    }


    public class UtilityList {

        @SerializedName("Mobile")
        @Expose
        private List<Mobile> mobile = null;
        @SerializedName("Paytv")
        @Expose
        private List<Paytv> paytv = null;
        @SerializedName("Electricity")
        @Expose
        private List<Electricity> electricity = null;
        @SerializedName("Domestic Tax")
        @Expose
        private List<DomesticTax> domesticTax = null;
        @SerializedName("Water Bills")
        @Expose
        private List<WaterBill> waterBills = null;
        @SerializedName("Council Bills")
        @Expose
        private List<CouncilBill> councilBills = null;

        public List<Mobile> getMobile() {
            return mobile;
        }

        public void setMobile(List<Mobile> mobile) {
            this.mobile = mobile;
        }

        public List<Paytv> getPaytv() {
            return paytv;
        }

        public void setPaytv(List<Paytv> paytv) {
            this.paytv = paytv;
        }

        public List<Electricity> getElectricity() {
            return electricity;
        }

        public void setElectricity(List<Electricity> electricity) {
            this.electricity = electricity;
        }

        public List<DomesticTax> getDomesticTax() {
            return domesticTax;
        }

        public void setDomesticTax(List<DomesticTax> domesticTax) {
            this.domesticTax = domesticTax;
        }

        public List<WaterBill> getWaterBills() {
            return waterBills;
        }

        public void setWaterBills(List<WaterBill> waterBills) {
            this.waterBills = waterBills;
        }

        public List<CouncilBill> getCouncilBills() {
            return councilBills;
        }

        public void setCouncilBills(List<CouncilBill> councilBills) {
            this.councilBills = councilBills;
        }
    }

    public class Electricity {

        @SerializedName("utility_id")
        @Expose
        private String utilityId;
        @SerializedName("utility_name")
        @Expose
        private String utilityName;
        @SerializedName("utility_description")
        @Expose
        private String utilityDescription;
        @SerializedName("utility_category_id")
        @Expose
        private String utilityCategoryId;
        @SerializedName("utility_contact_person")
        @Expose
        private String utilityContactPerson;
        @SerializedName("utility_support_mail")
        @Expose
        private String utilitySupportMail;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("category_name")
        @Expose
        private String categoryName;

        public String getUtilityId() {
            return utilityId;
        }

        public void setUtilityId(String utilityId) {
            this.utilityId = utilityId;
        }

        public String getUtilityName() {
            return utilityName;
        }

        public void setUtilityName(String utilityName) {
            this.utilityName = utilityName;
        }

        public String getUtilityDescription() {
            return utilityDescription;
        }

        public void setUtilityDescription(String utilityDescription) {
            this.utilityDescription = utilityDescription;
        }

        public String getUtilityCategoryId() {
            return utilityCategoryId;
        }

        public void setUtilityCategoryId(String utilityCategoryId) {
            this.utilityCategoryId = utilityCategoryId;
        }

        public String getUtilityContactPerson() {
            return utilityContactPerson;
        }

        public void setUtilityContactPerson(String utilityContactPerson) {
            this.utilityContactPerson = utilityContactPerson;
        }

        public String getUtilitySupportMail() {
            return utilitySupportMail;
        }

        public void setUtilitySupportMail(String utilitySupportMail) {
            this.utilitySupportMail = utilitySupportMail;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

    }

    public class CouncilBill implements Serializable {

        @SerializedName("utility_id")
        @Expose
        private String utilityId;
        @SerializedName("utility_name")
        @Expose
        private String utilityName;
        @SerializedName("utility_description")
        @Expose
        private String utilityDescription;
        @SerializedName("utility_category_id")
        @Expose
        private String utilityCategoryId;
        @SerializedName("utility_contact_person")
        @Expose
        private String utilityContactPerson;
        @SerializedName("utility_support_mail")
        @Expose
        private String utilitySupportMail;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("category_name")
        @Expose
        private String categoryName;

        public String getUtilityId() {
            return utilityId;
        }

        public void setUtilityId(String utilityId) {
            this.utilityId = utilityId;
        }

        public String getUtilityName() {
            return utilityName;
        }

        public void setUtilityName(String utilityName) {
            this.utilityName = utilityName;
        }

        public String getUtilityDescription() {
            return utilityDescription;
        }

        public void setUtilityDescription(String utilityDescription) {
            this.utilityDescription = utilityDescription;
        }

        public String getUtilityCategoryId() {
            return utilityCategoryId;
        }

        public void setUtilityCategoryId(String utilityCategoryId) {
            this.utilityCategoryId = utilityCategoryId;
        }

        public String getUtilityContactPerson() {
            return utilityContactPerson;
        }

        public void setUtilityContactPerson(String utilityContactPerson) {
            this.utilityContactPerson = utilityContactPerson;
        }

        public String getUtilitySupportMail() {
            return utilitySupportMail;
        }

        public void setUtilitySupportMail(String utilitySupportMail) {
            this.utilitySupportMail = utilitySupportMail;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        /*@Override
        public int describeContents() {
            return 0;
        }

        public final Parcelable.Creator<CouncilBill> CREATOR = new Parcelable.Creator<CouncilBill>() {

            @Override
            public CouncilBill createFromParcel(Parcel source) {
                return new CouncilBill(source);
            }

            @Override
            public CouncilBill[] newArray(int size) {
                return new CouncilBill[size];
            }
        };

        public CouncilBill(Parcel source) {
            utilityId = source.readString();
            utilityName = source.readString();
            utilityDescription = source.readString();
            utilityCategoryId = source.readString();
            utilityContactPerson = source.readString();
            utilitySupportMail = source.readString();
            status = source.readString();
            categoryName = source.readString();
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(utilityId);
            parcel.writeString(utilityName);
            parcel.writeString(utilityDescription);
            parcel.writeString(utilityCategoryId);
            parcel.writeString(utilityContactPerson);
            parcel.writeString(utilitySupportMail);
            parcel.writeString(status);
            parcel.writeString(categoryName);
        }*/
    }

    public class DomesticTax implements Serializable {

        @SerializedName("utility_id")
        @Expose
        private String utilityId;
        @SerializedName("utility_name")
        @Expose
        private String utilityName;
        @SerializedName("utility_description")
        @Expose
        private String utilityDescription;
        @SerializedName("utility_category_id")
        @Expose
        private String utilityCategoryId;
        @SerializedName("utility_contact_person")
        @Expose
        private String utilityContactPerson;
        @SerializedName("utility_support_mail")
        @Expose
        private String utilitySupportMail;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("category_name")
        @Expose
        private String categoryName;

        public String getUtilityId() {
            return utilityId;
        }

        public void setUtilityId(String utilityId) {
            this.utilityId = utilityId;
        }

        public String getUtilityName() {
            return utilityName;
        }

        public void setUtilityName(String utilityName) {
            this.utilityName = utilityName;
        }

        public String getUtilityDescription() {
            return utilityDescription;
        }

        public void setUtilityDescription(String utilityDescription) {
            this.utilityDescription = utilityDescription;
        }

        public String getUtilityCategoryId() {
            return utilityCategoryId;
        }

        public void setUtilityCategoryId(String utilityCategoryId) {
            this.utilityCategoryId = utilityCategoryId;
        }

        public String getUtilityContactPerson() {
            return utilityContactPerson;
        }

        public void setUtilityContactPerson(String utilityContactPerson) {
            this.utilityContactPerson = utilityContactPerson;
        }

        public String getUtilitySupportMail() {
            return utilitySupportMail;
        }

        public void setUtilitySupportMail(String utilitySupportMail) {
            this.utilitySupportMail = utilitySupportMail;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

       /* @Override
        public int describeContents() {
            return 0;
        }

        public final Parcelable.Creator<DomesticTax> CREATOR = new Parcelable.Creator<DomesticTax>() {

            @Override
            public DomesticTax createFromParcel(Parcel source) {
                return new DomesticTax(source);
            }

            @Override
            public DomesticTax[] newArray(int size) {
                return new DomesticTax[size];
            }
        };

        public DomesticTax(Parcel source) {
            utilityId = source.readString();
            utilityName = source.readString();
            utilityDescription = source.readString();
            utilityCategoryId = source.readString();
            utilityContactPerson = source.readString();
            utilitySupportMail = source.readString();
            status = source.readString();
            categoryName = source.readString();
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(utilityId);
            parcel.writeString(utilityName);
            parcel.writeString(utilityDescription);
            parcel.writeString(utilityCategoryId);
            parcel.writeString(utilityContactPerson);
            parcel.writeString(utilitySupportMail);
            parcel.writeString(status);
            parcel.writeString(categoryName);
        }
*/
    }

    public class Mobile implements Serializable {

        @SerializedName("utility_id")
        @Expose
        private String utilityId;
        @SerializedName("utility_name")
        @Expose
        private String utilityName;
        @SerializedName("utility_description")
        @Expose
        private String utilityDescription;
        @SerializedName("utility_category_id")
        @Expose
        private String utilityCategoryId;
        @SerializedName("utility_contact_person")
        @Expose
        private String utilityContactPerson;
        @SerializedName("utility_support_mail")
        @Expose
        private String utilitySupportMail;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("category_name")
        @Expose
        private String categoryName;

        public String getUtilityId() {
            return utilityId;
        }

        public void setUtilityId(String utilityId) {
            this.utilityId = utilityId;
        }

        public String getUtilityName() {
            return utilityName;
        }

        public void setUtilityName(String utilityName) {
            this.utilityName = utilityName;
        }

        public String getUtilityDescription() {
            return utilityDescription;
        }

        public void setUtilityDescription(String utilityDescription) {
            this.utilityDescription = utilityDescription;
        }

        public String getUtilityCategoryId() {
            return utilityCategoryId;
        }

        public void setUtilityCategoryId(String utilityCategoryId) {
            this.utilityCategoryId = utilityCategoryId;
        }

        public String getUtilityContactPerson() {
            return utilityContactPerson;
        }

        public void setUtilityContactPerson(String utilityContactPerson) {
            this.utilityContactPerson = utilityContactPerson;
        }

        public String getUtilitySupportMail() {
            return utilitySupportMail;
        }

        public void setUtilitySupportMail(String utilitySupportMail) {
            this.utilitySupportMail = utilitySupportMail;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        /*@Override
        public int describeContents() {
            return 0;
        }

        public final Parcelable.Creator<Mobile> CREATOR = new Parcelable.Creator<Mobile>() {

            @Override
            public Mobile createFromParcel(Parcel source) {
                return new Mobile(source);
            }

            @Override
            public Mobile[] newArray(int size) {
                return new Mobile[size];
            }
        };

        public Mobile(Parcel source) {
            utilityId = source.readString();
            utilityName = source.readString();
            utilityDescription = source.readString();
            utilityCategoryId = source.readString();
            utilityContactPerson = source.readString();
            utilitySupportMail = source.readString();
            status = source.readString();
            categoryName = source.readString();
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(utilityId);
            parcel.writeString(utilityName);
            parcel.writeString(utilityDescription);
            parcel.writeString(utilityCategoryId);
            parcel.writeString(utilityContactPerson);
            parcel.writeString(utilitySupportMail);
            parcel.writeString(status);
            parcel.writeString(categoryName);
        }
*/
    }

    public class Paytv implements Serializable {

        @SerializedName("utility_id")
        @Expose
        private String utilityId;
        @SerializedName("utility_name")
        @Expose
        private String utilityName;
        @SerializedName("utility_description")
        @Expose
        private String utilityDescription;
        @SerializedName("utility_category_id")
        @Expose
        private String utilityCategoryId;
        @SerializedName("utility_contact_person")
        @Expose
        private String utilityContactPerson;
        @SerializedName("utility_support_mail")
        @Expose
        private String utilitySupportMail;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("category_name")
        @Expose
        private String categoryName;

        public String getUtilityId() {
            return utilityId;
        }

        public void setUtilityId(String utilityId) {
            this.utilityId = utilityId;
        }

        public String getUtilityName() {
            return utilityName;
        }

        public void setUtilityName(String utilityName) {
            this.utilityName = utilityName;
        }

        public String getUtilityDescription() {
            return utilityDescription;
        }

        public void setUtilityDescription(String utilityDescription) {
            this.utilityDescription = utilityDescription;
        }

        public String getUtilityCategoryId() {
            return utilityCategoryId;
        }

        public void setUtilityCategoryId(String utilityCategoryId) {
            this.utilityCategoryId = utilityCategoryId;
        }

        public String getUtilityContactPerson() {
            return utilityContactPerson;
        }

        public void setUtilityContactPerson(String utilityContactPerson) {
            this.utilityContactPerson = utilityContactPerson;
        }

        public String getUtilitySupportMail() {
            return utilitySupportMail;
        }

        public void setUtilitySupportMail(String utilitySupportMail) {
            this.utilitySupportMail = utilitySupportMail;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

       /* @Override
        public int describeContents() {
            return 0;
        }

        public final Parcelable.Creator<Paytv> CREATOR = new Parcelable.Creator<Paytv>() {

            @Override
            public Paytv createFromParcel(Parcel source) {
                return new Paytv(source);
            }

            @Override
            public Paytv[] newArray(int size) {
                return new Paytv[size];
            }
        };

        public Paytv(Parcel source) {
            utilityId = source.readString();
            utilityName = source.readString();
            utilityDescription = source.readString();
            utilityCategoryId = source.readString();
            utilityContactPerson = source.readString();
            utilitySupportMail = source.readString();
            status = source.readString();
            categoryName = source.readString();
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(utilityId);
            parcel.writeString(utilityName);
            parcel.writeString(utilityDescription);
            parcel.writeString(utilityCategoryId);
            parcel.writeString(utilityContactPerson);
            parcel.writeString(utilitySupportMail);
            parcel.writeString(status);
            parcel.writeString(categoryName);
        }*/
    }

    public class WaterBill implements Serializable {

        @SerializedName("utility_id")
        @Expose
        private String utilityId;
        @SerializedName("utility_name")
        @Expose
        private String utilityName;
        @SerializedName("utility_description")
        @Expose
        private String utilityDescription;
        @SerializedName("utility_category_id")
        @Expose
        private String utilityCategoryId;
        @SerializedName("utility_contact_person")
        @Expose
        private String utilityContactPerson;
        @SerializedName("utility_support_mail")
        @Expose
        private String utilitySupportMail;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("category_name")
        @Expose
        private String categoryName;

        public String getUtilityId() {
            return utilityId;
        }

        public void setUtilityId(String utilityId) {
            this.utilityId = utilityId;
        }

        public String getUtilityName() {
            return utilityName;
        }

        public void setUtilityName(String utilityName) {
            this.utilityName = utilityName;
        }

        public String getUtilityDescription() {
            return utilityDescription;
        }

        public void setUtilityDescription(String utilityDescription) {
            this.utilityDescription = utilityDescription;
        }

        public String getUtilityCategoryId() {
            return utilityCategoryId;
        }

        public void setUtilityCategoryId(String utilityCategoryId) {
            this.utilityCategoryId = utilityCategoryId;
        }

        public String getUtilityContactPerson() {
            return utilityContactPerson;
        }

        public void setUtilityContactPerson(String utilityContactPerson) {
            this.utilityContactPerson = utilityContactPerson;
        }

        public String getUtilitySupportMail() {
            return utilitySupportMail;
        }

        public void setUtilitySupportMail(String utilitySupportMail) {
            this.utilitySupportMail = utilitySupportMail;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        /*@Override
        public int describeContents() {
            return 0;
        }

        public final Parcelable.Creator<WaterBill> CREATOR = new Parcelable.Creator<WaterBill>() {

            @Override
            public WaterBill createFromParcel(Parcel source) {
                return new WaterBill(source);
            }

            @Override
            public WaterBill[] newArray(int size) {
                return new WaterBill[size];
            }
        };

        public WaterBill(Parcel source) {
            utilityId = source.readString();
            utilityName = source.readString();
            utilityDescription = source.readString();
            utilityCategoryId = source.readString();
            utilityContactPerson = source.readString();
            utilitySupportMail = source.readString();
            status = source.readString();
            categoryName = source.readString();
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(utilityId);
            parcel.writeString(utilityName);
            parcel.writeString(utilityDescription);
            parcel.writeString(utilityCategoryId);
            parcel.writeString(utilityContactPerson);
            parcel.writeString(utilitySupportMail);
            parcel.writeString(status);
            parcel.writeString(categoryName);
        }*/

    }

}
