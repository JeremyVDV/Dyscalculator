package com.example.titan.dyscalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    SettingsListViewAdapter MyAdapter;
    ListView ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //View v = getLayoutInflater().inflate(R.layout.activity_settings_list_view, );
        ls = (ListView) findViewById(R.id.settings_list_view);

        MyAdapter = new SettingsListViewAdapter(this, new ArrayList<Setting>());

        Setting setting1 = new Setting("Test", "123");
        Setting setting2 = new Setting("Test2", "321");

        MyAdapter.settingItems.add(setting1);
        MyAdapter.settingItems.add(setting2);

        ls.setAdapter(MyAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                finish(); // close this activity and return to preview activity (if there is any)
                return true;
            // action with ID menu_main_settings was selected
            case R.id.action_calculator:
                finish(); // close this activity and return to preview activity (if there is any)
                return true;
            default:
                break;
        }

        return false;
    }

}
