package com.mdq.auditinspectionapp.DataManager;

import static com.mdq.auditinspectionapp.base.AuditInspectionApp.getApp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mdq.auditinspectionapp.Http.ApiInterface;
import com.mdq.auditinspectionapp.Interfaces.ResponseHandler;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateUpdateInspectionRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateUpdateProductionRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateUpdateInspectionResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateUpdateProductionResponseModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateInspectionDataManager {

    private final String TAG = UpdateInspectionDataManager.class.getSimpleName();
    private ApiInterface apiInterface;
    Context context;

    public UpdateInspectionDataManager(Context context) {
        this.context = context;
        this.apiInterface = getApp().getRetrofitInterface();
    }

    public void callEnqueue(String url, GenerateUpdateInspectionRequestModel generateUpdateInspectionRequestModel, final ResponseHandler<GenerateUpdateInspectionResponseModel> dataresponse) {

        //calling the generatePostLoginCall methode from call apiInterface
        Call<GenerateUpdateInspectionResponseModel> userMpinCall = apiInterface.generatePostUpdateInspectionCall(url, generateUpdateInspectionRequestModel);
        userMpinCall.enqueue(new Callback<GenerateUpdateInspectionResponseModel>() {

            /**
             * @param call
             * @param response
             * @breif getting response from api
             */
            @Override
            public void onResponse(Call<GenerateUpdateInspectionResponseModel> call, Response<GenerateUpdateInspectionResponseModel> response) {
                /**
                 * Invoked for a received HTTP response.
                 * <p>
                 * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
                 * Call {@link Response#isSuccessful()} to determine if the response indicates success.
                 *
                 * @param call
                 * @param response
                 */
                Log.i("responce", "response get");
                int statusCode = response.code();

                //if response is successful set the body of response to onSuccess methode in GenerateRegisterResponseModel else get the error body and set on onFailure in generateRegisterResponseModel
                if (response.isSuccessful()) {
                    dataresponse.onSuccess(response.body(), "SuccessModel");
                } else {
                    String serviceResponse = null;
                    try {
                        serviceResponse = response.errorBody().string();
                        ErrorBody errorBody = new Gson().fromJson(serviceResponse, ErrorBody.class);
                        if(errorBody!=null) {
                            dataresponse.onFailure(errorBody, statusCode);
                        }
                    } catch (JsonSyntaxException e) {
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            /**
             * @param call
             * @param t
             * @breif api call failure
             */
            @Override
            public void onFailure(Call<GenerateUpdateInspectionResponseModel> call, Throwable t) {
                Log.d(TAG, "onTokenExpired: " + t.getMessage());
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
