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
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.CustomerNameResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.FinalInvoiceResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.InvoiceResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.OrderStatusResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.OrderTypeResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.SeasonResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.SourceResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.SupplierResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.VendorNameResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonRequest.OrderTypeRequestModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.CustomerNameResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateBrandResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateFinalInvoiceResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateInvoiceResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateSeasonResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateSourceResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateSupplierResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.OrderStatusResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.OrderTypeResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.VendorNameResponseModel;
import com.mdq.auditinspectionapp.R;
import com.mdq.auditinspectionapp.Utils.PreferenceManager;
import com.mdq.auditinspectionapp.ViewModel.BrandRequestViewModel;
import com.mdq.auditinspectionapp.ViewModel.CustomerRequestViewModel;
import com.mdq.auditinspectionapp.ViewModel.FinalInvoiceViewModel;
import com.mdq.auditinspectionapp.ViewModel.GetInvoiceListReportViewModel;
import com.mdq.auditinspectionapp.ViewModel.InvoiceRequestViewModel;
import com.mdq.auditinspectionapp.ViewModel.OrderStatusViewModel;
import com.mdq.auditinspectionapp.ViewModel.OrderTypeViewModel;
import com.mdq.auditinspectionapp.ViewModel.SeasonRequestViewModel;
import com.mdq.auditinspectionapp.ViewModel.SourceRequestViewModel;
import com.mdq.auditinspectionapp.ViewModel.SupplierRequestViewModel;
import com.mdq.auditinspectionapp.ViewModel.VendorNameViewModel;
import com.mdq.auditinspectionapp.databinding.ActivitySelectionForReportBinding;
import com.mdq.auditinspectionapp.databinding.ActivityTransactionProductUpdateBinding;
import com.mdq.auditinspectionapp.enums.MessageViewType;
import com.mdq.auditinspectionapp.enums.ViewType;

import java.lang.reflect.Field;
import java.util.Calendar;

public class selectionForReport extends AppCompatActivity implements SourceResponseInterface, SeasonResponseInterface, BrandResponseInterface, SupplierResponseInterface, InvoiceResponseInterface, Interface_FinalInvoice, FinalInvoiceResponseInterface, OrderTypeResponseInterface, OrderStatusResponseInterface {

    TextView ProductUpdate, submit;
    BottomSheetDialog bottomSheetDialog;
    LinearLayout back;
    SourceRequestViewModel sourceRequestViewModel;
    SeasonRequestViewModel requestViewModel;
    BrandRequestViewModel brandRequestViewModel;
    SupplierRequestViewModel supplierRequestViewModel;
    GetInvoiceListReportViewModel invoiceRequestViewModel;
    FinalInvoiceViewModel finalInvoiceViewModel;
    OrderTypeViewModel orderTypeViewModel;
    OrderStatusViewModel orderStatusViewModel;
    PreferenceManager preferenceManager;
    ActivitySelectionForReportBinding at;
    AutoCompleteTextView SourceAuto, SeasonAuto, BrandAuto, OrderStatusAuto, OrderTypeAuto, newSupplierAuto;
    ArrayAdapter<String> sourceAdapter, seasonAdapter, brandAdapter, supplierAdapter, CustomerAdapter, OrderTpeAdapter, OrderStatusAdapter, newSupplierAdapter;
    String[] SourceId, SourceName, sourceFlag, SeasonName, SeasonId, BrandId, BrandName, OrderType, OrderStatus, OrderID, SupplierName, newSupplierName, newSupplierCode, InvoiceNo, InvoiceDate;
    int sourceNum, SeasonNum, BrandNum, OrderTypeNum, OrderStatusNUM, newSupplierNum;
    GenerateSeasonResponseModel generateSeasonResponseModel;
    GenerateSourceResponseModel generateSourceResponseModel;
    GenerateBrandResponseModel generateBrandResponseModel;
    GenerateSupplierResponseModel generateSupplierResponseModel;
    GenerateInvoiceResponseModel generateInvoiceResponseModel;
    CustomerNameResponseModel customerNameResponseModel;
    VendorNameResponseModel vendorNameResponseModel;
    OrderTypeResponseModel orderTypeRequestModel;
    OrderStatusResponseModel orderStatusRequestModel;
    AdapterForInvoice adapter;
    ConnectivityManager connectivityManager;
    int dpid;
    String from;
    String fromTO;
    boolean report = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        at = ActivitySelectionForReportBinding.inflate(getLayoutInflater());
        setContentView(at.getRoot());

        connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        ProductUpdate = findViewById(R.id.textProductupdate);
        submit = findViewById(R.id.submit);
        back = findViewById(R.id.linearBack);
        SourceAuto = findViewById(R.id.SourceAuto);
        SeasonAuto = findViewById(R.id.SeasonAuto);
        BrandAuto = findViewById(R.id.BrandAuto);
        newSupplierAuto = findViewById(R.id.newSupplierAuto);
//        CustomerAuto = findViewById(R.id.CustomerAuto);
        OrderStatusAuto = findViewById(R.id.OrderStatusAuto);
        OrderTypeAuto = findViewById(R.id.OrderTypeAuto);
//        SupplierAuto = findViewById(R.id.SupplierAuto);
        ProductUpdate.setPaintFlags(ProductUpdate.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        sourceRequestViewModel = new SourceRequestViewModel(getApplicationContext(), this);
        requestViewModel = new SeasonRequestViewModel(getApplicationContext(), this);
        brandRequestViewModel = new BrandRequestViewModel(getApplicationContext(), this);
        supplierRequestViewModel = new SupplierRequestViewModel(getApplicationContext(), this);
        invoiceRequestViewModel = new GetInvoiceListReportViewModel(getApplicationContext(), this);
        finalInvoiceViewModel = new FinalInvoiceViewModel(getApplicationContext(), this);

        orderTypeViewModel = new OrderTypeViewModel(getApplicationContext(), this);
        orderTypeViewModel.setAuthorization("Bearer " + getPreferenceManager().getPrefToken());
        orderTypeViewModel.setDbname(getPreferenceManager().getPrefDbname());
        orderTypeViewModel.generateOrderTypeRequest();

        orderStatusViewModel = new OrderStatusViewModel(getApplicationContext(), this);
        orderStatusViewModel.setAuthorization("Bearer " + getPreferenceManager().getPrefToken());
        orderStatusViewModel.setDbname(getPreferenceManager().getPrefDbname());
        orderStatusViewModel.generateOrderStatusRequest();

        Intent intent = getIntent();
        dpid = intent.getIntExtra("dpid", 0);
        from = intent.getStringExtra("ffrom");
        fromTO = intent.getStringExtra("FromTo");
        if (from != null) {
            if (from.equals("report")) {
                report = true;
            }
        }

        if (dpid != 0) {
            at.textProductupdate.setText("INSPECTION");
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
        at.ORDERStatusLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderStatusAuto.showDropDown();
            }
        });
        at.ORDERTypeLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderTypeAuto.showDropDown();
            }
        });
        at.SourceLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SourceAuto.showDropDown();
            }
        });
        at.newSupplierLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newSupplierAuto.showDropDown();
            }
        });
        SourceAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sourceNum = position + 1;
            }
        });
        newSupplierAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                newSupplierNum = position + 1;
            }
        });
        SeasonAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SeasonNum = position + 1;
            }
        });

        OrderTypeAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OrderTypeNum = position + 1;
            }
        });
        OrderStatusAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OrderStatusNUM = position + 1;
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
                if ((connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED) || (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)) {
                    if (SeasonNum != 0 && sourceNum != 0) {
                        if (getPreferenceManager().getPrefTeamId() != null) {
                            brandRequestViewModel.setAuthorization("Bearer " + getPreferenceManager().getPrefToken());
                            brandRequestViewModel.setSeasonId(SeasonId[SeasonNum - 1]);
                            int ss = sourceNum - 1;
                            brandRequestViewModel.setSourceFlag(sourceFlag[ss]);
                            brandRequestViewModel.setSourceId(SourceId[ss]);
                            brandRequestViewModel.setDbname(getPreferenceManager().getPrefDbname());
                            brandRequestViewModel.setTeamId(getPreferenceManager().getPrefTeamId());
                            brandRequestViewModel.generateBrandRequest();
                        } else {
                            Toast.makeText(getApplicationContext(), "Team id required.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Season and Source are needed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "This App Require Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });

        at.newSupplierLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED) || (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)) {
                    if (SeasonNum != 0 && sourceNum != 0 && BrandNum != 0) {
                        supplierRequestViewModel.setAuthorization("Bearer " + getPreferenceManager().getPrefToken());
                        supplierRequestViewModel.setSeasonId(SeasonId[SeasonNum - 1]);
                        int ss = sourceNum - 1;
                        supplierRequestViewModel.setSourceFlag(sourceFlag[ss]);
                        supplierRequestViewModel.setSourceId(SourceId[ss]);
                        supplierRequestViewModel.setBrandId(BrandId[BrandNum - 1]);
                        supplierRequestViewModel.setDbname(getPreferenceManager().getPrefDbname());
                        supplierRequestViewModel.generateSupplierRequest();
                    } else {
                        Toast.makeText(getApplicationContext(), "Season , Source and Brand are needed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "This App Require Internet", Toast.LENGTH_SHORT).show();
                }

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if ((connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED) || (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)) {
                    if (sourceNum != 0 && SeasonNum != 0 && BrandNum != 0 ) {
                        {
                            invoiceRequestViewModel.setAuthorization("Bearer " + getPreferenceManager().getPrefToken().trim());
                            invoiceRequestViewModel.setSeasonId(generateSeasonResponseModel.getResponse().get(SeasonNum - 1).getSeasonId().trim());
                            invoiceRequestViewModel.setSourceFlag(generateSourceResponseModel.getResponse().get(sourceNum - 1).getSourceFlag().trim());
                            invoiceRequestViewModel.setSupplierCode(generateSupplierResponseModel.getResponse().get(newSupplierNum - 1).getSupplierCode().trim());
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

        if ((connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED) || (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)) {
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
            newSupplierCode = new String[generateSupplierResponseModel.getResponse().size()];
            newSupplierName = new String[generateSupplierResponseModel.getResponse().size()];
            for (int i = 0; i < generateSupplierResponseModel.getResponse().size(); i++) {
                newSupplierCode[i] = generateSupplierResponseModel.getResponse().get(i).getSupplierCode();
                newSupplierName[i] = generateSupplierResponseModel.getResponse().get(i).getSupplierName();
            }
            this.generateSupplierResponseModel = generateSupplierResponseModel;
            newSupplierAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, newSupplierName);
            newSupplierAuto.setText(newSupplierAdapter.getItem(0).toString());
            newSupplierAuto.setAdapter(newSupplierAdapter);
            newSupplierNum = 1;
            newSupplierAuto.showDropDown();

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
            Intent intent = new Intent(selectionForReport.this, FinalProductionUpdate.class);
            intent.putExtra("invoice", generateFinalInvoiceResponseModel);
            startActivity(intent);
        }
    }

    @Override
    public void generateOrderTypeProcessed(OrderTypeResponseModel orderTypeResponseModel) {
        if (!orderTypeResponseModel.getData().isEmpty()) {
            OrderID = new String[orderTypeResponseModel.getData().size()];
            OrderType = new String[orderTypeResponseModel.getData().size()];
            for (int i = 0; i < orderTypeResponseModel.getData().size(); i++) {
                OrderID[i] = orderTypeResponseModel.getData().get(i).getOrderId();
                OrderType[i] = orderTypeResponseModel.getData().get(i).getOrderType();
            }
            this.orderTypeRequestModel = orderTypeResponseModel;
            OrderTpeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, OrderType);
            OrderTypeAuto.setAdapter(OrderTpeAdapter);

        } else {
            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void generateOrderStatusProcessed(OrderStatusResponseModel orderStatusResponseModel) {
        if (!orderStatusResponseModel.getData().isEmpty()) {
            OrderStatus = new String[orderStatusResponseModel.getData().size()];
            for (int i = 0; i < orderStatusResponseModel.getData().size(); i++) {
                OrderStatus[i] = orderStatusResponseModel.getData().get(i).getOrderStatus();
            }
            this.orderStatusRequestModel = orderStatusResponseModel;
            OrderStatusAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, OrderStatus);
            OrderStatusAuto.setAdapter(OrderStatusAdapter);

        } else {
            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();
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
            Intent intent = new Intent(getApplicationContext(), FinalReportScreen.class);
            intent.putExtra("orderType", orderTypeRequestModel.getData().get(OrderTypeNum - 1).getOrderId());
            intent.putExtra("Seasonname", generateSeasonResponseModel.getResponse().get(SeasonNum - 1).getSeasonId());
            intent.putExtra("piNo", generateInvoiceResponseModel.getResponse().get(invoice).getInvoidNo());
            intent.putExtra("SourceName", generateSourceResponseModel.getResponse().get(sourceNum - 1).getSourceName());
            intent.putExtra("OrderStatus", at.OrderStatusAuto.getText().toString().trim());
            intent.putExtra("BRAND", generateBrandResponseModel.getResponse().get(BrandNum - 1).brandId);
            intent.putExtra("who", "inspection");
            startActivity(intent);
        } else {
            Intent intent = new Intent(selectionForReport.this, FinalReportScreen.class);
            intent.putExtra("orderType", orderTypeRequestModel.getData().get(OrderTypeNum - 1).getOrderId());

            intent.putExtra("Seasonname", generateSeasonResponseModel.getResponse().get(SeasonNum - 1).getSeasonId());
            intent.putExtra("piNo", generateInvoiceResponseModel.getResponse().get(invoice).getInvoidNo());
            intent.putExtra("SourceName", generateSourceResponseModel.getResponse().get(sourceNum - 1).getSourceName());
            intent.putExtra("OrderStatus", at.OrderStatusAuto.getText().toString().trim());
            intent.putExtra("BRAND", generateBrandResponseModel.getResponse().get(BrandNum - 1).brandId);
            intent.putExtra("who", "production");
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(selectionForReport.this, welcomeSaibhavani.class));
        finishAffinity();
    }
}