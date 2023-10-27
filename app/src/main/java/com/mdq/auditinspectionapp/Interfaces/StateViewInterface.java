package com.mdq.auditinspectionapp.Interfaces;


import com.mdq.auditinspectionapp.enums.MessageViewType;
import com.mdq.auditinspectionapp.enums.ViewType;

public interface StateViewInterface {
        void ShowErrorMessage(MessageViewType messageViewType, String errorMessage);
        void ShowErrorMessage(MessageViewType messageViewType, ViewType viewType, String errorMessage);

    }

