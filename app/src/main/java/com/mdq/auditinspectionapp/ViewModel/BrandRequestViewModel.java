package com.mdq.auditinspectionapp.ViewModel;

import android.content.Context;
import android.util.Log;

import com.mdq.auditinspectionapp.DataManager.BrandDataManager;
import com.mdq.auditinspectionapp.DataManager.SourceDataManager;
import com.mdq.auditinspectionapp.Interfaces.ResponseHandler;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.BrandRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.BrandResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.SourceResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateBrandRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateSourceRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateBrandResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateSourceResponseModel;
import com.mdq.auditinspectionapp.Utils.ApiClass;

public class BrandRequestViewModel extends BrandRequestBaseViewModel implements BrandRequestInterface
{
    private BrandDataManager brandDataManager;
    private BrandResponseInterface brandResponseInterface;
    private Context mContext;

    public BrandRequestViewModel(Context mContext, BrandResponseInterface brandResponseInterface) {
        this.brandResponseInterface = brandResponseInterface;
        this.brandDataManager = new BrandDataManager(mContext);
        this.mContext = mContext;
    }

    private void goGenerateBrand() {
        GenerateBrandRequestModel sourceRequestModel=new GenerateBrandRequestModel();
        sourceRequestModel.sourceId=getSourceId();
        sourceRequestModel.sourceFlag=getSourceFlag();
        sourceRequestModel.seasonId=getSeasonId();
        sourceRequestModel.dbname=getDbname();
        sourceRequestModel.teamId=getTeamId();
        brandDataManager.callEnqueue(ApiClass.BRAND, getAuthorization(), sourceRequestModel,new ResponseHandler<GenerateBrandResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(GenerateBrandResponseModel item, String message) {
                Log.i("otpR","rr");
                if(item.getMessage()!=null) {
                    Log.i("otpRecevied", item.getMessage());
                    brandResponseInterface.generateBrandProcessed(item);
                }

            }
            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                if(errorBody.ErrorMessage!=null) {
                    Log.i("error" ,errorBody.ErrorMessage);
                    brandResponseInterface.onFailure(errorBody,statusCode);
                }
            }
        });
    }


    @Override
    public void generateBrandRequest() {
       goGenerateBrand();
    }
}


