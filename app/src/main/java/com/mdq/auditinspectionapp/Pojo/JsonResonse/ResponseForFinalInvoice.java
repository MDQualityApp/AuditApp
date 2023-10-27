package com.mdq.auditinspectionapp.Pojo.JsonResonse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseForFinalInvoice
{

    @SerializedName("sourceFlag")
    @Expose
    public String sourceFlag;

    @SerializedName("sourceId")
    @Expose
    public Integer sourceId;

    @SerializedName("pgmCode")
    @Expose
    public String pgmCode;

    @SerializedName("piNo")
    @Expose
    public String piNo;

    @SerializedName("sysOrderNo")
    @Expose
    public String sysOrderNo;

    @SerializedName("brandId")
    @Expose
    public String brandId;

    @SerializedName("styleId")
    @Expose
    public String styleId;

    @SerializedName("buyerPo")
    @Expose
    public String buyerPo;

    @SerializedName("collection")
    @Expose
    public String collection;

    @SerializedName("delWindow")
    @Expose
    public String delWindow;

    @SerializedName("supplierName")
    @Expose
    public String supplierName;

    @SerializedName("custOrderNo")
    @Expose
    public String custOrderNo;

    @SerializedName("styleCode")
    @Expose
    public String styleCode;

    @SerializedName("styleName")
    @Expose
    public String styleName;

    @SerializedName("buyerEtd")
    @Expose
    public String buyerEtd;

    @SerializedName("vendorDelDate")
    @Expose
    public String vendorDelDate;

    @SerializedName("cityName")
    @Expose
    public String cityName;

    @SerializedName("despatchModeId")
    @Expose
    public String despatchModeId;

    @SerializedName("forecastDelDate")
    @Expose
    public String forecastDelDate;

    @SerializedName("remarks")
    @Expose
    public String remarks;

    @SerializedName("inspectionDate")
    @Expose
    public String inspectionDate;

    @SerializedName("qcBy")
    @Expose
    public String qcBy;

    public String getDespatchMode()
    {
        return DespatchMode;
    }

    public void setDespatchMode(String despatchMode)
    {
        DespatchMode = despatchMode;
    }

    @SerializedName("DespatchMode")
    @Expose
    public String DespatchMode;

    @SerializedName("qcRemarks")
    @Expose
    public String qcRemarks;

    @SerializedName("orderQty")
    @Expose
    public String orderQty;

    @SerializedName("despQty")
    @Expose
    public String despQty;

    @SerializedName("balance")
    @Expose
    public String balance;

    @SerializedName("DELIVERY_TERMS")
    @Expose
    public String DELIVERY_TERMS;

    @SerializedName("Inspection_Remarks")
    @Expose
    public String Inspection_Remarks;

    @SerializedName("ProductionRemarks")
    @Expose
    public String ProductionRemarks;

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    @SerializedName("result")
    @Expose
    public String result;


    public String getDeliveryTerms()
    {
        return DELIVERY_TERMS;
    }

    public void setDeliveryTerms(String deliveryTerms)
    {
        this.DELIVERY_TERMS = deliveryTerms;
    }

    public String getSourceFlag()
    {
        return sourceFlag;
    }

    public void setSourceFlag(String sourceFlag)
    {
        this.sourceFlag = sourceFlag;
    }

    public Integer getSourceId()
    {
        return sourceId;
    }

    public void setSourceId(Integer sourceId)
    {
        this.sourceId = sourceId;
    }

    public String getPgmCode()
    {
        return pgmCode;
    }

    public void setPgmCode(String pgmCode)
    {
        this.pgmCode = pgmCode;
    }

    public String getPiNo()
    {
        return piNo;
    }

    public void setPiNo(String piNo)
    {
        this.piNo = piNo;
    }

    public String getSysOrderNo()
    {
        return sysOrderNo;
    }

    public void setSysOrderNo(String sysOrderNo)
    {
        this.sysOrderNo = sysOrderNo;
    }

    public String getbrandId()
    {
        return brandId;
    }

    public void setbrandId(String brandId)
    {
        this.brandId = brandId;
    }

    public String getStyleId()
    {
        return styleId;
    }

    public void setStyleId(String styleId)
    {
        this.styleId = styleId;
    }

    public String getBuyerPo()
    {
        return buyerPo;
    }

    public void setBuyerPo(String buyerPo)
    {
        this.buyerPo = buyerPo;
    }

    public String getCollection()
    {
        return collection;
    }

    public void setCollection(String collection)
    {
        this.collection = collection;
    }

    public String getDelWindow()
    {
        return delWindow;
    }

    public void setDelWindow(String delWindow)
    {
        this.delWindow = delWindow;
    }

    public String getSupplierName()
    {
        return supplierName;
    }

    public void setSupplierName(String supplierName)
    {
        this.supplierName = supplierName;
    }

    public String getCustOrderNo()
    {
        return custOrderNo;
    }

    public void setCustOrderNo(String custOrderNo)
    {
        this.custOrderNo = custOrderNo;
    }

    public String getStyleCode()
    {
        return styleCode;
    }

    public void setStyleCode(String styleCode)
    {
        this.styleCode = styleCode;
    }

    public String getStyleName()
    {
        return styleName;
    }

    public void setStyleName(String styleName)
    {
        this.styleName = styleName;
    }

    public String getBuyerEtd()
    {
        return buyerEtd;
    }

    public void setBuyerEtd(String buyerEtd)
    {
        this.buyerEtd = buyerEtd;
    }

    public String getVendorDelDate()
    {
        return vendorDelDate;
    }

    public void setVendorDelDate(String vendorDelDate)
    {
        this.vendorDelDate = vendorDelDate;
    }

    public String getCityName()
    {
        return cityName;
    }

    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    public String getDespatchModeId()
    {
        return despatchModeId;
    }

    public void setDespatchModeId(String despatchModeId)
    {
        this.despatchModeId = despatchModeId;
    }

    public String getForecastDelDate()
    {
        return forecastDelDate;
    }

    public void setForecastDelDate(String forecastDelDate)
    {
        this.forecastDelDate = forecastDelDate;
    }

    public String getRemarks()
    {
        return remarks;
    }

    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }

    public String getInspectionDate()
    {
        return inspectionDate;
    }

    public void setInspectionDate(String inspectionDate)
    {
        this.inspectionDate = inspectionDate;
    }

    public String getQcBy()
    {
        return qcBy;
    }

    public void setQcBy(String qcBy)
    {
        this.qcBy = qcBy;
    }

    public String getQcRemarks()
    {
        return qcRemarks;
    }

    public void setQcRemarks(String qcRemarks)
    {
        this.qcRemarks = qcRemarks;
    }

    public String getOrderQty()
    {
        return orderQty;
    }

    public void setOrderQty(String orderQty)
    {
        this.orderQty = orderQty;
    }

    public String getDespQty()
    {
        return despQty;
    }

    public void setDespQty(String despQty)
    {
        this.despQty = despQty;
    }

    public String getBalance()
    {
        return balance;
    }

    public void setBalance(String balance)
    {
        this.balance = balance;
    }

    public String getInspection_Remarks()
    {
        return Inspection_Remarks;
    }

    public void setInspection_Remarks(String inspection_Remarks)
    {
        Inspection_Remarks = inspection_Remarks;
    }

    public String getProductionRemarks()
    {
        return ProductionRemarks;
    }

    public void setProductionRemarks(String productionRemarks)
    {
        ProductionRemarks = productionRemarks;
    }
}
