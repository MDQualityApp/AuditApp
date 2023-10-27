package com.mdq.auditinspectionapp.Activity;

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
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.GetInspectionReportResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.QCNameResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.QCResultResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.ShipModeResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.UpdateInspectionResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateFinalInvoiceResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateQCNameResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateQCResultResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateShipModeResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateUpdateInspectionResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GetInspectionReportResponseModel;
import com.mdq.auditinspectionapp.R;
import com.mdq.auditinspectionapp.Utils.ApiClass;
import com.mdq.auditinspectionapp.Utils.PreferenceManager;
import com.mdq.auditinspectionapp.ViewModel.FinalInvoiceViewModel;
import com.mdq.auditinspectionapp.ViewModel.GetInspectionViewModel;
import com.mdq.auditinspectionapp.ViewModel.QCNameRequestViewModel;
import com.mdq.auditinspectionapp.ViewModel.QCResultRequestViewModel;
import com.mdq.auditinspectionapp.ViewModel.ShipModeRequestViewModel;
import com.mdq.auditinspectionapp.ViewModel.UpdateInspectionRequestViewModel;
import com.mdq.auditinspectionapp.databinding.ActivityFinalInspectionUpdateBinding;
import com.mdq.auditinspectionapp.databinding.ActivityFinalProductionScreenBinding;
import com.mdq.auditinspectionapp.enums.MessageViewType;
import com.mdq.auditinspectionapp.enums.ViewType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.function.LongFunction;

public class FinalInspectionScreen extends AppCompatActivity implements FinalInvoiceResponseInterface, QCNameResponseInterface, QCResultResponseInterface, UpdateInspectionResponseInterface, ShipModeResponseInterface, GetInspectionReportResponseInterface
{
    LinearLayout back;
    ImageView datepicker;
    TextView textView, prev, next;
    ActivityFinalInspectionUpdateBinding ap;
    FinalInvoiceViewModel finalInvoiceViewModel;
    GenerateFinalInvoiceResponseModel generateFinalInvoiceResponseModel;
    String piNo;
    ShipModeRequestViewModel shipModeRequestViewModel;
    String orderStatus, SourceFlag, SeasonAuto, SourceName;
    int SourceId, getId = 0, totalgetid = 0;
    ArrayAdapter<String> arrayAdapterQCResult, ArrayAdapterQCName;
    String[] QCResultArray, QCNameArray;
    int QCResultId = 0, QCNameId = 0;
    int dpid = 0;
    GenerateShipModeResponseModel generateShipModeResponseModel;
    QCResultRequestViewModel qcResultRequestViewModel;
    QCNameRequestViewModel qcNameRequestViewModel;
    UpdateInspectionRequestViewModel updateInspectionRequestViewModel;
    GenerateQCNameResponseModel generateQCNameResponseModel;
    GenerateQCNameResponseModel generateQCNameResponseModel2;
    GenerateQCResultResponseModel generateQCResultResponseModel;
    List<String> customerorderno = new ArrayList<>();
    PreferenceManager preferenceManager;
    String who, BRAND;
    String RSeasonID, RSourceId, RSourceFlag, RBrandId, RFrom, RTo, RSupplierCode;
    GetInspectionViewModel getInspectionViewModel;
    GetInspectionReportResponseModel getInspectionReportResponseModel;
    boolean InReport = true;
    String SupplierAuto;
    String perfrmaInVoiceDate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ap = ActivityFinalInspectionUpdateBinding.inflate(getLayoutInflater());
        setContentView(ap.getRoot());
        getInspectionViewModel = new GetInspectionViewModel(this, this);
        updateInspectionRequestViewModel = new UpdateInspectionRequestViewModel(getApplicationContext(), this);
        finalInvoiceViewModel = new FinalInvoiceViewModel(getApplicationContext(), this);
        back = findViewById(R.id.linearBack);
        datepicker = findViewById(R.id.date_picker);
        textView = findViewById(R.id.datetext);
        getInspectionReportResponseModel = new GetInspectionReportResponseModel();
        prev = findViewById(R.id.PREV);
        next = findViewById(R.id.NEXT);

