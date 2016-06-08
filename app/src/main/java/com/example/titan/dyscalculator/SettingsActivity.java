package com.example.titan.dyscalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class SettingsActivity extends AppCompatActivity {

    AppCompatSpinner uitspraak_vertraging_spinner;
    Switch uitspraak_oplichting_switch;

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


        uitspraak_vertraging_spinner = (AppCompatSpinner) findViewById(R.id.uitspraak_vertraging_spinner_spinner);

        //set the selection on item creation based on a saved value
        uitspraak_vertraging_spinner.setSelection(getSpinnerIndex(uitspraak_vertraging_spinner, Settings.getInstance(this).retrieveSetting(Settings.UITSPRAAK_VETRAGING_NAME, Settings.UITSPRAAK_VETRAGING_DEFAULT_VALUE)));

        uitspraak_vertraging_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {

                String selectedValue = adapter.getItemAtPosition(position).toString();

                //save settings.
                Settings.getInstance(getApplicationContext()).saveSetting(Settings.UITSPRAAK_VETRAGING_NAME, selectedValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        uitspraak_oplichting_switch = (Switch) findViewById(R.id.uitspraak_oplichting_switch_switch);

        uitspraak_oplichting_switch.setChecked(Boolean.valueOf(Settings.getInstance(this).retrieveSetting(Settings.UITSPRAAK_OPLICHTING_NAME, Settings.UITSPRAAK_OPLICHTING_DEFAULT_VALUE)));
        uitspraak_oplichting_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

               Settings.getInstance(getApplicationContext()).saveSetting(Settings.UITSPRAAK_OPLICHTING_NAME, (isChecked ? "true" : "false"));
            }
        });
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

    private int getSpinnerIndex(AppCompatSpinner spinner, String myString)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }
}
