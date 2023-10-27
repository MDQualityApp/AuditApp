package com.mdq.auditinspectionapp.ViewModel;

public class LoginRequestBaseViewModel {
    private String emp_id;
    private String password;

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    private String dbname;

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String user_id) {
        this.emp_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
