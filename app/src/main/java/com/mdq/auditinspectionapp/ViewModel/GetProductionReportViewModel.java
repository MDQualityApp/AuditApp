package com.mdq.auditinspectionapp.ViewModel;

import android.content.Context;
import android.util.Log;

import com.mdq.auditinspectionapp.DataManager.GetProductionReportDataManager;
import com.mdq.auditinspectionapp.DataManager.QCNameDataManager;
import com.mdq.auditinspectionapp.Interfaces.ResponseHandler;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.GetProductionRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.QCNameRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.GetProductionResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.QCNameResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateQCNameRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GetProductionReportRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateQCNameResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GetProductionReportResponseModel;
import com.mdq.auditinspectionapp.Utils.ApiClass;

public class GetProductionReportViewModel extends GetProductionReportBaseViewModel implements GetProductionRequestInterface {


    private GetProductionReportDataManager getProductionReportDataManager;
    private GetProductionResponseInterface getProductionResponseInterface;
    private Context mContext;

    public GetProductionReportViewModel(Context mContext, GetProductionResponseInterface getProductionResponseInterface) {
        this.getProductionResponseInterface = getProductionResponseInterface;
        this.getProductionReportDataManager = new GetProductionReportDataManager(mContext);
        this.mContext = mContext;
    }

    private void goGenerateBrand() {
        GetProductionReportRequestModel getProductionReportRequestModel=new GetProductionReportRequestModel();
        getProductionReportRequestModel.seasonName=getSeasonName();
        getProductionReportRequestModel.brand=getBrand();
        getProductionReportRequestModel.invoiceNo=getInvoiceNo();
        getProductionReportRequestModel.orderType=getOrderType();
        getProductionReportRequestModel.orderStatus=getOrderStatus();
        getProductionReportRequestModel.dbname=getDbname() ;

        getProductionReportDataManager.callEnqueue(ApiClass.GETPRODUCTIONREPORT,getAuth(), getProductionReportRequestModel,new ResponseHandler<GetProductionReportResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(GetProductionReportResponseModel item, String message) {
                Log.i("otpR","rr");
                if(item.getMessage()!=null) {
                    Log.i("otpRecevied", item.getMessage());
                    getProductionResponseInterface.getProductionReportCall(item);
                }

            }
            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                if(errorBody.ErrorMessage!=null) {
                    Log.i("error" ,errorBody.ErrorMessage);
                    getProductionResponseInterface.onFailure(errorBody,statusCode);
                }
            }
        });
    }
    @Override
    public void getProductionReportCall() {
        goGenerateBrand();
    }
}
