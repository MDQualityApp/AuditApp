package com.mdq.auditinspectionapp.ViewModel;

public class FinalInvoiceRequestBaseViewModel {

    private String piNo;
    private String sourceFlag;
    private Integer sourceId;
    private String orderStatus;

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    private String dbname;

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    private String auth;

    public String getPiNo() {
        return piNo;
    }

    public void setPiNo(String piNo) {
        this.piNo = piNo;
    }

    public String getSourceFlag() {
        return sourceFlag;
    }

    public void setSourceFlag(String sourceFlag) {
        this.sourceFlag = sourceFlag;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer seasonId) {
        this.sourceId = seasonId;
    }


    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
