package com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface;

import com.mdq.auditinspectionapp.Interfaces.StateViewInterface;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateLoginResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateSupplierResponseModel;

public interface SupplierResponseInterface extends StateViewInterface {

    void generateSupplierProcessed(GenerateSupplierResponseModel generateSupplierResponseModel);
    void onFailure(ErrorBody errorBody, int statusCode);
}
