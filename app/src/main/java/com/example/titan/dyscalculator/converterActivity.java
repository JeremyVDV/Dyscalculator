package com.example.titan.dyscalculator;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by stan on 7-6-2016.
 */
public class converterActivity extends AppCompatActivity {

    String text = null;
    List<String> afstandArray, oppervalkteArray,inhoudArray;
    List<Convert> lengteObjectArray, oppervalkteObjectArray, inhoudObjectArray;
    ConverterAdapter slaveAdapter;
    Button bereken;
    EditText vanEdit, naarEdit;
    DecimalFormat formatter;
    InputMethodManager imm;

    Spinner vanSpinner, naarSpinner, hoofdSpinner;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.converter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        bereken = (Button)findViewById(R.id.bereken);
        vanEdit = (EditText)findViewById(R.id.convertereditText);
        naarEdit = (EditText)findViewById(R.id.naareditText);


        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("afstand");
        spinnerArray.add("oppervlakte");
        spinnerArray.add("inhoud");



        lengteObjectArray = new ArrayList<Convert>();
        Convert c = new Convert("km",  1,"lengte");
        Convert c1 = new Convert("hm", 0.1,"lengte");
        Convert c2= new Convert("dam", 0.01,"lengte");
        Convert c3 = new Convert("m",  0.001,"lengte");
        Convert c4 = new Convert("dm", 0.0001,"lengte");
        Convert c5 = new Convert("cm", 0.00001,"lengte");
        Convert c6 = new Convert("mm", 0.000001,"lengte");

        lengteObjectArray.add(c6);
        lengteObjectArray.add(c5);
        lengteObjectArray.add(c4);
        lengteObjectArray.add(c3);
        lengteObjectArray.add(c2);
        lengteObjectArray.add(c1);
        lengteObjectArray.add(c);

        oppervalkteObjectArray = new ArrayList<Convert>();
        Convert d = new Convert("km2", 1,"oppervlakte");
        Convert d1 = new Convert("hm2", 0.01,"oppervlakte");
        Convert d2= new Convert("dam2", 0.0001,"oppervlakte");
        Convert d3 = new Convert("m2",  0.000001,"oppervlakte");
        Convert d4 = new Convert("dm2", 0.00000001,"oppervlakte");
        Convert d5 = new Convert("cm2", 0.0000000001,"oppervlakte");
        Convert d6 = new Convert("mm2", 0.000000000001,"oppervlakte");

        oppervalkteObjectArray.add(d6);
        oppervalkteObjectArray.add(d5);
        oppervalkteObjectArray.add(d4);
        oppervalkteObjectArray.add(d3);
        oppervalkteObjectArray.add(d2);
        oppervalkteObjectArray.add(d1);
        oppervalkteObjectArray.add(d);

        inhoudObjectArray = new ArrayList<Convert>();
        Convert b = new Convert("km3", 1,"inhoud");
        Convert b1 = new Convert("hm3", 0.001,"inhoud");
        Convert b2= new Convert("dam3", 0.000001,"inhoud");
        Convert b3 = new Convert("m3",  0.000000001,"inhoud");
        Convert b4 = new Convert("dm3", 0.000000000001,"inhoud");
        Convert b5 = new Convert("cm3", 0.000000000000001,"inhoud");
        Convert b6 = new Convert("mm3", 0.000000000000000001,"inhoud");
        Convert b7 = new Convert("l",   0.000000000001,"inhoud");
        Convert b8 = new Convert("cl",  0.0000000000001,"inhoud");
        Convert b9 = new Convert("dl",  0.00000000000001,"inhoud");
        Convert b10 = new Convert("ml", 0.000000000000001,"inhoud");

        inhoudObjectArray.add(b6);
        inhoudObjectArray.add(b5);
        inhoudObjectArray.add(b4);
        inhoudObjectArray.add(b3);
        inhoudObjectArray.add(b2);
        inhoudObjectArray.add(b1);
        inhoudObjectArray.add(b);
        inhoudObjectArray.add(b10);
        inhoudObjectArray.add(b9);
        inhoudObjectArray.add(b8);
        inhoudObjectArray.add(b7);

        inhoudArray = new ArrayList<String>();
        inhoudArray.add("mm3");
        inhoudArray.add("cm3");
        inhoudArray.add("dm3");
        inhoudArray.add("m3");
        inhoudArray.add("dam3");
        inhoudArray.add("hm3");
        inhoudArray.add("km3");
        inhoudArray.add("ml");
        inhoudArray.add("cl");
        inhoudArray.add("dl");
        inhoudArray.add("l");

        ArrayAdapter<String> hoofdAdapter = new ArrayAdapter<String>(
                this,  R.layout.spinner_item, spinnerArray);

        hoofdAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hoofdSpinner = (Spinner) findViewById(R.id.hoofdSpinner);
        hoofdSpinner.setAdapter(hoofdAdapter);

        slaveAdapter = new ConverterAdapter(this, R.layout.spinner_item, (ArrayList<Convert>) lengteObjectArray);

        slaveAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        vanSpinner = (Spinner) findViewById(R.id.vanspinner);
        naarSpinner = (Spinner) findViewById(R.id.naarspinner);

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Je hebt geen cijfer ingevoerd");
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //do things
            }
        });

        text = hoofdSpinner.getSelectedItem().toString();
        bereken.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                String text = hoofdSpinner.getSelectedItem().toString();
                String vannaam = vanSpinner.getSelectedItem().toString();
                String naarnaam = naarSpinner.getSelectedItem().toString();
                double vanValue = 0.0;
                double naarValue = 0.0;

                if (text.equals("afstand")) {
                    for (Convert con : lengteObjectArray) {
                        if (con.getNaam().equals(vannaam)) {
                            vanValue = con.getValue();
                        }
                    }
                    for (Convert con : lengteObjectArray) {
                        if (con.getNaam().equals(naarnaam)) {
                            naarValue = con.getValue();
                        }
                    }
                }
                else if(text.equals("oppervlakte")){
                    for (Convert con : oppervalkteObjectArray) {
                        if (con.getNaam().equals(vannaam)) {
                            vanValue = con.getValue();
                        }
                    }
                    for (Convert con : oppervalkteObjectArray) {
                        if (con.getNaam().equals(naarnaam)) {
                            naarValue = con.getValue();
                        }
                    }
                }
                else if(text.equals("inhoud")){
                    for (Convert con : inhoudObjectArray) {
                        if (con.getNaam().equals(vannaam)) {
                            vanValue = con.getValue();
                        }
                    }
                    for (Convert con : inhoudObjectArray) {
                        if (con.getNaam().equals(naarnaam)) {
                            naarValue = con.getValue();
                        }
                    }
                }
                double invoer = 0.0;

                try {
                    invoer = Double.parseDouble(vanEdit.getText().toString());
                } catch (Exception e) {
                    alertDialog.show();
                }

                    Log.v("invoer",invoer+"");
                    Log.v("valueVan",vanValue+"");
                    Log.v("valueNaar",naarValue+"");
                    double tussenUitkomst = invoer*vanValue;
                    Log.v("tussenUitkomst",tussenUitkomst+"");
                    double uitkomst = tussenUitkomst/naarValue;
                    Log.v("uitkomst",uitkomst+"");
                    formatter = new DecimalFormat("####.####################");
                    naarEdit.setText(formatter.format(uitkomst)+"");

            }
        });




        hoofdSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
               checkMeting();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        if (text.equals("afstand")) {
            slaveAdapter = new ConverterAdapter(this, R.layout.spinner_item, (ArrayList<Convert>) lengteObjectArray);

            slaveAdapter.notifyDataSetChanged();
            vanSpinner.setAdapter(slaveAdapter);
            naarSpinner.setAdapter(slaveAdapter);
        }
        else if(text.equals("oppervlakte")){
            slaveAdapter = new ConverterAdapter(this, R.layout.spinner_item, (ArrayList<Convert>) oppervalkteObjectArray);

            slaveAdapter.notifyDataSetChanged();
            vanSpinner.setAdapter(slaveAdapter);
            naarSpinner.setAdapter(slaveAdapter);
        }
        else if(text.equals("inhoud")){
            slaveAdapter = new ConverterAdapter(this, R.layout.spinner_item, (ArrayList<Convert>) inhoudObjectArray);

            slaveAdapter.notifyDataSetChanged();
            vanSpinner.setAdapter(slaveAdapter);
            naarSpinner.setAdapter(slaveAdapter);
        }
    }

    public void checkMeting(){
        text = hoofdSpinner.getSelectedItem().toString();
        if (text.equals("afstand")) {
            slaveAdapter = new ConverterAdapter(this, R.layout.spinner_item, (ArrayList<Convert>) lengteObjectArray);

            slaveAdapter.notifyDataSetChanged();
            vanSpinner.setAdapter(slaveAdapter);
            naarSpinner.setAdapter(slaveAdapter);
        }
        else if(text.equals("oppervlakte")){

            slaveAdapter = new ConverterAdapter(this, R.layout.spinner_item, (ArrayList<Convert>) oppervalkteObjectArray);

            slaveAdapter.notifyDataSetChanged();
            vanSpinner.setAdapter(slaveAdapter);
            naarSpinner.setAdapter(slaveAdapter);
        }
        else if(text.equals("inhoud")){
            slaveAdapter = new ConverterAdapter(this, R.layout.spinner_item, (ArrayList<Convert>) inhoudObjectArray);

            slaveAdapter.notifyDataSetChanged();
            vanSpinner.setAdapter(slaveAdapter);
            naarSpinner.setAdapter(slaveAdapter);
        }
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
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                finish();
// close this activity and return to preview activity (if there is any)
                return true;
            // action with ID menu_main_settings was selected
            case R.id.action_calculator:
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                finish(); // close this activity and return to preview activity (if there is any)
                return true;
            default:
                break;
        }

        return false;
    }
}
