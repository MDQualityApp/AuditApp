package com.mdq.auditinspectionapp.Interfaces;


import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;

public interface ResponseHandler<T> {
        void onSuccess(String message);

        void onSuccess(T item, String message);

        void onFailure(ErrorBody errorBody, int statusCode);

}


