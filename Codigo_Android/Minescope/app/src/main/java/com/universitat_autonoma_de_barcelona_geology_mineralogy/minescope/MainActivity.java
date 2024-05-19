package com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope.ui.main.ViewPagerAdapter;
import com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);
        SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        String language = sharedPreferences.getString("language", Locale.getDefault().getLanguage());
        Log.i("language",language);
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.main);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(viewPagerAdapter);
        tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        setupTabIcons();

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Database myDb = new Database(this);
            boolean isMineralOpaques = myDb.isMineralOpaques(query);
            boolean isMineralTransparents = myDb.isMineralTransparents(query);
            if (isMineralOpaques) {
                doMySearch(query,"opaques");
            } else if (isMineralTransparents) {
                doMySearch(query,"transparents");
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.mineral_not_found), Toast.LENGTH_SHORT);
                toast.show();
            }
            myDb.closeDatabase();
        }
    }
    private void setupTabIcons() {
        tabs.getTabAt(0).setIcon(R.drawable.ic_transparents);
        tabs.getTabAt(1).setIcon(R.drawable.ic_opaques);
        tabs.getTabAt(2).setIcon(R.drawable.ic_filter);
    }
    public void doMySearch(String mineral, String clase) {
        if (clase.equals("opaques")) {
            Database myDb = new Database(this);
            String id = myDb.getIdFromMineralOpaque(mineral.substring(0,1).toUpperCase()+mineral.substring(1).toLowerCase());
            Intent intent = new Intent(MainActivity.this, FichaOpacos.class);
            intent.putExtra("id", id);
            startActivity(intent);
            myDb.closeDatabase();
        } else {
            Database myDb = new Database(this);
            String id = myDb.getIdFromMineralTransparent(mineral.substring(0,1).toUpperCase()+mineral.substring(1).toLowerCase());
            Intent intent = new Intent(MainActivity.this, FichaTransparentes.class);
            intent.putExtra("id", id);
            startActivity(intent);
            myDb.closeDatabase();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_settings) {
            Intent intent = new Intent(MainActivity.this, Settings.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        int positionOfMenuItem = 1; // or whatever...
        MenuItem item = menu.getItem(positionOfMenuItem);
        SpannableString s = new SpannableString("Settings");
        s.setSpan(new ForegroundColorSpan(Color.BLACK), 0, s.length(), 0);
        item.setTitle(s);
        return true;
    }
}