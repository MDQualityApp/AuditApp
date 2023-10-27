package com.mdq.auditinspectionapp.Pojo.JsonResonse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenerateQCResultResponseModel {

    @SerializedName("data")
    @Expose
    public List<ResponseForQCResult> data;

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

    public List<ResponseForQCResult> getResponse() {
        return data;
    }

    public void setResponse(List<ResponseForQCResult> response) {
        this.data = response;
    }

}
