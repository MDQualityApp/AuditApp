package com.mdq.auditinspectionapp.ViewModel;

public class getByUserIDBaseViewModel {
    public String dbname;
    public String user_id;
    public String Auth;

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAuth() {
        return Auth;
    }

    public void setAuth(String auth) {
        Auth = auth;
    }
}
