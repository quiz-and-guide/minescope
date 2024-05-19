package com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope.ui.main;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope.R;

import java.util.List;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {

    private List<String> data;
    private LayoutInflater inflater;
    private OnRecyclerItemClickListener onRecyclerItemClickListener;
    private String theme;
    private SharedPreferences sharedPreferences;
    private Context mContext;
    public CustomRecyclerAdapter(Context context, List<String> data, OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.data = data;
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
        sharedPreferences = context.getSharedPreferences("UserPreferences", MODE_PRIVATE);
        theme = sharedPreferences.getString("theme", "light");
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view, onRecyclerItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String item = data.get(position);
        holder.textView.setText(item);
        holder.textView.setGravity(Gravity.CENTER_HORIZONTAL);
        if (theme.equals("light")) {
            holder.textView.setTextColor(ContextCompat.getColor(this.mContext, R.color.black));
            holder.textView.setBackgroundColor(ContextCompat.getColor(this.mContext,R.color.white));
        } else {
            holder.textView.setTextColor(ContextCompat.getColor(this.mContext, R.color.white));
            holder.textView.setBackgroundColor(ContextCompat.getColor(this.mContext,R.color.black));
        }
        String sizeText = sharedPreferences.getString("sizeText", "0");
        switch (isTablet(mContext)) {
            case "2":
                switch (sizeText) {
                    case "+2":
                        holder.textView.setTextSize(32);
                        break;
                    case "+1":
                        holder.textView.setTextSize(30);
                        break;
                    case "0":
                        holder.textView.setTextSize(28);
                        break;
                    case "-1":
                        holder.textView.setTextSize(26);
                        break;
                    case "-2":
                        holder.textView.setTextSize(24);
                        break;
                }
                break;
            case "1":
                switch (sizeText) {
                    case "+2":
                        holder.textView.setTextSize(26);
                        break;
                    case "+1":
                        holder.textView.setTextSize(24);
                        break;
                    case "0":
                        holder.textView.setTextSize(22);
                        break;
                    case "-1":
                        holder.textView.setTextSize(20);
                        break;
                    case "-2":
                        holder.textView.setTextSize(18);
                        break;
                }
                break;
            case "0":
                switch (sizeText) {
                    case "+2":
                        holder.textView.setTextSize(20);
                        break;
                    case "+1":
                        holder.textView.setTextSize(18);
                        break;
                    case "0":
                        holder.textView.setTextSize(16);
                        break;
                    case "-1":
                        holder.textView.setTextSize(14);
                        break;
                    case "-2":
                        holder.textView.setTextSize(12);
                        break;
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public interface OnRecyclerItemClickListener {
        void onRecyclerItemClick(View view, int position);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView, final OnRecyclerItemClickListener onRecyclerItemClickListener) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRecyclerItemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onRecyclerItemClickListener.onRecyclerItemClick(v, position);
                        }
                    }
                }
            });
        }
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

