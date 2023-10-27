
package com.mdq.auditinspectionapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.mdq.auditinspectionapp.R;
import com.mdq.auditinspectionapp.Utils.PreferenceManager;
import com.mdq.auditinspectionapp.databinding.ActivityReportScreenBinding;

public class ReportScreen extends AppCompatActivity {

    ActivityReportScreenBinding activityReportScreenBinding;
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityReportScreenBinding=ActivityReportScreenBinding.inflate(getLayoutInflater());
        setContentView(activityReportScreenBinding.getRoot());

        activityReportScreenBinding.welcomeText.setText("welcome "+getPreferenceManager().getPrefUsername());

        activityReportScreenBinding.CardProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReportScreen.this,selectionForReport.class)
                        .putExtra("ffrom","production"));
            }
        });

        activityReportScreenBinding.inspections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReportScreen.this,selectionForReport.class)
                        .putExtra("ffrom","inspection")
                        .putExtra("dpid",getPreferenceManager().getPrefDpid()));
            }
        });


    }

    /**
     * @return
     * @brief initializing the preferenceManager from shared preference for local use in this activity
     */

    public PreferenceManager getPreferenceManager() {
        if (preferenceManager == null) {
            preferenceManager = PreferenceManager.getInstance();
            preferenceManager.initialize(getApplicationContext());
        }
        return preferenceManager;
    }
}