package com.mdq.auditinspectionapp.ViewModel;

import android.content.Context;
import android.util.Log;

import com.mdq.auditinspectionapp.DataManager.BrandDataManager;
import com.mdq.auditinspectionapp.DataManager.ShipModeDataManager;
import com.mdq.auditinspectionapp.Interfaces.ResponseHandler;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.ShipModeRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.BrandResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.ShipModeResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateBrandRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateShipModeRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateBrandResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateShipModeResponseModel;
import com.mdq.auditinspectionapp.Utils.ApiClass;

public class ShipModeRequestViewModel extends ShipModeRequestBaseViewModel implements ShipModeRequestInterface
    {
        private ShipModeDataManager shipModeDataManager;
        private ShipModeResponseInterface shipModeResponseInterface;
        private Context mContext;

    public ShipModeRequestViewModel(Context mContext, ShipModeResponseInterface shipModeResponseInterface) {
        this.shipModeResponseInterface = shipModeResponseInterface;
        this.shipModeDataManager = new ShipModeDataManager(mContext);
        this.mContext = mContext;
    }

        private void goGenerateBrand() {
        GenerateShipModeRequestModel sourceRequestModel=new GenerateShipModeRequestModel();
        sourceRequestModel.dbname=getDbname();
            shipModeDataManager.callEnqueue(ApiClass.SHIPMODE,getAuth(), sourceRequestModel,new ResponseHandler<GenerateShipModeResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(GenerateShipModeResponseModel item, String message) {
                Log.i("otpR","rr");
                if(item.getMessage()!=null) {
                    Log.i("otpRecevied", item.getMessage());
                    shipModeResponseInterface.generateShipModeProcessed(item);
                }

            }
            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                if(errorBody.ErrorMessage!=null) {
                    Log.i("error" ,errorBody.ErrorMessage);
                    shipModeResponseInterface.onFailure(errorBody,statusCode);
                }
            }
        });
    }


    @Override
    public void generateShipModeRequest() {
        goGenerateBrand();
    }
}
