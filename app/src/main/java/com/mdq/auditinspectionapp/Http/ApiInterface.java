package com.mdq.auditinspectionapp.Http;

import com.mdq.auditinspectionapp.Pojo.JsonRequest.CustomerNameRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateBrandRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateFinalInvoiceRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateInvoiceRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateLoginRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateQCNameRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateQCResultRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateSeasonRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateShipModeRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateSourceRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateSupplierRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateUpdateInspectionRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateUpdateProductionRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GetInspectionReportRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GetProductionReportRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.OrderStatusRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.OrderTypeRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.VendorNameRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.getByUserIDRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.CustomerNameResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateBrandResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateFinalInvoiceResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateInvoiceResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateLoginResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateQCNameResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateQCResultResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateSeasonResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateShipModeResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateSourceResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateSupplierResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateUpdateInspectionResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateUpdateProductionResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GetInspectionReportResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GetProductionReportResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.OrderStatusResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.OrderTypeResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.VendorNameResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.getByUserIDResponseModel;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

//calling backend with api
public interface ApiInterface {

    @POST
    Call<GenerateLoginResponseModel> generatePostLoginCall(@Url String url, @Body GenerateLoginRequestModel generateLoginResponseModel);

    @POST
    Call<GenerateSourceResponseModel> generateGetSourceCall(@Url String url, @Header("Authorization") String Authorization, @Body GenerateSourceRequestModel generateSourceRequestModel);

    @POST
    Call<GenerateSeasonResponseModel> generateGetSeasonCall(@Url String url, @Header("Authorization") String Authorization, @Body GenerateSeasonRequestModel seasonRequestModel);

    @POST
    Call<GenerateBrandResponseModel> generateGetBrandCall(@Url String url, @Header("Authorization") String Authorization, @Body GenerateBrandRequestModel generateBrandRequestModel);

    @POST
    Call<GenerateSupplierResponseModel> generateGetSupplierCall(@Url String url, @Header("Authorization") String Authorization, @Body GenerateSupplierRequestModel generateSupplierRequestModel);

    @POST
    Call<GenerateInvoiceResponseModel> generateGetInvoiceCall(@Url String url, @Header("Authorization") String Authorization, @Body GenerateInvoiceRequestModel generateInvoiceRequestModel);

    @POST
    Call<GenerateInvoiceResponseModel> generateGetInvoiceListReportCall(@Url String url, @Header("Authorization") String Authorization, @Body GenerateInvoiceRequestModel generateInvoiceRequestModel);

    @POST
    Call<GenerateFinalInvoiceResponseModel> generateGetFinalInvoiceCall(@Url String url, @Header("Authorization") String Authorization, @Body GenerateFinalInvoiceRequestModel generateFinalInvoiceRequestModel);

    @POST
    Call<GenerateShipModeResponseModel> generateGetShipModeCall(@Url String url, @Header("Authorization") String Authorization, @Body GenerateShipModeRequestModel generateShipModeRequestModel);

    @POST
    Call<GenerateQCResultResponseModel> generateGetQCResultCall(@Url String url, @Header("Authorization") String Authorization, @Body GenerateQCResultRequestModel generateQCResultRequestModel);

    @POST
    Call<GenerateQCNameResponseModel> generateGetQCNameCall(@Url String url, @Header("Authorization") String Authorization, @Body GenerateQCNameRequestModel generateQCNameRequestModel);

    @POST
    Call<GenerateUpdateProductionResponseModel> generatePostUpdateProductionCall(@Url String url, @Header("Authorization") String Authorization, @Body GenerateUpdateProductionRequestModel generateUpdateProductionRequestModel);

    @POST
    Call<GenerateUpdateInspectionResponseModel> generatePostUpdateInspectionCall(@Url String url, @Header("Authorization") String Authorization, @Body GenerateUpdateInspectionRequestModel generateUpdateInspectionRequestModel);

    @POST
    Call<GetProductionReportResponseModel> getProductionReportCall(@Url String url, @Header("Authorization") String Authorization, @Body GetProductionReportRequestModel getProductionReportRequestModel);

    @POST
    Call<GetInspectionReportResponseModel> getInspectionReportCall(@Url String url, @Header("Authorization") String Authorization, @Body GetInspectionReportRequestModel getInspectionReportRequestModel);

    @POST
    Call<CustomerNameResponseModel> generateCustomerNameCall(@Url String url, @Header("Authorization") String Authorization, @Body CustomerNameRequestModel customerNameRequestModel);

    @POST
    Call<VendorNameResponseModel> generateVendorNameCall(@Url String url, @Header("Authorization") String Authorization, @Body VendorNameRequestModel vendorNameRequestModel);

    @POST
    Call<OrderTypeResponseModel> generateOrderTypeCall(@Url String url, @Header("Authorization") String Authorization, @Body OrderTypeRequestModel orderTypeRequestModel);

    @POST
    Call<OrderStatusResponseModel> generateOrderStatusCall(@Url String url, @Header("Authorization") String Authorization, @Body OrderStatusRequestModel statusRequestModel);

    @POST
    Call<getByUserIDResponseModel> getByUSERID(@Url String url, @Header("Authorization") String Authorization, @Body getByUserIDRequestModel getByUserIDRequestModel);

}
