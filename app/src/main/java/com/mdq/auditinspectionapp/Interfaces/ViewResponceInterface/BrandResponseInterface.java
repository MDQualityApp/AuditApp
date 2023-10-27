package com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface;

import com.mdq.auditinspectionapp.Interfaces.StateViewInterface;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateBrandResponseModel;

public interface BrandResponseInterface extends StateViewInterface {

    void generateBrandProcessed(GenerateBrandResponseModel generateBrandResponseModel);
    void onFailure(ErrorBody errorBody, int statusCode);
}
