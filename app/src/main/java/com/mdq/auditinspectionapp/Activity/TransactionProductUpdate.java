package com.mdq.auditinspectionapp.Activity;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.mdq.auditinspectionapp.Adapter.AdapterForInvoice;
import com.mdq.auditinspectionapp.Interfaces.Interface_FinalInvoice;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.BrandResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.FinalInvoiceResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.InvoiceResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.SeasonResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.SourceResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.SupplierResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateBrandResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateFinalInvoiceResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateInvoiceResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateSeasonResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateSourceResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateSupplierResponseModel;
import com.mdq.auditinspectionapp.R;
import com.mdq.auditinspectionapp.Utils.PreferenceManager;
import com.mdq.auditinspectionapp.ViewModel.BrandRequestViewModel;
import com.mdq.auditinspectionapp.ViewModel.FinalInvoiceViewModel;
import com.mdq.auditinspectionapp.ViewModel.InvoiceRequestViewModel;
import com.mdq.auditinspectionapp.ViewModel.SeasonRequestViewModel;
import com.mdq.auditinspectionapp.ViewModel.SourceRequestViewModel;
import com.mdq.auditinspectionapp.ViewModel.SupplierRequestViewModel;
import com.mdq.auditinspectionapp.databinding.ActivityTransactionProductUpdateBinding;
import com.mdq.auditinspectionapp.enums.MessageViewType;
import com.mdq.auditinspectionapp.enums.ViewType;

import java.lang.reflect.Field;
import java.util.Calendar;

public class TransactionProductUpdate extends AppCompatActivity implements SourceResponseInterface, SeasonResponseInterface, BrandResponseInterface, SupplierResponseInterface, InvoiceResponseInterface, Interface_FinalInvoice, FinalInvoiceResponseInterface {

    TextView ProductUpdate, submit;
    BottomSheetDialog bottomSheetDialog;
    LinearLayout back;
    SourceRequestViewModel sourceRequestViewModel;
    SeasonRequestViewModel requestViewModel;
    BrandRequestViewModel brandRequestViewModel;
    SupplierRequestViewModel supplierRequestViewModel;
    InvoiceRequestViewModel invoiceRequestViewModel;
    FinalInvoiceViewModel finalInvoiceViewModel;
    PreferenceManager preferenceManager;
    ActivityTransactionProductUpdateBinding at;
    AutoCompleteTextView SourceAuto, SeasonAuto, BrandAuto, SupplierAuto;
    ArrayAdapter<String> sourceAdapter, seasonAdapter, brandAdapter, supplierAdapter;
    String[] SourceId, SourceName, sourceFlag, SeasonName, SeasonId, BrandId, BrandName, SupplierCode, SupplierName, InvoiceNo, InvoiceDate;
    int sourceNum, SeasonNum, BrandNum, SupplierNum;
    GenerateSeasonResponseModel generateSeasonResponseModel;
    GenerateSourceResponseModel generateSourceResponseModel;
    GenerateBrandResponseModel generateBrandResponseModel;
    GenerateSupplierResponseModel generateSupplierResponseModel;
    GenerateInvoiceResponseModel generateInvoiceResponseModel;
    AdapterForInvoice adapter;
    ConnectivityManager connectivityManager;
    int dpid;
    String from;
    String fromTO;
    boolean report=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        at = ActivityTransactionProductUpdateBinding.inflate(getLayoutInflater());
        setContentView(at.getRoot());

