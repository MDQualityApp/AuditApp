package com.mdq.auditinspectionapp.ViewModel;

import android.content.Context;
import android.util.Log;

import com.mdq.auditinspectionapp.DataManager.BrandDataManager;
import com.mdq.auditinspectionapp.DataManager.QCResultDataManager;
import com.mdq.auditinspectionapp.Interfaces.ResponseHandler;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.QCResultRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.BrandResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.QCResultResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateBrandRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateQCResultRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateBrandResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateQCResultResponseModel;
import com.mdq.auditinspectionapp.Utils.ApiClass;

public class QCResultRequestViewModel extends QCResultRequestBaseViewModel implements QCResultRequestInterface {

    private QCResultDataManager qcResultDataManager;
    private QCResultResponseInterface qcResultResponseInterface;
    private Context mContext;

    public QCResultRequestViewModel(Context mContext, QCResultResponseInterface qcResultResponseInterface) {
        this.qcResultResponseInterface = qcResultResponseInterface;
        this.qcResultDataManager = new QCResultDataManager(mContext);
        this.mContext = mContext;
    }

    private void goGenerateBrand() {
        GenerateQCResultRequestModel sourceRequestModel=new GenerateQCResultRequestModel();
        sourceRequestModel.dbname = getDbname();

        qcResultDataManager.callEnqueue(ApiClass.QCRESULT, getAuth(),sourceRequestModel,new ResponseHandler<GenerateQCResultResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(GenerateQCResultResponseModel item, String message) {
                Log.i("otpR","rr");
                if(item.getMessage()!=null) {
                    Log.i("otpRecevied", item.getMessage());
                    qcResultResponseInterface.generateQCResultProcessed(item);
                }

            }
            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                if(errorBody.ErrorMessage!=null) {
                    Log.i("error" ,errorBody.ErrorMessage);
                    qcResultResponseInterface.onFailure(errorBody,statusCode);
                }
            }
        });
    }

    @Override
    public void generateQCResultRequest() {
        goGenerateBrand();
    }
}
