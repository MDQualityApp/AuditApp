package com.mdq.auditinspectionapp.ViewModel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mdq.auditinspectionapp.DataManager.LoginDataManger;
import com.mdq.auditinspectionapp.Interfaces.ResponseHandler;
import com.mdq.auditinspectionapp.Interfaces.ViewInterface.LoginRequestInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.LoginResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateLoginRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateLoginResponseModel;
import com.mdq.auditinspectionapp.Utils.ApiClass;

public class LoginRequestViewModel extends LoginRequestBaseViewModel implements LoginRequestInterface {
    private LoginDataManger loginDataManager;
    private LoginResponseInterface loginResponseInterface;
    private Context mContext;

    public LoginRequestViewModel(Context mContext, LoginResponseInterface loginResponseInterface) {
        this.loginResponseInterface = loginResponseInterface;
        this.loginDataManager = new LoginDataManger(mContext);
        this.mContext = mContext;
    }

    private void goGenerateLogin() {
        GenerateLoginRequestModel generateLoginRequestModel=new GenerateLoginRequestModel();
        generateLoginRequestModel.emp_id=getEmp_id();
        generateLoginRequestModel.password=getPassword();
        generateLoginRequestModel.dbname=getDbname();
        loginDataManager.callEnqueue(ApiClass.LOGIN, generateLoginRequestModel, new ResponseHandler<GenerateLoginResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(GenerateLoginResponseModel item, String message) {
                Log.i("otpR","rr");
                if(item.getMessage()!=null) {
                    Log.i("otpRecevied", item.getMessage());
                    loginResponseInterface.generateLoginProcessed(item);
                }

            }
            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                if(statusCode>=400 && statusCode<500) {
                    Toast.makeText(mContext, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    loginResponseInterface.onFailure(errorBody, statusCode);
                }
            }
        });
    }

    @Override
    public void generateLoginRequest() {
        goGenerateLogin();
    }
}