        shipModeRequestViewModel = new ShipModeRequestViewModel(getApplicationContext(), this);
        shipModeRequestViewModel.setAuth("Bearer " + getPreferenceManager().getPrefToken());
        shipModeRequestViewModel.setDbname(getPreferenceManager().getPrefDbname());
        shipModeRequestViewModel.generateShipModeRequest();

        Intent intent = getIntent();
        who = intent.getStringExtra("who");
        dpid = intent.getIntExtra("dpid", 0);
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
        ap.vendor.setText(SupplierAuto);

        if (!piNo.isEmpty() && !SourceFlag.isEmpty() && SourceId != 0 && !orderStatus.isEmpty())
        {
            finalInvoice();
        }

        ap.names.setText("Welcome " + getPreferenceManager().getPrefUsername());
        ApiClass apiClass = new ApiClass();
        qcNameRequestViewModel = new QCNameRequestViewModel(getApplicationContext(), this);
        qcResultRequestViewModel = new QCResultRequestViewModel(getApplicationContext(), this);

        if (getPreferenceManager().getPrefDpid() != 0)
        {

            qcNameRequestViewModel.setAuth("Bearer " + getPreferenceManager().getPrefToken().trim());
            qcNameRequestViewModel.setDbname(getPreferenceManager().getPrefDbname());
            qcNameRequestViewModel.setSourceFlag(SourceFlag);
            qcNameRequestViewModel.setSourceId(String.valueOf(SourceId));
            qcNameRequestViewModel.setDbname(getPreferenceManager().getPrefDbname());
            Log.i("wded", "" + apiClass.QCNAME);
            qcResultRequestViewModel.setAuth("Bearer " + getPreferenceManager().getPrefToken().trim());
            qcResultRequestViewModel.setDbname(getPreferenceManager().getPrefDbname());
            qcNameRequestViewModel.generateQCNameRequest();
            qcResultRequestViewModel.generateQCResultRequest();
        }

