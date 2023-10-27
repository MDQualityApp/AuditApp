package com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface;

import com.mdq.auditinspectionapp.Interfaces.StateViewInterface;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.OrderTypeResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.VendorNameResponseModel;

public interface OrderTypeResponseInterface extends StateViewInterface {
    void generateOrderTypeProcessed(OrderTypeResponseModel orderTypeResponseModel);
    void onFailure(ErrorBody errorBody, int statusCode);
}
