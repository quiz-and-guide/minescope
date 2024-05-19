package com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.Locale;

public class Database extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "MINESCOPE.db";
    private static final int DATABASE_VERSION = 1;
    private final SQLiteDatabase db;
    private final String tableTransparents,tableOpacos,tableFiltroOpacos,tableFiltroTransparentes,tablePropiedadesTransparentes,tablePropiedadesOpacos;
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        setForcedUpgrade();
        db = getReadableDatabase();
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserPreferences", MODE_PRIVATE);
        String language = sharedPreferences.getString("language", Locale.getDefault().getLanguage());
        switch (language) {
            case "es":
                tableOpacos = "OPAQUES_ES";
                tableTransparents = "TRANSPARENTS_ES";
                tableFiltroOpacos = "FILTRO_OPACOS_ES";
                tableFiltroTransparentes = "FILTRO_TRANSPARENTES_ES";
                tablePropiedadesTransparentes = "PROPIEDADES_FILTRO_TRANSPARENTES_ES";
                tablePropiedadesOpacos = "PROPIEDADES_FILTRO_OPACOS_ES";
                break;
            case "ca":
                tableOpacos = "OPAQUES_CA";
                tableTransparents = "TRANSPARENTS_CA";
                tableFiltroOpacos = "FILTRO_OPACOS_CA";
                tableFiltroTransparentes = "FILTRO_TRANSPARENTES_CA";
                tablePropiedadesTransparentes = "PROPIEDADES_FILTRO_TRANSPARENTES_CA";
                tablePropiedadesOpacos = "PROPIEDADES_FILTRO_OPACOS_CA";
                break;
            default:
                tableOpacos = "OPAQUES_EN";
                tableTransparents = "TRANSPARENTS_EN";
                tableFiltroOpacos = "FILTRO_OPACOS_EN";
                tableFiltroTransparentes = "FILTRO_TRANSPARENTES_EN";
                tablePropiedadesTransparentes = "PROPIEDADES_FILTRO_TRANSPARENTES_EN";
                tablePropiedadesOpacos = "PROPIEDADES_FILTRO_OPACOS_EN";
                break;
        }
    }
    public ArrayList<String> getPropiedadesTransparentesFiltro(String id) {
        ArrayList<String> propiedades = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT MINERAL,RELIEF,COLOR,PLEOCHROISM,NUMBERCLEAVAGEDIRECTIONS,ANGLEOFCLEAVAGE,INTERFERENCECOLOR,EXTINCTION,TWINNING,INTERFERENCEFIGURE,OPTICALSIGN FROM '"+tableFiltroTransparentes+"' WHERE ID ='"+id+"'", null);
        while (cursor.moveToNext()) {
            propiedades.add(cursor.getString(0));
            propiedades.add(cursor.getString(1));
            propiedades.add(cursor.getString(2));
            propiedades.add(cursor.getString(3));
            propiedades.add(cursor.getString(4));
            propiedades.add(cursor.getString(5));
            propiedades.add(cursor.getString(6));
            propiedades.add(cursor.getString(7));
            propiedades.add(cursor.getString(8));
            propiedades.add(cursor.getString(9));
            propiedades.add(cursor.getString(10));
        }
        cursor.close();
        return propiedades;
    }
    public ArrayList<String> getPropiedadesOpacosFiltro(String id) {
        ArrayList<String> propiedades = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT MINERAL,COLOR,REFLECTANCE,PLEOCHROISM,POLISHINGHARDNESS,ANISOTROPISM,INTERFERENCECOLORS,INTERNALREFLECTIONS FROM '"+tableFiltroOpacos+"' WHERE ID ='"+id+"'", null);
        while (cursor.moveToNext()) {
            propiedades.add(cursor.getString(0));
            propiedades.add(cursor.getString(1));
            propiedades.add(cursor.getString(2));
            propiedades.add(cursor.getString(3));
            propiedades.add(cursor.getString(4));
            propiedades.add(cursor.getString(5));
            propiedades.add(cursor.getString(6));
            propiedades.add(cursor.getString(7));
        }
        cursor.close();
        return propiedades;
    }
    public ArrayList<String> getPropiedadesTransparentes() {
        ArrayList<String> propiedades = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT RELIEF,COLOUR,PLEOCHROISM,NUMBEROFCLEAVAGEDIRECTIONS,ANGLEOFCLEAVAGE,INTERFERENCECOLOR,EXTINCTION,TWINNING FROM '"+tablePropiedadesTransparentes+"'", null);
        while (cursor.moveToNext()) {
            propiedades.add(cursor.getString(0));
            propiedades.add(cursor.getString(1));
            propiedades.add(cursor.getString(2));
            propiedades.add(cursor.getString(3));
            propiedades.add(cursor.getString(4));
            propiedades.add(cursor.getString(5));
            propiedades.add(cursor.getString(6));
            propiedades.add(cursor.getString(7));
        }
        cursor.close();
        return propiedades;
    }
    public ArrayList<String> getPropiedadesOpacos() {
        ArrayList<String> propiedades = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT COLOR,REFLECTANCE,PLEOCHROISM,POLISHINGHARDNESS,ANISOTROPISM,INTERFERENCECOLORS,INTERNALREFLECTIONS FROM \"" + tablePropiedadesOpacos + "\"", null);

        while (cursor.moveToNext()) {
            propiedades.add(cursor.getString(0));
            propiedades.add(cursor.getString(1));
            propiedades.add(cursor.getString(2));
            propiedades.add(cursor.getString(3));
            propiedades.add(cursor.getString(4));
            propiedades.add(cursor.getString(5));
            propiedades.add(cursor.getString(6));
        }
        cursor.close();
        return propiedades;
    }
    public boolean isMineralOpaques(String texto) {
        Cursor cursor = db.rawQuery("SELECT MINERAL FROM '"+tableOpacos+"'", null);
        while (cursor.moveToNext()) {
            String mineral = cursor.getString(0);
            if (texto.equalsIgnoreCase(mineral)) {
                cursor.close();
                return true;
            }
        }
        cursor.close();
        return false;
    }
    public boolean isMineralFiltroOpacos(String texto) {
        Cursor cursor = db.rawQuery("SELECT MINERAL FROM '"+tableFiltroOpacos+"'", null);
        while (cursor.moveToNext()) {
            String mineral = cursor.getString(0);
            if (texto.equalsIgnoreCase(mineral)) {
                cursor.close();
                return true;
            }
        }
        cursor.close();
        return false;
    }
    public boolean isMineralFiltroTransparentes(String texto) {
        Cursor cursor = db.rawQuery("SELECT MINERAL FROM '"+tableFiltroTransparentes+"'", null);
        while (cursor.moveToNext()) {
            String mineral = cursor.getString(0);
            if (texto.equalsIgnoreCase(mineral)) {
                cursor.close();
                return true;
            }
        }
        cursor.close();
        return false;
    }
    public ArrayList<String> getNombres() {
        ArrayList<String> mineNombres = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT MINERAL FROM '"+tableOpacos+"'", null);
        while (cursor.moveToNext()) {
            String mineral = cursor.getString(0);
            mineNombres.add(mineral.toLowerCase());
        }
        cursor.close();
        Cursor cursor2 = db.rawQuery("SELECT MINERAL FROM '"+tableTransparents+"'", null);
        while (cursor2.moveToNext()) {
            String mineral = cursor2.getString(0);
            mineNombres.add(mineral.toLowerCase());
        }
        cursor2.close();
        return mineNombres;
    }
    public boolean isMineralTransparents(String texto) {
        Cursor cursor = db.rawQuery("SELECT MINERAL FROM '"+tableTransparents+"'", null);
        while (cursor.moveToNext()) {
            String mineral = cursor.getString(0);
            if (texto.equalsIgnoreCase(mineral)) {
                cursor.close();
                return true;
            }
        }
        cursor.close();
        return false;
    }
    public ArrayList<String> getOpacos() {
        Cursor cursor = db.rawQuery("SELECT MINERAL FROM '"+tableOpacos+"'", null);
        ArrayList<String> minerales = new ArrayList<>();
        while (cursor.moveToNext()) {
            minerales.add(cursor.getString(0));
        }
        cursor.close();
        return removeDuplicates(minerales);
    }
    public ArrayList<String> getTransparents() {
        Cursor cursor = db.rawQuery("SELECT MINERAL FROM '"+tableTransparents+"'", null);
        ArrayList<String> minerales = new ArrayList<>();
        while (cursor.moveToNext()) {
            minerales.add(cursor.getString(0));
        }
        cursor.close();
        return removeDuplicates(minerales);
    }
    public ArrayList<String> getTransparentsFiltro() {
        Cursor cursor = db.rawQuery("SELECT MINERAL FROM '"+tableFiltroTransparentes+"'", null);
        ArrayList<String> minerales = new ArrayList<>();
        while (cursor.moveToNext()) {
            minerales.add(cursor.getString(0));
        }
        cursor.close();
        return minerales;
    }
    public ArrayList<String> getOpacosFiltro() {
        Cursor cursor = db.rawQuery("SELECT MINERAL FROM '"+tableFiltroOpacos+"'", null);
        ArrayList<String> minerales = new ArrayList<>();
        while (cursor.moveToNext()) {
            minerales.add(cursor.getString(0));
        }
        cursor.close();
        return minerales;
    }
    public String getReliefTransparentesFiltro(String mineral) {
        Cursor cursor = db.rawQuery("SELECT RELIEF FROM '"+tableFiltroTransparentes+"' WHERE MINERAL ='"+mineral+"'", null);
        String relief = "";
        while (cursor.moveToNext()) {
            relief = cursor.getString(0);
        }
        cursor.close();
        return relief;
    }
    public String getColorTransparentesFiltro(String mineral) {
        Cursor cursor = db.rawQuery("SELECT COLOR FROM '"+tableFiltroTransparentes+"' WHERE MINERAL ='"+mineral+"'", null);
        String color = "";
        while (cursor.moveToNext()) {
            color = cursor.getString(0);
        }
        cursor.close();
        return color;
    }
    public String getPleochroismTransparentesFiltro(String mineral) {
        Cursor cursor = db.rawQuery("SELECT PLEOCHROISM FROM '"+tableFiltroTransparentes+"' WHERE MINERAL ='"+mineral+"'", null);
        String pleochroism = "";
        while (cursor.moveToNext()) {
            pleochroism = cursor.getString(0);
        }
        cursor.close();
        return pleochroism;
    }
    public String getNumberCleavageDirectionsTransparentesFiltro(String mineral) {
        Cursor cursor = db.rawQuery("SELECT NUMBERCLEAVAGEDIRECTIONS FROM '"+tableFiltroTransparentes+"' WHERE MINERAL ='"+mineral+"'", null);
        String numberCleavageDirections = "";
        while (cursor.moveToNext()) {
            numberCleavageDirections = cursor.getString(0);
        }
        cursor.close();
        return numberCleavageDirections;
    }
    public String getAngleOfCleavageTransparentesFiltro(String mineral) {
        Cursor cursor = db.rawQuery("SELECT ANGLEOFCLEAVAGE FROM '"+tableFiltroTransparentes+"' WHERE MINERAL ='"+mineral+"'", null);
        String angleOfCleavage = "";
        while (cursor.moveToNext()) {
            angleOfCleavage = cursor.getString(0);
        }
        cursor.close();
        return angleOfCleavage;
    }
    public String getInterferenceColorTransparentesFiltro(String mineral) {
        Cursor cursor = db.rawQuery("SELECT INTERFERENCECOLOR FROM '"+tableFiltroTransparentes+"' WHERE MINERAL ='"+mineral+"'", null);
        String interferenceColor = "";
        while (cursor.moveToNext()) {
            interferenceColor = cursor.getString(0);
        }
        cursor.close();
        return interferenceColor;
    }
    public String getExtinctionTransparentesFiltro(String mineral) {
        Cursor cursor = db.rawQuery("SELECT EXTINCTION FROM '"+tableFiltroTransparentes+"' WHERE MINERAL ='"+mineral+"'", null);
        String extinction = "";
        while (cursor.moveToNext()) {
            extinction = cursor.getString(0);
        }
        cursor.close();
        return extinction;
    }
    public String getTwinningTransparentesFiltro(String mineral) {
        Cursor cursor = db.rawQuery("SELECT TWINNING FROM '"+tableFiltroTransparentes+"' WHERE MINERAL ='"+mineral+"'", null);
        String twinning = "";
        while (cursor.moveToNext()) {
            twinning = cursor.getString(0);
        }
        cursor.close();
        return twinning;
    }
    public String getInterferenceFigureTransparentesFiltro(String mineral) {
        Cursor cursor = db.rawQuery("SELECT INTERFERENCEFIGURE FROM '"+tableFiltroTransparentes+"' WHERE MINERAL ='"+mineral+"'", null);
        String interferenceFigure = "";
        while (cursor.moveToNext()) {
            interferenceFigure = cursor.getString(0);
        }
        cursor.close();
        return interferenceFigure;
    }
    public String getOpticalSignTransparentesFiltro(String mineral) {
        Cursor cursor = db.rawQuery("SELECT OPTICALSIGN FROM '"+tableFiltroTransparentes+"' WHERE MINERAL ='"+mineral+"'", null);
        String opticalSign = "";
        while (cursor.moveToNext()) {
            opticalSign = cursor.getString(0);
        }
        cursor.close();
        return opticalSign;
    }
    public String getColorOpacosFiltro(String mineral) {
        Cursor cursor = db.rawQuery("SELECT COLOR FROM '"+tableFiltroOpacos+"' WHERE MINERAL ='"+mineral+"'", null);
        String color = "";
        while (cursor.moveToNext()) {
            color = cursor.getString(0);
        }
        cursor.close();
        return color;
    }
    public String getReflectanceOpacosFiltro(String mineral) {
        Cursor cursor = db.rawQuery("SELECT REFLECTANCE FROM '"+tableFiltroOpacos+"' WHERE MINERAL ='"+mineral+"'", null);
        String reflectance = "";
        while (cursor.moveToNext()) {
            reflectance = cursor.getString(0);
        }
        cursor.close();
        return reflectance;
    }
    public String getPleochroismOpacosFiltro(String mineral) {
        Cursor cursor = db.rawQuery("SELECT PLEOCHROISM FROM '"+tableFiltroOpacos+"' WHERE MINERAL ='"+mineral+"'", null);
        String pleochroism = "";
        while (cursor.moveToNext()) {
            pleochroism = cursor.getString(0);
        }
        cursor.close();
        return pleochroism;
    }
    public String getPolishingHardnessOpacosFiltro(String mineral) {
        Cursor cursor = db.rawQuery("SELECT POLISHINGHARDNESS FROM '"+tableFiltroOpacos+"' WHERE MINERAL ='"+mineral+"'", null);
        String polishingHardness = "";
        while (cursor.moveToNext()) {
            polishingHardness = cursor.getString(0);
        }
        cursor.close();
        return polishingHardness;
    }
    public String getAnisotropismOpacosFiltro(String mineral) {
        Cursor cursor = db.rawQuery("SELECT ANISOTROPISM FROM '"+tableFiltroOpacos+"' WHERE MINERAL ='"+mineral+"'", null);
        String anisotropism = "";
        while (cursor.moveToNext()) {
            anisotropism = cursor.getString(0);
        }
        cursor.close();
        return anisotropism;
    }
    public String getInterenceColorsOpacosFiltro(String mineral) {
        Cursor cursor = db.rawQuery("SELECT INTERFERENCECOLORS FROM '"+tableFiltroOpacos+"' WHERE MINERAL ='"+mineral+"'", null);
        String interferenceColors = "";
        while (cursor.moveToNext()) {
            interferenceColors = cursor.getString(0);
        }
        cursor.close();
        return interferenceColors;
    }
    public String getInternalReflectionsOpacosFiltro(String mineral) {
        Cursor cursor = db.rawQuery("SELECT INTERNALREFLECTIONS FROM '"+tableFiltroOpacos+"' WHERE MINERAL ='"+mineral+"'", null);
        String internalReflections = "";
        while (cursor.moveToNext()) {
            internalReflections = cursor.getString(0);
        }
        cursor.close();
        return internalReflections;
    }
    public String getFotoOpaquesFromURL(String url1) {
        Cursor cursor = db.rawQuery("SELECT FOTO FROM '"+tableOpacos+"' WHERE URL1 ='"+url1+"' OR URL2='"+url1+"'", null);
        String fotoName = "";
        while (cursor.moveToNext()) {
            fotoName = cursor.getString(0);
        }
        cursor.close();
        return fotoName;
    }
    public String getFotoTransparentsFromURL(String url1) {
        Cursor cursor = db.rawQuery("SELECT FOTO FROM '"+tableTransparents+"' WHERE URL1 ='"+url1+"' OR URL2='"+url1+"'", null);
        String fotoName = "";
        while (cursor.moveToNext()) {
            fotoName = cursor.getString(0);
        }
        cursor.close();
        return fotoName;
    }
    public String getFotoFromMineralOpaques(String id) {
        Cursor cursor = db.rawQuery("SELECT FOTO FROM '"+tableOpacos+"' WHERE ID ='"+id+"'", null);
        String fotoName = "";
        while (cursor.moveToNext()) {
            fotoName = cursor.getString(0);
        }
        cursor.close();
        return fotoName;
    }
    public String getFotoFromMineralTransparents(String id) {
        Cursor cursor = db.rawQuery("SELECT FOTO FROM '"+tableTransparents+"' WHERE ID ='"+id+"'", null);
        String fotoName = "";
        while (cursor.moveToNext()) {
            fotoName = cursor.getString(0);
        }
        cursor.close();
        return fotoName;
    }
    public String getDescriptionTransparents(String id) {
        Cursor cursor = db.rawQuery("SELECT MINERAL2 FROM '"+tableTransparents+"' WHERE ID ='"+id+"'", null);
        String description = "";
        while (cursor.moveToNext()) {
            description = cursor.getString(0);
        }
        cursor.close();
        return description;
    }
    public String getDescriptionOpaques(String id) {
        Cursor cursor = db.rawQuery("SELECT MINERAL2 FROM '"+tableOpacos+"' WHERE ID ='"+id+"'", null);
        String description = "";
        while (cursor.moveToNext()) {
            description = cursor.getString(0);
        }
        cursor.close();
        return description;
    }
    public ArrayList<String>urlsOpaquesToDownload() {
        Cursor cursor = db.rawQuery("SELECT URL1 FROM '"+tableOpacos+"'", null);
        ArrayList<String> urls = new ArrayList<>();
        while (cursor.moveToNext()) {
            urls.add(cursor.getString(0));
        }
        cursor.close();
        Cursor cursor2 = db.rawQuery("SELECT URL2 FROM '"+tableOpacos+"'", null);
        while (cursor2.moveToNext()) {
            urls.add(cursor2.getString(0));
        }
        cursor2.close();
        return urls;
    }
    public ArrayList<String>urlsTransparentsToDownload() {
        Cursor cursor = db.rawQuery("SELECT URL1 FROM '"+tableTransparents+"'", null);
        ArrayList<String> urls = new ArrayList<>();
        while (cursor.moveToNext()) {
            urls.add(cursor.getString(0));
        }
        cursor.close();
        Cursor cursor2 = db.rawQuery("SELECT URL2 FROM '"+tableTransparents+"'", null);
        while (cursor2.moveToNext()) {
            urls.add(cursor2.getString(0));
        }
        cursor2.close();
        return urls;
    }
    public String getURLIcon1Opacos(String mineral) {
        Cursor cursor = db.rawQuery("SELECT URLICON1 FROM '"+tableOpacos+"' WHERE MINERAL ='"+mineral+"'", null);
        String icon1 = "";
        while (cursor.moveToNext()) {
            icon1 = cursor.getString(0);
        }
        cursor.close();
        return icon1;
    }
    public String getURLIcon2Opacos(String mineral) {
        Cursor cursor = db.rawQuery("SELECT URLICON2 FROM '"+tableOpacos+"' WHERE MINERAL ='"+mineral+"'", null);
        String icon2 = "";
        while (cursor.moveToNext()) {
            icon2 = cursor.getString(0);
        }
        cursor.close();
        return icon2;
    }
    public String getURLIcon1Transparents(String mineral) {
        Cursor cursor = db.rawQuery("SELECT URLICON1 FROM '"+tableTransparents+"' WHERE MINERAL ='"+mineral+"'", null);
        String icon1 = "";
        while (cursor.moveToNext()) {
            icon1 = cursor.getString(0);
        }
        cursor.close();
        return icon1;
    }
    public String getURLIcon2Transparents(String mineral) {
        Cursor cursor = db.rawQuery("SELECT URLICON2 FROM '"+tableTransparents+"' WHERE MINERAL ='"+mineral+"'", null);
        String icon2 = "";
        while (cursor.moveToNext()) {
            icon2 = cursor.getString(0);
        }
        cursor.close();
        return icon2;
    }
    public String getURL1TransparentsFromId(String id) {
        Cursor cursor = db.rawQuery("SELECT URL1 FROM '"+tableTransparents+"' WHERE ID ='"+id+"'", null);
        String url1 = "";
        while (cursor.moveToNext()) {
            url1 = cursor.getString(0);
        }
        cursor.close();
        return url1;
    }
    public String getURL2TransparentsFromId(String id) {
        Cursor cursor = db.rawQuery("SELECT URL2 FROM '"+tableTransparents+"' WHERE ID ='"+id+"'", null);
        String url1 = "";
        while (cursor.moveToNext()) {
            url1 = cursor.getString(0);
        }
        cursor.close();
        return url1;
    }
    public String getURL1OpaquesFromId(String id) {
        Cursor cursor = db.rawQuery("SELECT URL1 FROM '"+tableOpacos+"' WHERE ID ='"+id+"'", null);
        String url1 = "";
        while (cursor.moveToNext()) {
            url1 = cursor.getString(0);
        }
        cursor.close();
        return url1;
    }
    public String getURL2OpaquesFromId(String id) {
        Cursor cursor = db.rawQuery("SELECT URL2 FROM '"+tableOpacos+"' WHERE ID ='"+id+"'", null);
        String url1 = "";
        while (cursor.moveToNext()) {
            url1 = cursor.getString(0);
        }
        cursor.close();
        return url1;
    }
    public String getIdFromMineralOpaque(String mineral) {
        Cursor cursor = db.rawQuery("SELECT ID FROM '"+tableOpacos+"' WHERE MINERAL ='"+mineral+"'", null);
        String id = "";
        while (cursor.moveToNext()) {
            id = cursor.getString(0);
        }
        cursor.close();
        return id;
    }
    public String getIdFromMineralTransparent(String mineral) {
        Cursor cursor = db.rawQuery("SELECT ID FROM '"+tableTransparents+"' WHERE MINERAL ='"+mineral+"'", null);
        String id = "";
        while (cursor.moveToNext()) {
            id = cursor.getString(0);
        }
        cursor.close();
        return id;
    }
    public String getIdFromMineralFiltroOpacos(String mineral) {
        Cursor cursor = db.rawQuery("SELECT ID FROM '"+tableFiltroOpacos+"' WHERE MINERAL ='"+mineral+"'", null);
        String id = "";
        while (cursor.moveToNext()) {
            id = cursor.getString(0);
        }
        cursor.close();
        return id;
    }
    public String getIdFromMineralFiltroTransparentes(String mineral) {
        Cursor cursor = db.rawQuery("SELECT ID FROM '"+tableFiltroTransparentes+"' WHERE MINERAL ='"+mineral+"'", null);
        String id = "";
        while (cursor.moveToNext()) {
            id = cursor.getString(0);
        }
        cursor.close();
        return id;
    }
    public ArrayList<String> getGeneralesTransparentes(String id) {
        ArrayList<String> propiedades = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT MINERAL,FOTO,FORMULA,SHAPE,RELIEF,COLORS,PLEOCHROISM,CLEAVAGE,ALTERATION,INTERFERENCECOLOR,EXTINCTION,TWINNING,ZONATION,INTERFERENCEFIGURE,OPTICALSIGN FROM '"+tableTransparents+"' WHERE ID ='"+id+"'", null);
        while (cursor.moveToNext()) {
            propiedades.add(cursor.getString(0));
            propiedades.add(cursor.getString(1));
            propiedades.add(cursor.getString(2));
            propiedades.add(cursor.getString(3));
            propiedades.add(cursor.getString(4));
            propiedades.add(cursor.getString(5));
            propiedades.add(cursor.getString(6));
            propiedades.add(cursor.getString(7));
            propiedades.add(cursor.getString(8));
            propiedades.add(cursor.getString(9));
            propiedades.add(cursor.getString(10));
            propiedades.add(cursor.getString(11));
            propiedades.add(cursor.getString(12));
            propiedades.add(cursor.getString(13));
            propiedades.add(cursor.getString(14));
        }
        cursor.close();
        return propiedades;
    }
    public ArrayList<String> getGeneralesOpacos(String id) {
        ArrayList<String> propiedades = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT MINERAL,FOTO,FORMULA,CLEAVAGE,REFLECTANCE,COLORS,HARDNESS,PLEOCHROISM,ANISOTROPISM,INFERENCECOLORS,REFELCTIONS,ASSOCIATION FROM '"+tableOpacos+"' WHERE ID ='"+id+"'", null);
        while (cursor.moveToNext()) {
            propiedades.add(cursor.getString(0));
            propiedades.add(cursor.getString(1));
            propiedades.add(cursor.getString(2));
            propiedades.add(cursor.getString(3));
            propiedades.add(cursor.getString(4));
            propiedades.add(cursor.getString(5));
            propiedades.add(cursor.getString(6));
            propiedades.add(cursor.getString(7));
            propiedades.add(cursor.getString(8));
            propiedades.add(cursor.getString(9));
            propiedades.add(cursor.getString(10));
            propiedades.add(cursor.getString(11));
        }
        cursor.close();
        return propiedades;
    }
    public ArrayList<String> getLaminaTransparentes(String id) {
        ArrayList<String> propiedades = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT MINERAL2,SHAPE2,RELIEFS2,COLORS2,PLEOCHROISM2,CLEAVAGE2,ALTERATION2,INTERFERENCECOLOR2,EXTINCTION2,TWINNING2,ZONATION2,ABUNDANCE,OTHERS FROM '"+tableTransparents+"' WHERE ID ='"+id+"'", null);
        while (cursor.moveToNext()) {
            propiedades.add(cursor.getString(0));
            propiedades.add(cursor.getString(1));
            propiedades.add(cursor.getString(2));
            propiedades.add(cursor.getString(3));
            propiedades.add(cursor.getString(4));
            propiedades.add(cursor.getString(5));
            propiedades.add(cursor.getString(6));
            propiedades.add(cursor.getString(7));
            propiedades.add(cursor.getString(8));
            propiedades.add(cursor.getString(9));
            propiedades.add(cursor.getString(10));
            propiedades.add(cursor.getString(11));
            propiedades.add(cursor.getString(12));
        }
        cursor.close();
        return propiedades;
    }
    public ArrayList<String> getLaminaOpaques(String id) {
        ArrayList<String> propiedades = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT MINERAL2,MORPHOLOGY,CLEAVAGE2,REFLECTANCE2,COLORS2,HARDNESS2,PLEOCHROISM2,ANISOTROPISM2,INFERENCECOLORS2,REFLECTIONS2,ABUNDANCE2,OTHERS FROM '"+tableOpacos+"' WHERE ID ='"+id+"'", null);
        while (cursor.moveToNext()) {
            propiedades.add(cursor.getString(0));
            propiedades.add(cursor.getString(1));
            propiedades.add(cursor.getString(2));
            propiedades.add(cursor.getString(3));
            propiedades.add(cursor.getString(4));
            propiedades.add(cursor.getString(5));
            propiedades.add(cursor.getString(6));
            propiedades.add(cursor.getString(7));
            propiedades.add(cursor.getString(8));
            propiedades.add(cursor.getString(9));
            propiedades.add(cursor.getString(10));
            propiedades.add(cursor.getString(11));
        }
        cursor.close();
        return propiedades;
    }
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {
        ArrayList<T> newList = new ArrayList<T>();
        for (T element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
    }

    public void closeDatabase() {
        db.close();
    }
}
