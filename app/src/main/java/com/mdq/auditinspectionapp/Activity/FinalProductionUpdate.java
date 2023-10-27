package com.mdq.auditinspectionapp.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.FinalInvoiceResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.GetProductionResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.ShipModeResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.UpdateProductionResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateFinalInvoiceResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateShipModeResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateUpdateProductionResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GetProductionReportResponseModel;
import com.mdq.auditinspectionapp.R;
import com.mdq.auditinspectionapp.Utils.PreferenceManager;
import com.mdq.auditinspectionapp.ViewModel.FinalInvoiceViewModel;
import com.mdq.auditinspectionapp.ViewModel.GetProductionReportViewModel;
import com.mdq.auditinspectionapp.ViewModel.ShipModeRequestViewModel;
import com.mdq.auditinspectionapp.ViewModel.UpdateProductionRequestViewModel;
import com.mdq.auditinspectionapp.databinding.ActivityFinalInspectionUpdateBinding;
import com.mdq.auditinspectionapp.databinding.ActivityFinalProductionScreenBinding;
import com.mdq.auditinspectionapp.enums.MessageViewType;
import com.mdq.auditinspectionapp.enums.ViewType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Locale;

public class FinalProductionUpdate extends AppCompatActivity implements FinalInvoiceResponseInterface, ShipModeResponseInterface, UpdateProductionResponseInterface, GetProductionResponseInterface {
    LinearLayout back;
    ImageView datepicker;
    TextView textView, prev, next;
    GenerateFinalInvoiceResponseModel generateFinalInvoiceRequestModel;
    ActivityFinalProductionScreenBinding ap;
    FinalInvoiceViewModel finalInvoiceViewModel;
    GenerateFinalInvoiceResponseModel generateFinalInvoiceResponseModel;
    String piNo;
    String  orderStatus, SourceFlag, SourceName, SeasonAuto;
    int SourceId, getId = 0, totalgetid = 0;
    ArrayAdapter<String> arrayAdapter;
    String[] ShipModeArray;
    int ShipId = 0;
    ShipModeRequestViewModel shipModeRequestViewModel;
    UpdateProductionRequestViewModel updateProductionRequestViewModel;
    GenerateShipModeResponseModel generateShipModeResponseModel;
    int bachi = 0;
    PreferenceManager preferenceManager;
    DatePickerDialog datePickerDialog;
    String who;
    String RSeasonID, RSourceId, RSourceFlag, RBrandId, RFrom, RTo, RSupplierCode;
    GetProductionReportViewModel getProductionReportViewModel;
    GetProductionReportResponseModel getProductionReportResponseModel;
    boolean report = true;
    String BRAND, SupplierAuto,perfrmaInVoiceDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ap = ActivityFinalProductionScreenBinding.inflate(getLayoutInflater());
        setContentView(ap.getRoot());
        updateProductionRequestViewModel = new UpdateProductionRequestViewModel(getApplicationContext(), this);
        finalInvoiceViewModel = new FinalInvoiceViewModel(getApplicationContext(), this);
        getProductionReportViewModel = new GetProductionReportViewModel(this, this);
        getProductionReportResponseModel = new GetProductionReportResponseModel();

        back = findViewById(R.id.linearBack);
        datepicker = findViewById(R.id.date_picker);
        textView = findViewById(R.id.datetext);
        prev = findViewById(R.id.PREV);
        next = findViewById(R.id.NEXT);

        Intent intent = getIntent();
        who = intent.getStringExtra("who");

        piNo = intent.getStringExtra("piNo");

        SourceId = intent.getIntExtra("SourceId", 0);
        SourceFlag = intent.getStringExtra("SourceFlag");
        orderStatus = intent.getStringExtra("OrderStatus");
        SeasonAuto = intent.getStringExtra("SeasonAuto");
        SourceName = intent.getStringExtra("SourceName");
        BRAND = intent.getStringExtra("BRAND");
        SupplierAuto = intent.getStringExtra("SupplierAuto");
        perfrmaInVoiceDate = intent.getStringExtra("perfrmaInVoiceDate");
        ap.piDate.setText(perfrmaInVoiceDate);


