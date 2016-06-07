package com.example.titan.dyscalculator;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Joris on 7-6-2016.
 */
public class SettingsListViewAdapter extends BaseAdapter {

    Context context;
    ArrayList<Setting> settingItems;

    public SettingsListViewAdapter(Context context) {
        this.context = context;
    }

    public SettingsListViewAdapter(Context context, ArrayList<Setting> settingItems) {
        this.context = context;
        this.settingItems = settingItems;
    }

    @Override
    public int getCount() {
        return settingItems.size();
    }

    @Override
    public Object getItem(int position) {
        return settingItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return settingItems.indexOf(getItem(position));
    }

    /* private view holder class */
    private class ViewHolder {
        TextView setting_name_view;
        TextView setting_value_view;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.activity_settings_list_item, null);

            holder = new ViewHolder();
            holder.setting_name_view = (TextView) convertView
                    .findViewById(R.id.setting_name);
            holder.setting_value_view = (TextView) convertView.findViewById(R.id.setting_value);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Setting setting = settingItems.get(position);
        holder.setting_name_view.setText(setting.setting_name);
        holder.setting_value_view.setText(setting.setting_value);

        return convertView;
    }
}

