package com.mdq.auditinspectionapp.Pojo.JsonResonse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getByUserIDResponseModel {


    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("status")
    @Expose
    public String status;

    @SerializedName("users")
    @Expose
    public List<ResonseForUserId> users;

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

    public List<ResonseForUserId> getUsers() {
        return users;
    }

    public void setUsers(List<ResonseForUserId> users) {
        this.users = users;
    }
}
