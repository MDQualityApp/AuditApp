package com.mdq.auditinspectionapp.ViewModel;

import android.content.Context;
import android.util.Log;

import com.mdq.auditinspectionapp.DataManager.OrderStatusDataManager;
import com.mdq.auditinspectionapp.DataManager.OrderTypeDataManager;
import com.mdq.auditinspectionapp.Interfaces.ResponseHandler;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.OrderStatusRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.OrderTypeRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.OrderStatusResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.OrderTypeResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.OrderStatusRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.OrderStatusResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.OrderTypeResponseModel;
import com.mdq.auditinspectionapp.Utils.ApiClass;

public class OrderStatusViewModel extends OrderStatusBaseViewModel implements OrderStatusRequestInterface

{
    private OrderStatusDataManager orderStatusDataManager;
    private OrderStatusResponseInterface orderStatusResponseInterface;
    private Context mContext;

    public OrderStatusViewModel(Context mContext, OrderStatusResponseInterface orderStatusResponseInterface) {
        this.orderStatusResponseInterface = orderStatusResponseInterface;
        this.orderStatusDataManager = new OrderStatusDataManager(mContext);
        this.mContext = mContext;
    }

    private void goGenerateBrand() {

        OrderStatusRequestModel orderStatusRequestModel = new OrderStatusRequestModel();
        orderStatusRequestModel.dbname = getDbname();

        orderStatusDataManager.callEnqueue(ApiClass.ORDERSTATUS,orderStatusRequestModel,getAuthorization(),new ResponseHandler<OrderStatusResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(OrderStatusResponseModel item, String message) {
                Log.i("otpR","rr");
                if(item.data!=null) {
                    orderStatusResponseInterface.generateOrderStatusProcessed(item);
                }

            }
            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                if(errorBody.ErrorMessage!=null) {
                    Log.i("error" ,errorBody.ErrorMessage);
                    orderStatusResponseInterface.onFailure(errorBody,statusCode);
                }
            }
        });
    }

    @Override
    public void generateOrderStatusRequest() {
        goGenerateBrand();
    }
}

