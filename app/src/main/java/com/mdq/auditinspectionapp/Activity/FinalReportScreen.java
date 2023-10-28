package com.mdq.auditinspectionapp.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.GetInspectionReportResponseInterface;
import com.mdq.auditinspectionapp.Interfaces.ViewResponceInterface.GetProductionResponseInterface;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.ErrorBody;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GetInspectionReportResponseModel;
import com.mdq.auditinspectionapp.Pojo.JsonResonse.GetProductionReportResponseModel;
import com.mdq.auditinspectionapp.R;
import com.mdq.auditinspectionapp.Utils.PreferenceManager;
import com.mdq.auditinspectionapp.ViewModel.GetInspectionViewModel;
import com.mdq.auditinspectionapp.ViewModel.GetProductionReportViewModel;
import com.mdq.auditinspectionapp.databinding.ActivityFinalReportScreenBinding;
import com.mdq.auditinspectionapp.enums.MessageViewType;
import com.mdq.auditinspectionapp.enums.ViewType;

import org.apache.http.conn.ssl.AllowAllHostnameVerifier;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class FinalReportScreen extends AppCompatActivity implements GetInspectionReportResponseInterface, GetProductionResponseInterface {

    TextView name;
    PreferenceManager preferenceManager;
    ActivityFinalReportScreenBinding activityFinalReportScreenBinding;
    String orderType, vendor, customer, Seasonname, piNo, SourceName, OrderStatus, BRAND, who;
    GetInspectionViewModel getInspectionViewModel;
    GetProductionReportViewModel getProductionReportViewModel;
    boolean download = false;
    GetInspectionReportResponseModel getInspectionReportResponseModel;
    GetProductionReportResponseModel getProductionReportResponseModel;
    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityFinalReportScreenBinding = ActivityFinalReportScreenBinding.inflate(getLayoutInflater());
        setContentView(activityFinalReportScreenBinding.getRoot());
        getInspectionViewModel = new GetInspectionViewModel(this, this);
        getProductionReportViewModel = new GetProductionReportViewModel(this, this);
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission == PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0012);
        }

        name = findViewById(R.id.name);
        name.setText(getPreferenceManager().getPrefUsername());

        Intent intent = getIntent();

        orderType = intent.getStringExtra("orderType");

        Seasonname = intent.getStringExtra("Seasonname");
        piNo = intent.getStringExtra("piNo");
        SourceName = intent.getStringExtra("SourceName");
        OrderStatus = intent.getStringExtra("OrderStatus");
        BRAND = intent.getStringExtra("BRAND");
        who = intent.getStringExtra("who");

        if (who.equals("production")) {
            getProductionReportViewModel.setAuth("Bearer " + getPreferenceManager().getPrefToken());
            getProductionReportViewModel.setBrand(BRAND);
            getProductionReportViewModel.setInvoiceNo(piNo);
            getProductionReportViewModel.setOrderStatus(OrderStatus);
            getProductionReportViewModel.setOrderType(orderType);
            getProductionReportViewModel.setSeasonName(Seasonname);
            getProductionReportViewModel.setDbname(getPreferenceManager().getPrefDbname());
            getProductionReportViewModel.getProductionReportCall();

        } else if (who.equals("inspection")) {
            getInspectionViewModel.setAuth("Bearer " + getPreferenceManager().getPrefToken());
            getInspectionViewModel.setBrand(BRAND);
            getInspectionViewModel.setInvoiceNo(piNo);
            getInspectionViewModel.setOrderStatus(OrderStatus);
            getInspectionViewModel.setOrderType(orderType);
            getInspectionViewModel.setSeasonName(Seasonname);
            getInspectionViewModel.setDbname(getPreferenceManager().getPrefDbname());

            getInspectionViewModel.getInspectionReportCall();
        }
        activityFinalReportScreenBinding.SAVE.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                int permission = ContextCompat.checkSelfPermission(FinalReportScreen.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (permission == PackageManager.PERMISSION_GRANTED) {
                    if (download) {
                        if (who.equals("inspection")) {
//                            download(getInspectionReportResponseModel.getLink(), filename);
                            new DownloadFileFromURL().execute(getInspectionReportResponseModel.getLink(),filename);
                        } else if (who.equals("production")) {
//                            download(getProductionReportResponseModel.getLink(), filename);
                            new DownloadFileFromURL().execute(getProductionReportResponseModel.getLink(),filename);
                        }
                    }
                } else {
                    ActivityCompat.requestPermissions(FinalReportScreen.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0012);
                }
            }
        });
        activityFinalReportScreenBinding.Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        activityFinalReportScreenBinding.Backarraow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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

    @Override
    public void ShowErrorMessage(MessageViewType messageViewType, String errorMessage) {

    }

    @Override
    public void ShowErrorMessage(MessageViewType messageViewType, ViewType viewType, String errorMessage) {

    }

    @Override
    public void GetInspectionReportProcess(GetInspectionReportResponseModel getInspectionReportResponseModel) {

        try {
            if (getInspectionReportResponseModel != null && !getInspectionReportResponseModel.getLink().isEmpty()) {
                download = true;
                String str = getProductionReportResponseModel.getLink().replace("https://219.90.67.5:4545/audit/production/upload/production-", "");
                filename = str;
                this.getInspectionReportResponseModel = getInspectionReportResponseModel;
                activityFinalReportScreenBinding.textView22.setText("Data found");
                activityFinalReportScreenBinding.textView22.setVisibility(View.VISIBLE);
                activityFinalReportScreenBinding.SAVE.setVisibility(View.VISIBLE);
                activityFinalReportScreenBinding.fetching.setVisibility(View.INVISIBLE);
                activityFinalReportScreenBinding.progress.setVisibility(View.INVISIBLE);
            } else {
                activityFinalReportScreenBinding.textView22.setText("Data not found");
                activityFinalReportScreenBinding.textView22.setVisibility(View.VISIBLE);
                activityFinalReportScreenBinding.SAVE.setVisibility(View.INVISIBLE);
                activityFinalReportScreenBinding.fetching.setVisibility(View.INVISIBLE);
                activityFinalReportScreenBinding.progress.setVisibility(View.INVISIBLE);
            }
        } catch (Exception e) {
            activityFinalReportScreenBinding.textView22.setText("Data not found");
            activityFinalReportScreenBinding.textView22.setVisibility(View.VISIBLE);
            activityFinalReportScreenBinding.SAVE.setVisibility(View.INVISIBLE);
            activityFinalReportScreenBinding.fetching.setVisibility(View.INVISIBLE);
            activityFinalReportScreenBinding.progress.setVisibility(View.INVISIBLE);

        }
    }

    @Override
    public void getProductionReportCall(GetProductionReportResponseModel getProductionReportResponseModel) {

        try {

            if (getProductionReportResponseModel != null && !getProductionReportResponseModel.getLink().isEmpty()) {
                this.getProductionReportResponseModel = getProductionReportResponseModel;
                download = true;
                String str = getProductionReportResponseModel.getLink().replace("https://219.90.67.5:4545/audit/production/upload/production-", "");
                filename = str;
                activityFinalReportScreenBinding.textView22.setText("Data found");
                activityFinalReportScreenBinding.textView22.setVisibility(View.VISIBLE);
                activityFinalReportScreenBinding.SAVE.setVisibility(View.VISIBLE);
                activityFinalReportScreenBinding.fetching.setVisibility(View.INVISIBLE);
                activityFinalReportScreenBinding.progress.setVisibility(View.INVISIBLE);

            } else {
                activityFinalReportScreenBinding.textView22.setText("Data not found");
                activityFinalReportScreenBinding.textView22.setVisibility(View.VISIBLE);
                activityFinalReportScreenBinding.SAVE.setVisibility(View.INVISIBLE);
                activityFinalReportScreenBinding.fetching.setVisibility(View.INVISIBLE);
                activityFinalReportScreenBinding.progress.setVisibility(View.INVISIBLE);
            }
        } catch (Exception e) {
            activityFinalReportScreenBinding.textView22.setText("Data not found");
            activityFinalReportScreenBinding.textView22.setVisibility(View.VISIBLE);
            activityFinalReportScreenBinding.SAVE.setVisibility(View.INVISIBLE);
            activityFinalReportScreenBinding.fetching.setVisibility(View.INVISIBLE);
            activityFinalReportScreenBinding.progress.setVisibility(View.INVISIBLE);

        }
    }

    @Override
    public void onFailure(ErrorBody errorBody, int statusCode) {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void download(String link, String filename) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        String url = link.trim();
//        String url = "https://mdqualityapps.in/vsafe/UAT/admin/current_version/Version1.2_Android.bin";

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription(filename + "/ " + date);
        
        String nnme = filename + "_" + date + ".pdf";
        Log.i("sanjai", filename + "/" + date);
        request.setTitle(filename + "/" + date);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                filename + "/" + date);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }

        String uurl = filename.replace(".pdf", "");
        uurl = uurl + "-" + date + ".pdf";
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, uurl);

        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        BroadcastReceiver downloadReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

                if (downloadId == -1)
                    return;

                // query download status
                Cursor cursor = manager.query(new DownloadManager.Query().setFilterById(downloadId));
                if (cursor.moveToFirst()) {
                    int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                    if (status == DownloadManager.STATUS_SUCCESSFUL) {
                        // download is successful
                        Dialog dialog = new Dialog(FinalReportScreen.this, R.style.dialog_center);
                        dialog.setContentView(R.layout.sucees_pop_up);
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                            }
                        }, 2000);
                    } else {
                    }
                } else {
                    // download is cancelled
                }
            }
        };
        registerReceiver(downloadReceiver, filter);

    }


    class DownloadFileFromURL extends AsyncTask<String, String, String>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(String... values)
        {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try
            {
                TrustManager[] trustAllCertificates = new TrustManager[]{
                        new X509TrustManager()
                        {
                            public java.security.cert.X509Certificate[] getAcceptedIssuers()
                            {
                                return null;
                            }

                            public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
                            {
                            }

                            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
                            {
                            }
                        }
                };

                // Create an SSLContext with the trustAllCertificates
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, trustAllCertificates, new SecureRandom());
                URL url = new URL(f_url[0]);
                // Set the custom SSLContext for the connection
                HttpsURLConnection conection = null;
                (conection).setDefaultSSLSocketFactory(sslContext.getSocketFactory());
                    conection = (HttpsURLConnection) url.openConnection();
                conection.setHostnameVerifier(new AllowAllHostnameVerifier());
                conection.connect();

                int lengthOfFile = conection.getContentLength();

                // Download the file
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Define the directory path in the "Download" directory
                File downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                if (!downloadDir.exists())
                {
                    downloadDir.mkdirs(); // Create the directory if it doesn't exist
                }

                File outputFile = new File(downloadDir, f_url[1]);

                try (FileOutputStream output = new FileOutputStream(outputFile))
                {
                    byte data[] = new byte[1024];
                    long total = 0;

                    while ((count = input.read(data)) != -1)
                    {
                        total += count;
                        // Publishing the progress (you can modify this part as needed)
                        // After this onProgressUpdate will be called
//                    publishProgress("" + (int) ((total * 100) / lengthOfFile);
                        // Writing data to file
                        output.write(data, 0, count);
                    }

                    output.flush();
                    // Closing streams
                    output.close();
                    input.close();

                    // Notify the MediaScanner about the new file so it appears in the device's media library
                    MediaScannerConnection.scanFile(FinalReportScreen.this, new String[]{outputFile.getAbsolutePath()}, null, null);


                    Log.i("Done", "Done");
                } catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}

