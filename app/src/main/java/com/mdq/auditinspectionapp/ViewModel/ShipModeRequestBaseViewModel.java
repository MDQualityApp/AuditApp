package com.mdq.auditinspectionapp.ViewModel;

public class ShipModeRequestBaseViewModel {
    private String Auth;
    private String dbname;

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getAuth() {
        return Auth;
    }

    public void setAuth(String auth) {
        Auth = auth;
    }
}
