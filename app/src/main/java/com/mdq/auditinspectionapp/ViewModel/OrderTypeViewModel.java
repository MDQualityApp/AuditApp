package com.mdq.auditinspectionapp.ViewModel;

import android.content.Context;
import android.util.Log;

import com.mdq.auditinspectionapp.DataManager.CustomerNameDataManager;
import com.mdq.auditinspectionapp.DataManager.OrderTypeDataManager;
import com.mdq.auditinspectionapp.Interfaces.ResponseHandler;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.CustomerNameRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.OrderTypeRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.CustomerNameResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.OrderTypeResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.OrderTypeRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.CustomerNameResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.OrderTypeResponseModel;
import com.mdq.auditinspectionapp.Utils.ApiClass;

public class OrderTypeViewModel extends OrderTypeBaseViewModel implements OrderTypeRequestInterface

{
    private OrderTypeDataManager orderTypeDataManager;
    private OrderTypeResponseInterface orderTypeResponseInterface;
    private Context mContext;

    public OrderTypeViewModel(Context mContext, OrderTypeResponseInterface orderTypeResponseInterface) {
        this.orderTypeResponseInterface = orderTypeResponseInterface;
        this.orderTypeDataManager = new OrderTypeDataManager(mContext);
        this.mContext = mContext;
    }

    private void goGenerateBrand() {

        OrderTypeRequestModel orderTypeRequestModel =new OrderTypeRequestModel();
        orderTypeRequestModel.dbname =getDbname();
        orderTypeDataManager.callEnqueue(ApiClass.ORDERTYPE,orderTypeRequestModel,getAuthorization(),new ResponseHandler<OrderTypeResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(OrderTypeResponseModel item, String message) {
                Log.i("otpR","rr");
                if(item.data!=null) {
                    orderTypeResponseInterface.generateOrderTypeProcessed(item);
                }

            }
            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                if(errorBody.ErrorMessage!=null) {
                    Log.i("error" ,errorBody.ErrorMessage);
                    orderTypeResponseInterface.onFailure(errorBody,statusCode);
                }
            }
        });
    }

    @Override
    public void generateOrderTypeRequest() {
        goGenerateBrand();
    }
}

