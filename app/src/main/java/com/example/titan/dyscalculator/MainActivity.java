package com.example.titan.dyscalculator;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button one, two, three, four, five, six, seven, eight, nine, zero, comma, is, min, divide;
    ImageButton delete, plus, multiply, clear;
    EditText display;
    String s = "";
    Calculator cal;
    DecimalFormat formatter = new DecimalFormat("#,###.#########");
    String fromattedResult;
    HorizontalScrollView sc;
    int numberOfOutcomes = 0;
    int clicks = 0;
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
        comma = (Button) findViewById(R.id.bComma);
        is = (Button) findViewById(R.id.bIs);
        min = (Button) findViewById(R.id.bMin);
        divide = (Button) findViewById(R.id.bDivide);
        plus = (ImageButton) findViewById(R.id.bPlus);
        multiply = (ImageButton) findViewById(R.id.bMultiply);
        clear = (ImageButton) findViewById(R.id.bClear);



        one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicks++;
                checknumberOfOutcomes();
                s = s + "1";
                display.setText(s);
                formatCalculation();
                display.setSelection(display.getText().length());
                goToRight();

            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicks++;
                checknumberOfOutcomes();
                s = s + "2";
                display.setText(s);
                formatCalculation();
                display.setSelection(display.getText().length());
                goToRight();
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicks++;
                checknumberOfOutcomes();
                s = s + "3";
                display.setText(s);
                formatCalculation();
                display.setSelection(display.getText().length());
                goToRight();
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicks++;
                checknumberOfOutcomes();
                s = s + "4";
                display.setText(s);
                formatCalculation();
                display.setSelection(display.getText().length());
                goToRight();
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicks++;
                checknumberOfOutcomes();
                s = s + "5";
                display.setText(s);
                formatCalculation();
                display.setSelection(display.getText().length());
                goToRight();
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicks++;
                checknumberOfOutcomes();
                s = s + "6";
                display.setText(s);
                formatCalculation();
                display.setSelection(display.getText().length());
                goToRight();
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicks++;
                checknumberOfOutcomes();
                s = s + "7";
                display.setText(s);
                formatCalculation();
                display.setSelection(display.getText().length());
                goToRight();
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicks++;
                checknumberOfOutcomes();
                s = s + "8";
                display.setText(s);
                formatCalculation();
                display.setSelection(display.getText().length());
                goToRight();
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicks++;
                checknumberOfOutcomes();
                s = s + "9";
                display.setText(s);
                formatCalculation();
                display.setSelection(display.getText().length());
                goToRight();
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicks++;
                checknumberOfOutcomes();
                s = s + "0";
                display.setText(s);
                formatCalculation();
                display.setSelection(display.getText().length());
                goToRight();
            }
        });
        comma.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicks++;
                checknumberOfOutcomes();
                s = s + ",";
                display.setText(display.getText().toString() + ",");
                display.setSelection(display.getText().length());
                goToRight();
            }
        });

        is.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (display.getText().toString().length() > 0){
                    numberOfOutcomes = numberOfOutcomes + 1;

                String[] splitted = s.split(";");
                int last = splitted.length - 1;

                String lastCalculation = splitted[last];
                String calculation = "" + cal.Calculate(lastCalculation.replace(".", "").replace(",", ".").replaceAll("\\s", ""));
                fromattedResult = calculation;
                s = s + " = " + formatter.format(Double.parseDouble(calculation));
                display.setText(s);
                formatCalculation();
                display.setSelection(display.getText().length());
                clicks = 0;

                goToRight();
                }
            }
        });

        min.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicks++;
                checknumberOfOutcomes();
                s = s + " - ";
                display.setText(s);
                formatCalculation();
                display.setSelection(display.getText().length());
                goToRight();
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicks++;
                checknumberOfOutcomes();
                s = s + " + ";
                display.setText(s);
                formatCalculation();
                display.setSelection(display.getText().length());
                goToRight();
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicks++;
                checknumberOfOutcomes();
                s = s + " x ";
                display.setText(s);
                formatCalculation();
                display.setSelection(display.getText().length());;
                goToRight();
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checknumberOfOutcomes();
                s = s + " : ";
                display.setText(s);
                formatCalculation();
                display.setSelection(display.getText().length());
                goToRight();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                clicks =0;
                numberOfOutcomes = 0;
                s = "";
                display.setText(s);
                formatCalculation();
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
                    // changed s to display, the s does not have the thousands seperators
                    StringBuffer text = new StringBuffer(display.getText().toString());
                    text.replace(cursorEndPosition - 1, cursorEndPosition, "");
                    s = text.toString();
                    display.setText(s);
                    display.setSelection(cursorEndPosition - 1);
                    goToRight();
                }
            }
        });
    }

    // add by Stan -------------------------------------------------------
    private void checknumberOfOutcomes() {
        if (numberOfOutcomes >0 && clicks == 1){
            s = s + "; " + formatter.format(Double.parseDouble(fromattedResult));
            display.setText(s);
        }
    }
    public void formatCalculation(){
        String calculations = s;
        String without = calculations.replace(".", "").replaceAll(",", ".").replaceAll(" ", "");
        Log.v("without", without);
        String[] characters = without.split("");
        ArrayList<String> splitted = new ArrayList<>();
        String cijfer = "";
        for(int i =0; i < characters.length; i++){

            if(characters[i].equals("x") || characters[i].equals(":") || characters[i].equals("-") || characters[i].equals("+") || characters[i].equals("=")  || characters[i].equals(";")){
                splitted.add(characters[i]);
                cijfer = "";
            }
            else{
                if (splitted.size() > 1){

                    String x = splitted.get(splitted.size() - 1);

                    if (!x.equals("x") && !x.equals(":") && !x.equals("-") && !x.equals("+") && !x.equals("=")&& !x.equals(";")) {
                        Log.v("komt hier", "jemoeder" + splitted.get(splitted.size() - 1) + "kutzoi");
                        splitted.remove(splitted.size() - 1);
                    }
                }
                // || !x.equals(":") || !x.equals("-") || !x.equals("+")|| !x.equals("=")
                cijfer = cijfer + characters[i];
                splitted.add(cijfer);


            }

        }
        //String[] splitted = without.split(" ");
        String combined = "";
        //for(int i =0; i < splitted.size(); i++){
        int i = 0;
        for (String split : splitted) {
            try {
                split = formatter.format(Double.parseDouble(split));

            } catch (Exception e) {
                //The handling for the code
            }

            Log.v("splitted", split);
            if (i == 0 ){
                combined = split;
            }
            else {
                combined = combined + split ;
            }
            i++;

        }

        display.setText(combined);
    }

    // add by Stan -------------------------------------------------------

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
