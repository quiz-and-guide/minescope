package com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

public class FichaOpacos extends AppCompatActivity {
    private ImageView platina1,platina2,platina3,platina4;
    private  String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_opacos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Database myDb = new Database(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Log.i("id",id);
        SharedPreferences sharedPreferences = this.getSharedPreferences("UserPreferences", MODE_PRIVATE);
        String theme = sharedPreferences.getString("theme", "light");
        String sizeText = sharedPreferences.getString("sizeText", "0");
        String language = sharedPreferences.getString("language", Locale.getDefault().getLanguage());
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getBaseContext().getResources().updateConfiguration(config, this.getBaseContext().getResources().getDisplayMetrics());
        ArrayList<String> propiedades = myDb.getGeneralesOpacos(id);
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
        text1.setText(propiedades.get(0).toUpperCase());
        text3.setText(Html.fromHtml(propiedades.get(2)));
        text5.setText(propiedades.get(3));
        text7.setText(propiedades.get(4));
        text9.setText(propiedades.get(5));
        text11.setText(propiedades.get(6));
        text13.setText(propiedades.get(7));
        text15.setText(propiedades.get(8));
        text17.setText(propiedades.get(9));
        text19.setText(propiedades.get(10));
        text21.setText(propiedades.get(11));
        platina1 = findViewById(R.id.platina1);
        platina2 = findViewById(R.id.platina2);
        platina3 = findViewById(R.id.platina3);
        platina4 = findViewById(R.id.platina4);
        RadioGroup rg1 = findViewById(R.id.radiogroup1);
        RadioGroup rg2 = findViewById(R.id.radiogroup2);
        RadioGroup rg3 = findViewById(R.id.radiogroup3);
        RadioGroup rg4 = findViewById(R.id.radiogroup4);
        RadioButton rbparalelos1 = findViewById(R.id.radioParalelos1);
        RadioButton rbparalelos2 = findViewById(R.id.radioParalelos2);
        RadioButton rbparalelos3 = findViewById(R.id.radioParalelos3);
        RadioButton rbparalelos4 = findViewById(R.id.radioParalelos4);
        RadioButton rbcruzados1 = findViewById(R.id.radioCruzados1);
        RadioButton rbcruzados2 = findViewById(R.id.radioCruzados2);
        RadioButton rbcruzados3 = findViewById(R.id.radioCruzados3);
        RadioButton rbcruzados4 = findViewById(R.id.radioCruzados4);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        TextView texto1 = findViewById(R.id.text1);
        TextView texto2 = findViewById(R.id.text2);
        TextView texto3 = findViewById(R.id.text3);
        TextView texto4 = findViewById(R.id.text4);
        ScrollView screen = findViewById(R.id.screen);
        String isTablet = "0";
        if (isTablet(getApplicationContext()).equals("2")) {
            isTablet = "2";
        } else if (isTablet(getApplicationContext()).equals("1")) {
            isTablet = "1";
        } else if (isTablet(getApplicationContext()).equals("0")) {
            isTablet = "0";
        }
        String fotoLast = myDb.getFotoFromMineralOpaques(id);
        String numberMinerals = fotoLast.substring(fotoLast.length()-1);
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
            texto1.setTextColor(getResources().getColor(R.color.black));
            texto2.setTextColor(getResources().getColor(R.color.black));
            texto3.setTextColor(getResources().getColor(R.color.black));
            texto4.setTextColor(getResources().getColor(R.color.black));
            rbparalelos1.setTextColor(getResources().getColor(R.color.black));
            rbparalelos2.setTextColor(getResources().getColor(R.color.black));
            rbparalelos3.setTextColor(getResources().getColor(R.color.black));
            rbparalelos4.setTextColor(getResources().getColor(R.color.black));
            rbcruzados1.setTextColor(getResources().getColor(R.color.black));
            rbcruzados2.setTextColor(getResources().getColor(R.color.black));
            rbcruzados3.setTextColor(getResources().getColor(R.color.black));
            rbcruzados4.setTextColor(getResources().getColor(R.color.black));
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
            texto1.setTextColor(getResources().getColor(R.color.white));
            texto2.setTextColor(getResources().getColor(R.color.white));
            texto3.setTextColor(getResources().getColor(R.color.white));
            texto4.setTextColor(getResources().getColor(R.color.white));
            rbparalelos1.setTextColor(getResources().getColor(R.color.white));
            rbparalelos2.setTextColor(getResources().getColor(R.color.white));
            rbparalelos3.setTextColor(getResources().getColor(R.color.white));
            rbparalelos4.setTextColor(getResources().getColor(R.color.white));
            rbcruzados1.setTextColor(getResources().getColor(R.color.white));
            rbcruzados2.setTextColor(getResources().getColor(R.color.white));
            rbcruzados3.setTextColor(getResources().getColor(R.color.white));
            rbcruzados4.setTextColor(getResources().getColor(R.color.white));
        }
        switch (numberMinerals) {
            case "1":
                platina2.setVisibility(View.GONE);
                platina3.setVisibility(View.GONE);
                platina4.setVisibility(View.GONE);
                rg2.setVisibility(View.GONE);
                rg3.setVisibility(View.GONE);
                rg4.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                button3.setVisibility(View.GONE);
                button4.setVisibility(View.GONE);
                texto2.setVisibility(View.GONE);
                texto3.setVisibility(View.GONE);
                texto4.setVisibility(View.GONE);
                rbparalelos1.setChecked(true);
                String description1 = myDb.getDescriptionOpaques(id);
                texto1.setText(description1.toUpperCase());
                String nameImage = myDb.getFotoFromMineralOpaques(id);
                String completImageName = nameImage + "a-1.jpg";
                boolean imageExist = imageExists(completImageName);
                if (imageExist) {
                    loadImageFromInternalStorage(completImageName,platina1);
                } else {
                    String urlParalelos = myDb.getURL1OpaquesFromId(id)+"1.jpg";
                    Picasso.get().load(urlParalelos).into(platina1);
                }
                break;
            case "2":
                platina3.setVisibility(View.GONE);
                platina4.setVisibility(View.GONE);
                rg3.setVisibility(View.GONE);
                rg4.setVisibility(View.GONE);
                button3.setVisibility(View.GONE);
                button4.setVisibility(View.GONE);
                texto3.setVisibility(View.GONE);
                texto4.setVisibility(View.GONE);
                rbparalelos1.setChecked(true);
                rbparalelos2.setChecked(true);
                String description2 = myDb.getDescriptionOpaques(id);
                texto1.setText(description2.toUpperCase());
                String description3 = myDb.getDescriptionOpaques(String.valueOf(Integer.parseInt(id)-1));
                texto2.setText(description3.toUpperCase());
                String nameImage1 = myDb.getFotoFromMineralOpaques(id);
                String completImageName1 = nameImage1 + "a-1.jpg";
                boolean imageExist1 = imageExists(completImageName1);
                if (imageExist1) {
                    loadImageFromInternalStorage(completImageName1,platina1);
                } else {
                    String urlParalelos1 = myDb.getURL1OpaquesFromId(id)+"1.jpg";
                    Picasso.get().load(urlParalelos1).into(platina1);
                }
                String nameImage2 = myDb.getFotoFromMineralOpaques(String.valueOf(Integer.parseInt(id)-1));
                String completImageName2 = nameImage2 + "a-1.jpg";
                boolean imageExist2 = imageExists(completImageName2);
                if (imageExist2) {
                    loadImageFromInternalStorage(completImageName2,platina2);
                } else {
                    String urlParalelos2 = myDb.getURL1OpaquesFromId(String.valueOf(Integer.parseInt(id)-1))+"1.jpg";
                    Picasso.get().load(urlParalelos2).into(platina2);
                }
                break;
            case "4":
                rbparalelos1.setChecked(true);
                rbparalelos2.setChecked(true);
                rbparalelos3.setChecked(true);
                rbparalelos4.setChecked(true);
                String description4 = myDb.getDescriptionOpaques(id);
                texto1.setText(description4.toUpperCase());
                String description5 = myDb.getDescriptionOpaques(String.valueOf(Integer.parseInt(id)-1));
                texto2.setText(description5.toUpperCase());
                String description6 = myDb.getDescriptionOpaques(String.valueOf(Integer.parseInt(id)-2));
                texto3.setText(description6.toUpperCase());
                String description7 = myDb.getDescriptionOpaques(String.valueOf(Integer.parseInt(id)-3));
                texto4.setText(description7.toUpperCase());
                String nameImage3 = myDb.getFotoFromMineralOpaques(id);
                String completImageName3 = nameImage3 + "a-1.jpg";
                boolean imageExist3 = imageExists(completImageName3);
                if (imageExist3) {
                    loadImageFromInternalStorage(completImageName3,platina1);
                } else {
                    String urlParalelos3 = myDb.getURL1OpaquesFromId(id)+"1.jpg";
                    Picasso.get().load(urlParalelos3).into(platina1);
                }
                int id4 = Integer.parseInt(id)-1;
                String nameImage4 = myDb.getFotoFromMineralOpaques(String.valueOf(id4));
                String completImageName4 = nameImage4 + "a-1.jpg";
                boolean imageExist4 = imageExists(completImageName4);
                if (imageExist4) {
                    loadImageFromInternalStorage(completImageName4,platina2);
                } else {
                    String urlParalelos3 = myDb.getURL1OpaquesFromId(String.valueOf(id4))+"1.jpg";
                    Picasso.get().load(urlParalelos3).into(platina2);
                }
                int id5 = Integer.parseInt(id)-2;
                String nameImage5 = myDb.getFotoFromMineralOpaques(String.valueOf(id5));
                String completImageName5 = nameImage5 + "a-1.jpg";
                boolean imageExist5 = imageExists(completImageName5);
                if (imageExist5) {
                    loadImageFromInternalStorage(completImageName5,platina3);
                } else {
                    String urlParalelos5 = myDb.getURL1OpaquesFromId(String.valueOf(id5))+"1.jpg";
                    Picasso.get().load(urlParalelos5).into(platina3);
                }
                int id6 = Integer.parseInt(id)-3;
                String nameImage6 = myDb.getFotoFromMineralOpaques(String.valueOf(id6));
                String completImageName6 = nameImage6 + "a-1.jpg";
                boolean imageExist6 = imageExists(completImageName6);
                if (imageExist6) {
                    loadImageFromInternalStorage(completImageName6,platina4);
                } else {
                    String urlParalelos6 = myDb.getURL1OpaquesFromId(String.valueOf(id6))+"1.jpg";
                    Picasso.get().load(urlParalelos6).into(platina4);
                }
                break;
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
                        texto1.setTextSize(34);
                        texto2.setTextSize(34);
                        texto3.setTextSize(34);
                        texto4.setTextSize(34);
                        rbparalelos1.setTextSize(34);
                        rbparalelos2.setTextSize(34);
                        rbparalelos3.setTextSize(34);
                        rbparalelos4.setTextSize(34);
                        rbcruzados1.setTextSize(34);
                        rbcruzados2.setTextSize(34);
                        rbcruzados3.setTextSize(34);
                        rbcruzados4.setTextSize(34);
                        button1.setTextSize(34);
                        button2.setTextSize(34);
                        button3.setTextSize(34);
                        button4.setTextSize(34);
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
                        texto1.setTextSize(32);
                        texto2.setTextSize(32);
                        texto3.setTextSize(32);
                        texto4.setTextSize(32);
                        rbparalelos1.setTextSize(32);
                        rbparalelos2.setTextSize(32);
                        rbparalelos3.setTextSize(32);
                        rbparalelos4.setTextSize(32);
                        rbcruzados1.setTextSize(32);
                        rbcruzados2.setTextSize(32);
                        rbcruzados3.setTextSize(32);
                        rbcruzados4.setTextSize(32);
                        button1.setTextSize(32);
                        button2.setTextSize(32);
                        button3.setTextSize(32);
                        button4.setTextSize(32);
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
                        texto1.setTextSize(30);
                        texto2.setTextSize(30);
                        texto3.setTextSize(30);
                        texto4.setTextSize(30);
                        rbparalelos1.setTextSize(30);
                        rbparalelos2.setTextSize(30);
                        rbparalelos3.setTextSize(30);
                        rbparalelos4.setTextSize(30);
                        rbcruzados1.setTextSize(30);
                        rbcruzados2.setTextSize(30);
                        rbcruzados3.setTextSize(30);
                        rbcruzados4.setTextSize(30);
                        button1.setTextSize(30);
                        button2.setTextSize(30);
                        button3.setTextSize(30);
                        button4.setTextSize(30);
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
                        texto1.setTextSize(28);
                        texto2.setTextSize(28);
                        texto3.setTextSize(28);
                        texto4.setTextSize(28);
                        rbparalelos1.setTextSize(28);
                        rbparalelos2.setTextSize(28);
                        rbparalelos3.setTextSize(28);
                        rbparalelos4.setTextSize(28);
                        rbcruzados1.setTextSize(28);
                        rbcruzados2.setTextSize(28);
                        rbcruzados3.setTextSize(28);
                        rbcruzados4.setTextSize(28);
                        button1.setTextSize(28);
                        button2.setTextSize(28);
                        button3.setTextSize(28);
                        button4.setTextSize(28);
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
                        texto1.setTextSize(26);
                        texto2.setTextSize(26);
                        texto3.setTextSize(26);
                        texto4.setTextSize(26);
                        rbparalelos1.setTextSize(26);
                        rbparalelos2.setTextSize(26);
                        rbparalelos3.setTextSize(26);
                        rbparalelos4.setTextSize(26);
                        rbcruzados1.setTextSize(26);
                        rbcruzados2.setTextSize(26);
                        rbcruzados3.setTextSize(26);
                        rbcruzados4.setTextSize(26);
                        button1.setTextSize(26);
                        button2.setTextSize(26);
                        button3.setTextSize(26);
                        button4.setTextSize(26);
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
                        texto1.setTextSize(28);
                        texto2.setTextSize(28);
                        texto3.setTextSize(28);
                        texto4.setTextSize(28);
                        rbparalelos1.setTextSize(28);
                        rbparalelos2.setTextSize(28);
                        rbparalelos3.setTextSize(28);
                        rbparalelos4.setTextSize(28);
                        rbcruzados1.setTextSize(28);
                        rbcruzados2.setTextSize(28);
                        rbcruzados3.setTextSize(28);
                        rbcruzados4.setTextSize(28);
                        button1.setTextSize(28);
                        button2.setTextSize(28);
                        button3.setTextSize(28);
                        button4.setTextSize(28);
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
                        texto1.setTextSize(26);
                        texto2.setTextSize(26);
                        texto3.setTextSize(26);
                        texto4.setTextSize(26);
                        rbparalelos1.setTextSize(26);
                        rbparalelos2.setTextSize(26);
                        rbparalelos3.setTextSize(26);
                        rbparalelos4.setTextSize(26);
                        rbcruzados1.setTextSize(26);
                        rbcruzados2.setTextSize(26);
                        rbcruzados3.setTextSize(26);
                        rbcruzados4.setTextSize(26);
                        button1.setTextSize(26);
                        button2.setTextSize(26);
                        button3.setTextSize(26);
                        button4.setTextSize(26);
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
                        texto1.setTextSize(24);
                        texto2.setTextSize(24);
                        texto3.setTextSize(24);
                        texto4.setTextSize(24);
                        rbparalelos1.setTextSize(24);
                        rbparalelos2.setTextSize(24);
                        rbparalelos3.setTextSize(24);
                        rbparalelos4.setTextSize(24);
                        rbcruzados1.setTextSize(24);
                        rbcruzados2.setTextSize(24);
                        rbcruzados3.setTextSize(24);
                        rbcruzados4.setTextSize(24);
                        button1.setTextSize(24);
                        button2.setTextSize(24);
                        button3.setTextSize(24);
                        button4.setTextSize(24);
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
                        texto1.setTextSize(22);
                        texto2.setTextSize(22);
                        texto3.setTextSize(22);
                        texto4.setTextSize(22);
                        rbparalelos1.setTextSize(22);
                        rbparalelos2.setTextSize(22);
                        rbparalelos3.setTextSize(22);
                        rbparalelos4.setTextSize(22);
                        rbcruzados1.setTextSize(22);
                        rbcruzados2.setTextSize(22);
                        rbcruzados3.setTextSize(22);
                        rbcruzados4.setTextSize(22);
                        button1.setTextSize(22);
                        button2.setTextSize(22);
                        button3.setTextSize(22);
                        button4.setTextSize(22);
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
                        texto1.setTextSize(20);
                        texto2.setTextSize(20);
                        texto3.setTextSize(20);
                        texto4.setTextSize(20);
                        rbparalelos1.setTextSize(20);
                        rbparalelos2.setTextSize(20);
                        rbparalelos3.setTextSize(20);
                        rbparalelos4.setTextSize(20);
                        rbcruzados1.setTextSize(20);
                        rbcruzados2.setTextSize(20);
                        rbcruzados3.setTextSize(20);
                        rbcruzados4.setTextSize(20);
                        button1.setTextSize(20);
                        button2.setTextSize(20);
                        button3.setTextSize(20);
                        button4.setTextSize(20);
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
                        texto1.setTextSize(22);
                        texto2.setTextSize(22);
                        texto3.setTextSize(22);
                        texto4.setTextSize(22);
                        rbparalelos1.setTextSize(22);
                        rbparalelos2.setTextSize(22);
                        rbparalelos3.setTextSize(22);
                        rbparalelos4.setTextSize(22);
                        rbcruzados1.setTextSize(22);
                        rbcruzados2.setTextSize(22);
                        rbcruzados3.setTextSize(22);
                        rbcruzados4.setTextSize(22);
                        button1.setTextSize(22);
                        button2.setTextSize(22);
                        button3.setTextSize(22);
                        button4.setTextSize(22);
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
                        texto1.setTextSize(20);
                        texto2.setTextSize(20);
                        texto3.setTextSize(20);
                        texto4.setTextSize(20);
                        rbparalelos1.setTextSize(20);
                        rbparalelos2.setTextSize(20);
                        rbparalelos3.setTextSize(20);
                        rbparalelos4.setTextSize(20);
                        rbcruzados1.setTextSize(20);
                        rbcruzados2.setTextSize(20);
                        rbcruzados3.setTextSize(20);
                        rbcruzados4.setTextSize(20);
                        button1.setTextSize(20);
                        button2.setTextSize(20);
                        button3.setTextSize(20);
                        button4.setTextSize(20);
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
                        texto1.setTextSize(18);
                        texto2.setTextSize(18);
                        texto3.setTextSize(18);
                        texto4.setTextSize(18);
                        rbparalelos1.setTextSize(18);
                        rbparalelos2.setTextSize(18);
                        rbparalelos3.setTextSize(18);
                        rbparalelos4.setTextSize(18);
                        rbcruzados1.setTextSize(18);
                        rbcruzados2.setTextSize(18);
                        rbcruzados3.setTextSize(18);
                        rbcruzados4.setTextSize(18);
                        button1.setTextSize(18);
                        button2.setTextSize(18);
                        button3.setTextSize(18);
                        button4.setTextSize(18);
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
                        texto1.setTextSize(16);
                        texto2.setTextSize(16);
                        texto3.setTextSize(16);
                        texto4.setTextSize(16);
                        rbparalelos1.setTextSize(16);
                        rbparalelos2.setTextSize(16);
                        rbparalelos3.setTextSize(16);
                        rbparalelos4.setTextSize(16);
                        rbcruzados1.setTextSize(16);
                        rbcruzados2.setTextSize(16);
                        rbcruzados3.setTextSize(16);
                        rbcruzados4.setTextSize(16);
                        button1.setTextSize(16);
                        button2.setTextSize(16);
                        button3.setTextSize(16);
                        button4.setTextSize(16);
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
                        texto1.setTextSize(14);
                        texto2.setTextSize(14);
                        texto3.setTextSize(14);
                        texto4.setTextSize(14);
                        rbparalelos1.setTextSize(14);
                        rbparalelos2.setTextSize(14);
                        rbparalelos3.setTextSize(14);
                        rbparalelos4.setTextSize(14);
                        rbcruzados1.setTextSize(14);
                        rbcruzados2.setTextSize(14);
                        rbcruzados3.setTextSize(14);
                        rbcruzados4.setTextSize(14);
                        button1.setTextSize(14);
                        button2.setTextSize(14);
                        button3.setTextSize(14);
                        button4.setTextSize(14);
                        break;
                }
                break;
        }
        button1.setOnClickListener(view -> {
            Intent intent1 = new Intent(FichaOpacos.this, MicroscopioOpacos.class);
            intent1.putExtra("id", id);
            startActivity(intent1);
        });
        button2.setOnClickListener(view -> {
            Intent intent1 = new Intent(FichaOpacos.this, MicroscopioOpacos.class);
            String idMicroscopio = String.valueOf(Integer.parseInt(id)-1);
            intent1.putExtra("id", idMicroscopio);
            startActivity(intent1);
        });
        button3.setOnClickListener(view -> {
            Intent intent1 = new Intent(FichaOpacos.this, MicroscopioOpacos.class);
            String idMicroscopio = String.valueOf(Integer.parseInt(id)-2);
            intent1.putExtra("id", idMicroscopio);
            startActivity(intent1);
        });
        button4.setOnClickListener(view -> {
            Intent intent1 = new Intent(FichaOpacos.this, MicroscopioOpacos.class);
            String idMicroscopio = String.valueOf(Integer.parseInt(id)-3);
            intent1.putExtra("id", idMicroscopio);
            startActivity(intent1);
        });
        rg1.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId==R.id.radioParalelos1) {
                Database myDb2 = new Database(this);
                String completImageName1 = myDb2.getFotoFromMineralOpaques(id) + "a-1.jpg";
                boolean imageExist1 = imageExists(completImageName1);
                if (imageExist1) {
                    loadImageFromInternalStorage(completImageName1, platina1);
                } else {
                    String urlParalelos = myDb2.getURL1OpaquesFromId(id) + "1.jpg";
                    Picasso.get().load(urlParalelos).into(platina1);
                }
                myDb2.closeDatabase();
            } else {
                Database myDb3 = new Database(this);
                String completImageName2 = myDb3.getFotoFromMineralOpaques(id) + "b-1.jpg";
                boolean imageExist2 = imageExists(completImageName2);
                if (imageExist2) {
                    loadImageFromInternalStorage(completImageName2,platina1);
                } else {
                    String urlCruzados = myDb3.getURL2OpaquesFromId(id)+"1.jpg";
                    Picasso.get().load(urlCruzados).into(platina1);
                }
                myDb3.closeDatabase();
            }
        });
        rg2.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId==R.id.radioParalelos2) {
                Database myDb2 = new Database(this);
                String completImageName1 = myDb2.getFotoFromMineralOpaques(String.valueOf(Integer.parseInt(id) - 1)) + "a-1.jpg";
                boolean imageExist1 = imageExists(completImageName1);
                if (imageExist1) {
                    loadImageFromInternalStorage(completImageName1, platina2);
                } else {
                    String urlParalelos = myDb2.getURL1OpaquesFromId(String.valueOf(Integer.parseInt(id) - 1)) + "1.jpg";
                    Picasso.get().load(urlParalelos).into(platina2);
                }
                myDb2.closeDatabase();
            } else {
                Database myDb3 = new Database(this);
                String completImageName2 = myDb3.getFotoFromMineralOpaques(String.valueOf(Integer.parseInt(id)-1)) + "b-1.jpg";
                boolean imageExist2 = imageExists(completImageName2);
                if (imageExist2) {
                    loadImageFromInternalStorage(completImageName2,platina2);
                } else {
                    String urlCruzados = myDb3.getURL2OpaquesFromId(String.valueOf(Integer.parseInt(id)-1))+"1.jpg";
                    Picasso.get().load(urlCruzados).into(platina2);
                }
                myDb3.closeDatabase();
            }
        });
        rg3.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId==R.id.radioParalelos3) {
                Database myDb2 = new Database(this);
                String completImageName1 = myDb2.getFotoFromMineralOpaques(String.valueOf(Integer.parseInt(id) - 2)) + "a-1.jpg";
                boolean imageExist1 = imageExists(completImageName1);
                if (imageExist1) {
                    loadImageFromInternalStorage(completImageName1, platina3);
                } else {
                    String urlParalelos = myDb2.getURL1OpaquesFromId(String.valueOf(Integer.parseInt(id) - 2)) + "1.jpg";
                    Picasso.get().load(urlParalelos).into(platina3);
                }
                myDb2.closeDatabase();
            } else {
                Database myDb3 = new Database(this);
                String completImageName2 = myDb3.getFotoFromMineralOpaques(String.valueOf(Integer.parseInt(id)-2)) + "b-1.jpg";
                boolean imageExist2 = imageExists(completImageName2);
                if (imageExist2) {
                    loadImageFromInternalStorage(completImageName2,platina3);
                } else {
                    String urlCruzados = myDb3.getURL2OpaquesFromId(String.valueOf(Integer.parseInt(id)-2))+"1.jpg";
                    Picasso.get().load(urlCruzados).into(platina3);
                }
                myDb3.closeDatabase();
            }
        });
        rg4.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId==R.id.radioParalelos4) {
                Database myDb2 = new Database(this);
                String completImageName1 = myDb2.getFotoFromMineralOpaques(String.valueOf(Integer.parseInt(id) - 3)) + "a-1.jpg";
                boolean imageExist1 = imageExists(completImageName1);
                if (imageExist1) {
                    loadImageFromInternalStorage(completImageName1, platina3);
                } else {
                    String urlParalelos = myDb2.getURL1OpaquesFromId(String.valueOf(Integer.parseInt(id) - 3)) + "1.jpg";
                    Picasso.get().load(urlParalelos).into(platina4);
                }
                myDb2.closeDatabase();
            } else {
                Database myDb3 = new Database(this);
                String completImageName2 = myDb3.getFotoFromMineralOpaques(String.valueOf(Integer.parseInt(id)-3)) + "b-1.jpg";
                boolean imageExist2 = imageExists(completImageName2);
                if (imageExist2) {
                    loadImageFromInternalStorage(completImageName2,platina4);
                } else {
                    String urlCruzados = myDb3.getURL2OpaquesFromId(String.valueOf(Integer.parseInt(id)-3))+"1.jpg";
                    Picasso.get().load(urlCruzados).into(platina4);
                }
                myDb3.closeDatabase();
            }
        });
        myDb.closeDatabase();
    }
    private boolean imageExists(String imageName) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File imageFile = new File(directory, imageName);
        return imageFile.exists();
    }
    private void loadImageFromInternalStorage(String imageName,ImageView image) {
        ContextWrapper cw = new ContextWrapper(this);
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File mypath = new File(directory, imageName);
        image.setImageDrawable(Drawable.createFromPath(mypath.toString()));
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