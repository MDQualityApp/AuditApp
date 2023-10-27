package com.mdq.auditinspectionapp.Pojo.JsonResonse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenerateQCNameResponseModel {

    @SerializedName("data")
    @Expose
    public List<ResponseForQCName> data;

    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("status")
    @Expose
    public String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResponseForQCName> getResponse() {
        return data;
    }

    public void setResponse(List<ResponseForQCName> response) {
        this.data = response;
    }

}
