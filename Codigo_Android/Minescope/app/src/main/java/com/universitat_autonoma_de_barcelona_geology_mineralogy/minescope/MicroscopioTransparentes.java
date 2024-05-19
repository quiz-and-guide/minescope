package com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MicroscopioTransparentes extends AppCompatActivity {
    private String urlParalelos;
    private String urlCruzados;
    private String id;
    private static String isTablet;
    private int numNoDescargas;
    private ImageView platina;
    private ProgressDialog progress;
    private RadioButton rdParalelos;
    private ArrayList<Drawable> imagenesParalelos, imagenesCruzados;
    private boolean paralelos, cruzados, hasFinishedParalelos, hasFinishedCruzados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_microscopio_transparentes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Database myDb = new Database(this);
        String fotoName = myDb.getFotoFromMineralTransparents(id);
        platina = findViewById(R.id.platina);
        if (isTablet(getApplicationContext()).equals("0")) {
            ViewGroup.LayoutParams layoutParams = platina.getLayoutParams();
            layoutParams.height = (int) (400 * getResources().getDisplayMetrics().density);
            platina.setLayoutParams(layoutParams);
        } else if (isTablet(getApplicationContext()).equals("1")) {
            ViewGroup.LayoutParams layoutParams = platina.getLayoutParams();
            layoutParams.height = (int) (600 * getResources().getDisplayMetrics().density);
            platina.setLayoutParams(layoutParams);
        } else if (isTablet(getApplicationContext()).equals("2")) {
            ViewGroup.LayoutParams layoutParams = platina.getLayoutParams();
            layoutParams.height = (int) (800 * getResources().getDisplayMetrics().density);
            platina.setLayoutParams(layoutParams);
        }
        isTablet = "0";
        if (isTablet(getApplicationContext()).equals("2")) {
            isTablet = "2";
        } else if (isTablet(getApplicationContext()).equals("1")) {
            isTablet = "1";
        } else if (isTablet(getApplicationContext()).equals("0")) {
            isTablet = "0";
        }
        rdParalelos = findViewById(R.id.radioParalelos);
        RadioButton rdCruzados = findViewById(R.id.radioCruzados);
        rdParalelos.setChecked(true);
        urlParalelos = myDb.getURL1TransparentsFromId(id);
        urlCruzados = myDb.getURL2TransparentsFromId(id);
        imagenesParalelos = new ArrayList<>();
        imagenesCruzados = new ArrayList<>();
        numNoDescargas = 0;
        int numImagesParalelos = 0;
        for (int i=0;i<144;i++) {
            String image = fotoName + "a-" + (i+1) + ".jpg";
            if (imageExists(image)) {
                numImagesParalelos+=1;
            }
        }
        boolean existsParalelos = numImagesParalelos == 144;
        int numImagesCruzados = 0;
        for (int i=0;i<144;i++) {
            String image = fotoName + "b-" + (i+1) + ".jpg";
            if (imageExists(image)) {
                numImagesCruzados+=1;
            }
        }
        boolean existsCruzados = numImagesCruzados == 144;
        boolean allDownloaded = existsParalelos && existsCruzados;
        Button btnDownload = findViewById(R.id.btndownload);
        switch (isTablet) {
            case "2":
                btnDownload.setText(getResources().getString(R.string.downloadThinSectionTransparentsTablet10));
                break;
            case "1":
                btnDownload.setText(getResources().getString(R.string.downloadThinSectionTransparentsTablet7));
                break;
            case "0":
                btnDownload.setText(getResources().getString(R.string.downloadThinSectionTransparentsPhone));
                break;
        }
        if (allDownloaded) {
            String imageName = fotoName + "a-1.jpg";
            loadImageFromInternalStorage(imageName);
            btnDownload.setVisibility(View.GONE);
        } else {
            String urlInicialParalelos=urlParalelos + "2.jpg";
            Picasso.get().load(urlInicialParalelos).into(platina);
        }
        btnDownload.setOnClickListener(v -> {
            if (isInternetConnected(getApplicationContext())) {
                try {
                    if (checkServer("https://ddd.uab.cat/record/189295?ln=ca") == 200) {
                        downloadAll(30);
                    } else {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MicroscopioTransparentes.this);
                        alertDialogBuilder.setMessage(getResources().getString(R.string.serverOut))
                                .setPositiveButton(getResources().getString(R.string.ok), (dialog2, id2) -> {
                                });
                        alertDialogBuilder.create();
                        alertDialogBuilder.show();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                String message = getApplicationContext().getResources().getString(R.string.noConnection);
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
        if (!isInternetConnected(getApplicationContext())) {
            String message = getApplicationContext().getResources().getString(R.string.noConnection);
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
        SharedPreferences sharedPreferences = this.getSharedPreferences("UserPreferences", MODE_PRIVATE);
        String theme = sharedPreferences.getString("theme", "light");
        String sizeText = sharedPreferences.getString("sizeText", "0");
        String language = sharedPreferences.getString("language", Locale.getDefault().getLanguage());
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getBaseContext().getResources().updateConfiguration(config, this.getBaseContext().getResources().getDisplayMetrics());
        ArrayList<String> propiedades = myDb.getLaminaTransparentes(id);
        TextView text1 = findViewById(R.id.tv1);
        TextView text2 = findViewById(R.id.tv2);
        TextView text3 = findViewById(R.id.tv3);
        TextView text4 = findViewById(R.id.tv4);
        TextView text5 = findViewById(R.id.tv5);
        TextView text6 = findViewById(R.id.tv6);
        TextView text7 = findViewById(R.id.tv7);
        TextView text8 = findViewById(R.id.tv8);
        TextView text9 = findViewById(R.id.tv9);
        TextView text10 = findViewById(R.id.tv10);
        TextView text11 = findViewById(R.id.tv11);
        TextView text12 = findViewById(R.id.tv12);
        TextView text13 = findViewById(R.id.tv13);
        TextView text14 = findViewById(R.id.tv14);
        TextView text15 = findViewById(R.id.tv15);
        TextView text16 = findViewById(R.id.tv16);
        TextView text17 = findViewById(R.id.tv17);
        TextView text18 = findViewById(R.id.tv18);
        TextView text19 = findViewById(R.id.tv19);
        TextView text20 = findViewById(R.id.tv20);
        TextView text21 = findViewById(R.id.tv21);
        TextView text22 = findViewById(R.id.tv22);
        TextView text23 = findViewById(R.id.tv23);
        TextView text24 = findViewById(R.id.tv24);
        TextView text25 = findViewById(R.id.tv25);
        LinearLayout screen = findViewById(R.id.screen);
        text1.setText(propiedades.get(0).toUpperCase());
        text3.setText(propiedades.get(1));
        text5.setText(propiedades.get(2));
        text7.setText(propiedades.get(3));
        text9.setText(propiedades.get(4));
        text11.setText(propiedades.get(5));
        text13.setText(propiedades.get(6));
        text15.setText(propiedades.get(7));
        text17.setText(propiedades.get(8));
        text19.setText(propiedades.get(9));
        text21.setText(propiedades.get(10));
        text23.setText(propiedades.get(11));
        text25.setText(propiedades.get(12));
        if (theme.equals("light")) {
            screen.setBackgroundColor(getResources().getColor(R.color.white));
            text1.setTextColor(getResources().getColor(R.color.black));
            text2.setTextColor(getResources().getColor(R.color.black));
            text3.setTextColor(getResources().getColor(R.color.black));
            text4.setTextColor(getResources().getColor(R.color.black));
            text5.setTextColor(getResources().getColor(R.color.black));
            text6.setTextColor(getResources().getColor(R.color.black));
            text7.setTextColor(getResources().getColor(R.color.black));
            text8.setTextColor(getResources().getColor(R.color.black));
            text9.setTextColor(getResources().getColor(R.color.black));
            text10.setTextColor(getResources().getColor(R.color.black));
            text11.setTextColor(getResources().getColor(R.color.black));
            text12.setTextColor(getResources().getColor(R.color.black));
            text13.setTextColor(getResources().getColor(R.color.black));
            text14.setTextColor(getResources().getColor(R.color.black));
            text15.setTextColor(getResources().getColor(R.color.black));
            text16.setTextColor(getResources().getColor(R.color.black));
            text17.setTextColor(getResources().getColor(R.color.black));
            text18.setTextColor(getResources().getColor(R.color.black));
            text19.setTextColor(getResources().getColor(R.color.black));
            text20.setTextColor(getResources().getColor(R.color.black));
            text21.setTextColor(getResources().getColor(R.color.black));
            text22.setTextColor(getResources().getColor(R.color.black));
            text23.setTextColor(getResources().getColor(R.color.black));
            text24.setTextColor(getResources().getColor(R.color.black));
            text25.setTextColor(getResources().getColor(R.color.black));
            rdParalelos.setTextColor(getResources().getColor(R.color.black));
            rdCruzados.setTextColor(getResources().getColor(R.color.black));
        } else {
            screen.setBackgroundColor(getResources().getColor(R.color.black));
            text1.setTextColor(getResources().getColor(R.color.white));
            text2.setTextColor(getResources().getColor(R.color.white));
            text3.setTextColor(getResources().getColor(R.color.white));
            text4.setTextColor(getResources().getColor(R.color.white));
            text5.setTextColor(getResources().getColor(R.color.white));
            text6.setTextColor(getResources().getColor(R.color.white));
            text7.setTextColor(getResources().getColor(R.color.white));
            text8.setTextColor(getResources().getColor(R.color.white));
            text9.setTextColor(getResources().getColor(R.color.white));
            text10.setTextColor(getResources().getColor(R.color.white));
            text11.setTextColor(getResources().getColor(R.color.white));
            text12.setTextColor(getResources().getColor(R.color.white));
            text13.setTextColor(getResources().getColor(R.color.white));
            text14.setTextColor(getResources().getColor(R.color.white));
            text15.setTextColor(getResources().getColor(R.color.white));
            text16.setTextColor(getResources().getColor(R.color.white));
            text17.setTextColor(getResources().getColor(R.color.white));
            text18.setTextColor(getResources().getColor(R.color.white));
            text19.setTextColor(getResources().getColor(R.color.white));
            text20.setTextColor(getResources().getColor(R.color.white));
            text21.setTextColor(getResources().getColor(R.color.white));
            text22.setTextColor(getResources().getColor(R.color.white));
            text23.setTextColor(getResources().getColor(R.color.white));
            text24.setTextColor(getResources().getColor(R.color.white));
            text25.setTextColor(getResources().getColor(R.color.white));
            rdParalelos.setTextColor(getResources().getColor(R.color.white));
            rdCruzados.setTextColor(getResources().getColor(R.color.white));
        }
        switch (isTablet) {
            case "2":
                switch (sizeText) {
                    case "+2":
                        text1.setTextSize(38);
                        text2.setTextSize(34);
                        text3.setTextSize(34);
                        text4.setTextSize(34);
                        text5.setTextSize(34);
                        text6.setTextSize(34);
                        text7.setTextSize(34);
                        text8.setTextSize(34);
                        text9.setTextSize(34);
                        text10.setTextSize(34);
                        text11.setTextSize(34);
                        text12.setTextSize(34);
                        text13.setTextSize(34);
                        text14.setTextSize(34);
                        text15.setTextSize(34);
                        text16.setTextSize(34);
                        text17.setTextSize(34);
                        text18.setTextSize(34);
                        text19.setTextSize(34);
                        text20.setTextSize(34);
                        text21.setTextSize(34);
                        text22.setTextSize(34);
                        text23.setTextSize(34);
                        text24.setTextSize(34);
                        text25.setTextSize(34);
                        rdCruzados.setTextSize(34);
                        rdParalelos.setTextSize(34);
                        btnDownload.setTextSize(34);
                        break;
                    case "+1":
                        text1.setTextSize(36);
                        text2.setTextSize(32);
                        text3.setTextSize(32);
                        text4.setTextSize(32);
                        text5.setTextSize(32);
                        text6.setTextSize(32);
                        text7.setTextSize(32);
                        text8.setTextSize(32);
                        text9.setTextSize(32);
                        text10.setTextSize(32);
                        text11.setTextSize(32);
                        text12.setTextSize(32);
                        text13.setTextSize(32);
                        text14.setTextSize(32);
                        text15.setTextSize(32);
                        text16.setTextSize(32);
                        text17.setTextSize(32);
                        text18.setTextSize(32);
                        text19.setTextSize(32);
                        text20.setTextSize(32);
                        text21.setTextSize(32);
                        text22.setTextSize(32);
                        text23.setTextSize(32);
                        text24.setTextSize(32);
                        text25.setTextSize(32);
                        rdCruzados.setTextSize(32);
                        rdParalelos.setTextSize(32);
                        btnDownload.setTextSize(32);
                        break;
                    case "0":
                        text1.setTextSize(34);
                        text2.setTextSize(30);
                        text3.setTextSize(30);
                        text4.setTextSize(30);
                        text5.setTextSize(30);
                        text6.setTextSize(30);
                        text7.setTextSize(30);
                        text8.setTextSize(30);
                        text9.setTextSize(30);
                        text10.setTextSize(30);
                        text11.setTextSize(30);
                        text12.setTextSize(30);
                        text13.setTextSize(30);
                        text14.setTextSize(30);
                        text15.setTextSize(30);
                        text16.setTextSize(30);
                        text17.setTextSize(30);
                        text18.setTextSize(30);
                        text19.setTextSize(30);
                        text20.setTextSize(30);
                        text21.setTextSize(30);
                        text22.setTextSize(30);
                        text23.setTextSize(30);
                        text24.setTextSize(30);
                        text25.setTextSize(30);
                        rdCruzados.setTextSize(30);
                        rdParalelos.setTextSize(30);
                        btnDownload.setTextSize(30);
                        break;
                    case "-1":
                        text1.setTextSize(32);
                        text2.setTextSize(28);
                        text3.setTextSize(28);
                        text4.setTextSize(28);
                        text5.setTextSize(28);
                        text6.setTextSize(28);
                        text7.setTextSize(28);
                        text8.setTextSize(28);
                        text9.setTextSize(28);
                        text10.setTextSize(28);
                        text11.setTextSize(28);
                        text12.setTextSize(28);
                        text13.setTextSize(28);
                        text14.setTextSize(28);
                        text15.setTextSize(28);
                        text16.setTextSize(28);
                        text17.setTextSize(28);
                        text18.setTextSize(28);
                        text19.setTextSize(28);
                        text20.setTextSize(28);
                        text21.setTextSize(28);
                        text22.setTextSize(28);
                        text23.setTextSize(28);
                        text24.setTextSize(28);
                        text25.setTextSize(28);
                        rdCruzados.setTextSize(28);
                        rdParalelos.setTextSize(28);
                        btnDownload.setTextSize(28);
                        break;
                    case "-2":
                        text1.setTextSize(30);
                        text2.setTextSize(26);
                        text3.setTextSize(26);
                        text4.setTextSize(26);
                        text5.setTextSize(26);
                        text6.setTextSize(26);
                        text7.setTextSize(26);
                        text8.setTextSize(26);
                        text9.setTextSize(26);
                        text10.setTextSize(26);
                        text11.setTextSize(26);
                        text12.setTextSize(26);
                        text13.setTextSize(26);
                        text14.setTextSize(26);
                        text15.setTextSize(26);
                        text16.setTextSize(26);
                        text17.setTextSize(26);
                        text18.setTextSize(26);
                        text19.setTextSize(26);
                        text20.setTextSize(26);
                        text21.setTextSize(26);
                        text22.setTextSize(26);
                        text23.setTextSize(26);
                        text24.setTextSize(26);
                        text25.setTextSize(26);
                        rdCruzados.setTextSize(26);
                        rdParalelos.setTextSize(26);
                        btnDownload.setTextSize(26);
                        break;

                }
                break;
            case "1":
                switch (sizeText) {
                    case "+2":
                        text1.setTextSize(32);
                        text2.setTextSize(28);
                        text3.setTextSize(28);
                        text4.setTextSize(28);
                        text5.setTextSize(28);
                        text6.setTextSize(28);
                        text7.setTextSize(28);
                        text8.setTextSize(28);
                        text9.setTextSize(28);
                        text10.setTextSize(28);
                        text11.setTextSize(28);
                        text12.setTextSize(28);
                        text13.setTextSize(28);
                        text14.setTextSize(28);
                        text15.setTextSize(28);
                        text16.setTextSize(28);
                        text17.setTextSize(28);
                        text18.setTextSize(28);
                        text19.setTextSize(28);
                        text20.setTextSize(28);
                        text21.setTextSize(28);
                        text22.setTextSize(28);
                        text23.setTextSize(28);
                        text24.setTextSize(28);
                        text25.setTextSize(28);
                        rdCruzados.setTextSize(28);
                        rdParalelos.setTextSize(28);
                        btnDownload.setTextSize(28);
                        break;
                    case "+1":
                        text1.setTextSize(30);
                        text2.setTextSize(26);
                        text3.setTextSize(26);
                        text4.setTextSize(26);
                        text5.setTextSize(26);
                        text6.setTextSize(26);
                        text7.setTextSize(26);
                        text8.setTextSize(26);
                        text9.setTextSize(26);
                        text10.setTextSize(26);
                        text11.setTextSize(26);
                        text12.setTextSize(26);
                        text13.setTextSize(26);
                        text14.setTextSize(26);
                        text15.setTextSize(26);
                        text16.setTextSize(26);
                        text17.setTextSize(26);
                        text18.setTextSize(26);
                        text19.setTextSize(26);
                        text20.setTextSize(26);
                        text21.setTextSize(26);
                        text22.setTextSize(26);
                        text23.setTextSize(26);
                        text24.setTextSize(26);
                        text25.setTextSize(26);
                        rdCruzados.setTextSize(26);
                        rdParalelos.setTextSize(26);
                        btnDownload.setTextSize(26);
                        break;
                    case "0":
                        text1.setTextSize(28);
                        text2.setTextSize(24);
                        text3.setTextSize(24);
                        text4.setTextSize(24);
                        text5.setTextSize(24);
                        text6.setTextSize(24);
                        text7.setTextSize(24);
                        text8.setTextSize(24);
                        text9.setTextSize(24);
                        text10.setTextSize(24);
                        text11.setTextSize(24);
                        text12.setTextSize(24);
                        text13.setTextSize(24);
                        text14.setTextSize(24);
                        text15.setTextSize(24);
                        text16.setTextSize(24);
                        text17.setTextSize(24);
                        text18.setTextSize(24);
                        text19.setTextSize(24);
                        text20.setTextSize(24);
                        text21.setTextSize(24);
                        text22.setTextSize(24);
                        text23.setTextSize(24);
                        text24.setTextSize(24);
                        text25.setTextSize(24);
                        rdCruzados.setTextSize(24);
                        rdParalelos.setTextSize(24);
                        btnDownload.setTextSize(24);
                        break;
                    case "-1":
                        text1.setTextSize(26);
                        text2.setTextSize(22);
                        text3.setTextSize(22);
                        text4.setTextSize(22);
                        text5.setTextSize(22);
                        text6.setTextSize(22);
                        text7.setTextSize(22);
                        text8.setTextSize(22);
                        text9.setTextSize(22);
                        text10.setTextSize(22);
                        text11.setTextSize(22);
                        text12.setTextSize(22);
                        text13.setTextSize(22);
                        text14.setTextSize(22);
                        text15.setTextSize(22);
                        text16.setTextSize(22);
                        text17.setTextSize(22);
                        text18.setTextSize(22);
                        text19.setTextSize(22);
                        text20.setTextSize(22);
                        text21.setTextSize(22);
                        text22.setTextSize(22);
                        text23.setTextSize(22);
                        text24.setTextSize(22);
                        text25.setTextSize(22);
                        rdCruzados.setTextSize(22);
                        rdParalelos.setTextSize(22);
                        btnDownload.setTextSize(22);
                        break;
                    case "-2":
                        text1.setTextSize(24);
                        text2.setTextSize(20);
                        text3.setTextSize(20);
                        text4.setTextSize(20);
                        text5.setTextSize(20);
                        text6.setTextSize(20);
                        text7.setTextSize(20);
                        text8.setTextSize(20);
                        text9.setTextSize(20);
                        text10.setTextSize(20);
                        text11.setTextSize(20);
                        text12.setTextSize(20);
                        text13.setTextSize(20);
                        text14.setTextSize(20);
                        text15.setTextSize(20);
                        text16.setTextSize(20);
                        text17.setTextSize(20);
                        text18.setTextSize(20);
                        text19.setTextSize(20);
                        text20.setTextSize(20);
                        text21.setTextSize(20);
                        text22.setTextSize(20);
                        text23.setTextSize(20);
                        text24.setTextSize(20);
                        text25.setTextSize(20);
                        rdCruzados.setTextSize(20);
                        rdParalelos.setTextSize(20);
                        btnDownload.setTextSize(20);
                        break;
                }
                break;
            case "0":
                switch (sizeText) {
                    case "+2":
                        text1.setTextSize(26);
                        text2.setTextSize(22);
                        text3.setTextSize(22);
                        text4.setTextSize(22);
                        text5.setTextSize(22);
                        text6.setTextSize(22);
                        text7.setTextSize(22);
                        text8.setTextSize(22);
                        text9.setTextSize(22);
                        text10.setTextSize(22);
                        text11.setTextSize(22);
                        text12.setTextSize(22);
                        text13.setTextSize(22);
                        text14.setTextSize(22);
                        text15.setTextSize(22);
                        text16.setTextSize(22);
                        text17.setTextSize(22);
                        text18.setTextSize(22);
                        text19.setTextSize(22);
                        text20.setTextSize(22);
                        text21.setTextSize(22);
                        text22.setTextSize(22);
                        text23.setTextSize(22);
                        text24.setTextSize(22);
                        text25.setTextSize(22);
                        rdCruzados.setTextSize(22);
                        rdParalelos.setTextSize(22);
                        btnDownload.setTextSize(22);
                        break;
                    case "+1":
                        text1.setTextSize(24);
                        text2.setTextSize(20);
                        text3.setTextSize(20);
                        text4.setTextSize(20);
                        text5.setTextSize(20);
                        text6.setTextSize(20);
                        text7.setTextSize(20);
                        text8.setTextSize(20);
                        text9.setTextSize(20);
                        text10.setTextSize(20);
                        text11.setTextSize(20);
                        text12.setTextSize(20);
                        text13.setTextSize(20);
                        text14.setTextSize(20);
                        text15.setTextSize(20);
                        text16.setTextSize(20);
                        text17.setTextSize(20);
                        text18.setTextSize(20);
                        text19.setTextSize(20);
                        text20.setTextSize(20);
                        text21.setTextSize(20);
                        text22.setTextSize(20);
                        text23.setTextSize(20);
                        text24.setTextSize(20);
                        text25.setTextSize(20);
                        rdCruzados.setTextSize(20);
                        rdParalelos.setTextSize(20);
                        btnDownload.setTextSize(20);
                        break;
                    case "0":
                        text1.setTextSize(22);
                        text2.setTextSize(18);
                        text3.setTextSize(18);
                        text4.setTextSize(18);
                        text5.setTextSize(18);
                        text6.setTextSize(18);
                        text7.setTextSize(18);
                        text8.setTextSize(18);
                        text9.setTextSize(18);
                        text10.setTextSize(18);
                        text11.setTextSize(18);
                        text12.setTextSize(18);
                        text13.setTextSize(18);
                        text14.setTextSize(18);
                        text15.setTextSize(18);
                        text16.setTextSize(18);
                        text17.setTextSize(18);
                        text18.setTextSize(18);
                        text19.setTextSize(18);
                        text20.setTextSize(18);
                        text21.setTextSize(18);
                        text22.setTextSize(18);
                        text23.setTextSize(18);
                        text24.setTextSize(18);
                        text25.setTextSize(18);
                        rdCruzados.setTextSize(18);
                        rdParalelos.setTextSize(18);
                        btnDownload.setTextSize(18);
                        break;
                    case "-1":
                        text1.setTextSize(20);
                        text2.setTextSize(16);
                        text3.setTextSize(16);
                        text4.setTextSize(16);
                        text5.setTextSize(16);
                        text6.setTextSize(16);
                        text7.setTextSize(16);
                        text8.setTextSize(16);
                        text9.setTextSize(16);
                        text10.setTextSize(16);
                        text11.setTextSize(16);
                        text12.setTextSize(16);
                        text13.setTextSize(16);
                        text14.setTextSize(16);
                        text15.setTextSize(16);
                        text16.setTextSize(16);
                        text17.setTextSize(16);
                        text18.setTextSize(16);
                        text19.setTextSize(16);
                        text20.setTextSize(16);
                        text21.setTextSize(16);
                        text22.setTextSize(16);
                        text23.setTextSize(16);
                        text24.setTextSize(16);
                        text25.setTextSize(16);
                        rdCruzados.setTextSize(16);
                        rdParalelos.setTextSize(16);
                        btnDownload.setTextSize(16);
                        break;
                    case "-2":
                        text1.setTextSize(18);
                        text2.setTextSize(14);
                        text3.setTextSize(14);
                        text4.setTextSize(14);
                        text5.setTextSize(14);
                        text6.setTextSize(14);
                        text7.setTextSize(14);
                        text8.setTextSize(14);
                        text9.setTextSize(14);
                        text10.setTextSize(14);
                        text11.setTextSize(14);
                        text12.setTextSize(14);
                        text13.setTextSize(14);
                        text14.setTextSize(14);
                        text15.setTextSize(14);
                        text16.setTextSize(14);
                        text17.setTextSize(14);
                        text18.setTextSize(14);
                        text19.setTextSize(14);
                        text20.setTextSize(14);
                        text21.setTextSize(14);
                        text22.setTextSize(14);
                        text23.setTextSize(14);
                        text24.setTextSize(14);
                        text25.setTextSize(14);
                        rdCruzados.setTextSize(14);
                        rdParalelos.setTextSize(14);
                        btnDownload.setTextSize(14);
                        break;
                }
                break;
        }
        myDb.closeDatabase();
        SeekBar seekBar = findViewById(R.id.seek);
        seekBar.setProgress(0);
        seekBar.incrementProgressBy(1);
        seekBar.setMax(143);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (allDownloaded) {
                    if (rdParalelos.isChecked()) {
                        String imageName = fotoName + "a-" + (progress + 1) + ".jpg";
                        loadImageFromInternalStorage(imageName);
                    } else {
                        String imageName = fotoName + "b-" + (progress + 1) + ".jpg";
                        loadImageFromInternalStorage(imageName);
                    }
                } else {
                    if (rdParalelos.isChecked()) {
                        if (!paralelos) {
                            paralelos = true;
                            try {
                                if (checkServer("https://ddd.uab.cat/record/189295?ln=ca") == 200) {
                                    descargaParalelos();
                                } else {
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MicroscopioTransparentes.this);
                                    alertDialogBuilder.setMessage(getResources().getString(R.string.serverOut))
                                            .setPositiveButton(getResources().getString(R.string.ok), (dialog2, id2) -> {
                                            });
                                    alertDialogBuilder.create();
                                    alertDialogBuilder.show();
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else if (hasFinishedParalelos) {
                            MicroscopioTransparentes.this.runOnUiThread(() -> platina.setImageDrawable(imagenesParalelos.get(progress)));
                        }
                    } else {
                        if (!cruzados) {
                            cruzados = true;
                            try {
                                if (checkServer("https://ddd.uab.cat/record/189295?ln=ca") == 200) {
                                    descargaCruzados();
                                } else {
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MicroscopioTransparentes.this);
                                    alertDialogBuilder.setMessage(getResources().getString(R.string.serverOut))
                                            .setPositiveButton(getResources().getString(R.string.ok), (dialog2, id2) -> {
                                            });
                                    alertDialogBuilder.create();
                                    alertDialogBuilder.show();
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else if (hasFinishedCruzados) {
                            MicroscopioTransparentes.this.runOnUiThread(() -> platina.setImageDrawable(imagenesCruzados.get(progress)));
                        }
                    }
                }
            }
        });
        RadioGroup radiogroup = findViewById(R.id.radiogroup);
        radiogroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioParalelos) {
                if (allDownloaded) {
                    String imageName = fotoName + "a-" + (seekBar.getProgress() + 1) + ".jpg";
                    loadImageFromInternalStorage(imageName);
                } else {
                    String urlInicialParalelos="";
                    if (id.equals("37")) {
                        urlInicialParalelos = urlParalelos + (seekBar.getProgress() + 2) + ".jpg";
                    } else {
                        urlInicialParalelos = urlParalelos + (seekBar.getProgress() + 1) + ".jpg";
                    }
                    Picasso.get().load(urlInicialParalelos).into(platina);
                }
            } else if(checkedId == R.id.radioCruzados) {
                if (allDownloaded) {
                    String imageName = fotoName + "b-" + (seekBar.getProgress() + 1) + ".jpg";
                    loadImageFromInternalStorage(imageName);
                } else {
                    String urlInicialCruzados="";
                    if (id.equals("38")) {
                        urlInicialCruzados = urlCruzados + (seekBar.getProgress() + 2) + ".jpg";
                    } else {
                        urlInicialCruzados = urlCruzados + (seekBar.getProgress() + 1) + ".jpg";
                    }
                    Picasso.get().load(urlInicialCruzados).into(platina);
                }
            }
        });
    }
    private void saveToInternalStorage(Bitmap bitmapImage,String nameImage, int compression){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File mypath=new File(directory,nameImage);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, compression, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void descargaParalelos() {
        imagenesParalelos.clear();
        progress = new ProgressDialog(MicroscopioTransparentes.this);
        progress.setMessage("Loading images....");
        progress.setCancelable(false);
        progress.show();
        new Thread(() -> {
            for(int i= 0 ;i < 144; i++) {
                try {
                    if (id.equals("37")) {
                        imagenesParalelos.add(drawableFromUrl(urlParalelos + (i + 2) + ".jpg"));
                    } else {
                        imagenesParalelos.add(drawableFromUrl(urlParalelos + (i + 1) + ".jpg"));
                    }
                } catch (Exception exc) {
                }
                float tantoporuno = (float)(i+1)/(float)144;
                int porcentaje = Math.round(tantoporuno*100);
                String message = "Loading..." + porcentaje + "%. You can download all the thin section not to consume data and to avoid this waiting time.";
                MicroscopioTransparentes.this.runOnUiThread(() -> progress.setMessage(message));
                if (i==143) {
                    progress.dismiss();
                    hasFinishedParalelos = true;
                }
            }
        }).start();
    }
    public void descargaCruzados() {
        imagenesCruzados.clear();
        progress = new ProgressDialog(MicroscopioTransparentes.this);
        progress.setMessage("Loading images....");
        progress.setCancelable(false);
        progress.show();
        new Thread(() -> {
            for(int i= 0 ;i < 144; i++) {
                try {
                    if (id.equals("38")) {
                        imagenesCruzados.add(drawableFromUrl(urlCruzados + (i + 2) + ".jpg"));
                    } else {
                        imagenesCruzados.add(drawableFromUrl(urlCruzados + (i + 1) + ".jpg"));
                    }
                } catch (Exception exc) {
                }
                float tantoporuno = (float)(i+1)/(float)144;
                int porcentaje = Math.round(tantoporuno*100);
                String message = "Loading..." + porcentaje + "%. You can download all the thin section not to consume data and to avoid this waiting time.";
                MicroscopioTransparentes.this.runOnUiThread(() -> progress.setMessage(message));
                if (i==143) {
                    progress.dismiss();
                    hasFinishedCruzados = true;
                }
            }
        }).start();
    }
    public void downloadAll(int compression) {
        progress = new ProgressDialog(MicroscopioTransparentes.this);
        progress.setMessage("Starting download...");
        progress.show();
        progress.setCanceledOnTouchOutside(false);
        new Thread(() -> {
            for (int j=0;j<144;j++) {
                try {
                    URL url;
                    if (id.equals("37")) {
                        url = new URL(urlParalelos + (j + 2) + ".jpg");
                    } else {
                        url = new URL(urlParalelos + (j + 1) + ".jpg");
                    }
                    Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    Bitmap imageCut = null;
                    if (isTablet(this).equals("0")) {
                        imageCut = scaleBitmapMaintainingAspectRatio(image,400);
                    } else if (isTablet(this).equals("1")) {
                        imageCut = scaleBitmapMaintainingAspectRatio(image,600);
                    } else if (isTablet(this).equals("2")) {
                        imageCut = scaleBitmapMaintainingAspectRatio(image,800);
                    }
                    Database mYDb3 = new Database(MicroscopioTransparentes.this);
                    String fotoName = mYDb3.getFotoTransparentsFromURL(urlParalelos);
                    mYDb3.closeDatabase();
                    saveToInternalStorage(imageCut, fotoName + "a-" + (j + 1) + ".jpg", compression);
                    float tantoporuno = (float)(j+1)/(float)144;
                    int porcentaje = Math.round(tantoporuno*100);
                    String message = porcentaje / 2 + "%";
                    MicroscopioTransparentes.this.runOnUiThread(() -> progress.setMessage(message));
                } catch (IOException e) {
                    System.out.println(e);
                    numNoDescargas += 1;
                }
            }
            for (int j=0;j<144;j++) {
                try {
                    URL url;
                    if (id.equals("38")) {
                        url = new URL(urlCruzados + (j + 2) + ".jpg");
                    } else {
                        url = new URL(urlCruzados + (j + 1) + ".jpg");
                    }
                    Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    Bitmap imageCut = null;
                    if (isTablet(this).equals("0")) {
                        imageCut = scaleBitmapMaintainingAspectRatio(image,400);
                    } else if (isTablet(this).equals("1")) {
                        imageCut = scaleBitmapMaintainingAspectRatio(image,600);
                    } else if (isTablet(this).equals("2")) {
                        imageCut = scaleBitmapMaintainingAspectRatio(image,800);
                    }
                    Database mYDb3 = new Database(MicroscopioTransparentes.this);
                    String fotoName = mYDb3.getFotoTransparentsFromURL(urlCruzados);
                    mYDb3.closeDatabase();
                    saveToInternalStorage(imageCut, fotoName + "b-" + (j + 1) + ".jpg", compression);
                    float tantoporuno = (float)(j+1)/(float)144;
                    int porcentaje = Math.round(tantoporuno*100);
                    String message = porcentaje / 2 + 50 + "%";
                    MicroscopioTransparentes.this.runOnUiThread(() -> progress.setMessage(message));
                    if (j==143) {
                        progress.dismiss();
                        if (numNoDescargas>0) {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MicroscopioTransparentes.this);
                            alertDialogBuilder.setMessage(getResources().getString(R.string.problemDownload))
                                    .setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                        }
                                    });
                            alertDialogBuilder.create();
                            alertDialogBuilder.show();
                        }
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                } catch (IOException e) {
                    System.out.println(e);
                    numNoDescargas += 1;
                }
            }
        }).start();
    }
    public static Drawable drawableFromUrl(String url) throws IOException {
        Bitmap x;
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();
        x = BitmapFactory.decodeStream(input);
        int originalWidth = x.getWidth();
        int originalHeight = x.getHeight();
        int newHeight = 0;
        switch (isTablet) {
            case "2":
                newHeight = 800;
                break;
            case "1":
                newHeight = 600;
                break;
            case "0":
                newHeight = 400;
                break;
        }
        int newWidth = (int) Math.round((double) originalWidth * ((double) newHeight / (double) originalHeight));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(x, newWidth, newHeight, true);
        return new BitmapDrawable(Resources.getSystem(), resizedBitmap);
    }
    public Bitmap scaleBitmapMaintainingAspectRatio(Bitmap originalBitmap, int newHeight) {
        int originalWidth = originalBitmap.getWidth();
        int originalHeight = originalBitmap.getHeight();
        float aspectRatio = (float) originalWidth / (float) originalHeight;
        int newWidth = Math.round(newHeight * aspectRatio);
        return Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
    }
    private void loadImageFromInternalStorage(String imageName) {
        ContextWrapper cw = new ContextWrapper(this);
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File mypath = new File(directory, imageName);
        ImageView imageView = findViewById(R.id.platina);
        imageView.setImageDrawable(Drawable.createFromPath(mypath.toString()));
    }
    private boolean imageExists(String imageName) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File imageFile = new File(directory, imageName);
        return imageFile.exists();
    }
    public static boolean isInternetConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
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