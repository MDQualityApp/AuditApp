package com.mdq.auditinspectionapp.ViewModel;

import android.content.Context;
import android.util.Log;

import com.mdq.auditinspectionapp.DataManager.GetInspectionReportDataManager;
import com.mdq.auditinspectionapp.DataManager.getByUserIdDataManager;
import com.mdq.auditinspectionapp.Interfaces.ResponseHandler;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.GetInspectionReportRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.getByUserIDRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.GetInspectionReportResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.getByUserIDResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GetInspectionReportRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.getByUserIDRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GetInspectionReportResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.getByUserIDResponseModel;
import com.mdq.auditinspectionapp.Utils.ApiClass;

public class getByUserIDViewModel extends getByUserIDBaseViewModel implements getByUserIDRequestInterface {

    private getByUserIdDataManager getByUserIdDataManager;
    private getByUserIDResponseInterface getInspectionReportResponseInterface;
    private Context mContext;

    public getByUserIDViewModel(Context mContext, getByUserIDResponseInterface getByUserIDResponseInterfaces) {
        this.getInspectionReportResponseInterface = getByUserIDResponseInterfaces;
        this.getByUserIdDataManager = new getByUserIdDataManager(mContext);
        this.mContext = mContext;
    }

    private void goGenerateBrand() {
        getByUserIDRequestModel getByUserIDRequestModel = new getByUserIDRequestModel();
        getByUserIDRequestModel.user_id = getUser_id();
        getByUserIDRequestModel.dbname = getDbname();


        getByUserIdDataManager.callEnqueue(ApiClass.GETTEAMID, getAuth(), getByUserIDRequestModel, new ResponseHandler<getByUserIDResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(getByUserIDResponseModel item, String message) {
                Log.i("otpR", "rr");
                if (item.getMessage() != null) {
                    Log.i("otpRecevied", item.getMessage());
                    getInspectionReportResponseInterface.generateGETBYID(item);
                }

            }

            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                if (errorBody.ErrorMessage != null) {
                    Log.i("error", errorBody.ErrorMessage);
                    getInspectionReportResponseInterface.onFailure(errorBody, statusCode);
                }
            }
        });
    }

    @Override
    public void generateGetByID() {
        goGenerateBrand();
    }
}
