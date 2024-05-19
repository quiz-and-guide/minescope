package com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

public class Settings extends AppCompatActivity {
    private ArrayList<String> urlsOpaquesToDownload, urlsTransparentsToDownload;
    private ProgressDialog progress;
    private SharedPreferences.Editor myEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Database myDb = new Database(this);
        urlsOpaquesToDownload = myDb.urlsOpaquesToDownload();
        urlsTransparentsToDownload = myDb.urlsTransparentsToDownload();
        myDb.closeDatabase();
        SharedPreferences sharedPreferences = this.getSharedPreferences("UserPreferences", MODE_PRIVATE);
        myEdit = sharedPreferences.edit();
        String theme = sharedPreferences.getString("theme", "light");
        String sizeText = sharedPreferences.getString("sizeText", "0");
        String language = sharedPreferences.getString("language", Locale.getDefault().getLanguage());
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getBaseContext().getResources().updateConfiguration(config, this.getBaseContext().getResources().getDisplayMetrics());
        RadioButton rbVeryBig = findViewById(R.id.rbVeryBig);
        RadioButton rbBig = findViewById(R.id.rbBig);
        RadioButton rbMedium = findViewById(R.id.rbMedium);
        RadioButton rbSmall = findViewById(R.id.rbSmall);
        RadioButton rbVerySmall = findViewById(R.id.rbVerySmall);
        switch (sizeText) {
            case "+2":
                rbVeryBig.setChecked(true);
                break;
            case "+1":
                rbBig.setChecked(true);
                break;
            case "0":
                rbMedium.setChecked(true);
                break;
            case "-1":
                rbSmall.setChecked(true);
                break;
            case "-2":
                rbVerySmall.setChecked(true);
                break;
        }
        rbVeryBig.setOnClickListener(v -> {
            myEdit.putString("sizeText", "+2");
            myEdit.apply();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        rbBig.setOnClickListener(v -> {
            myEdit.putString("sizeText", "+1");
            myEdit.apply();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        rbMedium.setOnClickListener(v -> {
            myEdit.putString("sizeText", "0");
            myEdit.apply();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        rbSmall.setOnClickListener(v -> {
            myEdit.putString("sizeText", "-1");
            myEdit.apply();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        rbVerySmall.setOnClickListener(v -> {
            myEdit.putString("sizeText", "-2");
            myEdit.apply();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        ScrollView linear = findViewById(R.id.linear);
        TextView txtTheme = findViewById(R.id.theme);
        TextView images = findViewById(R.id.images);
        TextView txtLanguage = findViewById(R.id.languaje);
        TextView txtSize = findViewById(R.id.textSize);
        TextView txtContact = findViewById(R.id.contact);
        TextView txtPrivacy = findViewById(R.id.privacy_policy);
        TextView txtShare = findViewById(R.id.share);
        TextView txtCredits = findViewById(R.id.credits);
        Button btnLanguage = findViewById(R.id.btnLanguage);
        Button btnDownload = findViewById(R.id.download);
        RadioButton rdLight = findViewById(R.id.rbLight);
        RadioButton rdDarck = findViewById(R.id.rbDarck);
        rdLight.setOnClickListener(v -> {
            myEdit.putString("theme", "light");
            myEdit.apply();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        rdDarck.setOnClickListener(v -> {
            myEdit.putString("theme", "darck");
            myEdit.apply();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        if (theme.equals("light")) {
            rdLight.setChecked(true);
            linear.setBackgroundColor(getResources().getColor(R.color.white));
            txtTheme.setTextColor(getResources().getColor(R.color.black));
            rdLight.setTextColor(getResources().getColor(R.color.black));
            rdDarck.setTextColor(getResources().getColor(R.color.black));
            images.setTextColor(getResources().getColor(R.color.black));
            txtLanguage.setTextColor(getResources().getColor(R.color.black));
            txtSize.setTextColor(getResources().getColor(R.color.black));
            txtSize.setTextColor(getResources().getColor(R.color.black));
            txtSize.setTextColor(getResources().getColor(R.color.black));
            rbVeryBig.setTextColor(getResources().getColor(R.color.black));
            rbBig.setTextColor(getResources().getColor(R.color.black));
            rbMedium.setTextColor(getResources().getColor(R.color.black));
            rbSmall.setTextColor(getResources().getColor(R.color.black));
            rbVerySmall.setTextColor(getResources().getColor(R.color.black));
        } else {
            rdDarck.setChecked(true);
            linear.setBackgroundColor(getResources().getColor(R.color.black));
            txtTheme.setTextColor(getResources().getColor(R.color.white));
            rdLight.setTextColor(getResources().getColor(R.color.white));
            rdDarck.setTextColor(getResources().getColor(R.color.white));
            images.setTextColor(getResources().getColor(R.color.white));
            txtLanguage.setTextColor(getResources().getColor(R.color.white));
            txtSize.setTextColor(getResources().getColor(R.color.white));
            rbVeryBig.setTextColor(getResources().getColor(R.color.white));
            rbBig.setTextColor(getResources().getColor(R.color.white));
            rbMedium.setTextColor(getResources().getColor(R.color.white));
            rbSmall.setTextColor(getResources().getColor(R.color.white));
            rbVerySmall.setTextColor(getResources().getColor(R.color.white));
        }
        switch (isTablet(this)) {
            case "2":
                switch (sizeText) {
                    case "+2":
                        txtLanguage.setTextSize(38);
                        txtTheme.setTextSize(38);
                        txtSize.setTextSize(38);
                        images.setTextSize(38);
                        btnLanguage.setTextSize(34);
                        rdDarck.setTextSize(34);
                        rdLight.setTextSize(34);
                        rbVeryBig.setTextSize(34);
                        rbBig.setTextSize(34);
                        rbMedium.setTextSize(34);
                        rbSmall.setTextSize(34);
                        rbVerySmall.setTextSize(34);
                        btnDownload.setTextSize(34);
                        txtContact.setTextSize(34);
                        txtPrivacy.setTextSize(34);
                        txtShare.setTextSize(34);
                        txtCredits.setTextSize(34);
                        break;
                    case "+1":
                        txtLanguage.setTextSize(36);
                        txtTheme.setTextSize(36);
                        txtSize.setTextSize(36);
                        images.setTextSize(36);
                        btnLanguage.setTextSize(32);
                        rdDarck.setTextSize(32);
                        rdLight.setTextSize(32);
                        rbVeryBig.setTextSize(32);
                        rbBig.setTextSize(32);
                        rbMedium.setTextSize(32);
                        rbSmall.setTextSize(32);
                        rbVerySmall.setTextSize(32);
                        btnDownload.setTextSize(32);
                        txtContact.setTextSize(32);
                        txtPrivacy.setTextSize(32);
                        txtShare.setTextSize(32);
                        txtCredits.setTextSize(32);
                        break;
                    case "0":
                        txtLanguage.setTextSize(34);
                        txtTheme.setTextSize(34);
                        txtSize.setTextSize(34);
                        images.setTextSize(34);
                        btnLanguage.setTextSize(30);
                        rdDarck.setTextSize(30);
                        rdLight.setTextSize(30);
                        rbVeryBig.setTextSize(30);
                        rbBig.setTextSize(30);
                        rbMedium.setTextSize(30);
                        rbSmall.setTextSize(30);
                        rbVerySmall.setTextSize(30);
                        btnDownload.setTextSize(30);
                        txtContact.setTextSize(30);
                        txtPrivacy.setTextSize(30);
                        txtShare.setTextSize(30);
                        txtCredits.setTextSize(30);
                        break;
                    case "-1":
                        txtLanguage.setTextSize(32);
                        txtTheme.setTextSize(32);
                        txtSize.setTextSize(32);
                        images.setTextSize(32);
                        btnLanguage.setTextSize(28);
                        rdDarck.setTextSize(28);
                        rdLight.setTextSize(28);
                        rbVeryBig.setTextSize(28);
                        rbBig.setTextSize(28);
                        rbMedium.setTextSize(28);
                        rbSmall.setTextSize(28);
                        rbVerySmall.setTextSize(28);
                        btnDownload.setTextSize(28);
                        txtContact.setTextSize(28);
                        txtPrivacy.setTextSize(28);
                        txtShare.setTextSize(28);
                        txtCredits.setTextSize(28);
                        break;
                    case "-2":
                        txtLanguage.setTextSize(30);
                        txtTheme.setTextSize(30);
                        txtSize.setTextSize(30);
                        images.setTextSize(30);
                        btnLanguage.setTextSize(26);
                        rdDarck.setTextSize(26);
                        rdLight.setTextSize(26);
                        rbVeryBig.setTextSize(26);
                        rbBig.setTextSize(26);
                        rbMedium.setTextSize(26);
                        rbSmall.setTextSize(26);
                        rbVerySmall.setTextSize(26);
                        btnDownload.setTextSize(26);
                        txtContact.setTextSize(26);
                        txtPrivacy.setTextSize(26);
                        txtShare.setTextSize(26);
                        txtCredits.setTextSize(26);
                        break;
                }
                break;
            case "1":
                switch (sizeText) {
                    case "+2":
                        txtLanguage.setTextSize(32);
                        txtTheme.setTextSize(32);
                        txtSize.setTextSize(32);
                        images.setTextSize(32);
                        btnLanguage.setTextSize(28);
                        rdDarck.setTextSize(28);
                        rdLight.setTextSize(28);
                        rbVeryBig.setTextSize(28);
                        rbBig.setTextSize(28);
                        rbMedium.setTextSize(28);
                        rbSmall.setTextSize(28);
                        rbVerySmall.setTextSize(28);
                        btnDownload.setTextSize(28);
                        txtContact.setTextSize(28);
                        txtPrivacy.setTextSize(28);
                        txtShare.setTextSize(28);
                        txtCredits.setTextSize(28);
                        break;
                    case "+1":
                        txtLanguage.setTextSize(30);
                        txtTheme.setTextSize(30);
                        txtSize.setTextSize(30);
                        images.setTextSize(30);
                        btnLanguage.setTextSize(26);
                        rdDarck.setTextSize(26);
                        rdLight.setTextSize(26);
                        rbVeryBig.setTextSize(26);
                        rbBig.setTextSize(26);
                        rbMedium.setTextSize(26);
                        rbSmall.setTextSize(26);
                        rbVerySmall.setTextSize(26);
                        btnDownload.setTextSize(26);
                        txtContact.setTextSize(26);
                        txtPrivacy.setTextSize(26);
                        txtShare.setTextSize(26);
                        txtCredits.setTextSize(26);
                        break;
                    case "0":
                        txtLanguage.setTextSize(28);
                        txtTheme.setTextSize(28);
                        txtSize.setTextSize(28);
                        images.setTextSize(28);
                        btnLanguage.setTextSize(24);
                        rdDarck.setTextSize(24);
                        rdLight.setTextSize(24);
                        rbVeryBig.setTextSize(24);
                        rbBig.setTextSize(24);
                        rbMedium.setTextSize(24);
                        rbSmall.setTextSize(24);
                        rbVerySmall.setTextSize(24);
                        btnDownload.setTextSize(24);
                        txtContact.setTextSize(24);
                        txtPrivacy.setTextSize(24);
                        txtShare.setTextSize(24);
                        txtCredits.setTextSize(24);
                        break;
                    case "-1":
                        txtLanguage.setTextSize(26);
                        txtTheme.setTextSize(26);
                        txtSize.setTextSize(26);
                        images.setTextSize(26);
                        btnLanguage.setTextSize(22);
                        rdDarck.setTextSize(22);
                        rdLight.setTextSize(22);
                        rbVeryBig.setTextSize(22);
                        rbBig.setTextSize(22);
                        rbMedium.setTextSize(22);
                        rbSmall.setTextSize(22);
                        rbVerySmall.setTextSize(22);
                        btnDownload.setTextSize(22);
                        txtContact.setTextSize(22);
                        txtPrivacy.setTextSize(22);
                        txtShare.setTextSize(22);
                        txtCredits.setTextSize(22);
                        break;
                    case "-2":
                        txtLanguage.setTextSize(24);
                        txtTheme.setTextSize(24);
                        txtSize.setTextSize(24);
                        images.setTextSize(24);
                        btnLanguage.setTextSize(20);
                        rdDarck.setTextSize(20);
                        rdLight.setTextSize(20);
                        rbVeryBig.setTextSize(20);
                        rbBig.setTextSize(20);
                        rbMedium.setTextSize(20);
                        rbSmall.setTextSize(20);
                        rbVerySmall.setTextSize(20);
                        btnDownload.setTextSize(20);
                        txtContact.setTextSize(20);
                        txtPrivacy.setTextSize(20);
                        txtShare.setTextSize(20);
                        txtCredits.setTextSize(20);
                        break;
                }
                break;
            case "0":
                switch (sizeText) {
                    case "+2":
                        txtLanguage.setTextSize(26);
                        txtTheme.setTextSize(26);
                        txtSize.setTextSize(26);
                        images.setTextSize(26);
                        btnLanguage.setTextSize(22);
                        rdDarck.setTextSize(22);
                        rdLight.setTextSize(22);
                        rbVeryBig.setTextSize(22);
                        rbBig.setTextSize(22);
                        rbMedium.setTextSize(22);
                        rbSmall.setTextSize(22);
                        rbVerySmall.setTextSize(22);
                        btnDownload.setTextSize(22);
                        txtContact.setTextSize(22);
                        txtPrivacy.setTextSize(22);
                        txtShare.setTextSize(22);
                        txtCredits.setTextSize(22);
                        break;
                    case "+1":
                        txtLanguage.setTextSize(24);
                        txtTheme.setTextSize(24);
                        txtSize.setTextSize(24);
                        images.setTextSize(24);
                        btnLanguage.setTextSize(20);
                        rdDarck.setTextSize(20);
                        rdLight.setTextSize(20);
                        rbVeryBig.setTextSize(20);
                        rbBig.setTextSize(20);
                        rbMedium.setTextSize(20);
                        rbSmall.setTextSize(20);
                        rbVerySmall.setTextSize(20);
                        btnDownload.setTextSize(20);
                        txtContact.setTextSize(20);
                        txtPrivacy.setTextSize(20);
                        txtShare.setTextSize(20);
                        txtCredits.setTextSize(20);
                        break;
                    case "0":
                        txtLanguage.setTextSize(22);
                        txtTheme.setTextSize(22);
                        txtSize.setTextSize(22);
                        images.setTextSize(22);
                        btnLanguage.setTextSize(18);
                        rdDarck.setTextSize(18);
                        rdLight.setTextSize(18);
                        rbVeryBig.setTextSize(18);
                        rbBig.setTextSize(18);
                        rbMedium.setTextSize(18);
                        rbSmall.setTextSize(18);
                        rbVerySmall.setTextSize(18);
                        btnDownload.setTextSize(18);
                        txtContact.setTextSize(18);
                        txtPrivacy.setTextSize(18);
                        txtShare.setTextSize(18);
                        txtCredits.setTextSize(18);
                        break;
                    case "-1":
                        txtLanguage.setTextSize(20);
                        txtTheme.setTextSize(20);
                        txtSize.setTextSize(20);
                        images.setTextSize(20);
                        btnLanguage.setTextSize(16);
                        rdDarck.setTextSize(16);
                        rdLight.setTextSize(16);
                        rbVeryBig.setTextSize(16);
                        rbBig.setTextSize(16);
                        rbMedium.setTextSize(16);
                        rbSmall.setTextSize(16);
                        rbVerySmall.setTextSize(16);
                        btnDownload.setTextSize(16);
                        txtContact.setTextSize(16);
                        txtPrivacy.setTextSize(16);
                        txtShare.setTextSize(16);
                        txtCredits.setTextSize(16);
                        break;
                    case "-2":
                        txtLanguage.setTextSize(18);
                        txtTheme.setTextSize(18);
                        txtSize.setTextSize(18);
                        images.setTextSize(18);
                        btnLanguage.setTextSize(14);
                        rdDarck.setTextSize(14);
                        rdLight.setTextSize(14);
                        rbVeryBig.setTextSize(14);
                        rbBig.setTextSize(14);
                        rbMedium.setTextSize(14);
                        rbSmall.setTextSize(14);
                        rbVerySmall.setTextSize(14);
                        btnDownload.setTextSize(14);
                        txtContact.setTextSize(14);
                        txtPrivacy.setTextSize(14);
                        txtShare.setTextSize(14);
                        txtCredits.setTextSize(14);
                        break;
                }
                break;
        }
        switch (isTablet(this)) {
            case "2":
                btnDownload.setText(getResources().getString(R.string.downloadAllTablet10));
                break;
            case "1":
                btnDownload.setText(getResources().getString(R.string.downloadAllTablet7));
                break;
            case "0":
                btnDownload.setText(getResources().getString(R.string.downloadAllPhone));
                break;
        }
        btnDownload.setOnClickListener(v -> {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Settings.this);
            alertDialogBuilder.setMessage(getResources().getString(R.string.warning))
                    .setPositiveButton(getResources().getString(R.string.launch), (dialog, id) -> {
                                try {
                                    if (checkServer("https://ddd.uab.cat/record/189295?ln=ca")==200) {
                                        download();
                                    } else {
                                        AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(Settings.this);
                                        alertDialogBuilder2.setMessage(getResources().getString(R.string.serverOut))
                                                .setPositiveButton(getResources().getString(R.string.launch), (dialog2, id2) -> {
                                    });
                                        alertDialogBuilder2.create();
                                        alertDialogBuilder2.show();
                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                    )
                    .setNegativeButton(getResources().getString(R.string.cancelDownload), (dialog, id) -> {
                        Intent intent = new Intent(Settings.this, MainActivity.class);
                        startActivity(intent);
                    });
            alertDialogBuilder.create();
            alertDialogBuilder.show();
        });
        Spinner spinnerLanguage = findViewById(R.id.spinnerLanguage);
        ArrayAdapter<String> adapter0;
        if (theme.equals("light")) {
            adapter0 = new ArrayAdapter<>(this, R.layout.spinner_light, getResources().getStringArray(R.array.array_language));
        } else {
            adapter0 = new ArrayAdapter<>(this, R.layout.spinner_darck, getResources().getStringArray(R.array.array_language));
        }
        adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(adapter0);
        if (language.equals("es")) {
            spinnerLanguage.setSelection(1);
        } else if (language.equals("ca")) {
            spinnerLanguage.setSelection(2);
        } else {
            spinnerLanguage.setSelection(0);
        }
        btnLanguage.setOnClickListener(v -> {
            int posicion = spinnerLanguage.getSelectedItemPosition();
            if (posicion==0) {
                myEdit.putString("language", "en");
                myEdit.apply();
            } else if (posicion==1) {
                myEdit.putString("language", "es");
                myEdit.apply();
            } else if (posicion==2) {
                myEdit.putString("language", "ca");
                myEdit.apply();
            }
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
        });

        txtCredits.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.this, Credits.class);
            startActivity(intent);
        });
        txtPrivacy.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.es"));
            startActivity(browserIntent);
        });
        txtShare.setOnClickListener(v -> {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Minescope. Download free https://play.google.com/store/apps/");
            startActivity(Intent.createChooser(sharingIntent,"Share using"));
        });
        txtContact.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "minescope.oficial@gmail.com", null));
            startActivity(Intent.createChooser(intent, "Enviar correo electrónico"));
        });
    }
    public void download() {
        progress = new ProgressDialog(Settings.this);
        progress.setMessage("...");
        progress.show();
        progress.setCanceledOnTouchOutside(false);
        try {
            if (Settings.checkServer("https://ddd.uab.cat/record/189295?ln=ca") == 200) {
                new Thread(() -> {
                    for (int i = 0; i < urlsTransparentsToDownload.size(); i++) {
                        for (int j = 0; j < 144; j++) {
                            try {
                                URL url;
                                if (i == 36 || i == 77) {
                                    url = new URL(urlsTransparentsToDownload.get(i) + String.valueOf(j + 2) + ".jpg");
                                } else {
                                    url = new URL(urlsTransparentsToDownload.get(i) + String.valueOf(j + 1) + ".jpg");
                                }
                                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                                urlConnection.setRequestMethod("HEAD");
                                urlConnection.setConnectTimeout(5000); //set timeout to 5 seconds
                                urlConnection.connect();
                                int code = urlConnection.getResponseCode();
                                Log.i("Url nº", String.valueOf(i) + ", " + String.valueOf(j));
                                if (urlConnection != null) {
                                    urlConnection.disconnect();
                                }
                                Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                                Bitmap imageCut = null;
                                if (isTablet(this).equals("0")) {
                                    imageCut = scaleBitmapMaintainingAspectRatio(image, 400);
                                } else if (isTablet(this).equals("1")) {
                                    imageCut = scaleBitmapMaintainingAspectRatio(image, 600);
                                } else if (isTablet(this).equals("2")) {
                                    imageCut = scaleBitmapMaintainingAspectRatio(image, 800);
                                }
                                Database mYDb3 = new Database(Settings.this);
                                String fotoName = mYDb3.getFotoTransparentsFromURL(urlsTransparentsToDownload.get(i));
                                mYDb3.closeDatabase();
                                if (i < 40) {
                                    saveToInternalStorage(imageCut, fotoName + "a-" + (j + 1) + ".jpg");
                                } else {
                                    saveToInternalStorage(imageCut, fotoName + "b-" + (j + 1) + ".jpg");
                                }
                                float tantoporuno = (float) (j + 1) / (float) 144;
                                int porcentaje = Math.round(tantoporuno * 100);
                                String message = (i + 1) + "/110 " + porcentaje + "%";
                                Settings.this.runOnUiThread(() -> progress.setMessage(message));
                            } catch (IOException e) {
                                System.out.println(e);
                            }
                        }
                    }
                    for (int i = 0; i < urlsOpaquesToDownload.size(); i++) {
                        for (int j = 0; j < 72; j++) {
                            try {
                                URL url = new URL(urlsOpaquesToDownload.get(i) + (j + 1) + ".jpg");
                                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                                urlConnection.setRequestMethod("HEAD");
                                urlConnection.setConnectTimeout(5000); //set timeout to 5 seconds
                                urlConnection.connect();
                                int code = urlConnection.getResponseCode();
                                Log.i("Server Status Code", String.valueOf(code));
                                Log.i("Url nº",String.valueOf(i));
                                if (urlConnection != null) {
                                    urlConnection.disconnect();
                                }
                                Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                                Bitmap imageCut = null;
                                if (isTablet(this).equals("0")) {
                                    imageCut = scaleBitmapMaintainingAspectRatio(image, 400);
                                } else if (isTablet(this).equals("1")) {
                                    imageCut = scaleBitmapMaintainingAspectRatio(image, 600);
                                } else if (isTablet(this).equals("2")) {
                                    imageCut = scaleBitmapMaintainingAspectRatio(image, 800);
                                }
                                Database mYDb2 = new Database(Settings.this);
                                String fotoName = mYDb2.getFotoOpaquesFromURL(urlsOpaquesToDownload.get(i));
                                mYDb2.closeDatabase();
                                if (i < 15) {
                                    saveToInternalStorage(imageCut, fotoName + "a-" + (j + 1) + ".jpg");
                                } else {
                                    saveToInternalStorage(imageCut, fotoName + "b-" + (j + 1) + ".jpg");
                                }
                                float tantoporuno = (float) (j + 1) / (float) 72;
                                int porcentaje = Math.round(tantoporuno * 100);
                                String message = (i + 81) + "/110 " + porcentaje + "%";
                                Settings.this.runOnUiThread(() -> progress.setMessage(message));
                                if (i == 29 && j == 71) {
                                    progress.dismiss();
                                }
                            } catch (IOException e) {
                                System.out.println(e);
                            }
                        }
                    }
                }).start();
            } else {
                AlertDialog.Builder alertDialogBuilder3 = new AlertDialog.Builder(Settings.this);
                alertDialogBuilder3.setMessage(getResources().getString(R.string.serverOut))
                        .setPositiveButton(getResources().getString(R.string.ok), (dialog2, id2) -> {
                            progress.dismiss();
                        });
                alertDialogBuilder3.create();
                alertDialogBuilder3.show();
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
    private String saveToInternalStorage(Bitmap bitmapImage,String nameImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File mypath=new File(directory,nameImage);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 30, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
    public Bitmap scaleBitmapMaintainingAspectRatio(Bitmap originalBitmap, int newHeight) {
        int originalWidth = originalBitmap.getWidth();
        int originalHeight = originalBitmap.getHeight();
        float aspectRatio = (float) originalWidth / (float) originalHeight;
        int newWidth = Math.round(newHeight * aspectRatio);
        return Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
    }
    public String isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        if (xlarge) {
            return "2";
        } else if (large) {
            return "1";
        }
        return "0";
    }
    public static int checkServer(String targetUrl) throws InterruptedException {
        final int[] code = {0};
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean serverStatus = false;
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(targetUrl);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();

                    code[0] = connection.getResponseCode();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }

        });
                thread.start();
                thread.join();

        return code[0];
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.back) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}