package com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Locale;

public class FichaFiltroOpacos extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_filtro_opacos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        SharedPreferences sharedPreferences = this.getSharedPreferences("UserPreferences", MODE_PRIVATE);
        String theme = sharedPreferences.getString("theme", "light");
        String sizeText = sharedPreferences.getString("sizeText", "0");
        String language = sharedPreferences.getString("language", Locale.getDefault().getLanguage());
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getBaseContext().getResources().updateConfiguration(config, this.getBaseContext().getResources().getDisplayMetrics());
        Database myDb = new Database(this);
        ArrayList<String> propiedades = myDb.getPropiedadesOpacosFiltro(id);
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
        LinearLayout screen = findViewById(R.id.screen);
        text1.setText(propiedades.get(0).toUpperCase());
        text3.setText(propiedades.get(1));
        text5.setText(propiedades.get(2));
        text7.setText(propiedades.get(3));
        text9.setText(propiedades.get(4));
        text11.setText(propiedades.get(5));
        text13.setText(propiedades.get(6));
        text15.setText(propiedades.get(7));
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
        }
        switch (isTablet(this)) {
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
                        break;
                }
                break;
        }
        myDb.closeDatabase();
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
