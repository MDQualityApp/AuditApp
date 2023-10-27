package com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface;

import com.mdq.auditinspectionapp.Interfaces.StateViewInterface;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateFinalInvoiceResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GetProductionReportResponseModel;

public interface GetProductionResponseInterface extends StateViewInterface {
    void getProductionReportCall(GetProductionReportResponseModel getProductionReportResponseModel);
    void onFailure(ErrorBody errorBody, int statusCode);
}
