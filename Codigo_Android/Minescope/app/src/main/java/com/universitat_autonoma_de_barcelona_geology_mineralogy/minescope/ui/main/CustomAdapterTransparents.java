package com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope.ui.main;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;
import com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope.Database;
import com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope.R;
import java.util.ArrayList;

public class CustomAdapterTransparents extends ArrayAdapter {
    private final ArrayList<String> items;
    Context mContext;
    private String theme;
    private SharedPreferences sharedPreferences;
    public CustomAdapterTransparents(Context context, ArrayList<String> items) {
        super(context, R.layout.row_item, items);
        this.items = items;
        this.mContext = context;
        sharedPreferences = context.getSharedPreferences("UserPreferences", MODE_PRIVATE);
        theme = sharedPreferences.getString("theme", "light");
    }
    @Nullable
    @Override
    public String getItem(int position) {
        return items.get(position);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            rowView = inflater.inflate(R.layout.row_item, parent, false);
        }
        ImageView ivItem1 = rowView.findViewById(R.id.icon1);
        ImageView ivItem2 = rowView.findViewById(R.id.icon2);
        TextView tvTitle = rowView.findViewById(R.id.tw1);
        LinearLayout linearImages = rowView.findViewById(R.id.linearImages);
        String item = this.items.get(position);
        tvTitle.setText(item);
        if (theme.equals("light")) {
            tvTitle.setTextColor(ContextCompat.getColor(this.mContext,R.color.black));
            tvTitle.setBackgroundColor(ContextCompat.getColor(this.mContext,R.color.white));
            linearImages.setBackgroundColor(ContextCompat.getColor(this.mContext,R.color.white));
        } else {
            tvTitle.setTextColor(ContextCompat.getColor(this.mContext,R.color.white));
            tvTitle.setBackgroundColor(ContextCompat.getColor(this.mContext,R.color.black));
            linearImages.setBackgroundColor(ContextCompat.getColor(this.mContext,R.color.black));
        }
        Database myDb = new Database(mContext);
        String urlicon1 = myDb.getURLIcon1Transparents(item);
        Picasso.get().load(urlicon1).into(ivItem1);
        String urlicon2 = myDb.getURLIcon2Transparents(item);
        Picasso.get().load(urlicon2).into(ivItem2);
        myDb.closeDatabase();
        String sizeText = sharedPreferences.getString("sizeText", "0");
        switch (isTablet(mContext)) {
            case "2":
                switch (sizeText) {
                    case "+2":
                        tvTitle.setTextSize(32);
                        break;
                    case "+1":
                        tvTitle.setTextSize(30);
                        break;
                    case "0":
                        tvTitle.setTextSize(28);
                        break;
                    case "-1":
                        tvTitle.setTextSize(26);
                        break;
                    case "-2":
                        tvTitle.setTextSize(24);
                        break;
                }
                break;
            case "1":
                switch (sizeText) {
                    case "+2":
                        tvTitle.setTextSize(26);
                        break;
                    case "+1":
                        tvTitle.setTextSize(24);
                        break;
                    case "0":
                        tvTitle.setTextSize(22);
                        break;
                    case "-1":
                        tvTitle.setTextSize(20);
                        break;
                    case "-2":
                        tvTitle.setTextSize(18);
                        break;
                }
                break;
            case "0":
                switch (sizeText) {
                    case "+2":
                        tvTitle.setTextSize(20);
                        break;
                    case "+1":
                        tvTitle.setTextSize(18);
                        break;
                    case "0":
                        tvTitle.setTextSize(16);
                        break;
                    case "-1":
                        tvTitle.setTextSize(14);
                        break;
                    case "-2":
                        tvTitle.setTextSize(12);
                        break;
                }
                break;
        }
        return rowView;
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

