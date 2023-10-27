package com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface;

import com.mdq.auditinspectionapp.Interfaces.StateViewInterface;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateBrandResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateQCResultResponseModel;

public interface QCResultResponseInterface extends StateViewInterface {

    void generateQCResultProcessed(GenerateQCResultResponseModel generateQCResultResponseModel);
    void onFailure(ErrorBody errorBody, int statusCode);
}
