package com.example.titan.dyscalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;

public class MainActivity extends AppCompatActivity {

    Button one, delete;
    EditText display;
    String s = "";


    HorizontalScrollView sc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        display = (EditText) findViewById(R.id.editText);
        display.setRawInputType(InputType.TYPE_CLASS_TEXT);
        display.setTextIsSelectable(true);

        one = (Button) findViewById(R.id.b1);
        one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s = s + "1";
                display.setText(s);
                display.setSelection(display.getText().length());
                goToRight();
            }
        });

        delete = (Button) findViewById(R.id.bDelete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cursorEndPosition = display.getSelectionEnd();

                if (cursorEndPosition > 0) {
                    StringBuffer text = new StringBuffer(s);
                    text.replace(cursorEndPosition - 1, cursorEndPosition, "");
                    s = text.toString();
                    display.setText(s);
                    display.setSelection(cursorEndPosition - 1);
                    goToRight();
                }
            }
        });
    }

    public void goToRight(){
        display.setMovementMethod(new ScrollingMovementMethod());
        sc = (HorizontalScrollView) findViewById(R.id.sc);
        sc.postDelayed(new Runnable() {
            public void run() {
                sc.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        }, 100L);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("som", s);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        s = savedInstanceState.getString("som");


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
        // automatically handle clicks on the Home/Up buttonnumber, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
