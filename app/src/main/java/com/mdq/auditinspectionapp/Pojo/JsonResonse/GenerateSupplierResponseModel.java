package com.mdq.auditinspectionapp.Pojo.JsonResonse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenerateSupplierResponseModel {

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

    public List<ResponseForSupplier> getResponse() {
        return data;
    }

    public void setResponse(List<ResponseForSupplier> response) {
        this.data = response;
    }


    @SerializedName("data")
    @Expose
    public List<ResponseForSupplier> data;


}
