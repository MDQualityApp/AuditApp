package com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface;

import com.mdq.auditinspectionapp.Interfaces.StateViewInterface;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateInvoiceResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateLoginResponseModel;

public interface InvoiceResponseInterface extends StateViewInterface {
    void generateInvoiceProcessed(GenerateInvoiceResponseModel generateInvoiceResponseModel);
    void onFailure(ErrorBody errorBody, int statusCode);
}
