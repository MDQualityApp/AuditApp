package com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface;

import com.mdq.auditinspectionapp.Interfaces.StateViewInterface;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateSourceResponseModel;

public interface SourceResponseInterface extends StateViewInterface {

    void generateSourceProcessed(GenerateSourceResponseModel generateSourceResponseModel);
    void onFailure(ErrorBody errorBody, int statusCode);
}
