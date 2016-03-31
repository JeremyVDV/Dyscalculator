package com.example.titan.dyscalculator;

import android.graphics.Color;
import android.graphics.Typeface;
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
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button one, two, three, four, five, six, seven, eight, nine, zero, comma, is, min, divide;
    ImageButton delete, plus, multiply, clear;
    TextView history;
    EditText display;
    String s = "";
    Calculator cal;
    DecimalFormat formatter;

    String fromattedResult;
    HorizontalScrollView sc;
    ScrollView historyScroll;
    int numberOfOutcomes = 0;
    int clicks = 0;
    int textViewCount = 1;

    TextView[] textViewArray = new TextView[textViewCount];
    LinearLayout myLayout;
    LinearLayout.LayoutParams lp;
    TextView[] pairs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        display = (EditText) findViewById(R.id.editText);
        display.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/TitilliumWeb-Light.ttf"));
        display.setTextColor(Color.parseColor("#444763"));
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

         myLayout = (LinearLayout) findViewById(R.id.displayLayout);
         lp = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT,    LinearLayout.LayoutParams.WRAP_CONTENT);

        pairs=new TextView[textViewCount];


        one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicks++;
                checknumberOfOutcomes("1");
                insertDisplayCharacter("1");

            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                clicks++;
                checknumberOfOutcomes("2");
                insertDisplayCharacter("2");


            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicks++;
                checknumberOfOutcomes("3");
                insertDisplayCharacter("3");


            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                clicks++;
                checknumberOfOutcomes("4");
                insertDisplayCharacter("4");


            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicks++;
                checknumberOfOutcomes("5");
                insertDisplayCharacter("5");

            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicks++;
                checknumberOfOutcomes("6");
                insertDisplayCharacter("6");


            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                clicks++;
                checknumberOfOutcomes("7");
                insertDisplayCharacter("7");

            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicks++;
                checknumberOfOutcomes("8");
                insertDisplayCharacter("8");

            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicks++;
                checknumberOfOutcomes("9");
                insertDisplayCharacter("9");

            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                clicks++;
                checknumberOfOutcomes("0");
                insertDisplayCharacter("0");


            }
        });
        comma.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                clicks++;
                checknumberOfOutcomes(",");
                insertDisplayCharacter(",");
            }
        });

        is.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (display.getText().toString().length() > 0){
                numberOfOutcomes = numberOfOutcomes + 1;
                calculate();
                display.setText(s);
                display.setSelection(display.getText().length());
                    clicks = 0;

                }

            }
        });

        min.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicks++;
                checknumberOfOutcomes("-");
                insertDisplayCharacter("-");

            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicks++;
                checknumberOfOutcomes("+");
                insertDisplayCharacter("+");
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicks++;
                checknumberOfOutcomes("x");
                insertDisplayCharacter("x");
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicks++;
                checknumberOfOutcomes(":");
                insertDisplayCharacter(":");

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                clicks =0;
                numberOfOutcomes = 0;
                s = "";
                display.setText(s);

                display.setSelection(display.getText().length());
                goToRight();
                textViewCount = 1;

                myLayout.removeAllViews();
        }
        });

        delete = (ImageButton) findViewById(R.id.bDelete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDisplayCharacter();
            }
        });
    }

    private void calculate(){
        String[] splitted = s.split(";");
        int last = splitted.length - 1;
        if(s.contains(",")) {
            String lastCalculation = splitted[last].replace(".", "");
            String formatterFormat = "#,###.";
            int longestCommaValue = 0;
            String som = lastCalculation.replace(",", ".").replaceAll("\\s", "");
            ArrayList<String> theDoubles = new ArrayList<String>();
            ArrayList<String> seperatedValues = new ArrayList<String>();

            // Splitting the equation
            for (String s : som.split("\\+|\\-|x|:")) {
                theDoubles.add(s);
            }

            // Splitting the double based on the dot
            for (String s : theDoubles) {
                String[] temporary;
                if (s.contains(".")) {
                    temporary = s.split("\\.");
                    seperatedValues.add(temporary[1]);
                }
            }

            // Comparing every split double based on the length
            for (String s : seperatedValues) {
                if (s.length() > longestCommaValue) {
                    longestCommaValue = s.length();
                }
            }


            // Add zeros to formatter
            for (int i = 1; i <= longestCommaValue; i++) {
                formatterFormat += "0";
            }
            formatter = new DecimalFormat(formatterFormat);
        } else {
            formatter = new DecimalFormat("#,###.#########");
        }

        // Answer
        String somm = s;
        String[] splitter = somm.split(";");
        int lastest = splitter.length - 1;
        String lastCalculation = splitter[lastest].replace(".", "");
        s = lastCalculation;

        String completeEquation = "" + cal.Calculate(s.replace(",", ".").replaceAll("\\s",""));

        fromattedResult = formatter.format(Double.parseDouble(completeEquation));
        formatCalculation();
        s = somm + " = " + formatter.format(Double.parseDouble(completeEquation));
    }


    // add by Stan -------------------------------------------------------
    private void checknumberOfOutcomes(String character) {
        if (numberOfOutcomes >0 && clicks == 1 && s.length()>0) {
           int l= textViewCount -1;
            pairs[l] = new TextView(this);
            pairs[l].setTextSize(15);
            pairs[l].setLayoutParams(lp);
            pairs[l].setId(l);
            pairs[l].setText(s);
            pairs[l].setTextSize(20);
            pairs[l].setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/TitilliumWeb-Light.ttf"));
            pairs[l].setTextColor(Color.parseColor("#444763"));
            myLayout.addView(pairs[l]);

            historyScroll = (ScrollView) findViewById(R.id.verticalScroll);
            historyScroll.post(new Runnable() {
                public void run() {
                    historyScroll.fullScroll(ScrollView.FOCUS_DOWN);
                }
            });
            /* textViewArray[textViewCount]= new TextView(this);
            textViewArray[textViewCount].setLayoutParams(lp);
            textViewArray[textViewCount].setText(s);
            myLayout.addView(textViewArray[textViewCount]);*/
            textViewCount++;
            pairs=new TextView[textViewCount];
            if (character.equals("x") || character.equals(":") || character.equals("-") || character.equals("+")) {
                s = fromattedResult;
                goToLeft();
            }
            else{
                s = "";
                goToLeft();
            }

        }
    }

    public void formatCalculation() {
        DecimalFormat formatters = new DecimalFormat("#,###.#####");
        String calculations = s;
        String without = calculations.replace(".", "").replaceAll(",", ".").replaceAll(" ", "");
        String[] characters = without.split("");
        ArrayList<String> splitted = new ArrayList<>();
        String cijfer = "";
        for (int i = 0; i < characters.length; i++) {

            if (characters[i].equals("x") || characters[i].equals(":") || characters[i].equals("-") || characters[i].equals("+") || characters[i].equals("=") || characters[i].equals(";")) {
                splitted.add(characters[i]);
                cijfer = "";
            } else {
                if (splitted.size() > 1) {

                    String x = splitted.get(splitted.size() - 1);

                    if (!x.equals("x") && !x.equals(":") && !x.equals("-") && !x.equals("+") && !x.equals("=") && !x.equals(";")) {
                        splitted.remove(splitted.size() - 1);
                    }
                }
                cijfer = cijfer + characters[i];
                splitted.add(cijfer);
            }
        }
        String combined = "";
        int i = 0;
        for (String split : splitted) {

            if(split.contains(".")) {
                String replaced = split.replace(".",";");
                String[] dotSplit = replaced.split(";");
                String comaString = "";
                comaString = formatters.format(Double.parseDouble(dotSplit[0]));
                split = comaString+","+dotSplit[1];
            }
            else {
                try {
                    split = formatters.format(Double.parseDouble(split));
                } catch (Exception e) {
                    //The handling for the code
                }
            }
            if (i == 0) {
                combined = split;
            } else {
                combined = combined + split;
            }
            i++;

        }
       s = combined;
    }

    // add by Stan -------------------------------------------------------
    private void deleteDisplayCharacter () {
        int cursorEndPosition = display.getSelectionEnd();

        if (cursorEndPosition > 0) {
            StringBuffer text = new StringBuffer(display.getText().toString());

            text.deleteCharAt(cursorEndPosition - 1);
            display.setText(text.toString());

            display.setSelection(cursorEndPosition - 1);
            s = display.getText().toString();
        }
    }

    private void insertDisplayCharacter (String character) {

        if (clicks == 1){
            s = s + character;
            display.setText(s);
            display.setSelection(display.getText().length());
            goToRight();
        }
        else {
            int cursorEndPosition = display.getSelectionEnd();

            StringBuffer text = new StringBuffer(s);

            text.insert(cursorEndPosition, character);
            s = text.toString();
            String beforeformatS = s;
            if (s.contains(",") && character.equals("0") ){
            }
            else if (!character.equals("+") && !character.equals("x") && !character.equals(":") && !character.equals("-") && !character.equals(",") ) {
                formatCalculation();
            }

            int count = s.length() - beforeformatS.length();
            display.setText(s);
            if (cursorEndPosition == s.length()) {
                goToRight();
            }

            // de . moet nog worden toegevoegd aan de cursorendposition
            display.setSelection(cursorEndPosition + 1+count);
        }
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

    public void goToLeft(){
        display.setMovementMethod(new ScrollingMovementMethod());
        sc = (HorizontalScrollView) findViewById(R.id.sc);
        sc.post(new Runnable() {
            public void run() {
                sc.fullScroll(HorizontalScrollView.FOCUS_LEFT);
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
