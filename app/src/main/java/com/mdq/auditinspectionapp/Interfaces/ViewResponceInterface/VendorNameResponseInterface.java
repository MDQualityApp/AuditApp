package com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface;

import com.mdq.auditinspectionapp.Interfaces.StateViewInterface;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.CustomerNameResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.VendorNameResponseModel;

public interface VendorNameResponseInterface extends StateViewInterface {
    void generateVendorNameProcessed(VendorNameResponseModel vendorNameResponseModel);
    void onFailure(ErrorBody errorBody, int statusCode);
}
