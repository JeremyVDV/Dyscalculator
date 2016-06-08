package com.example.titan.dyscalculator;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joris on 7-6-2016.
 */
public class Settings {

    public static final String UITSPRAAK_VETRAGING_DEFAULT_VALUE = "0";
    public static final String UITSPRAAK_VETRAGING_NAME = "UITSPRAAK_VERTRAGING";

    private HashMap<String, String> settings = new HashMap<String, String>() { {put(UITSPRAAK_VETRAGING_NAME, UITSPRAAK_VETRAGING_DEFAULT_VALUE);} };

    public static final int totalSettings = 1;

    public final Context context;
    private final SharedPreferences sharedPrefs;

    private static Settings instance;

    public static Settings getInstance(Context context) {
        if (instance == null) {
            instance = new Settings(context.getApplicationContext());
        }
        return instance;
    }

    private Settings(Context context) {
        this.context = context;
        sharedPrefs = context.getApplicationContext().getSharedPreferences("Settings", Context.MODE_PRIVATE);
    }

    public boolean saveSetting(String setting_name, String setting_value) {
        SharedPreferences.Editor prefEditor = sharedPrefs.edit();
        prefEditor.putString(setting_name, setting_value);
        boolean sharedPrefSaved = prefEditor.commit();

        if (sharedPrefSaved) {
            settings.put(setting_name, setting_value);
            return true;
        } else {
            return false;
        }
    }

    public String retrieveSetting(String setting_name, String default_value)
    {
        if (settings.containsKey(setting_name)) {
          return settings.get(setting_name);
        }
        else { return default_value; }
        //return sharedPrefs.getString(setting_name, default_value);
    }

    public boolean loadAllSettings() {
        Map<String, ?> sharedPrefsAllSettings = sharedPrefs.getAll();
        int totalSettingsCount = 0;

        for (Map.Entry<String, ?> settingEntry : sharedPrefsAllSettings.entrySet()) {
            if (settingEntry.getKey().toString().equals(UITSPRAAK_VETRAGING_NAME)) {
                settings.put(UITSPRAAK_VETRAGING_NAME, settingEntry.getValue().toString());
                totalSettingsCount++;
            }
        }

        return totalSettingsCount == totalSettings ? true : false;
    }
}