package com.mdq.auditinspectionapp.ViewModel;

public class GetInspectionReportBaseVieModel {

    public String seasonName;
//    public String customer;
    public String brand;
    public String invoiceNo;
//    public String vendor;
    public String orderType;
    public String orderStatus;


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

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

//    public String getCustomer() {
//        return customer;
//    }

//    public void setCustomer(String customer) {
//        this.customer = customer;
//    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

//    public String getVendor() {
//        return vendor;
//    }

//    public void setVendor(String vendor) {
//        this.vendor = vendor;
//    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

}
