package com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface;

import com.mdq.auditinspectionapp.Interfaces.StateViewInterface;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateFinalInvoiceResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.getByUserIDResponseModel;

public interface getByUserIDResponseInterface extends StateViewInterface {
    void generateGETBYID(getByUserIDResponseModel getByUserIDResponseModel);
    void onFailure(ErrorBody errorBody, int statusCode);
}