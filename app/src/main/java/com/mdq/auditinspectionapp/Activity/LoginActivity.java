package com.mdq.auditinspectionapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.LoginResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.getByUserIDResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GenerateLoginResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.getByUserIDResponseModel;
import com.mdq.auditinspectionapp.R;
import com.mdq.auditinspectionapp.Utils.PreferenceManager;
import com.mdq.auditinspectionapp.ViewModel.LoginRequestViewModel;
import com.mdq.auditinspectionapp.ViewModel.getByUserIDViewModel;
import com.mdq.auditinspectionapp.databinding.ActivityMainBinding;
import com.mdq.auditinspectionapp.enums.MessageViewType;
import com.mdq.auditinspectionapp.enums.ViewType;

public class LoginActivity extends AppCompatActivity implements LoginResponseInterface, getByUserIDResponseInterface {

    TextView forgot;
    CardView cardView;
    ActivityMainBinding activityMainBinding;
    LoginRequestViewModel loginRequestViewModel;
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterForDB;
    getByUserIDViewModel getByUserIDViewModels;
    PreferenceManager preferenceManager;
    String[] DBList = new String[]{"FAI Group", "FA Impex", "Test"};
    String[] DBListActual = new String[]{"WindmillLive", "FAImpex", "WMTest"};
    int i = 0, dpid;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        autoCompleteTextView = findViewById(R.id.autoComplete);
        getByUserIDViewModels = new getByUserIDViewModel(this, this);
        //making status bar color as transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        forgot = findViewById(R.id.Forgot);
        cardView = findViewById(R.id.cardView);
        forgot.setPaintFlags(forgot.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        adapterForDB = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, DBList);
        autoCompleteTextView.setText(adapterForDB.getItem(0).toString());
        autoCompleteTextView.setAdapter(adapterForDB);

        TextView linkTextView = findViewById(R.id.link);
        linkTextView.setPaintFlags(linkTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
        linkTextView.setLinkTextColor(Color.BLUE);
        linkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.azonix.in/"));
                startActivity(browserIntent);
            }
        });

        activityMainBinding.CardForHeading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCompleteTextView.showDropDown();
            }
        });

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    i = 1;
                } else {
                    i = 0;
                }
            }
        });

        loginRequestViewModel = new LoginRequestViewModel(getApplicationContext(), this);
        activityMainBinding.logins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                if ((connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED)
                        || (connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                        .getState() == NetworkInfo.State.CONNECTED)) {
                    if (!activityMainBinding.UserId.getText().toString().isEmpty() && !activityMainBinding.password.getText().toString().isEmpty()) {
                        if (activityMainBinding.autoComplete.getText().toString().trim().equals("FAI Group")) {
                            loginRequestViewModel.setDbname(DBListActual[0]);
                            getPreferenceManager().setPrefDbname(DBListActual[0]);
                            Log.i("DBNAMEs", DBListActual[0]);
                        } else if (activityMainBinding.autoComplete.getText().toString().trim().equals("FA Impex")) {
                            loginRequestViewModel.setDbname(DBListActual[1]);
                            getPreferenceManager().setPrefDbname(DBListActual[1]);
                            Log.i("DBNAMEs", DBListActual[1]);
                        } else if (activityMainBinding.autoComplete.getText().toString().trim().equals("Test")) {
                            loginRequestViewModel.setDbname(DBListActual[2]);
                            getPreferenceManager().setPrefDbname(DBListActual[2]);
                            Log.i("DBNAMEs", DBListActual[2]);
                        }
                        loginRequestViewModel.setEmp_id(activityMainBinding.UserId.getText().toString());
                        loginRequestViewModel.setPassword(activityMainBinding.password.getText().toString());
                        loginRequestViewModel.generateLoginRequest();
                        activityMainBinding.Progress.bringToFront();
                        activityMainBinding.Progress.setVisibility(View.VISIBLE);

                    } else {
                        Toast.makeText(getApplicationContext(), "Require all fields", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "This App Require Internet", Toast.LENGTH_SHORT).show();
                }
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
    public void generateLoginProcessed(GenerateLoginResponseModel generateLoginResponseModel) {

        activityMainBinding.Progress.setVisibility(View.GONE);
        if (generateLoginResponseModel.getMessage().equals("Logged in successfully")) {
            getByUserIDViewModels.setUser_id(generateLoginResponseModel.getData().getId());
            getByUserIDViewModels.setDbname(getPreferenceManager().getPrefDbname());
            getByUserIDViewModels.setAuth("Bearer " + generateLoginResponseModel.getToken());
            getByUserIDViewModels.generateGetByID();
            dpid = Integer.parseInt(generateLoginResponseModel.getData().getDepartmentId());
            getPreferenceManager().setPrefToken(generateLoginResponseModel.getToken());
            getPreferenceManager().setPrefId(generateLoginResponseModel.getData().getId());
            name = generateLoginResponseModel.getData().getName();
            getPreferenceManager().setPrefUsername(generateLoginResponseModel.getData().getName());
            getPreferenceManager().setPrefDpid(Integer.parseInt(generateLoginResponseModel.getData().getDepartmentId().toString()));
        } else if (generateLoginResponseModel.getStatus().equals("1")) {
            Toast.makeText(getApplicationContext(), "" + generateLoginResponseModel.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Invalid Request", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void generateGETBYID(getByUserIDResponseModel getByUserIDResponseModel) {
        if (getByUserIDResponseModel.getStatus().equals("0")) {
            try {
                getPreferenceManager().setPrefTeamId(getByUserIDResponseModel.getUsers().get(0).getTeamId());
                Intent intent = new Intent(LoginActivity.this, welcomeSaibhavani.class);
                intent.putExtra("name", name);
                intent.putExtra("dpid", dpid);
                startActivity(intent);
                finishAffinity();
                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.i("ExceptionInTEAMISGET", "" + e);
            }
        }else{
            Toast.makeText(this, "User Team ID Not Found.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFailure(ErrorBody errorBody, int statusCode) {
        activityMainBinding.Progress.setVisibility(View.GONE);
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