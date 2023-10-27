package com.mdq.auditinspectionapp.ViewModel;

public class QCResultRequestBaseViewModel {

    private String Auth;

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    private String dbname;

    public String getAuth() {
        return Auth;
    }

    public void setAuth(String auth) {
        Auth = auth;
    }
}
