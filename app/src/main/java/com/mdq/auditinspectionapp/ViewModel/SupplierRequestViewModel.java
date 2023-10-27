package com.mdq.auditinspectionapp.ViewModel;

import android.content.Context;
import android.util.Log;

import com.mdq.auditinspectionapp.DataManager.BrandDataManager;
import com.mdq.auditinspectionapp.DataManager.SupplierDataManager;
import com.mdq.auditinspectionapp.Interfaces.ResponseHandler;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.SupplierRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.BrandResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.SupplierResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateBrandRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateSupplierRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateBrandResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateSupplierResponseModel;
import com.mdq.auditinspectionapp.Utils.ApiClass;

public class SupplierRequestViewModel extends SupplierRequestBaseViewModel implements SupplierRequestInterface {

    private SupplierDataManager supplierDataManager;
    private SupplierResponseInterface supplierResponseInterface;
    private Context mContext;

    public SupplierRequestViewModel(Context mContext, SupplierResponseInterface supplierResponseInterface) {
        this.supplierResponseInterface = supplierResponseInterface;
        this.supplierDataManager = new SupplierDataManager(mContext);
        this.mContext = mContext;
    }

    private void goGenerateSupplier() {
        GenerateSupplierRequestModel supplierRequestModel=new GenerateSupplierRequestModel();
        supplierRequestModel.sourceId=getSourceId();
        supplierRequestModel.sourceFlag=getSourceFlag();
        supplierRequestModel.seasonId=getSeasonId();
        supplierRequestModel.dbname=getDbname();
        supplierRequestModel.brandId=getBrandId();

        supplierDataManager.callEnqueue(ApiClass.SUPPLIER, getAuthorization(), supplierRequestModel,new ResponseHandler<GenerateSupplierResponseModel>() {
            @Override
            public void onSuccess(String message) {
            }

            @Override
            public void onSuccess(GenerateSupplierResponseModel item, String message) {
                Log.i("otpR","rr");
                if(item.getMessage()!=null) {
                    Log.i("otpRecevied", item.getMessage());
                    supplierResponseInterface.generateSupplierProcessed(item);
                }
            }

            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                if(errorBody.ErrorMessage!=null) {
                    Log.i("error" ,errorBody.ErrorMessage);
                    supplierResponseInterface.onFailure(errorBody,statusCode);
                }
            }
        });
    }


    @Override
    public void generateSupplierRequest() {
        goGenerateSupplier();
    }
}
