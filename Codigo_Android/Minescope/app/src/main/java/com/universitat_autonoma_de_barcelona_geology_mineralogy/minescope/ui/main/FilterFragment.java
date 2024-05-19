package com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope.ui.main;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope.Database;
import com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope.FichaFiltroOpacos;
import com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope.FichaFiltroTransparentes;
import com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope.FichaOpacos;
import com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope.FichaTransparentes;
import com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope.R;
import com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope.Settings;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A placeholder fragment containing a simple view.
 */
public class FilterFragment extends Fragment implements CustomRecyclerAdapter.OnRecyclerItemClickListener {

    private SearchView searchView;
    private LinearLayout list;
    private ArrayList<String> minerales;
    private ArrayList<String> mineNombres;
    private CustomRecyclerAdapter adapterBusqueda, adapter;
    private RecyclerView recyclerViewList,recyclerViewSearch;
    private Spinner spn1,spn2,spn3,spn4,spn5,spn6,spn7,spn8,spn9,spn10,spn11,spn12,spn13,spn14,spn15,spn16,spn17,spn18;
    private ArrayAdapter<String> adapterNone;
    public FilterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewScreen = inflater.inflate(R.layout.fragment_filter, container, false);
        setHasOptionsMenu(true);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserPreferences", MODE_PRIVATE);
        String theme = sharedPreferences.getString("theme", "light");
        String sizeText = sharedPreferences.getString("sizeText", "0");
        String language = sharedPreferences.getString("language", Locale.getDefault().getLanguage());
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getActivity().getBaseContext().getResources().updateConfiguration(config, getActivity().getBaseContext().getResources().getDisplayMetrics());

        Database myDb = new Database(getActivity());
        ArrayList<String> propiedadesTransparentes = myDb.getPropiedadesTransparentes();
        ArrayList<String> propiedadesOpacos = myDb.getPropiedadesOpacos();
        mineNombres = new ArrayList<>();
        recyclerViewSearch = viewScreen.findViewById(R.id.recycler_view_search);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterBusqueda = new CustomRecyclerAdapter(getActivity(), mineNombres,this);
        recyclerViewSearch.setAdapter(adapterBusqueda);
        minerales = myDb.getTransparentsFiltro();

