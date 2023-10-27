package com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface;

import com.mdq.auditinspectionapp.Interfaces.StateViewInterface;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.OrderStatusResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.OrderTypeResponseModel;

public interface OrderStatusResponseInterface  extends StateViewInterface {
    void generateOrderStatusProcessed(OrderStatusResponseModel orderStatusResponseModel);
    void onFailure(ErrorBody errorBody, int statusCode);
}
