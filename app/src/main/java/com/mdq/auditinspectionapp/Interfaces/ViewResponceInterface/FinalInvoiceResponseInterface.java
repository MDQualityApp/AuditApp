package com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface;

import com.mdq.auditinspectionapp.Interfaces.StateViewInterface;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateBrandResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateFinalInvoiceResponseModel;

public interface FinalInvoiceResponseInterface extends StateViewInterface {
    void generateFinalInvoiceProcessed(GenerateFinalInvoiceResponseModel generateFinalInvoiceResponseModel);
    void onFailure(ErrorBody errorBody, int statusCode);
}