        connectivityManager = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        ProductUpdate = findViewById(R.id.textProductupdate);
        submit = findViewById(R.id.submit);
        back = findViewById(R.id.linearBack);
        SourceAuto = findViewById(R.id.SourceAuto);
        SeasonAuto = findViewById(R.id.SeasonAuto);
        BrandAuto = findViewById(R.id.BrandAuto);
        SupplierAuto = findViewById(R.id.SupplierAuto);
        ProductUpdate.setPaintFlags(ProductUpdate.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        sourceRequestViewModel = new SourceRequestViewModel(getApplicationContext(), this);
        requestViewModel = new SeasonRequestViewModel(getApplicationContext(), this);
        brandRequestViewModel = new BrandRequestViewModel(getApplicationContext(), this);
        supplierRequestViewModel = new SupplierRequestViewModel(getApplicationContext(), this);
        invoiceRequestViewModel = new InvoiceRequestViewModel(getApplicationContext(), this);
        finalInvoiceViewModel = new FinalInvoiceViewModel(getApplicationContext(), this);
        Intent intent = getIntent();
        String names = intent.getStringExtra("name");
        dpid = intent.getIntExtra("dpid", 0);
        from=intent.getStringExtra("from");
        fromTO=intent.getStringExtra("FromTo");
        if(from!=null){
            if(from.equals("report")){
                report=true;
                if(fromTO.equals("Inspection")){
                    at.textProductupdate.setText("Inspection report");
                }else if(fromTO.equals("Production")){
                    at.textProductupdate.setText("Production report");
                }
            }
        }
        if (dpid != 0) {
            at.textProductupdate.setText("INSPECTION UPDATE");
        }
        at.welcomeText.setText("welcome " + getPreferenceManager().getPrefUsername());
        //making status bar color as transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        calingApi();

        at.SeasonAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeasonAuto.showDropDown();
            }
        });
        at.SourceAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SourceAuto.showDropDown();
            }
        });
        at.SeasonLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeasonAuto.showDropDown();
            }
        });
        at.SourceLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SourceAuto.showDropDown();
            }
        });
        SourceAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sourceNum = position + 1;
            }
        });

        SeasonAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SeasonNum = position + 1;
            }
        });
        SupplierAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SupplierNum = position + 1;
            }
        });
        BrandAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BrandNum = position + 1;
            }
        });

        at.BrandLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED)
                        || (connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                        .getState() == NetworkInfo.State.CONNECTED)) {
                    if (SeasonNum != 0 && sourceNum != 0) {
                        if(getPreferenceManager().getPrefTeamId()!=null) {
                            brandRequestViewModel.setAuthorization("Bearer " + getPreferenceManager().getPrefToken());
                            brandRequestViewModel.setSeasonId(SeasonId[SeasonNum - 1]);
                            int ss = sourceNum - 1;
                            brandRequestViewModel.setSourceFlag(sourceFlag[ss]);
                            brandRequestViewModel.setSourceId(SourceId[ss]);
                            brandRequestViewModel.setDbname(getPreferenceManager().getPrefDbname());
                            brandRequestViewModel.setTeamId(getPreferenceManager().getPrefTeamId());
                            brandRequestViewModel.generateBrandRequest();
                        }else{
                            Toast.makeText(getApplicationContext(), "Team id required", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Season and Source are needed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "This App Require Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });

        at.SupplierLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED)
                        || (connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                        .getState() == NetworkInfo.State.CONNECTED)) {
                    if (SeasonNum != 0 && sourceNum != 0 && BrandNum != 0) {
                        supplierRequestViewModel.setAuthorization("Bearer " + getPreferenceManager().getPrefToken());
                        supplierRequestViewModel.setSeasonId(SeasonId[SeasonNum - 1]);
                        int ss = sourceNum - 1;
                        int brandNum = BrandNum - 1;
                        supplierRequestViewModel.setSourceFlag(sourceFlag[ss]);
                        supplierRequestViewModel.setSourceId(SourceId[ss]);
                        supplierRequestViewModel.setBrandId(BrandId[brandNum]);
                        supplierRequestViewModel.setDbname(getPreferenceManager().getPrefDbname());
                        supplierRequestViewModel.generateSupplierRequest();
                    } else {
                        Toast.makeText(getApplicationContext(), "Season and Source Brand are needed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "This App Require Internet", Toast.LENGTH_SHORT).show();
                }

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if ((connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED)
                        || (connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                        .getState() == NetworkInfo.State.CONNECTED)) {
                    if (sourceNum != 0 && SeasonNum != 0 && BrandNum != 0 && SupplierNum != 0 ) {
                        if(report){
                            if(fromTO.equals("Inspection")) {
                                Intent intent = new Intent(TransactionProductUpdate.this, FinalInspectionScreen.class);

                                intent.putExtra("SeasonId", generateSeasonResponseModel.getResponse().get(SeasonNum - 1).getSeasonId().trim());
                                intent.putExtra("SourceId", generateSourceResponseModel.getResponse().get(sourceNum - 1).getSourceId().trim());
                                intent.putExtra("SupplierCode",generateSupplierResponseModel.getResponse().get(SupplierNum - 1).getSupplierCode().trim());
                                intent.putExtra("SourceFlag", generateSourceResponseModel.getResponse().get(sourceNum - 1).getSourceFlag().trim());
                                intent.putExtra("SeasonAuto", at.SeasonAuto.getText().toString());
                                intent.putExtra("SourceName", at.SourceAuto.getText().toString());
                                intent.putExtra("who","report");
                                intent.putExtra("SupplierAuto", at.SupplierAuto.getText().toString());
                                intent.putExtra("BRAND", at.BrandAuto.getText().toString().trim());
                                startActivity(intent);
                            }else{
                                Intent intent = new Intent(TransactionProductUpdate.this, FinalProductionUpdate.class);

                                intent.putExtra("SeasonId", generateSeasonResponseModel.getResponse().get(SeasonNum - 1).getSeasonId().trim());
                                intent.putExtra("SourceId", generateSourceResponseModel.getResponse().get(sourceNum - 1).getSourceId().trim());
                                intent.putExtra("SupplierCode",generateSupplierResponseModel.getResponse().get(SupplierNum - 1).getSupplierCode().trim());
                                intent.putExtra("SourceFlag", generateSourceResponseModel.getResponse().get(sourceNum - 1).getSourceFlag().trim());
                                intent.putExtra("BrandID",generateBrandResponseModel.getResponse().get(BrandNum-1).brandId.trim());
                                intent.putExtra("who","report");
                                intent.putExtra("SeasonAuto",  at.SeasonAuto.getText().toString());
                                intent.putExtra("SourceName", generateSourceResponseModel.getResponse().get(sourceNum - 1).getSourceName());
                                intent.putExtra("BRAND", at.BrandAuto.getText().toString());
                                intent.putExtra("SupplierAuto", at.SupplierAuto.getText().toString());

                                startActivity(intent);
                            }
                        }else {
                            invoiceRequestViewModel.setAuthorization("Bearer " + getPreferenceManager().getPrefToken().trim());
                            invoiceRequestViewModel.setSeasonId(generateSeasonResponseModel.getResponse().get(SeasonNum - 1).getSeasonId().trim());
                            invoiceRequestViewModel.setSourceFlag(generateSourceResponseModel.getResponse().get(sourceNum - 1).getSourceFlag().trim());
                            invoiceRequestViewModel.setSupplierCode(generateSupplierResponseModel.getResponse().get(SupplierNum - 1).getSupplierCode().trim());
                            invoiceRequestViewModel.setSourceId(generateSourceResponseModel.getResponse().get(sourceNum - 1).getSourceId().trim());
                            invoiceRequestViewModel.setBrandId(generateBrandResponseModel.getResponse().get(BrandNum - 1).getBrandId().trim());
                            invoiceRequestViewModel.setDbname(getPreferenceManager().getPrefDbname());
                            invoiceRequestViewModel.generateInvoiceRequest();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Enter all fields", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "This App Require Internet", Toast.LENGTH_SHORT).show();
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void calingApi() {

        if ((connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED)
                || (connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState() == NetworkInfo.State.CONNECTED)) {
            if (!getPreferenceManager().getPrefToken().isEmpty()) {
                sourceRequestViewModel.setDbname(getPreferenceManager().getPrefDbname());
                sourceRequestViewModel.setAuthorization("Bearer " + getPreferenceManager().getPrefToken());
                sourceRequestViewModel.setUser_id(getPreferenceManager().getPrefID());
                sourceRequestViewModel.generateSourceRequest();

                requestViewModel.setDbname(getPreferenceManager().getPrefDbname());
                requestViewModel.setAuthorization("Bearer " + getPreferenceManager().getPrefToken());
                requestViewModel.generateSourceRequest();
            }
        } else {
            Toast.makeText(getApplicationContext(), "This App Require Internet", Toast.LENGTH_SHORT).show();
        }
    }

    public void bottom() {
        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;
        TextView ses, ban;
        ImageView cross;
        bottomSheetDialog = new BottomSheetDialog(this, R.style.AppBottomSheetDialogTheme);
        bottomSheetDialog.setContentView(R.layout.bottomfortransaction_productupdate);
        recyclerView = bottomSheetDialog.findViewById(R.id.rvInvoice);
        layoutManager = new LinearLayoutManager(this);
        ses = bottomSheetDialog.findViewById(R.id.ses);
        ban = bottomSheetDialog.findViewById(R.id.ban);
        cross = bottomSheetDialog.findViewById(R.id.cross);

        try {
            Field behaviorField = bottomSheetDialog.getClass().getDeclaredField("behavior");
            behaviorField.setAccessible(true);
            final BottomSheetBehavior behavior = (BottomSheetBehavior) behaviorField.get(bottomSheetDialog);
            behavior.setState(STATE_EXPANDED);
            behavior.setPeekHeight(599, true);
            behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == STATE_EXPANDED) {
                        behavior.setState(STATE_EXPANDED);
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                }
            });

        } catch (Exception e) {

        }

        adapter = new AdapterForInvoice(getApplicationContext(), InvoiceNo, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        ses.setText(generateSeasonResponseModel.getResponse().get(SeasonNum - 1).getSeasonName());
        ban.setText(generateBrandResponseModel.getResponse().get(BrandNum - 1).getBrandName());


        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
    }

    @Override
    public void ShowErrorMessage(MessageViewType messageViewType, String errorMessage) {

    }

    @Override
    public void ShowErrorMessage(MessageViewType messageViewType, ViewType viewType, String errorMessage) {

    }

    @Override
    public void generateSourceProcessed(GenerateSourceResponseModel generateSourceResponseModel) {

        if (!generateSourceResponseModel.getResponse().isEmpty()) {
            SourceName = new String[generateSourceResponseModel.getResponse().size()];
            SourceId = new String[generateSourceResponseModel.getResponse().size()];
            sourceFlag = new String[generateSourceResponseModel.getResponse().size()];
            for (int i = 0; i < generateSourceResponseModel.getResponse().size(); i++) {
                SourceName[i] = generateSourceResponseModel.getResponse().get(i).getSourceName();
                SourceId[i] = generateSourceResponseModel.getResponse().get(i).getSourceId();
                sourceFlag[i] = generateSourceResponseModel.getResponse().get(i).getSourceFlag();
            }

            this.generateSourceResponseModel = generateSourceResponseModel;
            sourceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, SourceName);
            SourceAuto.setText(sourceAdapter.getItem(0).toString());
            SourceAuto.setAdapter(sourceAdapter);
            sourceNum = 1;
        }
    }

    @Override
    public void generateSeasonProcessed(GenerateSeasonResponseModel generateSeasonResponseModel) {

        if (!generateSeasonResponseModel.getResponse().isEmpty()) {
            SeasonName = new String[generateSeasonResponseModel.getResponse().size()];
            SeasonId = new String[generateSeasonResponseModel.getResponse().size()];
            for (int i = 0; i < generateSeasonResponseModel.getResponse().size(); i++) {
                SeasonName[i] = generateSeasonResponseModel.getResponse().get(i).getSeasonName();
                SeasonId[i] = generateSeasonResponseModel.getResponse().get(i).getSeasonId();
            }

            this.generateSeasonResponseModel = generateSeasonResponseModel;

            seasonAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, SeasonName);
            SeasonAuto.setText(seasonAdapter.getItem(0).toString());
            SeasonAuto.setAdapter(seasonAdapter);
            SeasonNum = 1;


        }
    }

    @Override
    public void generateBrandProcessed(GenerateBrandResponseModel generateBrandResponseModel) {
        if (!generateBrandResponseModel.getResponse().isEmpty()) {
            BrandId = new String[generateBrandResponseModel.getResponse().size()];
            BrandName = new String[generateBrandResponseModel.getResponse().size()];
            for (int i = 0; i < generateBrandResponseModel.getResponse().size(); i++) {
                BrandId[i] = generateBrandResponseModel.getResponse().get(i).getBrandId();
                BrandName[i] = generateBrandResponseModel.getResponse().get(i).getBrandName();
            }
            this.generateBrandResponseModel = generateBrandResponseModel;
            brandAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, BrandName);
            BrandAuto.setText(brandAdapter.getItem(0).toString());
            BrandAuto.setAdapter(brandAdapter);
            BrandNum = 1;
            BrandAuto.showDropDown();
        } else {
            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void generateSupplierProcessed(GenerateSupplierResponseModel generateSupplierResponseModel) {

        if (!generateSupplierResponseModel.getResponse().isEmpty()) {
            SupplierCode = new String[generateSupplierResponseModel.getResponse().size()];
            SupplierName = new String[generateSupplierResponseModel.getResponse().size()];
            for (int i = 0; i < generateSupplierResponseModel.getResponse().size(); i++) {
                SupplierCode[i] = generateSupplierResponseModel.getResponse().get(i).getSupplierCode();
                SupplierName[i] = generateSupplierResponseModel.getResponse().get(i).getSupplierName();
            }
            this.generateSupplierResponseModel = generateSupplierResponseModel;
            supplierAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, SupplierName);
            SupplierAuto.setText(supplierAdapter.getItem(0).toString());
            SupplierAuto.setAdapter(supplierAdapter);
            SupplierNum = 1;
            SupplierAuto.showDropDown();

        } else {
            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void generateInvoiceProcessed(GenerateInvoiceResponseModel generateInvoiceResponseModel) {

        this.generateInvoiceResponseModel = generateInvoiceResponseModel;
        InvoiceNo = new String[generateInvoiceResponseModel.getResponse().size()];
        InvoiceDate = new String[generateInvoiceResponseModel.getResponse().size()];
        for (int i = 0; i < generateInvoiceResponseModel.getResponse().size(); i++) {
            InvoiceDate[i] = generateInvoiceResponseModel.getResponse().get(i).getInvoidDate();
            InvoiceNo[i] = generateInvoiceResponseModel.getResponse().get(i).getInvoidNo();
        }
        bottom();
        bottomSheetDialog.show();

    }

    @Override
    public void generateFinalInvoiceProcessed(GenerateFinalInvoiceResponseModel generateFinalInvoiceResponseModel) {
        if (generateFinalInvoiceResponseModel.data != null) {
            bottomSheetDialog.dismiss();
            Intent intent = new Intent(TransactionProductUpdate.this, FinalProductionUpdate.class);
            intent.putExtra("invoice", generateFinalInvoiceResponseModel);
            startActivity(intent);
        }
    }

    @Override
    public void onFailure(ErrorBody errorBody, int statusCode) {

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

    @Override
    public void FinalInvoiceCall(int invoice) {
        bottomSheetDialog.dismiss();
        if (dpid != 0) {

            Intent intent = new Intent(getApplicationContext(), FinalInspectionScreen.class);
            intent.putExtra("piNo", generateInvoiceResponseModel.getResponse().get(invoice).getInvoidNo());

            intent.putExtra("perfrmaInVoiceDate", generateInvoiceResponseModel.getResponse().get(invoice).getInvoidDate());
            intent.putExtra("SourceId", Integer.valueOf(generateSourceResponseModel.getResponse().get(sourceNum - 1).getSourceId()));
            intent.putExtra("SourceFlag", generateSourceResponseModel.getResponse().get(sourceNum - 1).getSourceFlag());
            intent.putExtra("SeasonAuto", at.SeasonAuto.getText().toString());
            intent.putExtra("SupplierAuto", at.SupplierAuto.getText().toString());
            intent.putExtra("SourceName", generateSourceResponseModel.getResponse().get(sourceNum - 1).getSourceName());
            intent.putExtra("OrderStatus", "OutStanding");
            intent.putExtra("BRAND", at.BrandAuto.getText().toString());
            intent.putExtra("dpid", dpid);
            startActivity(intent);


        } else {

            Intent intent = new Intent(TransactionProductUpdate.this, FinalProductionUpdate.class);
            intent.putExtra("piNo", generateInvoiceResponseModel.getResponse().get(invoice).getInvoidNo());

            intent.putExtra("perfrmaInVoiceDate", generateInvoiceResponseModel.getResponse().get(invoice).getInvoidDate());
            intent.putExtra("SeasonAuto", at.SeasonAuto.getText().toString());
            intent.putExtra("SupplierAuto", at.SupplierAuto.getText().toString());
            intent.putExtra("SourceId", Integer.valueOf(generateSourceResponseModel.getResponse().get(sourceNum - 1).getSourceId()));
            intent.putExtra("SourceName", generateSourceResponseModel.getResponse().get(sourceNum - 1).getSourceName());
            intent.putExtra("SourceFlag", generateSourceResponseModel.getResponse().get(sourceNum - 1).getSourceFlag());
            intent.putExtra("BRAND", at.BrandAuto.getText().toString());
            intent.putExtra("OrderStatus", "OutStanding");
            startActivity(intent);

        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(TransactionProductUpdate.this, welcomeSaibhavani.class));
        finishAffinity();
    }
}