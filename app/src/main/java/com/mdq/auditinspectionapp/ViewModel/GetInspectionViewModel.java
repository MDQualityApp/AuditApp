package com.mdq.auditinspectionapp.ViewModel;

import android.content.Context;
import android.util.Log;

import com.mdq.auditinspectionapp.DataManager.GetInspectionReportDataManager;
import com.mdq.auditinspectionapp.DataManager.GetProductionReportDataManager;
import com.mdq.auditinspectionapp.Interfaces.ResponseHandler;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.GetInspectionReportRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.GetProductionRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.GetInspectionReportResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.GetProductionResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GetInspectionReportRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GetProductionReportRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GetInspectionReportResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GetProductionReportResponseModel;
import com.mdq.auditinspectionapp.Utils.ApiClass;

public class GetInspectionViewModel extends GetInspectionReportBaseVieModel implements GetInspectionReportRequestInterface {

    private GetInspectionReportDataManager getInspectionReportDataManager;
    private GetInspectionReportResponseInterface getInspectionReportResponseInterface;
    private Context mContext;

    public GetInspectionViewModel(Context mContext, GetInspectionReportResponseInterface getInspectionReportResponseInterface) {
        this.getInspectionReportResponseInterface = getInspectionReportResponseInterface;
        this.getInspectionReportDataManager = new GetInspectionReportDataManager(mContext);
        this.mContext = mContext;
    }

    private void goGenerateBrand() {
        GetInspectionReportRequestModel getInspectionReportRequestModel=new GetInspectionReportRequestModel();
        getInspectionReportRequestModel.seasonName=getSeasonName();
//        getInspectionReportRequestModel.customer=getCustomer();
        getInspectionReportRequestModel.brand=getBrand();
        getInspectionReportRequestModel.invoiceNo=getInvoiceNo();
//        getInspectionReportRequestModel.vendor=getVendor();
        getInspectionReportRequestModel.orderType=getOrderType();
        getInspectionReportRequestModel.orderStatus=getOrderStatus();
        getInspectionReportRequestModel.dbname=getDbname();

        getInspectionReportDataManager.callEnqueue(ApiClass.GETINSPECTIONREPORT,getAuth(), getInspectionReportRequestModel,new ResponseHandler<GetInspectionReportResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(GetInspectionReportResponseModel item, String message) {
                Log.i("otpR","rr");
                if(item.getMessage()!=null) {
                    Log.i("otpRecevied", item.getMessage());
                    getInspectionReportResponseInterface.GetInspectionReportProcess(item);
                }

            }
            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                if(errorBody.ErrorMessage!=null) {
                    Log.i("error" ,errorBody.ErrorMessage);
                    getInspectionReportResponseInterface.onFailure(errorBody,statusCode);
                }
            }
        });
    }

    @Override
    public void getInspectionReportCall() {
        goGenerateBrand();
    }
}
