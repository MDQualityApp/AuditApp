package com.mdq.auditinspectionapp.base;

import android.app.Application;
import android.content.Context;

import com.mdq.auditinspectionapp.Http.ApiInterface;
import com.mdq.auditinspectionapp.Http.AuditApiClient;

//For setting the retrofit for api call to communicate with the backend server
public class AuditInspectionApp extends Application {

        public static Context mContext;
        public static AuditInspectionApp mInstance;
        public static AuditApiClient auditApiClient ;

        @Override
        public void onCreate() {
            super.onCreate();
            mInstance = this;
            mContext = this;
            auditApiClient = new AuditApiClient();
        }

        public static synchronized Context getContext() {
            return mContext;
        }

        public static AuditInspectionApp getApp() {
            if (mInstance != null && mInstance instanceof AuditInspectionApp) {
                return mInstance;
            } else {
                mInstance = new AuditInspectionApp();
                mInstance.onCreate();
                return mInstance;
            }
        }

        public ApiInterface getRetrofitInterface() {
            return AuditApiClient.apiinterface();
        }

        protected void attachBaseContext(Context base) {
            super.attachBaseContext(base);
        }

    }


