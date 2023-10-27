package com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface;

import com.mdq.auditinspectionapp.Interfaces.StateViewInterface;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateShipModeResponseModel;

public interface ShipModeResponseInterface extends StateViewInterface
{
    void generateShipModeProcessed(GenerateShipModeResponseModel generateShipModeResponceModel);
    void onFailure(ErrorBody errorBody, int statusCode);
}
