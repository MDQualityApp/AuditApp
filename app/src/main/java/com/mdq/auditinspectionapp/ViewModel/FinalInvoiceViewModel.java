package com.mdq.auditinspectionapp.ViewModel;

import android.content.Context;
import android.util.Log;

import com.mdq.auditinspectionapp.DataManager.BrandDataManager;
import com.mdq.auditinspectionapp.DataManager.FinalInvoiceDataManager;
import com.mdq.auditinspectionapp.Interfaces.ResponseHandler;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.FinalInvoiceRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.BrandResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.FinalInvoiceResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateBrandRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateFinalInvoiceRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateBrandResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateFinalInvoiceResponseModel;
import com.mdq.auditinspectionapp.Utils.ApiClass;

public class FinalInvoiceViewModel extends FinalInvoiceRequestBaseViewModel implements FinalInvoiceRequestInterface

    {
        private FinalInvoiceDataManager finalInvoiceDataManager;
        private FinalInvoiceResponseInterface finalInvoiceResponseInterface;
        private Context mContext;

    public FinalInvoiceViewModel(Context mContext, FinalInvoiceResponseInterface finalInvoiceResponseInterface) {
        this.finalInvoiceResponseInterface = finalInvoiceResponseInterface;
        this.finalInvoiceDataManager = new FinalInvoiceDataManager(mContext);
        this.mContext = mContext;
    }

        private void goGenerateBrand() {
        GenerateFinalInvoiceRequestModel finalInvoiceRequestModel=new GenerateFinalInvoiceRequestModel();
            finalInvoiceRequestModel.sourceFlag=getSourceFlag();
            finalInvoiceRequestModel.sourceId=getSourceId();
            finalInvoiceRequestModel.piNo=getPiNo();
            finalInvoiceRequestModel.dbname=getDbname();
            finalInvoiceRequestModel.orderStatus=getOrderStatus();

        finalInvoiceDataManager.callEnqueue(ApiClass.FINALINVOICE,getAuth(), finalInvoiceRequestModel,new ResponseHandler<GenerateFinalInvoiceResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(GenerateFinalInvoiceResponseModel item, String message) {
                Log.i("otpR","rr");
                if(item.data!=null) {
                    finalInvoiceResponseInterface.generateFinalInvoiceProcessed(item);
                }

            }
            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                if(errorBody.ErrorMessage!=null) {
                    Log.i("error" ,errorBody.ErrorMessage);
                    finalInvoiceResponseInterface.onFailure(errorBody,statusCode);
                }
            }
        });
    }

        @Override
        public void generateFinalVoiceRequest() {
           goGenerateBrand();
        }

    }
