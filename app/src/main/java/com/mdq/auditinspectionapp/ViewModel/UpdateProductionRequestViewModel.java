package com.mdq.auditinspectionapp.ViewModel;

import android.content.Context;
import android.util.Log;

import com.mdq.auditinspectionapp.DataManager.BrandDataManager;
import com.mdq.auditinspectionapp.DataManager.UpdateProductionDataManager;
import com.mdq.auditinspectionapp.Interfaces.ResponseHandler;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.UpdateProductionRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.BrandResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.UpdateProductionResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateBrandRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateUpdateProductionRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateBrandResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateUpdateProductionResponseModel;
import com.mdq.auditinspectionapp.Utils.ApiClass;

public class UpdateProductionRequestViewModel extends UpdateProductionBaseViewModel implements UpdateProductionRequestInterface {
    private UpdateProductionDataManager updateProductionDataManager;
    private UpdateProductionResponseInterface updateProductionResponseInterface;
    private Context mContext;

    public UpdateProductionRequestViewModel(Context mContext, UpdateProductionResponseInterface updateProductionResponseInterface) {
        this.updateProductionResponseInterface = updateProductionResponseInterface;
        this.updateProductionDataManager = new UpdateProductionDataManager(mContext);
        this.mContext = mContext;
    }

    private void goGenerateBrand() {
        GenerateUpdateProductionRequestModel generateUpdateProductionRequestModel=new GenerateUpdateProductionRequestModel();
        generateUpdateProductionRequestModel.sourceId=getSourceId();
        generateUpdateProductionRequestModel.sourceFlag=getSourceFlag();
        generateUpdateProductionRequestModel.updateFrom="FORECAST";
        generateUpdateProductionRequestModel.dispatchModeId=getDispatchModeId();
        generateUpdateProductionRequestModel.foreCastDelDate=getForeCastDelDate();
        generateUpdateProductionRequestModel.custOrderNo=getCustOrderNo();
        generateUpdateProductionRequestModel.pgmCode=getPgmCode();
        generateUpdateProductionRequestModel.remarks=getRemarks();
        generateUpdateProductionRequestModel.styleId=getStyleId();
        generateUpdateProductionRequestModel.dbname=getDbname();
        generateUpdateProductionRequestModel.systemOrderNo=getSysOrderNo();

        updateProductionDataManager.callEnqueue(ApiClass.UPDATEPRODUCTION, getAuth(),generateUpdateProductionRequestModel,new ResponseHandler<GenerateUpdateProductionResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }
            @Override
            public void onSuccess(GenerateUpdateProductionResponseModel item, String message) {
                if(item.getMessage()!=null) {
                    Log.i("otpRecevied", item.getMessage());
                    updateProductionResponseInterface.generateUpdateProductionProcessed(item);
                }
            }
            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                if(errorBody.ErrorMessage!=null) {
                    Log.i("error" ,errorBody.ErrorMessage);
                    updateProductionResponseInterface.onFailure(errorBody,statusCode);
                }
            }
        });
    }
    @Override
    public void generateUpdateProductionRequest() {
        goGenerateBrand();
    }
}