        ap.Season.setText(SeasonAuto);
        ap.Buyer.setText(SourceName);
        ap.Brand.setText(BRAND);
        ap.vendors.setText(SupplierAuto);
        report = true;
        shipModeRequestViewModel = new ShipModeRequestViewModel(getApplicationContext(), this);
        shipModeRequestViewModel.setAuth("Bearer " + getPreferenceManager().getPrefToken());
        shipModeRequestViewModel.setDbname(getPreferenceManager().getPrefDbname());
        shipModeRequestViewModel.generateShipModeRequest();

        if (!piNo.isEmpty()  && !SourceFlag.isEmpty() && SourceId != 0 && !orderStatus.isEmpty()) {
            finainvoice();
        }

        shipModeRequestViewModel = new ShipModeRequestViewModel(getApplicationContext(), this);
        shipModeRequestViewModel.setAuth("Bearer " + getPreferenceManager().getPrefToken());
        shipModeRequestViewModel.setDbname(getPreferenceManager().getPrefDbname());
        shipModeRequestViewModel.generateShipModeRequest();

        ap.names.setText("Welcome " + getPreferenceManager().getPrefUsername());

        ap.dispatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (report) {
                    ap.dispatch.showDropDown();
                }
            }
        });

        ap.dispatch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShipId = position+1;
            }
        });

        generateFinalInvoiceResponseModel = new GenerateFinalInvoiceResponseModel();
        //making status bar color as transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        setClick();

        if (getId == 0) {
            ap.PREV.setClickable(false);
        } else {
            ap.PREV.setClickable(true);
        }

        if (generateFinalInvoiceResponseModel.getResponse() != null) {
            if (getId == generateFinalInvoiceResponseModel.getResponse().size()) {
                ap.NEXT.setClickable(false);
            } else {
                ap.NEXT.setClickable(true);
            }
        }


        ap.PREV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getId = getId - 1;
                FinalInvoice(generateFinalInvoiceResponseModel, 2);
                diable();
            }
        });

        ap.NEXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getId = getId + 1;
                FinalInvoice(generateFinalInvoiceResponseModel, 1);
                diable();
            }
        });

        ap.FORECASTDELDATE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (report) {
                    Calendar calendar = Calendar.getInstance();
                    int years = calendar.get(Calendar.YEAR);
                    int months = calendar.get(Calendar.MONTH);
                    int days = calendar.get(Calendar.DAY_OF_MONTH);

                    datePickerDialog = new DatePickerDialog(FinalProductionUpdate.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            month = month + 1;
                            String mm = String.valueOf(month);
                            String dd = String.valueOf(dayOfMonth);
                            if (mm.length() == 1) {
                                mm = "0" + mm;
                            }
                            if (dd.length() == 1) {
                                dd = "0" + dd;
                            }
                            String dates = year + "-" + mm + "-" + dd;
                            ap.FORECASTDELDATE.setText(dates);
                            time();
                        }

                    }, years, months, days);
                    int positiveColor = ContextCompat.getColor(FinalProductionUpdate.this, R.color.black);
                    int negativeColor = ContextCompat.getColor(FinalProductionUpdate.this, R.color.black);

                    datePickerDialog.show();
                    datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(positiveColor);
                    datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(negativeColor);

                }
            }
        });

        ap.SAVE.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String dates = ap.FORECASTDELDATE.getText().toString();
                if (dates.length() > 10) {

                } else {
                    dates = dates + "T00:00:00";
                }
                System.out.println("isValid - yyyy-MM-dd with 2017-18--15 = " + isValidFormat("yyyy-MM-dd", dates, Locale.ENGLISH));
                if (!dates.isEmpty()) {
                    if (!dates.contains("NO DATA")) {
                        if (!dates.equals("T00:00:00")) {
                            ap.Progress.setVisibility(View.VISIBLE);
                            updateProductionRequestViewModel.custOrderNo = generateFinalInvoiceResponseModel.getResponse().get(getId).getCustOrderNo().trim();
                            updateProductionRequestViewModel.dispatchModeId = Integer.parseInt(generateShipModeResponseModel.getResponse().get(ShipId-1).getModeId().trim());
                            updateProductionRequestViewModel.foreCastDelDate = dates.trim();
                            updateProductionRequestViewModel.pgmCode = generateFinalInvoiceResponseModel.getResponse().get(getId).getPgmCode().trim();
                            updateProductionRequestViewModel.remarks = ap.REMARKS.getText().toString().trim();
                            updateProductionRequestViewModel.sourceFlag = generateFinalInvoiceResponseModel.getResponse().get(getId).getSourceFlag().trim();
                            updateProductionRequestViewModel.sourceId = generateFinalInvoiceResponseModel.getResponse().get(getId).getSourceId();
                            updateProductionRequestViewModel.styleId = Integer.parseInt(generateFinalInvoiceResponseModel.getResponse().get(getId).getStyleId().trim());
                            updateProductionRequestViewModel.systemOrderNo = generateFinalInvoiceResponseModel.getResponse().get(getId).getSysOrderNo().trim();
                            updateProductionRequestViewModel.Auth = "Bearer " + getPreferenceManager().getPrefToken().trim();
                            updateProductionRequestViewModel.dbname = getPreferenceManager().getPrefDbname();
                            updateProductionRequestViewModel.generateUpdateProductionRequest();
                        } else {
                            Toast.makeText(getApplicationContext(), "select date properly", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "select date properly", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "select date", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void finainvoice() {
        finalInvoiceViewModel.setAuth("Bearer " + getPreferenceManager().getPrefToken());
        finalInvoiceViewModel.setPiNo(piNo.trim());

        finalInvoiceViewModel.setDbname(getPreferenceManager().getPrefDbname());
        finalInvoiceViewModel.setSourceFlag(SourceFlag.trim());
        finalInvoiceViewModel.setSourceId(SourceId);
        finalInvoiceViewModel.setOrderStatus(orderStatus.trim());
        finalInvoiceViewModel.generateFinalVoiceRequest();
        ap.piNo.setText(piNo.trim());
    }

    private void time() {
        datePickerDialog.dismiss();
        String date = ap.FORECASTDELDATE.getText().toString();
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);
        int sec = calendar.get(Calendar.SECOND);
        TimePickerDialog timePickerDialog = new TimePickerDialog(FinalProductionUpdate.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String hh, mm;
                hh = String.valueOf(hourOfDay);
                mm = String.valueOf(minute);
                String secs = String.valueOf(sec);
                if (hh.length() == 1) {
                    hh = "0" + hh;
                }
                if (mm.length() == 1) {
                    mm = "0" + mm;
                }
                if (secs.length() == 1) {
                    secs = "0" + secs;
                }
                String times = hh + ":" + mm + ":" + secs;
                ap.FORECASTDELDATE.setText(date + "T" + times);
            }
        }, hour, min, false);

        int positiveColor = ContextCompat.getColor(FinalProductionUpdate.this, R.color.black);
        int negativeColor = ContextCompat.getColor(FinalProductionUpdate.this, R.color.black);

        timePickerDialog.show();

        timePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(positiveColor);
        timePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(negativeColor);

    }

    private void diable() {

        if (getId == 0) {
            prev.setEnabled(false);
        } else {
            prev.setEnabled(true);
        }

        if (getId == totalgetid - 1) {
            next.setEnabled(false);
        } else {
            next.setEnabled(true);
        }
    }

    private void setClick() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int years = calendar.get(Calendar.YEAR);
                int months = calendar.get(Calendar.MONTH);
                int days = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(FinalProductionUpdate.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String mm = String.valueOf(month);
                        String dd = String.valueOf(dayOfMonth);
                        if (mm.length() == 1) {
                            mm = "0" + mm;
                        }
                        if (dd.length() == 1) {
                            dd = "0" + dd;
                        }
                        String dates = year + "-" + mm + "-" + dd;
                        textView.setText(dates);
                    }
                }, years, months, days);
                int positiveColor = ContextCompat.getColor(FinalProductionUpdate.this, R.color.black);
                int negativeColor = ContextCompat.getColor(FinalProductionUpdate.this, R.color.black);

                datePickerDialog.show();
                datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(positiveColor);
                datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(negativeColor);
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
    public void generateFinalInvoiceProcessed(GenerateFinalInvoiceResponseModel generateFinalInvoiceResponseModel) {

        ap.PREV.setClickable(true);
        ap.NEXT.setClickable(true);
        if (generateFinalInvoiceResponseModel != null) {
            ap.Progress.setVisibility(View.INVISIBLE);
            totalgetid = generateFinalInvoiceResponseModel.getResponse().size();
            FinalInvoice(generateFinalInvoiceResponseModel, 0);
            this.generateFinalInvoiceResponseModel = generateFinalInvoiceResponseModel;
        }
    }

    private void FinalInvoice(GenerateFinalInvoiceResponseModel generateFinalInvoiceResponseModel, int i) {

        if (i == 1) {
            Log.i("i", "" + i);
            ap.NEXT.setClickable(true);
            if (ap.NEXT.isClickable() == true) {
                Log.i("MyButton", "Clickable is true");
            }

        } else if (i == 2) {
            Log.i("i", "" + i);
            ap.PREV.setClickable(true);
        }

        try {
            if (!generateFinalInvoiceResponseModel.getResponse().isEmpty()) {
                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getStyleCode() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getStyleCode().isEmpty()) {
                    ap.STYLENAME.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getStyleCode().trim());
                    if(generateFinalInvoiceResponseModel.getResponse().get(getId).getStyleName() != null && generateFinalInvoiceResponseModel.getResponse().get(getId).getStyleName().trim()!=null){
                        Log.i("finalinspection","entered");
                        ap.STYLENAME.setText(ap.STYLENAME.getText()+"-"+generateFinalInvoiceResponseModel.getResponse().get(getId).getStyleName().trim());
                    }
                }else{
                    ap.STYLENAME.setText("-");
                }
                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getBuyerPo() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getBuyerPo().isEmpty()) {
                    ap.BUYER.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getBuyerPo().trim());
                } else {
                    ap.BUYER.setText("-");
                }
                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getDeliveryTerms() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getDeliveryTerms().isEmpty()) {
                    ap.DeleveryFac.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getDeliveryTerms().trim());
                } else {
                    ap.DeleveryFac.setText("-");
                }
                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getOrderQty() != null &&  !generateFinalInvoiceResponseModel.getResponse().get(getId).getOrderQty().isEmpty()) {

                    ap.ORDERQTY.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getOrderQty().trim());
                } else {
                    ap.ORDERQTY.setText("-");
                }
                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getBalance() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getBalance().isEmpty()) {

                    ap.BALANCE.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getBalance().trim());
                } else {
                    ap.BALANCE.setText("-");
                }
                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getVendorDelDate() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getVendorDelDate().isEmpty()) {

                    ap.VENDORDELDATE.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getVendorDelDate().trim());
                } else {
                    ap.VENDORDELDATE.setText("-");
                }

                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getInspection_Remarks() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getInspection_Remarks().isEmpty()) {
                    ap.inspectionREmarks.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getInspection_Remarks().trim());
                } else {
                    ap.inspectionREmarks.setText("-");
                }

                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getSupplierName() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getSupplierName().isEmpty()) {

                    ap.vendors.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getSupplierName().trim());
                } else {
                    ap.vendors.setText("-");
                }

                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getForecastDelDate() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getForecastDelDate().isEmpty()) {

                    ap.FORECASTDELDATE.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getForecastDelDate().trim());

                } else {
                    ap.FORECASTDELDATE.setText("-");
                }

                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getCityName() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getCityName().isEmpty()) {

                    ap.CITY.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getCityName().trim());
                } else {
                    ap.CITY.setText("-");
                }
                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getDespatchModeId() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getDespatchModeId().isEmpty()) {
                    int id = Integer.parseInt(generateFinalInvoiceResponseModel.getResponse().get(getId).getDespatchModeId().trim());
                    shipment(id);
                } else {
                    ap.dispatch.setText("-");
                }
                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getProductionRemarks() != null &&  !generateFinalInvoiceResponseModel.getResponse().get(getId).getProductionRemarks().isEmpty()) {
                    ap.REMARKS.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getProductionRemarks().trim());
                } else {
                    ap.REMARKS.setText("-");
                }
                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getInspectionDate() != null &&  !generateFinalInvoiceResponseModel.getResponse().get(getId).getInspectionDate().isEmpty()) {
                    ap.datetext.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getInspectionDate().trim());
                } else {
                    ap.datetext.setText("-");
                }
                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getQcBy() != null &&!generateFinalInvoiceResponseModel.getResponse().get(getId).getQcBy().isEmpty()) {

                    ap.QC.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getQcBy().trim());
                } else {
                    ap.QC.setText("-");
                }
                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getQcRemarks() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getQcRemarks().isEmpty()) {

                    ap.QCREMARKS.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getQcRemarks().trim());
                } else {
                    ap.QCREMARKS.setText("-");
                }
            } else {
                logout();
            }
        } catch (Exception e) {

        }
    }

    private void logout() {
        bachi = 1;
        Dialog dialoglogout = new Dialog(this, R.style.dialog_center);
        dialoglogout.setContentView(R.layout.logout);
        dialoglogout.setCanceledOnTouchOutside(true);
        dialoglogout.show();
        TextView textView23 = dialoglogout.findViewById(R.id.textView23);

        DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent KEvent) {
                int keyaction = KEvent.getAction();

                if (keyaction == KeyEvent.ACTION_DOWN) {
                    Intent intent = new Intent(getApplicationContext(), TransactionProductUpdate.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                return false;
            }
        };
        dialoglogout.setOnKeyListener(keylistener);

        textView23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialoglogout.dismiss();
                Intent intent = new Intent(getApplicationContext(), TransactionProductUpdate.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void generateShipModeProcessed(GenerateShipModeResponseModel generateShipModeResponceModel) {
        if (generateShipModeResponceModel != null) {
            this.generateShipModeResponseModel = generateShipModeResponceModel;
            ShipModeArray = new String[generateShipModeResponceModel.getResponse().size()];
            for (int i = 0; i < generateShipModeResponceModel.getResponse().size(); i++) {
                    ShipModeArray[i] = generateShipModeResponceModel.getResponse().get(i).getModeName();
            }
            shipment(0);
        }
    }

    private void shipment(int id) {
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, ShipModeArray);
        if (id != 0) {
            ShipId = id;
            ap.dispatch.setText(generateShipModeResponseModel.getResponse().get(id-1).getModeName());
        }
        ap.dispatch.setAdapter(arrayAdapter);
        ap.dispatch.dismissDropDown();
    }

    @Override
    public void generateUpdateProductionProcessed(GenerateUpdateProductionResponseModel generateUpdateProductionResponseModel) {
        Toast.makeText(getApplicationContext(), "" + generateUpdateProductionResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
        if (generateUpdateProductionResponseModel.getMessage().equals("time out")) {
            timeout();
        } else {
            finainvoice();
        }
    }

    public void timeout() {
        Dialog dialoglogout = new Dialog(this, R.style.dialog_center);
        dialoglogout.setContentView(R.layout.time_out);
        dialoglogout.setCanceledOnTouchOutside(true);
        dialoglogout.show();
        TextView textView23 = dialoglogout.findViewById(R.id.textView23);

        textView23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialoglogout.dismiss();
            }
        });
    }

    @Override
    public void getProductionReportCall(GetProductionReportResponseModel getProductionReportResponseModel) {

        if (getProductionReportResponseModel.getMessage().equals("Record found")) {
            this.getProductionReportResponseModel = getProductionReportResponseModel;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean isValidFormat(String format, String value, Locale locale) {
        LocalDateTime ldt = null;
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format, locale);

        try {
            ldt = LocalDateTime.parse(value, fomatter);
            String result = ldt.format(fomatter);
            return result.equals(value);
        } catch (DateTimeParseException e) {
            try {
                LocalDate ld = LocalDate.parse(value, fomatter);
                String result = ld.format(fomatter);
                return result.equals(value);
            } catch (DateTimeParseException exp) {
                try {
                    LocalTime lt = LocalTime.parse(value, fomatter);
                    String result = lt.format(fomatter);
                    return result.equals(value);
                } catch (DateTimeParseException e2) {
                    // Debugging purposes
                }
            }
        }
        return false;
    }
}