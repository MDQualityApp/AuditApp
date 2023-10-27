package com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface;

import com.mdq.auditinspectionapp.Interfaces.StateViewInterface;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateBrandResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateUpdateProductionResponseModel;

public interface UpdateProductionResponseInterface extends StateViewInterface {

    void generateUpdateProductionProcessed(GenerateUpdateProductionResponseModel generateUpdateProductionResponseModel);
    void onFailure(ErrorBody errorBody, int statusCode);
}
