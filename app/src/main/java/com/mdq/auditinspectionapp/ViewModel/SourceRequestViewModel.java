package com.mdq.auditinspectionapp.ViewModel;

import android.content.Context;
import android.util.Log;

import com.mdq.auditinspectionapp.DataManager.LoginDataManger;
import com.mdq.auditinspectionapp.DataManager.SourceDataManager;
import com.mdq.auditinspectionapp.Interfaces.ResponseHandler;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.SourceRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.LoginResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.SourceResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateLoginRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateSourceRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateLoginResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateSourceResponseModel;
import com.mdq.auditinspectionapp.Utils.ApiClass;

public class SourceRequestViewModel extends SourceRequestBaseViewModel implements SourceRequestInterface {
    private SourceDataManager sourceDataManager;
    private SourceResponseInterface sourceResponseInterface;
    private Context mContext;

    public SourceRequestViewModel(Context mContext, SourceResponseInterface sourceResponseInterface) {
        this.sourceResponseInterface = sourceResponseInterface;
        this.sourceDataManager = new SourceDataManager(mContext);
        this.mContext = mContext;
    }

    private void goGenerateSource() {
        GenerateSourceRequestModel sourceRequestModel=new GenerateSourceRequestModel();
        sourceRequestModel.dbname=getDbname();
        sourceRequestModel.user_id=getUser_id();
        sourceDataManager.callEnqueue(ApiClass.SOURCE,getAuthorization(),sourceRequestModel, new ResponseHandler<GenerateSourceResponseModel>() {
            @Override
            public void onSuccess(String message) {
            }

            @Override
            public void onSuccess(GenerateSourceResponseModel item, String message) {
                Log.i("otpR","rr");
                if(item.getMessage()!=null) {
                    Log.i("otpRecevied", item.getMessage());
                    sourceResponseInterface.generateSourceProcessed(item);
                }

            }
            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                if(errorBody.ErrorMessage!=null) {
                    Log.i("error" ,errorBody.ErrorMessage);
                    sourceResponseInterface.onFailure(errorBody,statusCode);
                }
            }
        });
    }

    @Override
    public void generateSourceRequest() {
        goGenerateSource();
    }
}


