package com.mdq.auditinspectionapp.ViewModel;

import android.content.Context;
import android.util.Log;

import com.mdq.auditinspectionapp.DataManager.BrandDataManager;
import com.mdq.auditinspectionapp.DataManager.QCNameDataManager;
import com.mdq.auditinspectionapp.Interfaces.ResponseHandler;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.QCNameRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.BrandResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.QCNameResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateBrandRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateQCNameRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateBrandResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateQCNameResponseModel;
import com.mdq.auditinspectionapp.Utils.ApiClass;

public class QCNameRequestViewModel extends QCNameRequestBaseViewModel implements QCNameRequestInterface {


    private QCNameDataManager qcNameDataManager;
    private QCNameResponseInterface qcNameResponseInterface;
    private Context mContext;

    public QCNameRequestViewModel(Context mContext, QCNameResponseInterface qcNameResponseInterface) {
        this.qcNameResponseInterface = qcNameResponseInterface;
        this.qcNameDataManager = new QCNameDataManager(mContext);
        this.mContext = mContext;
    }

    private void goGenerateBrand() {
        GenerateQCNameRequestModel sourceRequestModel=new GenerateQCNameRequestModel();
        sourceRequestModel.dbname=getDbname();
        sourceRequestModel.sourceFlag=getSourceFlag();
        sourceRequestModel.sourceId=getSourceId();
        qcNameDataManager.callEnqueue(ApiClass.QCNAME,getAuth(), sourceRequestModel,new ResponseHandler<GenerateQCNameResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(GenerateQCNameResponseModel item, String message) {
                Log.i("otpR","rr");
                if(item.getMessage()!=null) {
                    Log.i("otpRecevied", item.getMessage());
                    qcNameResponseInterface.generateQCNameProcessed(item);
                }

            }
            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                if(errorBody.ErrorMessage!=null) {
                    Log.i("error" ,errorBody.ErrorMessage);
                    qcNameResponseInterface.onFailure(errorBody,statusCode);
                }
            }
        });
    }
    @Override
    public void generateQCNameRequest() {
        goGenerateBrand();
    }
}
