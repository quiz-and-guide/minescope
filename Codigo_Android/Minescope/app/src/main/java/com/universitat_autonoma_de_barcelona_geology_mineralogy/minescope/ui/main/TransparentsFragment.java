package com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope.ui.main;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope.Database;
import com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope.FichaOpacos;
import com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope.FichaTransparentes;
import com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope.MicroscopioTransparentes;
import com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope.R;
import com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope.Settings;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class TransparentsFragment extends Fragment {


    ListView list;
    private ArrayList<String> mineNombres;
    private ArrayAdapter<String> adapterBusqueda;
    private SearchView searchView;

    public TransparentsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewScreen = inflater.inflate(R.layout.fragment_transparents, container, false);

        list = (ListView) viewScreen.findViewById(R.id.list_view);
        Database myDb = new Database(getActivity());
        ArrayList<String> minerales = myDb.getTransparents();
        myDb.closeDatabase();
        CustomAdapterTransparents adapter = new CustomAdapterTransparents(getActivity(),minerales);
        list.setAdapter(adapter);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserPreferences", MODE_PRIVATE);
        String theme = sharedPreferences.getString("theme", "light");
        setHasOptionsMenu(true);


        list.setOnItemClickListener((parent, view, position, id) -> {
            Database myDb2 = new Database(getActivity());
            String mineral = parent.getItemAtPosition(position).toString();
            String idIntent = myDb2.getIdFromMineralTransparent(mineral);
            myDb2.closeDatabase();
            Intent intent = new Intent(getActivity(), FichaTransparentes.class);
            intent.putExtra("id",idIntent);
            startActivity(intent);
        });
        final ListView listViewS = viewScreen.findViewById(R.id.list_view_search);
        mineNombres = new ArrayList<>();
        adapterBusqueda = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mineNombres);
        listViewS.setAdapter(adapterBusqueda);
        listViewS.setOnItemClickListener((parent, view, position, id) -> {
            String nombreMineral = mineNombres.get(position);
            if (nombreMineral.substring(0,3).equals("pro")) {
                Intent intent = new Intent(getActivity(), FichaOpacos.class);
                intent.putExtra("id", "11");
                startActivity(intent);
            } else if (nombreMineral.substring(0,3).equals("tet")) {
                Intent intent = new Intent(getActivity(), FichaOpacos.class);
                intent.putExtra("id", "15");
                startActivity(intent);
            } else {
                Database myDb2 = new Database(getActivity());
                String idMineOpaques = myDb2.getIdFromMineralOpaque(nombreMineral.substring(0, 1).toUpperCase() + nombreMineral.substring(1).toLowerCase());
                String idMineTransparents = myDb2.getIdFromMineralTransparent(nombreMineral.substring(0, 1).toUpperCase() + nombreMineral.substring(1).toLowerCase());
                myDb2.closeDatabase();
                mineNombres.clear();
                adapterBusqueda.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                if (idMineTransparents.length() > 0) {
                    Intent intent2 = new Intent(getActivity(), FichaTransparentes.class);
                    intent2.putExtra("id", idMineTransparents);
                    searchView.setQuery("", false);
                    startActivity(intent2);
                }
                if (idMineOpaques.length() > 0) {
                    Intent intent3 = new Intent(getActivity(), FichaOpacos.class);
                    intent3.putExtra("id", idMineOpaques);
                    searchView.setQuery("", false);
                    startActivity(intent3);
                }
            }
        });
        return viewScreen;
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
                    adapterBusqueda.notifyDataSetChanged();
                }
                Database myDb = new Database(getActivity());
                ArrayList<String> mineNombresTodos = myDb.getNombres();
                if (newText.length() != 0) {
                    mineNombres.clear();
                    adapterBusqueda.notifyDataSetChanged();
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
                            adapterBusqueda.notifyDataSetChanged();
                        }
                    }
                }
                myDb.closeDatabase();
                return true;
            }
        });
    }
}