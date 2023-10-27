package com.mdq.auditinspectionapp.Pojo.JsonResonse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseForGetInspectionReport {


    @SerializedName("style")
    @Expose
    public String style;

    @SerializedName("shipMode")
    @Expose
    public String shipMode;

    @SerializedName("orderedQty")
    @Expose
    public String orderedQty;

    @SerializedName("orderNo")
    @Expose
    public String orderNo;
    @SerializedName("vendorDel")
    @Expose
    public String vendorDel;

    @SerializedName("destination")
    @Expose
    public String destination;

    @SerializedName("brand")
    @Expose
    public String brand;

    @SerializedName("vendor")
    @Expose
    public String vendor;

    @SerializedName("season")
    @Expose
    public String season;

    @SerializedName("invoiceNo")
    @Expose
    public String invoiceNo;

    @SerializedName("invoiceDate")
    @Expose
    public String invoiceDate;

    @SerializedName("foreCastDelDate")
    @Expose
    public String foreCastDelDate;


    @SerializedName("remarks")
    @Expose
    public String remarks;

    @SerializedName("inspectionDate")
    @Expose
    public String inspectionDate;

    @SerializedName("qcBy")
    @Expose
    public String qcBy;

    public String getBalanceQty() {
        return balanceQty;
    }

    public void setBalanceQty(String balanceQty) {
        this.balanceQty = balanceQty;
    }

    @SerializedName("balanceQty")
    @Expose
    public String balanceQty;


    public String getDeliveryTerms() {
        return deliveryTerms;
    }

    public void setDeliveryTerms(String deliveryTerms) {
        this.deliveryTerms = deliveryTerms;
    }

    @SerializedName("deliveryTerms")
    @Expose
    public String deliveryTerms;

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getShipMode() {
        return shipMode;
    }

    public void setShipMode(String shipMode) {
        this.shipMode = shipMode;
    }

    public String getOrderedQty() {
        return orderedQty;
    }

    public void setOrderedQty(String orderedQty) {
        this.orderedQty = orderedQty;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getVendorDel() {
        return vendorDel;
    }

    public void setVendorDel(String vendorDel) {
        this.vendorDel = vendorDel;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

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

    @SerializedName("result")
    @Expose
    public String result;

    @SerializedName("qcRemarks")
    @Expose
    public String qcRemarks;

}