        ap.QCNAMES.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ap.QCNAMES.showDropDown();
            }
        });

        ap.QCRESULT.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (InReport)
                {
                    ap.QCRESULT.showDropDown();
                }
            }
        });

        ap.QCRESULT.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                QCResultId = position;
            }
        });
        ap.QCNAMES.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                QCNameId = position;
            }
        });

        generateFinalInvoiceResponseModel = new GenerateFinalInvoiceResponseModel();
        //making status bar color as transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        setClick();

        if (getId == 0)
        {
            ap.PREV.setClickable(false);
        } else
        {
            ap.PREV.setClickable(true);
        }


        if (generateFinalInvoiceResponseModel.getResponse() != null)
        {
            if (getId == generateFinalInvoiceResponseModel.getResponse().size())
            {
                ap.NEXT.setClickable(false);
            } else
            {
                ap.NEXT.setClickable(true);
            }
        }


        ap.PREV.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getId = getId - 1;
                FinalInvoice(generateFinalInvoiceResponseModel, 2);
                diable();
            }
        });

        ap.NEXT.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getId = getId + 1;
                FinalInvoice(generateFinalInvoiceResponseModel, 1);
                diable();

            }
        });

        ap.SAVE.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (InReport)
                {
                    customerorderno.clear();
                    String dates = ap.datetext.getText().toString();
                    if (dates.length() > 10)
                    {

                    } else
                    {
                        dates = dates + "T00:00:00";
                    }
                    if (!dates.isEmpty())
                    {
                        if (!dates.contains("NO DATA"))
                        {
                            if (!dates.equals("T00:00:00"))
                            {
                                try
                                {
                                    customerorderno.add(generateFinalInvoiceResponseModel.getResponse().get(getId).getCustOrderNo().trim());
                                    updateInspectionRequestViewModel.inspectionDate = dates;
                                    updateInspectionRequestViewModel.qcRemarks = ap.QCREMARKS.getText().toString();
                                    updateInspectionRequestViewModel.customerOrderNos = generateFinalInvoiceResponseModel.getResponse().get(getId).getCustOrderNo().trim();
                                    updateInspectionRequestViewModel.pgmCode = generateFinalInvoiceResponseModel.getResponse().get(getId).getPgmCode();
                                    updateInspectionRequestViewModel.result = ap.QCRESULT.getText().toString();
                                    updateInspectionRequestViewModel.sourceFlag = generateFinalInvoiceResponseModel.getResponse().get(getId).getSourceFlag();
                                    updateInspectionRequestViewModel.sourceId = generateFinalInvoiceResponseModel.getResponse().get(getId).getSourceId();
                                    updateInspectionRequestViewModel.sourceId = generateFinalInvoiceResponseModel.getResponse().get(getId).getSourceId();
                                    updateInspectionRequestViewModel.styleId = Integer.parseInt(generateFinalInvoiceResponseModel.getResponse().get(getId).getStyleId());

                                    if (generateQCNameResponseModel.getResponse().get(QCNameId).getEmpNo().trim() != null)
                                    {
                                        updateInspectionRequestViewModel.qcBy = generateQCNameResponseModel.getResponse().get(QCNameId).getEmpNo().trim();
                                    } else
                                    {
                                        updateInspectionRequestViewModel.qcBy = "0";
                                    }
                                    updateInspectionRequestViewModel.Auth = "Bearer " + getPreferenceManager().getPrefToken().trim();
                                    updateInspectionRequestViewModel.dbname = getPreferenceManager().getPrefDbname();
                                    updateInspectionRequestViewModel.systemOrderNo = generateFinalInvoiceResponseModel.getResponse().get(getId).getSysOrderNo();
                                    updateInspectionRequestViewModel.generateUpdateInspectionRequest();
                                } catch (Exception e)
                                {
                                    Log.i("Exception", e.toString());
                                }
                            } else
                            {
                                Toast.makeText(getApplicationContext(), "Select Inspection Date", Toast.LENGTH_SHORT).show();
                            }
                        } else
                        {
                            Toast.makeText(getApplicationContext(), "Select Inspection Date", Toast.LENGTH_SHORT).show();
                        }
                    } else
                    {
                        Toast.makeText(getApplicationContext(), "Select Inspection Date", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void finalInvoice()
    {
        finalInvoiceViewModel.setAuth("Bearer " + getPreferenceManager().getPrefToken().trim());
        finalInvoiceViewModel.setPiNo(piNo);
        finalInvoiceViewModel.setSourceFlag(SourceFlag);
        finalInvoiceViewModel.setSourceId(SourceId);
        finalInvoiceViewModel.setDbname(getPreferenceManager().getPrefDbname());
        finalInvoiceViewModel.setOrderStatus(orderStatus);
        finalInvoiceViewModel.generateFinalVoiceRequest();

        ap.piNo.setText(piNo);
    }

    private void setClick()
    {
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });
        ap.datetext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Calendar calendar = Calendar.getInstance();
                int years = calendar.get(Calendar.YEAR);
                int months = calendar.get(Calendar.MONTH);
                int days = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(FinalInspectionScreen.this, new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                    {
                        month = month + 1;
                        String mm = String.valueOf(month);
                        String dd = String.valueOf(dayOfMonth);
                        if (mm.length() == 1)
                        {
                            mm = "0" + mm;
                        }
                        if (dd.length() == 1)
                        {
                            dd = "0" + dd;
                        }
                        String dates = year + "-" + mm + "-" + dd;
                        ap.datetext.setText(dates);
                        time();
                    }
                }, years, months, days);
                int positiveColor = ContextCompat.getColor(FinalInspectionScreen.this, R.color.black);
                int negativeColor = ContextCompat.getColor(FinalInspectionScreen.this, R.color.black);

                datePickerDialog.show();
                datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(positiveColor);
                datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(negativeColor);

            }
        });
    }


    private void diable()
    {

        if (getId == 0)
        {
            prev.setEnabled(false);
        } else
        {
            prev.setEnabled(true);
        }

        if (getId == totalgetid - 1)
        {
            next.setEnabled(false);
        } else
        {
            next.setEnabled(true);
        }

    }

    private void time()
    {

        String date = ap.datetext.getText().toString();
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);
        int sec = calendar.get(Calendar.SECOND);
        TimePickerDialog timePickerDialog = new TimePickerDialog(FinalInspectionScreen.this, new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
            {
                String hh, mm;
                hh = String.valueOf(hourOfDay);
                mm = String.valueOf(minute);
                String secs = String.valueOf(sec);
                if (hh.length() == 1)
                {
                    hh = "0" + hh;
                }
                if (mm.length() == 1)
                {
                    mm = "0" + mm;
                }
                if (secs.length() == 1)
                {
                    secs = "0" + secs;
                }
                String times = hh + ":" + mm + ":" + secs;
                ap.datetext.setText(date + "T" + times);
            }
        }, hour, min, false);

        int positiveColor = ContextCompat.getColor(FinalInspectionScreen.this, R.color.black);
        int negativeColor = ContextCompat.getColor(FinalInspectionScreen.this, R.color.black);

        timePickerDialog.show();

        timePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(positiveColor);
        timePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(negativeColor);

    }


    @Override
    public void ShowErrorMessage(MessageViewType messageViewType, String errorMessage)
    {

    }

    @Override
    public void ShowErrorMessage(MessageViewType messageViewType, ViewType viewType, String errorMessage)
    {

    }

    @Override
    public void generateFinalInvoiceProcessed(GenerateFinalInvoiceResponseModel generateFinalInvoiceResponseModel)
    {

        ap.PREV.setClickable(true);
        ap.NEXT.setClickable(true);
        if (generateFinalInvoiceResponseModel != null)
        {
            ap.Progress.setVisibility(View.INVISIBLE);
            totalgetid = generateFinalInvoiceResponseModel.getResponse().size();
            FinalInvoice(generateFinalInvoiceResponseModel, 0);
            this.generateFinalInvoiceResponseModel = generateFinalInvoiceResponseModel;
        }

    }

    private void FinalInvoice(GenerateFinalInvoiceResponseModel generateFinalInvoiceResponseModel, int i)
    {

        if (i == 1)
        {
            Log.i("i", "" + i);
            ap.NEXT.setClickable(true);
            if (ap.NEXT.isClickable() == true)
            {
                Log.i("MyButton", "Clickable is true");
            }
        } else if (i == 2)
        {
            Log.i("i", "" + i);
            ap.PREV.setClickable(true);
        }
        try
        {
            if (!generateFinalInvoiceResponseModel.getResponse().isEmpty())
            {
                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getProductionRemarks() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getProductionRemarks().isEmpty())
                {
                    ap.REMARKSs.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getProductionRemarks().trim());
                } else
                {
                    ap.REMARKSs.setText("-");

                }

                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getResult() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getResult().isEmpty())
                {
                    ap.QCRESULT.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getResult().trim());
                } else
                {
                    ap.QCRESULT.setText("-");
                }

                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getDespatchMode() != null)
                {
                    ap.dispatch.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getDespatchMode().trim());
                } else
                {
                    ap.dispatch.setText("-");
                }

                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getInspectionDate() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getInspectionDate().trim().isEmpty())
                {
                    ap.datetext.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getInspectionDate().trim());
                } else
                {
                    ap.datetext.setText("-");
                }

                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getInspection_Remarks() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getInspection_Remarks().trim().isEmpty())
                {
                    ap.QCREMARKS.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getInspection_Remarks().trim());
                } else
                {
                    ap.QCREMARKS.setText("-");
                }


                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getStyleCode() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getStyleCode().trim().isEmpty())
                {
                    ap.STYLENAME.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getStyleCode().trim());
                    if (generateFinalInvoiceResponseModel.getResponse().get(getId).getStyleName() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getStyleName().trim().isEmpty())
                    {
                        Log.i("finalinspection", "entered");
                        ap.STYLENAME.setText(ap.STYLENAME.getText() + "-" + generateFinalInvoiceResponseModel.getResponse().get(getId).getStyleName().trim());
                    }
                } else
                {
                    if (generateFinalInvoiceResponseModel.getResponse().get(getId).getStyleName() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getStyleName().trim().isEmpty())
                    {
                        Log.i("finalinspection", "entered");
                        ap.STYLENAME.setText(ap.STYLENAME.getText() + "-" + generateFinalInvoiceResponseModel.getResponse().get(getId).getStyleName().trim());
                    } else
                    {
                        ap.STYLENAME.setText("-");
                    }
                }

                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getDeliveryTerms() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getBuyerEtd().trim().isEmpty())
                {
                    ap.DeleveryFac.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getDeliveryTerms().trim());
                } else
                {
                    ap.DeleveryFac.setText("-");
                }

                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getCustOrderNo() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getCustOrderNo().isEmpty())
                {
                    ap.BUYER.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getCustOrderNo().trim());
                } else
                {
                    ap.Buyer.setText("-");
                }
                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getSupplierName() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getSupplierName().isEmpty())
                {
                    ap.vendor.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getSupplierName().trim());
                } else
                {
                    ap.vendor.setText("-");
                }
                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getOrderQty() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getOrderQty().isEmpty())
                {

                    ap.ORDERQTY.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getOrderQty().trim());
                } else
                {
                    ap.ORDERQTY.setText("-");
                }
                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getBalance() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getBalance().isEmpty())
                {

                    ap.BALANCE.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getBalance().trim());
                } else
                {
                    ap.BALANCE.setText("-");
                }
                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getVendorDelDate() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getVendorDelDate().isEmpty())
                {

                    ap.VENDORDELDATE.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getVendorDelDate().trim());
                } else
                {
                    ap.VENDORDELDATE.setText("-");
                }
                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getForecastDelDate() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getForecastDelDate().isEmpty())
                {

                    ap.FORECASTDELDATE.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getForecastDelDate().trim());

                } else
                {
                    ap.FORECASTDELDATE.setText("-");
                }
                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getCityName() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getCityName().isEmpty())
                {

                    ap.CITY.setText(generateFinalInvoiceResponseModel.getResponse().get(getId).getCityName().trim());
                } else
                {
                    ap.CITY.setText("-");
                }
                if (generateFinalInvoiceResponseModel.getResponse().get(getId).getQcBy() != null && !generateFinalInvoiceResponseModel.getResponse().get(getId).getQcBy().isEmpty())
                {
                    String id = generateFinalInvoiceResponseModel.getResponse().get(getId).getQcBy().trim();
                    for (int j = 0; j <= generateQCNameResponseModel2.getResponse().size(); j++)
                    {
                        if (id.equals(generateQCNameResponseModel2.getResponse().get(j).getEmpNo().trim()))
                        {
                            QCNameId = j;
                            String ss = generateQCNameResponseModel2.getResponse().get(j).getEmpName().trim();
                            ap.QCNAMES.setText(ss);
                            ArrayAdapterQCName = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, QCNameArray);
                            ap.QCNAMES.setAdapter(ArrayAdapterQCName);
                            ap.QCNAMES.dismissDropDown();
                        }
                    }
                } else
                {
                    ap.QCNAMES.setText("-");
                }
            } else
            {
                logout();
            }
        } catch (Exception e)
        {
            Log.i("Exception_inspection", "" + e);
        }
    }

    public void logout()
    {
        Dialog dialoglogout = new Dialog(this, R.style.dialog_center);
        dialoglogout.setCanceledOnTouchOutside(false);
        dialoglogout.setContentView(R.layout.logout);
        dialoglogout.show();
        TextView textView23 = dialoglogout.findViewById(R.id.textView23);

        DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener()
        {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent KEvent)
            {
                int keyaction = KEvent.getAction();

                if (keyaction == KeyEvent.ACTION_DOWN)
                {
                    Intent intent = new Intent(getApplicationContext(), TransactionProductUpdate.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                return false;
            }
        };
        dialoglogout.setOnKeyListener(keylistener);

        textView23.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialoglogout.dismiss();

                Intent intent = new Intent(getApplicationContext(), TransactionProductUpdate.class);
                startActivity(intent);
                finish();
            }

        });

    }

    @Override
    public void generateQCNameProcessed(GenerateQCNameResponseModel generateQCNameResponseModel)
    {
        this.generateQCNameResponseModel = generateQCNameResponseModel;
        this.generateQCNameResponseModel2 = generateQCNameResponseModel;
        if (generateQCNameResponseModel.getResponse() != null)
        {
            QCNameArray = new String[generateQCNameResponseModel.getResponse().size()];
            for (int i = 0; i < generateQCNameResponseModel.getResponse().size(); i++)
            {
                QCNameArray[i] = generateQCNameResponseModel.getResponse().get(i).getEmpName().trim();
            }
            ArrayAdapterQCName = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, QCNameArray);
            ap.QCNAMES.setAdapter(ArrayAdapterQCName);
        }
    }

    private void qcName(String id)
    {
        Log.i("isisis", "" + id);


    }


    @Override
    public void generateQCResultProcessed(GenerateQCResultResponseModel
                                                  generateQCResultResponseModel)
    {
        if (generateQCResultResponseModel.getResponse() != null)
        {
            this.generateQCResultResponseModel = generateQCResultResponseModel;
            QCResultArray = new String[generateQCResultResponseModel.getResponse().size()];
            for (int i = 0; i < generateQCResultResponseModel.getResponse().size(); i++)
            {
                QCResultArray[i] = generateQCResultResponseModel.getResponse().get(i).getResultName().trim();
            }

            arrayAdapterQCResult = new ArrayAdapter<>(FinalInspectionScreen.this, android.R.layout.simple_dropdown_item_1line, QCResultArray);
            ap.QCRESULT.setAdapter(arrayAdapterQCResult);
        }
    }

    @Override
    public void generateUpdateInspectionProcessed(GenerateUpdateInspectionResponseModel
                                                          generateUpdateInspectionResponseModel)
    {
        if (generateUpdateInspectionResponseModel.getMessage().equals("time out"))
        {
            timeout();
        } else
        {
            Toast.makeText(getApplicationContext(), "" + generateUpdateInspectionResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
            finalInvoice();
        }
    }

    @Override
    public void generateShipModeProcessed(GenerateShipModeResponseModel
                                                  generateShipModeResponceModel)
    {
        if (generateShipModeResponceModel != null)
        {
            this.generateShipModeResponseModel = generateShipModeResponceModel;
        }
    }

    private void shipment(int id)
    {
        Log.i("isisis", "" + id);
        for (int i = 0; i <= generateShipModeResponseModel.getResponse().size(); i++)
        {
            if (id == Integer.parseInt(generateShipModeResponseModel.getResponse().get(i).getModeId()))
            {
                ap.dispatch.setText(generateShipModeResponseModel.getResponse().get(i).getModeName());
            }
        }
    }

    @Override
    public void GetInspectionReportProcess(GetInspectionReportResponseModel
                                                   getInspectionReportResponseModel)
    {
        if (getInspectionReportResponseModel.getMessage().equals("Record found"))
        {
            this.getInspectionReportResponseModel = getInspectionReportResponseModel;
        }

    }

    @Override
    public void onFailure(ErrorBody errorBody, int statusCode)
    {

    }

    /**
     * @return
     * @brief initializing the preferenceManager from shared preference for local use in this activity
     */

    public PreferenceManager getPreferenceManager()
    {
        if (preferenceManager == null)
        {
            preferenceManager = PreferenceManager.getInstance();
            preferenceManager.initialize(getApplicationContext());
        }
        return preferenceManager;
    }

    public void timeout()
    {
        Dialog dialoglogout = new Dialog(this, R.style.dialog_center);
        dialoglogout.setContentView(R.layout.time_out);
        dialoglogout.setCanceledOnTouchOutside(true);
        dialoglogout.show();
        TextView textView23 = dialoglogout.findViewById(R.id.textView23);

        textView23.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialoglogout.dismiss();
            }
        });

    }

}
