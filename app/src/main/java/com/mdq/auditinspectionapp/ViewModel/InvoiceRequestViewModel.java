package com.mdq.auditinspectionapp.ViewModel;

import android.content.Context;
import android.util.Log;

import com.mdq.auditinspectionapp.DataManager.BrandDataManager;
import com.mdq.auditinspectionapp.DataManager.InvoiceDataManger;
import com.mdq.auditinspectionapp.Interfaces.ResponseHandler;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.InvoiceRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.BrandResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.InvoiceResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateBrandRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateInvoiceRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateBrandResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateInvoiceResponseModel;
import com.mdq.auditinspectionapp.Utils.ApiClass;

public class InvoiceRequestViewModel extends InvoiceRequestBaseViewModel implements InvoiceRequestInterface {
    private InvoiceDataManger invoiceDataManger;
    private InvoiceResponseInterface invoiceResponseInterface;
    private Context mContext;

    public InvoiceRequestViewModel(Context mContext, InvoiceResponseInterface invoiceResponseInterface) {
        this.invoiceResponseInterface = invoiceResponseInterface;
        this.invoiceDataManger = new InvoiceDataManger(mContext);
        this.mContext = mContext;
    }

    private void goGenerateBrand() {
        GenerateInvoiceRequestModel generateInvoiceRequestModel=new GenerateInvoiceRequestModel();
        generateInvoiceRequestModel.sourceFlag=getSourceFlag();
        generateInvoiceRequestModel.supplierCode=getSupplierCode();
        generateInvoiceRequestModel.sourceId=getSourceId();
        generateInvoiceRequestModel.seasonId=getSeasonId();
        generateInvoiceRequestModel.brandId=getBrandId();
//        generateInvoiceRequestModel.from=getFrom();
//        generateInvoiceRequestModel.to=getTo();
        generateInvoiceRequestModel.dbname=getDbname();
        invoiceDataManger.callEnqueue(ApiClass.INVOICE, getAuthorization(), generateInvoiceRequestModel,new ResponseHandler<GenerateInvoiceResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(GenerateInvoiceResponseModel item, String message) {
                Log.i("otpR","rr");
                if(item.getMessage()!=null) {
                    Log.i("otpRecevied", item.getMessage());
                    invoiceResponseInterface.generateInvoiceProcessed(item);
                }

            }
            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                if(errorBody.ErrorMessage!=null) {
                    Log.i("error" ,errorBody.ErrorMessage);
                    invoiceResponseInterface.onFailure(errorBody,statusCode);
                }
            }
        });
    }

    @Override
    public void generateInvoiceRequest() {
        goGenerateBrand();
    }
}


