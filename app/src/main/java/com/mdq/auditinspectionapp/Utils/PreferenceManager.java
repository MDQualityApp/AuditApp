package com.mdq.auditinspectionapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.mdq.auditinspectionapp.Pojo.JsonRequest.GenerateFinalInvoiceRequestModel;

//For local Storage
public class PreferenceManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context mContext;
    private int mPrivateMode = 0;

    //For storing token
    private static final String PREF_TOKEN = "PREF_TOKEN";

    //For Storing account login or not
    private static final String PREF_IS_LOGGED_IN = "PREF_IS_LOGGED_IN";

    //For Storing user name
    private static final String PREF_NAME = "PREF_MYFINALYST";

    private static final String PREF_INVOICE = "PREF_INVOICE";

    private static final String PREF_USERNAME = "PREF_USERNAME";

    private static final String PREF_DPID = "PREF_DPID";

    private static final String PREF_DBNAME = "PREF_DBNAME";

    private static final String PREF_ID = "PREF_ID";

    private static final String PREF_TEAM_ID = "PREF_TEAM_ID";

    private static PreferenceManager mInstance;

    public void initialize(Context context) {
        this.mContext = context;
        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, mPrivateMode);
        editor = sharedPreferences.edit();
    }

    public static PreferenceManager getInstance() {
        if (mInstance == null)
            mInstance = new PreferenceManager();

        return mInstance;
    }

    private PreferenceManager() {
    }

    public void clearPreference() {
        //editor.clear();
        editor.remove(PREF_IS_LOGGED_IN);
        editor.remove(PREF_TOKEN);
        editor.apply();
    }

    public void setPrefToken(String Token) {
        editor.putString(PREF_TOKEN, Token);
        editor.commit();
    }

    public String getPrefToken() {
        return sharedPreferences.getString(PREF_TOKEN, null);
    }

    public void setPrefDpid(int Token) {
        editor.putInt(PREF_DPID, Token);
        editor.commit();
    }

    public int getPrefDpid() {
        return sharedPreferences.getInt(PREF_DPID, 0);
    }

    public void setPrefId(String Token) {
        editor.putString(PREF_ID, Token);
        editor.commit();
    }

    public String getPrefID() {
        return sharedPreferences.getString(PREF_ID, null);
    }

    public void setPrefDbname(String Token) {
        editor.putString(PREF_DBNAME, Token);
        editor.commit();
    }

    public String getPrefDbname() {
        return sharedPreferences.getString(PREF_DBNAME, "null");
    }

    public void setPrefUsername(String Token) {
        editor.putString(PREF_USERNAME, Token);
        editor.commit();
    }

    public String getPrefUsername() {
        return sharedPreferences.getString(PREF_USERNAME, null);
    }

    public void setPrefTeamId(String Token) {
        editor.putString(PREF_TEAM_ID, Token);
        editor.commit();
    }

    public String getPrefTeamId() {
        return sharedPreferences.getString(PREF_TEAM_ID, null);
    }

    public void setPrefInvoice(GenerateFinalInvoiceRequestModel Token) {
//        editor.(PREF_TOKEN, Token);
        editor.commit();
    }

    public String getPrefInvoice() {
        return sharedPreferences.getString(PREF_TOKEN, null);
    }

}