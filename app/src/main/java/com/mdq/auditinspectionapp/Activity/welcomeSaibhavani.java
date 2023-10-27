package com.mdq.auditinspectionapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.mdq.auditinspectionapp.R;
import com.mdq.auditinspectionapp.Utils.PreferenceManager;
import com.mdq.auditinspectionapp.databinding.ActivityWelcomeSaibhavaniBinding;

public class welcomeSaibhavani extends AppCompatActivity {

    CardView cardProduct,ReportCard;
    ActivityWelcomeSaibhavaniBinding aw;
    int dpid=0;
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aw=ActivityWelcomeSaibhavaniBinding.inflate(getLayoutInflater());
        setContentView(aw.getRoot());
        cardProduct=findViewById(R.id.CardProduct);
        ReportCard=findViewById(R.id.ReportCard);
        //making status bar color as transparent
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        dpid=intent.getIntExtra("dpid",0);
        aw.welcomeText.setText("welcome "+getPreferenceManager().getPrefUsername());

        aw.inspections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(welcomeSaibhavani.this, TransactionProductUpdate.class);
                intent.putExtra("name",name);
                intent.putExtra("dpid",getPreferenceManager().getPrefDpid());
                startActivity(intent);
            }
        });
        cardProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(welcomeSaibhavani.this, TransactionProductUpdate.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });

        ReportCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(), ReportScreen.class);
                startActivity(intent);

            }
        });

        aw.LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(welcomeSaibhavani.this, LoginActivity.class));
                finishAffinity();
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