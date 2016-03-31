package com.example.titan.dyscalculator;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.RecognizerIntent;
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
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button one, two, three, four, five, six, seven, eight, nine, zero, comma, is, min, divide;
    ImageButton delete, plus, multiply, clear, mic;
    EditText display;
    String s = "";
    Calculator cal;
    DecimalFormat formatter;
    HorizontalScrollView sc;
    private static final int SPEECH_REQUEST_CODE = 0;

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
        mic = (ImageButton) findViewById(R.id.bMic);

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

        mic.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                displaySpeechRecognizer();
                Log.v("Speech","------");
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

    // Create an intent that can start the Speech Recognizer activity
    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Spreek de som");
// Start the activity, the intent will be populated with the speech text
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    // This callback is invoked when the Speech Recognizer returns.
// This is where you process the intent and extract the speech text from the intent.
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {

            /* een volledige som 10+10*/
            String regex1 = "^\\s*([-+]?)(\\d+)(?:\\s*([-+*x:,\\/])\\s*((?:\\s[-+])?\\d+)\\s*)+$";
            /*een getal zonder iets*/
            String regex2 = "\\d+";
            /* een getal met een karakter ervoor*/
            String regex3 = "(\\d+)\\s*([-+]?)";
            /* een getal met een karakter er achter*/
            String regex4 = "^\\s*([-+]?)(\\d+)";

            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            String number = "";
//            for(String temp : results){
//                Log.v("Speech",temp);
//                if(temp.matches("\\d+")){
//                    //number = temp;
//                }
//            }
                String spokenSum = "";
                spokenSum = replaceSpokenText(spokenText);


            //refactor met 1if 1 else en dan || met alle regex
            if(spokenSum.matches(regex1)) {
                Log.v("SpeechResult Regex1", spokenSum);
                s = s + spokenSum;
                display.setText(s);
                display.setSelection(display.getText().length());
            }
            else if(spokenSum.matches(regex2)){
                Log.v("SpreechResult Regex2", spokenSum);
                s = s + spokenSum;
                display.setText(s);
                display.setSelection(display.getText().length());

            }
            else if(spokenSum.matches(regex3)){
                Log.v("SpreechResult Regex3", spokenSum);
                s = s + spokenSum;
                display.setText(s);
                display.setSelection(display.getText().length());

            }
            else if(spokenSum.matches(regex4)){
                Log.v("SpreechResult Regex4", spokenSum);
                s = s + spokenSum;
                display.setText(s);
                display.setSelection(display.getText().length());
            }
            else{
                Log.v("SpeechResult Else", spokenSum);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public String replaceSpokenText(String spokenText){
        String spokenSum;

        spokenSum = spokenText.replaceAll(" ", "");
        spokenSum = spokenSum.replaceAll("plus", "+");
        spokenSum = spokenSum.replaceAll("min", "-");
        spokenSum = spokenSum.replaceAll("keer", "x");
        spokenSum = spokenSum.replaceAll("maal", "x");
        spokenSum = spokenSum.replaceAll("gedeelddoor", ":");
        spokenSum = spokenSum.replaceAll("delendoor", ":");
        spokenSum = spokenSum.replaceAll("Plus", "+");
        spokenSum = spokenSum.replaceAll("Min", "-");
        spokenSum = spokenSum.replaceAll("Keer", "x");
        spokenSum = spokenSum.replaceAll("Maal", "x");
        spokenSum = spokenSum.replaceAll("Gedeelddoor", ":");
        spokenSum = spokenSum.replaceAll("Delendoor", ":");
        spokenSum = spokenSum.replaceAll("eenmiljoen", "1000000");
        spokenSum = spokenSum.replaceAll("Eenmiljoen", "1000000");
        spokenSum = spokenSum.replaceAll("een", "1");
        spokenSum = spokenSum.replaceAll("Een", "1");
        spokenSum = spokenSum.replaceAll("één", "1");
        spokenSum = spokenSum.replaceAll("Eén", "1");

        return spokenSum;
    }
}
