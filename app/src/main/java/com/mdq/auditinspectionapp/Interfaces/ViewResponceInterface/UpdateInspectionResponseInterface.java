package com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface;

import com.mdq.auditinspectionapp.Interfaces.StateViewInterface;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateUpdateInspectionResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateUpdateProductionResponseModel;

public interface UpdateInspectionResponseInterface extends StateViewInterface {

    void generateUpdateInspectionProcessed(GenerateUpdateInspectionResponseModel generateUpdateInspectionResponseModel);
    void onFailure(ErrorBody errorBody, int statusCode);
}
