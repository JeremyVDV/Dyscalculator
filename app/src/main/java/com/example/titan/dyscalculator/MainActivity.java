package com.example.titan.dyscalculator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button one;
    Button plus;
    Button is;
    EditText et;
    String s = "";
    HorizontalScrollView sc;
    Calculator cal;
    String test = "172928,22x2,12";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        et = (EditText) findViewById(R.id.editText);
        one = (Button) findViewById(R.id.button);
        one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s = s + "1";
                goToRight();
            }
        });
        plus = (Button) findViewById(R.id.button1);;
        plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s = s + " + ";
                et.setText(s);
                goToRight();
            }
        });

        is = (Button) findViewById(R.id.button2);;
        is.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s = s + " = 2";
                et.setText(s);
                goToRight();
            }
        });

    }

    public void goToRight(){
        et.setMovementMethod(new ScrollingMovementMethod());
        sc = (HorizontalScrollView) findViewById(R.id.sc);
        sc.postDelayed(new Runnable() {
            public void run() {
                sc.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        }, 100L);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
