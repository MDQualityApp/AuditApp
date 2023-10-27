package com.mdq.auditinspectionapp.Pojo.JsonResonse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VendorNameResponseModel {



    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResponseForVendorName> getData() {
        return data;
    }

    public void setData(List<ResponseForVendorName> data) {
        this.data = data;
    }

    @SerializedName("data")
    @Expose
    public List<ResponseForVendorName> data;


}