        recyclerViewList = viewScreen.findViewById(R.id.recycler_view);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(getActivity()));

        /*recyclerView.setOnItemClickListener((parent, view, position, id) -> {
            String nombreMineral = mineNombres.get(position);
            Database myDb2 = new Database(getActivity());
            String idMineOpaques = myDb2.getIdFromMineralOpaque(nombreMineral.substring(0,1).toUpperCase()+nombreMineral.substring(1).toLowerCase());
            String idMineTransparents = myDb2.getIdFromMineralTransparent(nombreMineral.substring(0,1).toUpperCase()+nombreMineral.substring(1).toLowerCase());
            myDb2.closeDatabase();
            mineNombres.clear();
            adapterBusqueda.notifyDataSetChanged();
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            if (idMineTransparents.length()>0) {
                Intent intent2 = new Intent(getActivity(), FichaTransparentes.class);
                intent2.putExtra("id",idMineTransparents);
                searchView.setQuery("",false);
                startActivity(intent2);
            }
            if (idMineOpaques.length()>0) {
                Intent intent3 = new Intent(getActivity(), FichaOpacos.class);
                intent3.putExtra("id",idMineOpaques);
                searchView.setQuery("",false);
                startActivity(intent3);
            }
        });*/
        NestedScrollView screen = viewScreen.findViewById(R.id.screen);
        TextView question2 = viewScreen.findViewById(R.id.question2);
        TextView question3 = viewScreen.findViewById(R.id.question3);
        TextView question4 = viewScreen.findViewById(R.id.question4);
        TextView question5 = viewScreen.findViewById(R.id.question5);
        TextView question6 = viewScreen.findViewById(R.id.question6);
        TextView question7 = viewScreen.findViewById(R.id.question7);
        TextView question8 = viewScreen.findViewById(R.id.question8);
        TextView question9 = viewScreen.findViewById(R.id.question9);
        TextView question10 = viewScreen.findViewById(R.id.question10);
        TextView question11 = viewScreen.findViewById(R.id.question11);
        TextView question12 = viewScreen.findViewById(R.id.question12);
        TextView question13 = viewScreen.findViewById(R.id.question13);
        TextView question14 = viewScreen.findViewById(R.id.question14);
        TextView question15 = viewScreen.findViewById(R.id.question15);
        TextView question16 = viewScreen.findViewById(R.id.question16);
        TextView question17 = viewScreen.findViewById(R.id.question17);
        TextView question18 = viewScreen.findViewById(R.id.question18);
        TextView text1 = viewScreen.findViewById(R.id.txt1);
        TextView text2 = viewScreen.findViewById(R.id.txt2);
        TextView text3 = viewScreen.findViewById(R.id.txt3);
        TextView text4 = viewScreen.findViewById(R.id.txt4);
        TextView text5 = viewScreen.findViewById(R.id.txt5);
        TextView text6 = viewScreen.findViewById(R.id.txt6);
        TextView text7 = viewScreen.findViewById(R.id.txt7);
        TextView text8 = viewScreen.findViewById(R.id.txt8);
        TextView text9 = viewScreen.findViewById(R.id.txt9);
        TextView text10 = viewScreen.findViewById(R.id.txt10);
        TextView text11 = viewScreen.findViewById(R.id.txt11);
        TextView text12 = viewScreen.findViewById(R.id.txt12);
        TextView text13 = viewScreen.findViewById(R.id.txt13);
        TextView text14 = viewScreen.findViewById(R.id.txt14);
        TextView text15 = viewScreen.findViewById(R.id.txt15);
        TextView text16 = viewScreen.findViewById(R.id.txt16);
        TextView text17 = viewScreen.findViewById(R.id.txt17);
        TextView text18 = viewScreen.findViewById(R.id.txt18);
        TextView textMineralMatch = viewScreen.findViewById(R.id.textMineralMatch);
        switch (isTablet(getActivity())) {
            case "2":
                switch (sizeText) {
                    case "+2":
                        text1.setTextSize(34);
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
                        textMineralMatch.setTextSize(38);
                        question2.setTextSize(34);
                        question3.setTextSize(34);
                        question4.setTextSize(34);
                        question5.setTextSize(34);
                        question6.setTextSize(34);
                        question7.setTextSize(34);
                        question8.setTextSize(34);
                        question9.setTextSize(34);
                        question10.setTextSize(34);
                        question11.setTextSize(34);
                        question12.setTextSize(34);
                        question13.setTextSize(34);
                        question14.setTextSize(34);
                        question15.setTextSize(34);
                        question16.setTextSize(34);
                        question17.setTextSize(34);
                        question18.setTextSize(34);
                        break;
                    case "+1":
                        text1.setTextSize(32);
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
                        textMineralMatch.setTextSize(36);
                        question2.setTextSize(32);
                        question3.setTextSize(32);
                        question4.setTextSize(32);
                        question5.setTextSize(32);
                        question6.setTextSize(32);
                        question7.setTextSize(32);
                        question8.setTextSize(32);
                        question9.setTextSize(32);
                        question10.setTextSize(32);
                        question11.setTextSize(32);
                        question12.setTextSize(32);
                        question13.setTextSize(32);
                        question14.setTextSize(32);
                        question15.setTextSize(32);
                        question16.setTextSize(32);
                        question17.setTextSize(32);
                        question18.setTextSize(32);
                        break;
                    case "0":
                        text1.setTextSize(30);
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
                        textMineralMatch.setTextSize(34);
                        question2.setTextSize(30);
                        question3.setTextSize(30);
                        question4.setTextSize(30);
                        question5.setTextSize(30);
                        question6.setTextSize(30);
                        question7.setTextSize(30);
                        question8.setTextSize(30);
                        question9.setTextSize(30);
                        question10.setTextSize(30);
                        question11.setTextSize(30);
                        question12.setTextSize(30);
                        question13.setTextSize(30);
                        question14.setTextSize(30);
                        question15.setTextSize(30);
                        question16.setTextSize(30);
                        question17.setTextSize(30);
                        question18.setTextSize(30);
                        break;
                    case "-1":
                        text1.setTextSize(28);
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
                        textMineralMatch.setTextSize(32);
                        question2.setTextSize(28);
                        question3.setTextSize(28);
                        question4.setTextSize(28);
                        question5.setTextSize(28);
                        question6.setTextSize(28);
                        question7.setTextSize(28);
                        question8.setTextSize(28);
                        question9.setTextSize(28);
                        question10.setTextSize(28);
                        question11.setTextSize(28);
                        question12.setTextSize(28);
                        question13.setTextSize(28);
                        question14.setTextSize(28);
                        question15.setTextSize(28);
                        question16.setTextSize(28);
                        question17.setTextSize(28);
                        question18.setTextSize(28);
                        break;
                    case "-2":
                        text1.setTextSize(26);
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
                        textMineralMatch.setTextSize(30);
                        question2.setTextSize(26);
                        question3.setTextSize(26);
                        question4.setTextSize(26);
                        question5.setTextSize(26);
                        question6.setTextSize(26);
                        question7.setTextSize(26);
                        question8.setTextSize(26);
                        question9.setTextSize(26);
                        question10.setTextSize(26);
                        question11.setTextSize(26);
                        question12.setTextSize(26);
                        question13.setTextSize(26);
                        question14.setTextSize(26);
                        question15.setTextSize(26);
                        question16.setTextSize(26);
                        question17.setTextSize(26);
                        question18.setTextSize(26);
                        break;
                }
                break;
            case "1":
                switch (sizeText) {
                    case "+2":
                        text1.setTextSize(28);
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
                        textMineralMatch.setTextSize(32);
                        question2.setTextSize(28);
                        question3.setTextSize(28);
                        question4.setTextSize(28);
                        question5.setTextSize(28);
                        question6.setTextSize(28);
                        question7.setTextSize(28);
                        question8.setTextSize(28);
                        question9.setTextSize(28);
                        question10.setTextSize(28);
                        question11.setTextSize(28);
                        question12.setTextSize(28);
                        question13.setTextSize(28);
                        question14.setTextSize(28);
                        question15.setTextSize(28);
                        question16.setTextSize(28);
                        question17.setTextSize(28);
                        question18.setTextSize(28);
                        break;
                    case "+1":
                        text1.setTextSize(26);
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
                        textMineralMatch.setTextSize(30);
                        question2.setTextSize(26);
                        question3.setTextSize(26);
                        question4.setTextSize(26);
                        question5.setTextSize(26);
                        question6.setTextSize(26);
                        question7.setTextSize(26);
                        question8.setTextSize(26);
                        question9.setTextSize(26);
                        question10.setTextSize(26);
                        question11.setTextSize(26);
                        question12.setTextSize(26);
                        question13.setTextSize(26);
                        question14.setTextSize(26);
                        question15.setTextSize(26);
                        question16.setTextSize(26);
                        question17.setTextSize(26);
                        question18.setTextSize(26);
                        break;
                    case "0":
                        text1.setTextSize(24);
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
                        textMineralMatch.setTextSize(28);
                        question2.setTextSize(24);
                        question3.setTextSize(24);
                        question4.setTextSize(24);
                        question5.setTextSize(24);
                        question6.setTextSize(24);
                        question7.setTextSize(24);
                        question8.setTextSize(24);
                        question9.setTextSize(24);
                        question10.setTextSize(24);
                        question11.setTextSize(24);
                        question12.setTextSize(24);
                        question13.setTextSize(24);
                        question14.setTextSize(24);
                        question15.setTextSize(24);
                        question16.setTextSize(24);
                        question17.setTextSize(24);
                        question18.setTextSize(24);
                        break;
                    case "-1":
                        text1.setTextSize(22);
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
                        textMineralMatch.setTextSize(26);
                        question2.setTextSize(22);
                        question3.setTextSize(22);
                        question4.setTextSize(22);
                        question5.setTextSize(22);
                        question6.setTextSize(22);
                        question7.setTextSize(22);
                        question8.setTextSize(22);
                        question9.setTextSize(22);
                        question10.setTextSize(22);
                        question11.setTextSize(22);
                        question12.setTextSize(22);
                        question13.setTextSize(22);
                        question14.setTextSize(22);
                        question15.setTextSize(22);
                        question16.setTextSize(22);
                        question17.setTextSize(22);
                        question18.setTextSize(22);
                        break;
                    case "-2":
                        text1.setTextSize(20);
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
                        textMineralMatch.setTextSize(24);
                        question2.setTextSize(20);
                        question3.setTextSize(20);
                        question4.setTextSize(20);
                        question5.setTextSize(20);
                        question6.setTextSize(20);
                        question7.setTextSize(20);
                        question8.setTextSize(20);
                        question9.setTextSize(20);
                        question10.setTextSize(20);
                        question11.setTextSize(20);
                        question12.setTextSize(20);
                        question13.setTextSize(20);
                        question14.setTextSize(20);
                        question15.setTextSize(20);
                        question16.setTextSize(20);
                        question17.setTextSize(20);
                        question18.setTextSize(20);
                        break;
                }
                break;
            case "0":
                switch (sizeText) {
                    case "+2":
                        text1.setTextSize(22);
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
                        textMineralMatch.setTextSize(26);
                        question2.setTextSize(22);
                        question3.setTextSize(22);
                        question4.setTextSize(22);
                        question5.setTextSize(22);
                        question6.setTextSize(22);
                        question7.setTextSize(22);
                        question8.setTextSize(22);
                        question9.setTextSize(22);
                        question10.setTextSize(22);
                        question11.setTextSize(22);
                        question12.setTextSize(22);
                        question13.setTextSize(22);
                        question14.setTextSize(22);
                        question15.setTextSize(22);
                        question16.setTextSize(22);
                        question17.setTextSize(22);
                        question18.setTextSize(22);
                        break;
                    case "+1":
                        text1.setTextSize(20);
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
                        textMineralMatch.setTextSize(24);
                        question2.setTextSize(20);
                        question3.setTextSize(20);
                        question4.setTextSize(20);
                        question5.setTextSize(20);
                        question6.setTextSize(20);
                        question7.setTextSize(20);
                        question8.setTextSize(20);
                        question9.setTextSize(20);
                        question10.setTextSize(20);
                        question11.setTextSize(20);
                        question12.setTextSize(20);
                        question13.setTextSize(20);
                        question14.setTextSize(20);
                        question15.setTextSize(20);
                        question16.setTextSize(20);
                        question17.setTextSize(20);
                        question18.setTextSize(20);
                        break;
                    case "0":
                        text1.setTextSize(18);
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
                        textMineralMatch.setTextSize(22);
                        question2.setTextSize(18);
                        question3.setTextSize(18);
                        question4.setTextSize(18);
                        question5.setTextSize(18);
                        question6.setTextSize(18);
                        question7.setTextSize(18);
                        question8.setTextSize(18);
                        question9.setTextSize(18);
                        question10.setTextSize(18);
                        question11.setTextSize(18);
                        question12.setTextSize(18);
                        question13.setTextSize(18);
                        question14.setTextSize(18);
                        question15.setTextSize(18);
                        question16.setTextSize(18);
                        question17.setTextSize(18);
                        question18.setTextSize(18);
                        break;
                    case "-1":
                        text1.setTextSize(16);
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
                        textMineralMatch.setTextSize(20);
                        question2.setTextSize(16);
                        question3.setTextSize(16);
                        question4.setTextSize(16);
                        question5.setTextSize(16);
                        question6.setTextSize(16);
                        question7.setTextSize(16);
                        question8.setTextSize(16);
                        question9.setTextSize(16);
                        question10.setTextSize(16);
                        question11.setTextSize(16);
                        question12.setTextSize(16);
                        question13.setTextSize(16);
                        question14.setTextSize(16);
                        question15.setTextSize(16);
                        question16.setTextSize(16);
                        question17.setTextSize(16);
                        question18.setTextSize(16);
                        break;
                    case "-2":
                        text1.setTextSize(14);
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
                        textMineralMatch.setTextSize(14);
                        question2.setTextSize(14);
                        question3.setTextSize(14);
                        question4.setTextSize(14);
                        question5.setTextSize(14);
                        question6.setTextSize(14);
                        question7.setTextSize(14);
                        question8.setTextSize(14);
                        question9.setTextSize(14);
                        question10.setTextSize(14);
                        question11.setTextSize(14);
                        question12.setTextSize(14);
                        question13.setTextSize(14);
                        question14.setTextSize(14);
                        question15.setTextSize(14);
                        question16.setTextSize(14);
                        question17.setTextSize(14);
                        question18.setTextSize(14);
                        break;
                }
                break;
        }
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
            textMineralMatch.setTextColor(getResources().getColor(R.color.black));
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
            textMineralMatch.setTextColor(getResources().getColor(R.color.white));
        }
        question2.setTextColor(getResources().getColor(R.color.blue));
        question3.setTextColor(getResources().getColor(R.color.blue));
        question4.setTextColor(getResources().getColor(R.color.blue));
        question5.setTextColor(getResources().getColor(R.color.blue));
        question6.setTextColor(getResources().getColor(R.color.blue));
        question7.setTextColor(getResources().getColor(R.color.blue));
        question8.setTextColor(getResources().getColor(R.color.blue));
        question9.setTextColor(getResources().getColor(R.color.blue));
        question10.setTextColor(getResources().getColor(R.color.blue));
        question11.setTextColor(getResources().getColor(R.color.blue));
        question12.setTextColor(getResources().getColor(R.color.blue));
        question13.setTextColor(getResources().getColor(R.color.blue));
        question14.setTextColor(getResources().getColor(R.color.blue));
        question15.setTextColor(getResources().getColor(R.color.blue));
        question16.setTextColor(getResources().getColor(R.color.blue));
        question17.setTextColor(getResources().getColor(R.color.blue));
        question18.setTextColor(getResources().getColor(R.color.blue));
        question2.setOnClickListener(v -> showAlertDialog(propiedadesTransparentes.get(0)));
        question3.setOnClickListener(v -> showAlertDialog(propiedadesTransparentes.get(1)));
        question4.setOnClickListener(v -> showAlertDialog(propiedadesTransparentes.get(2)));
        question5.setOnClickListener(v -> showAlertDialog(propiedadesTransparentes.get(3)));
        question6.setOnClickListener(v -> showAlertDialog(propiedadesTransparentes.get(4)));
        question7.setOnClickListener(v -> showAlertDialog(propiedadesTransparentes.get(5)));
        question8.setOnClickListener(v -> showAlertDialog(propiedadesTransparentes.get(6)));
        question9.setOnClickListener(v -> showAlertDialog(propiedadesTransparentes.get(7)));
        question10.setOnClickListener(v -> showAlertDialogFiguraInterferencia(inflater.inflate(R.layout.alert_dialog_figura_interferencia, null)));
        question11.setOnClickListener(v -> {
            if (spn10.getSelectedItemPosition()==0) {
                showAlertDialogSignoOptico(inflater.inflate(R.layout.alert_dialog_signo_optico,null));
            } else if (spn10.getSelectedItemPosition()==1) {
                showAlertDialogSignoOptico(inflater.inflate(R.layout.alert_dialog_signo_optico_uniaxial,null));
            } else if (spn10.getSelectedItemPosition()==2) {
                showAlertDialogSignoOptico(inflater.inflate(R.layout.alert_dialog_signo_optico_biaxial,null));
            }
        });
        question12.setOnClickListener(v -> showAlertDialog(propiedadesOpacos.get(0)));
        question13.setOnClickListener(v -> showAlertDialog(propiedadesOpacos.get(1)));
        question14.setOnClickListener(v -> showAlertDialog(propiedadesOpacos.get(2)));
        question15.setOnClickListener(v -> showAlertDialog(propiedadesOpacos.get(3)));
        question16.setOnClickListener(v -> showAlertDialog(propiedadesOpacos.get(4)));
        question17.setOnClickListener(v -> showAlertDialog(propiedadesOpacos.get(5)));
        question18.setOnClickListener(v -> showAlertDialog(propiedadesOpacos.get(6)));


        LinearLayout transparents = viewScreen.findViewById(R.id.transparents);
        LinearLayout opaques = viewScreen.findViewById(R.id.opaques);
        spn1 = viewScreen.findViewById(R.id.spn1);
        spn2 = viewScreen.findViewById(R.id.spn2);
        spn3 = viewScreen.findViewById(R.id.spn3);
        spn4 = viewScreen.findViewById(R.id.spn4);
        spn5 = viewScreen.findViewById(R.id.spn5);
        spn6 = viewScreen.findViewById(R.id.spn6);
        spn7 = viewScreen.findViewById(R.id.spn7);
        spn8 = viewScreen.findViewById(R.id.spn8);
        spn9 = viewScreen.findViewById(R.id.spn9);
        spn10 = viewScreen.findViewById(R.id.spn10);
        spn11 = viewScreen.findViewById(R.id.spn11);
        spn12 = viewScreen.findViewById(R.id.spn12);
        spn13 = viewScreen.findViewById(R.id.spn13);
        spn14 = viewScreen.findViewById(R.id.spn14);
        spn15 = viewScreen.findViewById(R.id.spn15);
        spn16 = viewScreen.findViewById(R.id.spn16);
        spn17 = viewScreen.findViewById(R.id.spn17);
        spn18 = viewScreen.findViewById(R.id.spn18);
        ArrayAdapter<String> adapter1,adapter2,adapter3,adapter4,adapter5,adapter6,adapter7,adapter8,adapter9,
                adapter10,adapter11,adapter12,adapter13,adapter14,adapter15,adapter16,adapter17,adapter18;
        if (theme.equals("light")) {
            adapter1 = new ArrayAdapter<>(getActivity(), R.layout.spinner_light, getActivity().getResources().getStringArray(R.array.array_diaphaneity));
            adapter2 = new ArrayAdapter<>(getActivity(), R.layout.spinner_light, getActivity().getResources().getStringArray(R.array.array_relief));
            adapter3 = new ArrayAdapter<>(getActivity(), R.layout.spinner_light, getActivity().getResources().getStringArray(R.array.array_color));
            adapter4 = new ArrayAdapter<>(getActivity(), R.layout.spinner_light, getActivity().getResources().getStringArray(R.array.array_pleochroism));
            adapter5 = new ArrayAdapter<>(getActivity(), R.layout.spinner_light, getActivity().getResources().getStringArray(R.array.array_cleavage));
            adapter6 = new ArrayAdapter<>(getActivity(), R.layout.spinner_light, getActivity().getResources().getStringArray(R.array.array_angle));
            adapter7 = new ArrayAdapter<>(getActivity(), R.layout.spinner_light, getActivity().getResources().getStringArray(R.array.array_interference));
            adapter8 = new ArrayAdapter<>(getActivity(), R.layout.spinner_light, getActivity().getResources().getStringArray(R.array.array_extinction));
            adapter9 = new ArrayAdapter<>(getActivity(), R.layout.spinner_light, getActivity().getResources().getStringArray(R.array.array_twinning));
            adapter10 = new ArrayAdapter<>(getActivity(), R.layout.spinner_light, getActivity().getResources().getStringArray(R.array.array_interference_figure));
            adapter11 = new ArrayAdapter<>(getActivity(), R.layout.spinner_light, getActivity().getResources().getStringArray(R.array.array_optical_sign));
            adapter12 = new ArrayAdapter<>(getActivity(), R.layout.spinner_light, getActivity().getResources().getStringArray(R.array.array_color_opaques));
            adapter13 = new ArrayAdapter<>(getActivity(), R.layout.spinner_light, getActivity().getResources().getStringArray(R.array.array_reflectance));
            adapter14 = new ArrayAdapter<>(getActivity(), R.layout.spinner_light, getActivity().getResources().getStringArray(R.array.array_pleochroism_opaques));
            adapter15 = new ArrayAdapter<>(getActivity(), R.layout.spinner_light, getActivity().getResources().getStringArray(R.array.array_polishing_hardness));
            adapter16 = new ArrayAdapter<>(getActivity(), R.layout.spinner_light, getActivity().getResources().getStringArray(R.array.array_anisotropism));
            adapter17 = new ArrayAdapter<>(getActivity(), R.layout.spinner_light, getActivity().getResources().getStringArray(R.array.array_interference_colors));
            adapter18 = new ArrayAdapter<>(getActivity(), R.layout.spinner_light, getActivity().getResources().getStringArray(R.array.array_internal_reflections));
        } else {
            adapter1 = new ArrayAdapter<>(getActivity(), R.layout.spinner_darck, getActivity().getResources().getStringArray(R.array.array_diaphaneity));
            adapter2 = new ArrayAdapter<>(getActivity(), R.layout.spinner_darck, getActivity().getResources().getStringArray(R.array.array_relief));
            adapter3 = new ArrayAdapter<>(getActivity(), R.layout.spinner_darck, getActivity().getResources().getStringArray(R.array.array_color));
            adapter4 = new ArrayAdapter<>(getActivity(), R.layout.spinner_darck, getActivity().getResources().getStringArray(R.array.array_pleochroism));
            adapter5 = new ArrayAdapter<>(getActivity(), R.layout.spinner_darck, getActivity().getResources().getStringArray(R.array.array_cleavage));
            adapter6 = new ArrayAdapter<>(getActivity(), R.layout.spinner_darck, getActivity().getResources().getStringArray(R.array.array_angle));
            adapter7 = new ArrayAdapter<>(getActivity(), R.layout.spinner_darck, getActivity().getResources().getStringArray(R.array.array_interference));
            adapter8 = new ArrayAdapter<>(getActivity(), R.layout.spinner_darck, getActivity().getResources().getStringArray(R.array.array_extinction));
            adapter9 = new ArrayAdapter<>(getActivity(), R.layout.spinner_darck, getActivity().getResources().getStringArray(R.array.array_twinning));
            adapter10 = new ArrayAdapter<>(getActivity(), R.layout.spinner_darck, getActivity().getResources().getStringArray(R.array.array_interference_figure));
            adapter11 = new ArrayAdapter<>(getActivity(), R.layout.spinner_darck, getActivity().getResources().getStringArray(R.array.array_optical_sign));
            adapter12 = new ArrayAdapter<>(getActivity(), R.layout.spinner_darck, getActivity().getResources().getStringArray(R.array.array_color_opaques));
            adapter13 = new ArrayAdapter<>(getActivity(), R.layout.spinner_darck, getActivity().getResources().getStringArray(R.array.array_reflectance));
            adapter14 = new ArrayAdapter<>(getActivity(), R.layout.spinner_darck, getActivity().getResources().getStringArray(R.array.array_pleochroism_opaques));
            adapter15 = new ArrayAdapter<>(getActivity(), R.layout.spinner_darck, getActivity().getResources().getStringArray(R.array.array_polishing_hardness));
            adapter16 = new ArrayAdapter<>(getActivity(), R.layout.spinner_darck, getActivity().getResources().getStringArray(R.array.array_anisotropism));
            adapter17 = new ArrayAdapter<>(getActivity(), R.layout.spinner_darck, getActivity().getResources().getStringArray(R.array.array_interference_colors));
            adapter18 = new ArrayAdapter<>(getActivity(), R.layout.spinner_darck, getActivity().getResources().getStringArray(R.array.array_internal_reflections));
        }
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter14.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter15.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter16.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter17.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter18.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn1.setAdapter(adapter1);
        spn2.setAdapter(adapter2);
        spn3.setAdapter(adapter3);
        spn4.setAdapter(adapter4);
        spn5.setAdapter(adapter5);
        spn6.setAdapter(adapter6);
        spn7.setAdapter(adapter7);
        spn8.setAdapter(adapter8);
        spn9.setAdapter(adapter9);
        spn10.setAdapter(adapter10);
        spn11.setAdapter(adapter11);
        spn12.setAdapter(adapter12);
        spn13.setAdapter(adapter13);
        spn14.setAdapter(adapter14);
        spn15.setAdapter(adapter15);
        spn16.setAdapter(adapter16);
        spn17.setAdapter(adapter17);
        spn18.setAdapter(adapter18);
        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0) {
                    Database myDb2 = new Database(getActivity());
                    minerales.clear();
                    minerales = myDb2.getTransparentsFiltro();
                    adapter = new CustomRecyclerAdapter(getActivity(), minerales, FilterFragment.this);
                    recyclerViewList.setAdapter(adapter);
                    myDb2.closeDatabase();
                    LinearLayout transparentes = viewScreen.findViewById(R.id.transparents);
                    transparentes.setVisibility(View.VISIBLE);
                    opaques.setVisibility(View.GONE);
                    spn2.setSelection(0);
                    spn3.setSelection(0);
                    spn4.setSelection(0);
                    spn5.setSelection(0);
                    spn6.setSelection(0);
                    spn7.setSelection(0);
                    spn8.setSelection(0);
                    spn9.setSelection(0);
                    spn10.setSelection(0);
                    spn11.setSelection(0);
                } else if (position==1) {
                    Database myDb3 = new Database(getActivity());
                    minerales.clear();
                    minerales = myDb3.getOpacosFiltro();
                    adapter = new CustomRecyclerAdapter(getActivity(), minerales,FilterFragment.this);
                    recyclerViewList.setAdapter(adapter);
                    myDb3.closeDatabase();
                    LinearLayout opacos = viewScreen.findViewById(R.id.opaques);
                    opacos.setVisibility(View.VISIBLE);
                    transparents.setVisibility(View.GONE);
                    spn12.setSelection(0);
                    spn13.setSelection(0);
                    spn14.setSelection(0);
                    spn15.setSelection(0);
                    spn16.setSelection(0);
                    spn17.setSelection(0);
                    spn18.setSelection(0);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spn2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rellenaTransparentes();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spn3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rellenaTransparentes();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spn4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rellenaTransparentes();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spn5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rellenaTransparentes();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spn6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rellenaTransparentes();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spn7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rellenaTransparentes();
                if (position==1) {
                    if (theme.equals("light")) {
                        adapterNone = new ArrayAdapter<>(getActivity(), R.layout.spinner_light, getActivity().getResources().getStringArray(R.array.array_none));
                    } else {
                        adapterNone = new ArrayAdapter<>(getActivity(), R.layout.spinner_darck, getActivity().getResources().getStringArray(R.array.array_none));
                    }
                    adapterNone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spn8.setAdapter(adapterNone);
                    spn9.setAdapter(adapterNone);
                    spn10.setAdapter(adapterNone);
                    spn11.setAdapter(adapterNone);
                } else {
                    spn8.setAdapter(adapter8);
                    spn9.setAdapter(adapter9);
                    spn10.setAdapter(adapter10);
                    spn11.setAdapter(adapter11);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spn8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rellenaTransparentes();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spn9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rellenaTransparentes();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spn10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rellenaTransparentes();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spn11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rellenaTransparentes();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spn12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rellenaOpacos();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spn13.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rellenaOpacos();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spn14.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rellenaOpacos();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spn15.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rellenaOpacos();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spn16.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rellenaOpacos();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spn17.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rellenaOpacos();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spn18.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rellenaOpacos();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        myDb.closeDatabase();
        return viewScreen;
    }
    public void rellenaTransparentes() {
        minerales.clear();
        Database myDb = new Database(getActivity());
        ArrayList<String> mineralesCopy = myDb.getTransparentsFiltro();
        for (int i=0;i<mineralesCopy.size();i++) {
            String relief = myDb.getReliefTransparentesFiltro(mineralesCopy.get(i));
            String reliefSpinner = spn2.getSelectedItem().toString();
            boolean containsRelief = false;
            if (spn2.getSelectedItemPosition()!=0) {
                if (containsWord(reliefSpinner,relief)) {
                    containsRelief = true;
                }
            } else {
                containsRelief = true;
            }
            String color = myDb.getColorTransparentesFiltro(mineralesCopy.get(i));
            String colorSpinner = spn3.getSelectedItem().toString();
            boolean containsColor = false;
            if (spn3.getSelectedItemPosition()!=0) {
                if (containsWord(colorSpinner,color)) {
                    containsColor = true;
                }
            } else {
                containsColor = true;
            }
            String pleochroism = myDb.getPleochroismTransparentesFiltro(mineralesCopy.get(i));
            String pleochroismSpinner = spn4.getSelectedItem().toString();
            boolean containsPleochroism = false;
            if (spn4.getSelectedItemPosition()!=0) {
                if (containsWord(pleochroismSpinner,pleochroism)) {
                    containsPleochroism = true;
                }
            } else {
                containsPleochroism = true;
            }
            String numberCleavageDirections = myDb.getNumberCleavageDirectionsTransparentesFiltro(mineralesCopy.get(i));
            String numberCleavageDirectionsSpinner = spn5.getSelectedItem().toString();
            boolean containsNumberCleavageDirections = false;
            if (spn5.getSelectedItemPosition()!=0) {
                if (containsWord(numberCleavageDirectionsSpinner,numberCleavageDirections)) {
                    containsNumberCleavageDirections = true;
                }
            } else {
                containsNumberCleavageDirections = true;
            }
            String angleOfCleavage = myDb.getAngleOfCleavageTransparentesFiltro(mineralesCopy.get(i));
            String angleOfCleavageSpinner = spn6.getSelectedItem().toString();
            boolean containsAngleOfCleavage = false;
            if (spn6.getSelectedItemPosition()!=0) {
                if (containsWord(angleOfCleavageSpinner,angleOfCleavage)) {
                    containsAngleOfCleavage = true;
                }
            } else {
                containsAngleOfCleavage = true;
            }
            String interferenceColor = myDb.getInterferenceColorTransparentesFiltro(mineralesCopy.get(i));
            String interferenceColorSpinner = spn7.getSelectedItem().toString();
            boolean containsInterferenceColor = false;
            if (spn7.getSelectedItemPosition()!=0) {
                if (containsWord(interferenceColorSpinner,interferenceColor)) {
                    containsInterferenceColor = true;
                }
            } else {
                containsInterferenceColor = true;
            }
            String extinction = myDb.getExtinctionTransparentesFiltro(mineralesCopy.get(i));
            String extinctionSpinner = spn8.getSelectedItem().toString();
            boolean containsExtinction = false;
            if (spn8.getSelectedItemPosition()!=0) {
                if (containsWord(extinctionSpinner,extinction)) {
                    containsExtinction = true;
                }
            } else {
                containsExtinction = true;
            }
            String twinning = myDb.getTwinningTransparentesFiltro(mineralesCopy.get(i));
            String twinningSpinner = spn9.getSelectedItem().toString();
            boolean containsTwinning = false;
            if (spn9.getSelectedItemPosition()!=0) {
                if (containsWord(twinningSpinner,twinning)) {
                    containsTwinning = true;
                }
            } else {
                containsTwinning = true;
            }
            String interferenceFigure = myDb.getInterferenceFigureTransparentesFiltro(mineralesCopy.get(i));
            String interferenceFigureSpinner = spn10.getSelectedItem().toString();
            boolean containsInterferenceFigure = false;
            if (spn10.getSelectedItemPosition()!=0) {
                if (containsWord(interferenceFigureSpinner,interferenceFigure)) {
                    containsInterferenceFigure = true;
                }
            } else {
                containsInterferenceFigure = true;
            }
            String opticalSign = myDb.getOpticalSignTransparentesFiltro(mineralesCopy.get(i));
            String opticalSignSpinner = spn11.getSelectedItem().toString();
            boolean containsOpticalSign = false;
            if (spn11.getSelectedItemPosition()!=0) {
                if (containsWord(opticalSignSpinner,opticalSign)) {
                    containsOpticalSign = true;
                }
            } else {
                containsOpticalSign = true;
            }
            if (containsRelief&&containsColor&&containsPleochroism&&containsNumberCleavageDirections&&containsAngleOfCleavage&&containsInterferenceColor
            &&containsExtinction&&containsTwinning&&containsInterferenceFigure&&containsOpticalSign) {
                minerales.add(mineralesCopy.get(i));
            }
        }
        adapter = new CustomRecyclerAdapter(getActivity(), minerales,FilterFragment.this);
        recyclerViewList.setAdapter(adapter);
        myDb.closeDatabase();
    }
    public void rellenaOpacos() {
        minerales.clear();
        Database myDb = new Database(getActivity());
        ArrayList<String> mineralesCopy = myDb.getOpacosFiltro();
        for (int i=0;i<mineralesCopy.size();i++) {
            String color = myDb.getColorOpacosFiltro(mineralesCopy.get(i));
            String colorSpinner = spn12.getSelectedItem().toString();
            boolean containsColor = false;
            if (spn12.getSelectedItemPosition()!=0) {
                if (containsWord(colorSpinner,color)) {
                    containsColor = true;
                }
            } else {
                containsColor = true;
            }
            String reflectance = myDb.getReflectanceOpacosFiltro(mineralesCopy.get(i));
            String reflectanceSpinner = spn13.getSelectedItem().toString();
            boolean containsReflectance = false;
            if (spn13.getSelectedItemPosition()!=0) {
                if (containsWord(reflectanceSpinner,reflectance)) {
                    containsReflectance = true;
                }
            } else {
                containsReflectance = true;
            }
            String pleochroism = myDb.getPleochroismOpacosFiltro(mineralesCopy.get(i));
            String pleochroismSpinner = spn14.getSelectedItem().toString();
            boolean containsPleochroism = false;
            if (spn14.getSelectedItemPosition()!=0) {
                if (containsWord(pleochroismSpinner,pleochroism)) {
                    containsPleochroism = true;
                }
            } else {
                containsPleochroism = true;
            }
            String polishingHardness = myDb.getPolishingHardnessOpacosFiltro(mineralesCopy.get(i));
            String polishingHardnessSpinner = spn15.getSelectedItem().toString();
            boolean containsPolishingHardness = false;
            if (spn15.getSelectedItemPosition()!=0) {
                if (containsWord(polishingHardnessSpinner,polishingHardness)) {
                    containsPolishingHardness = true;
                }
            } else {
                containsPolishingHardness = true;
            }
            String anisotropism = myDb.getAnisotropismOpacosFiltro(mineralesCopy.get(i));
            String anisotropismSpinner = spn16.getSelectedItem().toString();
            boolean containsAnisotropism = false;
            if (spn16.getSelectedItemPosition()!=0) {
                if (containsWord(anisotropismSpinner,anisotropism)) {
                    containsAnisotropism = true;
                }
            } else {
                containsAnisotropism = true;
            }
            String interferenceColors = myDb.getInterenceColorsOpacosFiltro(mineralesCopy.get(i));
            String interferenceColorsSpinner = spn17.getSelectedItem().toString();
            boolean containsInterferenceColors = false;
            if (spn17.getSelectedItemPosition()!=0) {
                if (containsWord(interferenceColorsSpinner,interferenceColors)) {
                    containsInterferenceColors = true;
                }
            } else {
                containsInterferenceColors = true;
            }
            String internalReflections = myDb.getInternalReflectionsOpacosFiltro(mineralesCopy.get(i));
            String internalReflectionsSpinner = spn18.getSelectedItem().toString();
            boolean containsInternalReflections = false;
            if (spn18.getSelectedItemPosition()!=0) {
                if (containsWord(internalReflectionsSpinner,internalReflections)) {
                    containsInternalReflections = true;
                }
            } else {
                containsInternalReflections = true;
            }

            if (containsColor&&containsReflectance&&containsPleochroism&&containsPolishingHardness&&containsAnisotropism
            &&containsInterferenceColors&&containsInternalReflections) {
                minerales.add(mineralesCopy.get(i));
            }
        }
        adapter = new CustomRecyclerAdapter(getActivity(), minerales,FilterFragment.this);
        recyclerViewList.setAdapter(adapter);
        myDb.closeDatabase();
    }
    private boolean containsWord(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return false;
        }
        return word2.toLowerCase().contains(word1.toLowerCase());
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                if (newText.length() == 0) {
                    mineNombres.clear();
                    adapterBusqueda = new CustomRecyclerAdapter(getActivity(), mineNombres,FilterFragment.this);
                    recyclerViewSearch.setAdapter(adapterBusqueda);
                }
                Database myDb = new Database(getActivity());
                ArrayList<String> mineNombresTodos = myDb.getNombres();
                if (newText.length() != 0) {
                    mineNombres.clear();
                    adapterBusqueda = new CustomRecyclerAdapter(getActivity(), mineNombres,FilterFragment.this);
                    recyclerViewSearch.setAdapter(adapterBusqueda);
                    int chars = newText.length();
                    for (int i=0;i<mineNombresTodos.size();i++) {
                        String nombre = mineNombresTodos.get(i).toLowerCase();
                        String subNombre = "";
                        try {
                            subNombre = nombre.substring(0, chars);
                        } catch (Exception exc) {
                            subNombre = nombre.substring(0, nombre.length() - 1);
                        }
                        boolean yaNombre = false;
                        for (int h = 0; h < mineNombres.size(); h++) {
                            if (nombre.equals(mineNombres.get(h))) {
                                yaNombre = true;
                            }
                        }
                        if (newText.equals(subNombre) && (!yaNombre)) {
                            mineNombres.add(nombre);
                            adapterBusqueda = new CustomRecyclerAdapter(getActivity(), mineNombres,FilterFragment.this);
                            recyclerViewSearch.setAdapter(adapterBusqueda);
                        }
                    }
                }
                myDb.closeDatabase();
                return true;
            }
        });
    }

    @Override
    public void onRecyclerItemClick(View view, int position) {
        RecyclerView clickedRecyclerView = (RecyclerView) view.getParent();
        Database myDb = new Database(getActivity());
        if (clickedRecyclerView == recyclerViewList) {
            String mineral = minerales.get(position);
            if (spn1.getSelectedItemPosition()==0) {
                if (myDb.isMineralTransparents(mineral)) {
                    String id = myDb.getIdFromMineralTransparent(mineral);
                    Intent intent = new Intent(getActivity(), FichaTransparentes.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                } else if (myDb.isMineralFiltroTransparentes(mineral)) {
                    String id = myDb.getIdFromMineralFiltroTransparentes(mineral);
                    Intent intent = new Intent(getActivity(), FichaFiltroTransparentes.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            } else {
                if (myDb.isMineralOpaques(mineral)) {
                    String id = myDb.getIdFromMineralOpaque(mineral);
                    Intent intent = new Intent(getActivity(), FichaOpacos.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                } else if (myDb.isMineralFiltroOpacos(mineral)) {
                    String id = myDb.getIdFromMineralFiltroOpacos(mineral);
                    Intent intent = new Intent(getActivity(), FichaFiltroOpacos.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            }
        } else if (clickedRecyclerView == recyclerViewSearch) {
            String mineral = mineNombres.get(position);
            if (mineral.substring(0,3).equals("pro")) {
                String id = "11";
                Intent intent = new Intent(getActivity(), FichaOpacos.class);
                intent.putExtra("id", id);
                startActivity(intent);
            } else if (mineral.substring(0,3).equals("tet")) {
                String id = "15";
                Intent intent = new Intent(getActivity(), FichaOpacos.class);
                intent.putExtra("id", id);
                startActivity(intent);
            } else if (myDb.isMineralOpaques(mineral)) {
                String id = myDb.getIdFromMineralOpaque(mineral.substring(0,1).toUpperCase()+mineral.substring(1));
                Intent intent = new Intent(getActivity(), FichaOpacos.class);
                intent.putExtra("id", id);
                startActivity(intent);
            } else if (myDb.isMineralTransparents(mineral)) {
                String id = myDb.getIdFromMineralTransparent(mineral.substring(0,1).toUpperCase()+mineral.substring(1));
                Intent intent = new Intent(getActivity(), FichaTransparentes.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        }
        myDb.closeDatabase();
    }
    private void showAlertDialog(String message) {
        // Reemplazar los caracteres \n con saltos de línea reales
        String formattedMessage = message.replace("\\n", "\n");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(formattedMessage)
                .setPositiveButton("Accept", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void showAlertDialogFiguraInterferencia(View customAlertDialogView) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setPositiveButton(getResources().getString(R.string.close), (dialog, which) -> dialog.dismiss());
        alertDialogBuilder.setView(customAlertDialogView);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    private void showAlertDialogSignoOptico(View customAlertDialogView) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setPositiveButton(getResources().getString(R.string.close), (dialog, which) -> dialog.dismiss());
        alertDialogBuilder.setView(customAlertDialogView);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
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
}