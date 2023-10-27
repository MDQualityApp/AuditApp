package com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface;

import com.mdq.auditinspectionapp.Interfaces.StateViewInterface;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateLoginResponseModel;

public interface LoginResponseInterface extends StateViewInterface {

    void generateLoginProcessed(GenerateLoginResponseModel generateLoginResponseModel);
    void onFailure(ErrorBody errorBody, int statusCode);
}
