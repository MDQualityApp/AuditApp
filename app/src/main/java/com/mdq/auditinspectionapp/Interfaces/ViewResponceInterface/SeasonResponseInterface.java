package com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface;

import com.mdq.auditinspectionapp.Interfaces.StateViewInterface;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateSeasonResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateSourceResponseModel;

public interface SeasonResponseInterface extends StateViewInterface {
    void generateSeasonProcessed(GenerateSeasonResponseModel generateSeasonResponseModel);
    void onFailure(ErrorBody errorBody, int statusCode);
}
