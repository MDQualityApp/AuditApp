package com.mdq.auditinspectionapp.ViewModel;

import java.util.List;

public class UpdateInspectionRequestBaseViewModel {

    public String getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(String inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getQcBy() {
        return qcBy;
    }

    public void setQcBy(String qcBy) {
        this.qcBy = qcBy;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getQcRemarks() {
        return qcRemarks;
    }

    public void setQcRemarks(String qcRemarks) {
        this.qcRemarks = qcRemarks;
    }

    public String getUpdateForm() {
        return updateForm;
    }

    public void setUpdateForm(String updateForm) {
        this.updateForm = updateForm;
    }

    public String getPgmCode() {
        return pgmCode;
    }

    public void setPgmCode(String pgmCode) {
        this.pgmCode = pgmCode;
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

    public int getStyleId() {
        return styleId;
    }

    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }

    public String getCustomerOrderNos() {
        return customerOrderNos;
    }

    public void setCustomerOrderNos(String customerOrderNos) {
        this.customerOrderNos = customerOrderNos;
    }

    public String inspectionDate;
    public String qcBy;
    public String result;
    public String qcRemarks;
    public String updateForm;
    public String pgmCode;

    public String getSystemOrderNo() {
        return systemOrderNo;
    }

    public void setSystemOrderNo(String systemOrderNo) {
        this.systemOrderNo = systemOrderNo;
    }

    public String systemOrderNo;

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
    public String sourceFlag;
    public int sourceId;
    public int styleId;
    public String customerOrderNos;

   }

