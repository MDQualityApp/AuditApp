package com.mdq.auditinspectionapp.ViewModel;

public class UpdateProductionBaseViewModel {

    public String foreCastDelDate;
    public String remarks;
    public int dispatchModeId;
    public String updateFrom;
    public String sourceFlag;
    public int sourceId;
    public String pgmCode;
    public String systemOrderNo;
    public int styleId;
    public String custOrderNo;

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String dbname;


    public String getAuth() {
        return Auth;
    }

    public void setAuth(String auth) {
        Auth = auth;
    }

    public String Auth;

    public String getForeCastDelDate() {
        return foreCastDelDate;
    }

    public void setForeCastDelDate(String foreCastDelDate) {
        this.foreCastDelDate = foreCastDelDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getDispatchModeId() {
        return dispatchModeId;
    }

    public void setDispatchModeId(int dispatchModeId) {
        this.dispatchModeId = dispatchModeId;
    }

    public String getUpdateForm() {
        return updateFrom;
    }

    public void setUpdateForm(String updateForm) {
        this.updateFrom = updateForm;
    }

    public String getSourceFlag() {
        return sourceFlag;
    }

    public void setSourceFlag(String sourceFlag) {
        this.sourceFlag = sourceFlag;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public String getPgmCode() {
        return pgmCode;
    }

    public void setPgmCode(String pgmCode) {
        this.pgmCode = pgmCode;
    }

    public String getSysOrderNo() {
        return systemOrderNo;
    }

    public void setSysOrderNo(String sysOrderNo) {
        this.systemOrderNo = sysOrderNo;
    }

    public int getStyleId() {
        return styleId;
    }

    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }

    public String getCustOrderNo() {
        return custOrderNo;
    }

    public void setCustOrderNo(String custOrderNo) {
        this.custOrderNo = custOrderNo;
    }
}
