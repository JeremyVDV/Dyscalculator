package com.example.titan.dyscalculator;

import android.content.Intent;
import android.content.res.Configuration;
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
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.titan.dyscalculator.CustomViews.DisplayEditText;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button one, two, three, four, five, six, seven, eight, nine, zero, comma, is, min, divide, cash;
    ImageButton delete, plus, multiply, clear, mic;
    EditText display;
    String s = "";
    Calculator cal;
    DecimalFormat formatter;

    String fromattedResult;
    HorizontalScrollView sc;

    private static final int SPEECH_REQUEST_CODE = 0;

    ScrollView historyScroll;
    int numberOfOutcomes = 0;
    int clicks = 0;
    int textViewCount = 1;

    LinearLayout myLayout;
    LinearLayout.LayoutParams lp;
    TextView[] pairs;
    boolean cashMode = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Locale locale = new Locale("nl");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        display = (EditText) findViewById(R.id.editText);
        display.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/TitilliumWeb-Light.ttf"));
        display.setTextColor(Color.parseColor("#444763"));
        display.setRawInputType(InputType.TYPE_CLASS_TEXT);
        display.setTextIsSelectable(true);
        display.setHorizontalScrollView(sc);

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
        cash = (Button) findViewById(R.id.bEuro);
        plus = (ImageButton) findViewById(R.id.bPlus);
        multiply = (ImageButton) findViewById(R.id.bMultiply);
        clear = (ImageButton) findViewById(R.id.bClear);
        mic = (ImageButton) findViewById(R.id.bMic);
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

        cash.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(s.equals("")){
                    if(!cashMode){
                        //cashMode on
                        cashMode = true;
                        cash.setBackgroundResource(R.drawable.buttonpressed);
                    } else {
                        //cashMode off
                        cashMode = false;
                        cash.setBackgroundResource(R.drawable.buttoncharacter);
                    }
                }
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

        mic.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                displaySpeechRecognizer();
                Log.v("Speech","------");
            }
        });
    }

    private void calculate() {
        String[] splitted = s.split(";");
        int last = splitted.length - 1;
        if (cashMode) {
            formatter = new DecimalFormat("#,###.00");
        } else {
            if (s.contains(",")) {
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
        }

        // Answer
        String somm = s;
        String[] splitter = somm.split(";");
        int lastest = splitter.length - 1;
        String lastCalculation = splitter[lastest].replace(".", "");
        s = lastCalculation;

        String completeEquation = "" + cal.Calculate(s.replace(",", ".").replaceAll("\\s", ""));

        fromattedResult = formatter.format(Double.parseDouble(completeEquation));
        if(cashMode){
            s = somm + " = €" + formatter.format(Double.parseDouble(completeEquation));
        } else {
            s = somm + " = " + formatter.format(Double.parseDouble(completeEquation));
        }
    }

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
        if(cashMode) {
            formatter = new DecimalFormat("#,###.##");
        } else {
            formatter = new DecimalFormat("#,###.#####");
        }
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
            if(cashMode) {
                try {
                    split = formatter.format(Double.parseDouble(split));
                } catch (Exception e) {
                    //No exception needed
                }
            }
            else {
                if (split.contains(".")) {
                    String replaced = split.replace(".", ";");
                    String[] dotSplit = replaced.split(";");
                    String comaString = "";
                    comaString = formatter.format(Double.parseDouble(dotSplit[0]));
                    split = comaString + "," + dotSplit[1];
                } else {
                    try {
                        split = formatter.format(Double.parseDouble(split));
                    } catch (Exception e) {
                        //No exception needed
                    }
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

            display.setSelection(cursorEndPosition + 1+count);
        }
    }


    public void goToRight(){
        display.setMovementMethod(new ScrollingMovementMethod());
       // sc = (HorizontalScrollView) findViewById(R.id.sc);
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
            String spokenSum = "";
            spokenSum = replaceSpokenText(spokenText);


            //refactor met 1 if 1 else en dan || met alle regex
            if(spokenSum.matches(regex1)) {
                s = s + spokenSum;
                formatCalculation();
                display.setText(s);
                display.setSelection(display.getText().length());
            }
            else if(spokenSum.matches(regex2)){
                s = s + spokenSum;
                formatCalculation();
                display.setText(s);
                display.setSelection(display.getText().length());

            }
            else if(spokenSum.matches(regex3)){
                s = s + spokenSum;
                formatCalculation();
                display.setText(s);
                display.setSelection(display.getText().length());

            }
            else if(spokenSum.matches(regex4)){
                s = s + spokenSum;
                formatCalculation();
                display.setText(s);
                display.setSelection(display.getText().length());
            }
            else{
                // ?
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public String replaceSpokenText(String spokenText){
        String spokenSum;

        // Lowercase will remove the half of the spokensums
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
