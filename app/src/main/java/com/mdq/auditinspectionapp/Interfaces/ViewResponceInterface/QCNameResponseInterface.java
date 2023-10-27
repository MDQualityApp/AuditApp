package com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface;

import com.mdq.auditinspectionapp.Interfaces.StateViewInterface;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateBrandResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateQCNameResponseModel;

public interface QCNameResponseInterface extends StateViewInterface {

    void generateQCNameProcessed(GenerateQCNameResponseModel generateQCNameResponseModel);
    void onFailure(ErrorBody errorBody, int statusCode);
}
