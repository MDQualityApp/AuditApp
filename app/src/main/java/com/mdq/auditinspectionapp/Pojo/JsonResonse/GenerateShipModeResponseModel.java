package com.mdq.auditinspectionapp.Pojo.JsonResonse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenerateShipModeResponseModel {
    @SerializedName("data")
    @Expose
    public List<ResponseForShipMode> data;

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


    public List<ResponseForShipMode> getResponse() {
        return data;
    }

    public void setResponse(List<ResponseForShipMode> response) {
        this.data = response;
    }
}
