package com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface;

import com.mdq.auditinspectionapp.Interfaces.StateViewInterface;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateFinalInvoiceResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GetInspectionReportResponseModel;

public interface GetInspectionReportResponseInterface extends StateViewInterface {
    void GetInspectionReportProcess(GetInspectionReportResponseModel getInspectionReportResponseModel);
    void onFailure(ErrorBody errorBody, int statusCode);
}
