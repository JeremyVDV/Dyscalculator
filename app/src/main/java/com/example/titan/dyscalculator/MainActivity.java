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

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button one, two, three, four, five, six, seven, eight, nine, zero, comma, is, min, divide;
    ImageButton delete, plus, multiply, clear;
    EditText display;
    String s = "";
    Calculator cal;
    DecimalFormat formatter;
    HorizontalScrollView sc;

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

        one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insertDisplayCharacter("1");
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insertDisplayCharacter("2");
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insertDisplayCharacter("3");;
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insertDisplayCharacter("4");
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insertDisplayCharacter("5");
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insertDisplayCharacter("6");
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insertDisplayCharacter("7");
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insertDisplayCharacter("8");
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insertDisplayCharacter("9");
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insertDisplayCharacter("0");
            }
        });
        comma.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insertDisplayCharacter(",");
            }
        });

        is.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(s.contains(",")) {
                    String formatterFormat = "#,###.";
                    int longestCommaValue = 0;
                    String som = s.replace(",", ".").replaceAll("\\s", "");
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
                        Log.d("seperatedValues", s);
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
                    formatter = new DecimalFormat("#,###");
                }
                // Answer
                String completeEquation = "" + cal.Calculate(s.replace(",", ".").replaceAll("\\s",""));
                s = s + " = " + formatter.format(Double.parseDouble(completeEquation));
                display.setText(s);
                display.setSelection(display.getText().length());
                goToRight();
            }
        });

        min.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insertDisplayCharacter("-");
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insertDisplayCharacter("+");
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insertDisplayCharacter("x");
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insertDisplayCharacter(":");
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
                deleteDisplayCharacter();
            }
        });
    }

    private void deleteDisplayCharacter () {
        int cursorEndPosition = display.getSelectionEnd();

        if (cursorEndPosition > 0) {
            StringBuffer text = new StringBuffer(s);

            text.deleteCharAt(cursorEndPosition - 1);
            s = text.toString();
            display.setText(s);
            display.setSelection(cursorEndPosition - 1);
        }
    }

    private void insertDisplayCharacter (String character) {
        int cursorEndPosition = display.getSelectionEnd();

            StringBuffer text = new StringBuffer(s);

            text.insert(cursorEndPosition, character);
            s = text.toString();
            display.setText(s);

            if (cursorEndPosition == s.length()) {
                goToRight();
            }

            display.setSelection(cursorEndPosition + 1);
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
