package com.mdq.auditinspectionapp.ViewModel;

import android.content.Context;
import android.util.Log;

import com.mdq.auditinspectionapp.DataManager.UpdateInspectionDataManager;
import com.mdq.auditinspectionapp.DataManager.UpdateProductionDataManager;
import com.mdq.auditinspectionapp.Interfaces.ResponseHandler;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.UpdateInspectionRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.UpdateInspectionResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.UpdateProductionResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateUpdateInspectionRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateUpdateProductionRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateUpdateInspectionResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateUpdateProductionResponseModel;
import com.mdq.auditinspectionapp.Utils.ApiClass;

public class UpdateInspectionRequestViewModel extends UpdateInspectionRequestBaseViewModel implements UpdateInspectionRequestInterface {
    private UpdateInspectionDataManager updateInspectionDataManager;
    private UpdateInspectionResponseInterface updateInspectionResponseInterface;
    private Context mContext;

    public UpdateInspectionRequestViewModel(Context mContext, UpdateInspectionResponseInterface updateInspectionResponseInterface) {
        this.updateInspectionResponseInterface = updateInspectionResponseInterface;
        this.updateInspectionDataManager = new UpdateInspectionDataManager(mContext);
        this.mContext = mContext;
    }

    private void goGenerateBrand() {
        GenerateUpdateInspectionRequestModel generateUpdateInspectionRequestModel=new GenerateUpdateInspectionRequestModel();
        generateUpdateInspectionRequestModel.sourceId=getSourceId();
        generateUpdateInspectionRequestModel.sourceFlag=getSourceFlag();
        generateUpdateInspectionRequestModel.updateFrom="INSPECTION";
        generateUpdateInspectionRequestModel.inspectionDate=getInspectionDate();
        generateUpdateInspectionRequestModel.qcBy=getQcBy();
        generateUpdateInspectionRequestModel.qcRemarks=getQcRemarks();
        generateUpdateInspectionRequestModel.pgmCode=getPgmCode();
        generateUpdateInspectionRequestModel.customerOrderNos=getCustomerOrderNos();
        generateUpdateInspectionRequestModel.styleId=getStyleId();
        generateUpdateInspectionRequestModel.result=getResult();
        generateUpdateInspectionRequestModel.dbname=getDbname();
        generateUpdateInspectionRequestModel.systemOrderNo=getSystemOrderNo();

        updateInspectionDataManager.callEnqueue(ApiClass.UPDATEINSPECTION,getAuth(), generateUpdateInspectionRequestModel,new ResponseHandler<GenerateUpdateInspectionResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }
            @Override
            public void onSuccess(GenerateUpdateInspectionResponseModel item, String message) {
                Log.i("otpR","rr");
                if(item.getMessage()!=null) {
                    Log.i("otpRecevied", item.getMessage());
                    updateInspectionResponseInterface.generateUpdateInspectionProcessed(item);
                }
            }
            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                if(errorBody.ErrorMessage!=null) {
                    Log.i("error" ,errorBody.ErrorMessage);
                    updateInspectionResponseInterface.onFailure(errorBody,statusCode);
                }
            }
        });
    }
    @Override
    public void generateUpdateInspectionRequest() {
     goGenerateBrand();
    }
}


