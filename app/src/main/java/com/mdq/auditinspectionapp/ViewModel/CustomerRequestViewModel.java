package com.mdq.auditinspectionapp.ViewModel;

import android.content.Context;
import android.util.Log;

import com.mdq.auditinspectionapp.DataManager.CustomerNameDataManager;
import com.mdq.auditinspectionapp.DataManager.FinalInvoiceDataManager;
import com.mdq.auditinspectionapp.Interfaces.ResponseHandler;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.CustomerNameRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.FinalInvoiceRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.CustomerNameResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.FinalInvoiceResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.CustomerNameRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateFinalInvoiceRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.CustomerNameResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateFinalInvoiceResponseModel;
import com.mdq.auditinspectionapp.Utils.ApiClass;

public class CustomerRequestViewModel extends CustomerRequestBaseViewModel implements CustomerNameRequestInterface

{
    private CustomerNameDataManager customerNameDataManager;
    private CustomerNameResponseInterface customerNameResponseInterface;
    private Context mContext;

    public CustomerRequestViewModel(Context mContext, CustomerNameResponseInterface customerNameResponseInterface) {
        this.customerNameResponseInterface = customerNameResponseInterface;
        this.customerNameDataManager = new CustomerNameDataManager(mContext);
        this.mContext = mContext;
    }

    private void goGenerateBrand() {
        CustomerNameRequestModel customerNameRequestModel =new CustomerNameRequestModel();
        customerNameRequestModel.dbname = getDbname();

        customerNameDataManager.callEnqueue(ApiClass.CUSTOMERNAME,customerNameRequestModel,getAuthorization(),new ResponseHandler<CustomerNameResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(CustomerNameResponseModel item, String message) {
                Log.i("otpR","rr");
                if(item.data!=null) {
                    customerNameResponseInterface.generateCustomerProcessed(item);
                }

            }
            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                if(errorBody.ErrorMessage!=null) {
                    Log.i("error" ,errorBody.ErrorMessage);
                    customerNameResponseInterface.onFailure(errorBody,statusCode);
                }
            }
        });
    }

    @Override
    public void generateCustomerRequest() {
        goGenerateBrand();
    }
}

