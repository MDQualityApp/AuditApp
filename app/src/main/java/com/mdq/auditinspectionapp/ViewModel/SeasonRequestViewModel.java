package com.mdq.auditinspectionapp.ViewModel;

import android.content.Context;
import android.util.Log;

import com.mdq.auditinspectionapp.DataManager.SeasonDataManager;
import com.mdq.auditinspectionapp.DataManager.SourceDataManager;
import com.mdq.auditinspectionapp.Interfaces.ResponseHandler;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.SourceRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.SeasonResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.SourceResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateSeasonRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateSourceRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateSeasonResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateSourceResponseModel;
import com.mdq.auditinspectionapp.Utils.ApiClass;

public class SeasonRequestViewModel extends SeasonRequestBaseViewModel implements SourceRequestInterface {
    private SeasonDataManager seasonDataManager;
    private SeasonResponseInterface seasonResponseInterface;
    private Context mContext;

    public SeasonRequestViewModel(Context mContext, SeasonResponseInterface seasonResponseInterface) {
        this.seasonResponseInterface = seasonResponseInterface;
        this.seasonDataManager = new SeasonDataManager(mContext);
        this.mContext = mContext;
    }

    private void goGenerateSeason() {
        GenerateSeasonRequestModel seasonRequestModel=new GenerateSeasonRequestModel();
        seasonRequestModel.dbname=getDbname();
        seasonDataManager.callEnqueue(ApiClass.SEASON, getAuthorization(), seasonRequestModel,new ResponseHandler<GenerateSeasonResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(GenerateSeasonResponseModel item, String message) {
                Log.i("otpR","rr");
                if(item.getMessage()!=null) {
                    Log.i("otpRecevied", item.getMessage());
                    seasonResponseInterface.generateSeasonProcessed(item);
                }

            }
            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                if(errorBody.ErrorMessage!=null) {
                    Log.i("error" ,errorBody.ErrorMessage);
                    seasonResponseInterface.onFailure(errorBody,statusCode);
                }
            }
        });
    }

    @Override
    public void generateSourceRequest() {
        goGenerateSeason();
    }
}
