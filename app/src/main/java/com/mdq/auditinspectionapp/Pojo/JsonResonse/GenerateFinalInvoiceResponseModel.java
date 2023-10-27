package com.mdq.auditinspectionapp.Pojo.JsonResonse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GenerateFinalInvoiceResponseModel implements Serializable {



    public List<ResponseForFinalInvoice> getResponse() {
        return data;
    }

    public void setResponse(List<ResponseForFinalInvoice> response) {
        this.data = response;
    }

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


    @SerializedName("status")
    @Expose
    public  String status;
    @SerializedName("message")
    @Expose
    public   String message;
    @SerializedName("data")
    @Expose
    public  List<ResponseForFinalInvoice> data;

}
