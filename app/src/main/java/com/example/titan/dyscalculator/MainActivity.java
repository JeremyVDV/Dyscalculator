package com.example.titan.dyscalculator;

import android.media.Image;
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
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    Button one, two, three, four, five, six, seven, eight, nine, zero, coma, is, min, divide;
    ImageButton delete, plus, multipli, clear;
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
        two = (Button) findViewById(R.id.b2);
        three = (Button) findViewById(R.id.b3);
        four = (Button) findViewById(R.id.b4);
        five = (Button) findViewById(R.id.b5);
        six = (Button) findViewById(R.id.b6);
        seven = (Button) findViewById(R.id.b7);
        eight = (Button) findViewById(R.id.b8);
        nine = (Button) findViewById(R.id.b9);
        zero = (Button) findViewById(R.id.b0);
        coma = (Button) findViewById(R.id.bComma);
        is = (Button) findViewById(R.id.bIs);
        min = (Button) findViewById(R.id.bMin);
        divide = (Button) findViewById(R.id.bDivide);
        plus = (ImageButton) findViewById(R.id.bPlus);
        multipli = (ImageButton) findViewById(R.id.bMultipli);
        clear = (ImageButton) findViewById(R.id.bClear);

        one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s = s + "1";
                display.setText(s);
                display.setSelection(display.getText().length());
                goToRight();
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s = s + "2";
                display.setText(s);
                display.setSelection(display.getText().length());
                goToRight();
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    s = s + "3";
                display.setText(s);
                display.setSelection(display.getText().length());
                goToRight();
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s = s + "4";
                display.setText(s);
                display.setSelection(display.getText().length());
                goToRight();
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s = s + "5";
                display.setText(s);
                display.setSelection(display.getText().length());
                goToRight();
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s = s + "6";
                display.setText(s);
                display.setSelection(display.getText().length());
                goToRight();
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s = s + "7";
                display.setText(s);
                display.setSelection(display.getText().length());
                goToRight();
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s = s + "8";
                display.setText(s);
                display.setSelection(display.getText().length());
                goToRight();
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s = s + "9";
                display.setText(s);
                display.setSelection(display.getText().length());
                goToRight();
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s = s + "0";
                display.setText(s);
                display.setSelection(display.getText().length());
                goToRight();
            }
        });
        coma.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s = s + ",";
                display.setText(s);
                display.setSelection(display.getText().length());
                goToRight();
            }
        });

        is.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //jeremy doe deze ff
            }
        });

        min.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s = s + " - ";
                display.setText(s);
                display.setSelection(display.getText().length());
                goToRight();
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s = s + " + ";
                display.setText(s);
                display.setSelection(display.getText().length());
                goToRight();
            }
        });

        multipli.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s = s + " x ";
                display.setText(s);
                display.setSelection(display.getText().length());
                goToRight();
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s = s + " : ";
                display.setText(s);
                display.setSelection(display.getText().length());
                goToRight();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s = "";
                display.setText(s);
                display.setSelection(display.getText().length());
                goToRight();
            }
        });

        delete = (ImageButton) findViewById(R.id.bDelete);
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
        sc.post(new Runnable() {
            public void run() {
                sc.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        });

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
