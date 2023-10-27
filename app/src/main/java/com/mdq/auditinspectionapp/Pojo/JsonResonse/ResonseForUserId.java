package com.mdq.auditinspectionapp.Pojo.JsonResonse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResonseForUserId {

    @SerializedName("emp_no")
    @Expose
    public String emp_no;
    @SerializedName("user_id")
    @Expose
    public String user_id;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("user_type")
    @Expose
    public String user_type;
    @SerializedName("department_name")
    @Expose
    public String department_name;
    @SerializedName("teamId")
    @Expose
    public String teamId;

    public String getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(String emp_no) {
        this.emp_no = emp_no;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
