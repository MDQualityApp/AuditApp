package com.mdq.auditinspectionapp.ViewModel;

import android.content.Context;
import android.util.Log;

import com.mdq.auditinspectionapp.DataManager.CustomerNameDataManager;
import com.mdq.auditinspectionapp.DataManager.VendorNameDataManager;
import com.mdq.auditinspectionapp.Interfaces.ResponseHandler;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.CustomerNameRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.CustomerNameResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.VendorNameResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.VendorNameRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.CustomerNameResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.VendorNameResponseModel;
import com.mdq.auditinspectionapp.Utils.ApiClass;

public class VendorNameViewModel extends CustomerRequestBaseViewModel implements CustomerNameRequestInterface

{
    private VendorNameDataManager vendorNameDataManager;
    private VendorNameResponseInterface vendorNameResponseInterface;
    private Context mContext;

    public VendorNameViewModel(Context mContext, VendorNameResponseInterface vendorNameResponseInterface) {
        this.vendorNameResponseInterface = vendorNameResponseInterface;
        this.vendorNameDataManager = new VendorNameDataManager(mContext);
        this.mContext = mContext;
    }

    private void goGenerateBrand() {

        VendorNameRequestModel vendorNameRequestModel =new VendorNameRequestModel();
        vendorNameRequestModel.dbname = getDbname();

        vendorNameDataManager.callEnqueue(ApiClass.VENDORNAME,vendorNameRequestModel,getAuthorization(),new ResponseHandler<VendorNameResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(VendorNameResponseModel item, String message) {
                Log.i("otpR","rr");
                if(item.data!=null) {
                    vendorNameResponseInterface.generateVendorNameProcessed(item);
                }

            }
            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                if(errorBody.ErrorMessage!=null) {
                    Log.i("error" ,errorBody.ErrorMessage);
                    vendorNameResponseInterface.onFailure(errorBody,statusCode);
                }
            }
        });
    }

    @Override
    public void generateCustomerRequest() {
        goGenerateBrand();
    }
}

