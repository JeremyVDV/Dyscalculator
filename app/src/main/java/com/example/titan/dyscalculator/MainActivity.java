package com.example.titan.dyscalculator;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.Spannable;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
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
import android.widget.TableLayout;
import android.widget.TextView;
import com.example.titan.dyscalculator.CustomViews.DisplayEditText;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button one, two, three, four, five, six, seven, eight, nine, zero, comma, is, min, divide, cash;

    ImageButton delete, plus, multiply, clear, mic, speak;
    DisplayEditText display;

    DisplayEditText displayEquation, displayIs, displayAnswer;
    String equationStr = "", isStr = "", answerStr = "";

    Calculator cal;
    DecimalFormat formatter;

    public HashSet<String> operatorsWithComma = new HashSet<String>() { { add("+"); add("-"); add("x"); add(":"); add(","); } };
    public HashSet<String> operators = new HashSet<String>() { { add("+"); add("-"); add("x"); add(":"); } };
    String formattedResult;
    HorizontalScrollView sc;

    private static final int SPEECH_REQUEST_CODE = 0;

    ScrollView historyScroll;
    int numberOfOutcomes = 0;
    int clicks = 0;
    int textViewCount = 1;

    LinearLayout myLayout;
    LinearLayout.LayoutParams lp;
    EditText[] pairs;
    boolean cashMode = false;
    TextToSpeech t1;

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

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.getDefault());
                }
            }
        });

        sc = (HorizontalScrollView) findViewById(R.id.sc);

        displayEquation = (DisplayEditText) findViewById(R.id.editText1);
        displayEquation.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/TitilliumWeb-Light.ttf"));
        displayEquation.setTextColor(Color.parseColor("#444763"));
        displayEquation.setRawInputType(InputType.TYPE_CLASS_TEXT);
        displayEquation.setTextIsSelectable(true);
        displayEquation.setHorizontalScrollView(sc);

        displayIs = (DisplayEditText) findViewById(R.id.editText2);
        displayIs.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/TitilliumWeb-Light.ttf"));
        displayIs.setTextColor(Color.parseColor("#444763"));
        displayIs.setRawInputType(InputType.TYPE_CLASS_TEXT);
        displayIs.setTextIsSelectable(true);
        displayIs.setHorizontalScrollView(sc);

        displayAnswer = (DisplayEditText) findViewById(R.id.editText3);
        displayAnswer.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/TitilliumWeb-Light.ttf"));
        displayAnswer.setTextColor(Color.parseColor("#444763"));
        displayAnswer.setRawInputType(InputType.TYPE_CLASS_TEXT);
        displayAnswer.setTextIsSelectable(true);
        displayAnswer.setHorizontalScrollView(sc);

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
        speak = (ImageButton) findViewById(R.id.bSpeak);
        myLayout = (LinearLayout) findViewById(R.id.displayLayout);
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        pairs = new EditText[textViewCount];


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
                String replacesA = displayEquation.getText().toString().replaceAll("x", ";");

                String[] comacheck = replacesA.split("\\+|\\;|\\:|\\-");
                int lastNumber = comacheck.length - 1;
                if (!displayEquation.getText().toString().equals("")) {
                    if (!comacheck[lastNumber].contains(",")) {
                        clicks++;
                        checknumberOfOutcomes(",");
                        insertDisplayCharacter(",");
                    }
                }
            }
        });


        is.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (displayEquation.getText().toString().length() > 0){
                numberOfOutcomes = numberOfOutcomes + 1;
                    try {
                        calculate();
                        displayEquation.setText(equationStr);
                        displayIs.setText(isStr);
                        displayAnswer.setText(answerStr);

                        ChangeDisplayCharactersColor(displayEquation.getText());
                        ChangeDisplayCharactersColor(displayAnswer.getText());

                        displayEquation.setSelection(displayEquation.getText().length());
                        clicks = 0;
                        displayEquation.setFocusable(false);
                        displayEquation.setFocusableInTouchMode(false);
                        displayIs.setFocusable(false);
                        displayIs.setFocusableInTouchMode(false);
                        displayAnswer.setFocusable(false);
                        displayAnswer.setFocusableInTouchMode(false);
                        delete.setEnabled(false);
                    }catch (Exception e){

                    }
                }
                float f = displayAnswer.getTextSize();
                float a = convertPixelsToDp(f, getBaseContext());
                displayEquation.setTextSize(a);
                displayIs.setTextSize(a);
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
                if(equationStr.equals("")){
                    if(!cashMode){
                        cashMode = true;
                        cash.setBackgroundResource(R.drawable.buttonpressed);
                    } else {
                        cashMode = false;
                        cash.setBackgroundResource(R.drawable.buttoncharacter);
                    }
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                clicks = 0;
                numberOfOutcomes = 0;
                equationStr = "";
                isStr = "";
                answerStr = "";
                displayEquation.setText(equationStr);
                displayIs.setText(isStr);
                displayAnswer.setText(answerStr);

                displayEquation.setSelection(displayEquation.getText().length());
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

        mic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                displaySpeechRecognizer();
                Log.v("Speech", "------");
            }
        });

        speak.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String speak = equationStr + isStr + answerStr;
                speak = speak.replaceAll("-","min");
                speak = speak.replaceAll("x","keer");
                speak = speak.replaceAll(":","gedeeld door");

                t1.setSpeechRate(0.6F);
                t1.speak(speak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    private void calculate() {
        String[] splitted = equationStr.split(";");
        int last = splitted.length - 1;
        if (cashMode) {
            formatter = new DecimalFormat("#,###.00");
        } else {
            if (equationStr.contains(",")) {
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
        String somm = equationStr;
        String[] splitter = somm.split(";");
        int lastest = splitter.length - 1;
        String lastCalculation = splitter[lastest].replace(".", "");
        equationStr = lastCalculation;

        String completeEquation = "" + cal.Calculate(equationStr.replace(",", ".").replaceAll("\\s", ""));

        formattedResult = formatter.format(Double.parseDouble(completeEquation));
        if(cashMode){
            equationStr = somm;
            isStr = " = ";
            answerStr = "€" + formatter.format(Double.parseDouble(completeEquation));
        } else {
           equationStr = somm;
           isStr = " = " ;
           answerStr = formatter.format(Double.parseDouble(completeEquation));
        }
    }

    private void checknumberOfOutcomes(String character) {
        if (numberOfOutcomes >0 && clicks == 1 && equationStr.length()>0) {
           int l= textViewCount -1;
            pairs[l] = new EditText(this);
            pairs[l].setTextSize(15);
            pairs[l].setLayoutParams(lp);
            pairs[l].setId(l);
            pairs[l].setText(equationStr + isStr + answerStr);
            pairs[l].setTextSize(20);
            pairs[l].setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/TitilliumWeb-Light.ttf"));
            pairs[l].setTextColor(Color.parseColor("#444763"));

            pairs[l].setRawInputType(InputType.TYPE_CLASS_TEXT);
            pairs[l].setTextIsSelectable(false);
            pairs[l].setFocusableInTouchMode(false);
            pairs[l].setFocusable(false);

            pairs[l].setBackgroundColor(Color.TRANSPARENT);
            ChangeDisplayCharactersColor(pairs[l].getEditableText());
            TableLayout.LayoutParams params = new TableLayout.LayoutParams();
            params.setMargins(-0, -15, 0, -15);
            pairs[l].setLayoutParams(params);

            myLayout.addView(pairs[l]);

            historyScroll = (ScrollView) findViewById(R.id.verticalScroll);
            historyScroll.post(new Runnable() {
                public void run() {
                    historyScroll.fullScroll(ScrollView.FOCUS_DOWN);

                }
            });

            textViewCount++;
            pairs = new EditText[textViewCount];
            if (character.equals("x") || character.equals(":") || character.equals("-") || character.equals("+")) {
                equationStr = formattedResult;
                isStr = "";
                answerStr = "";
                goToLeft();
            }
            else{
                equationStr = "";
                isStr = "";
                answerStr = "";
                goToLeft();
            }
            displayIs.setText(isStr);
            displayAnswer.setText(answerStr);
        }

        displayEquation.setFocusable(true);
        displayIs.setFocusable(true);
        displayAnswer.setFocusable(true);
        displayEquation.setFocusableInTouchMode(true);
        displayIs.setFocusableInTouchMode(true);
        displayAnswer.setFocusableInTouchMode(true);
        delete.setEnabled(true);
    }

    public void formatCalculation() {
        if(cashMode) {
            formatter = new DecimalFormat("#,###.##");
        } else {
            formatter = new DecimalFormat("#,###.#####");
        }
        String calculations = equationStr;
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
        equationStr = combined;
    }

    private void deleteDisplayCharacter () {
        int cursorEndPosition = displayEquation.getSelectionEnd();

        if (cursorEndPosition > 0) {
            StringBuffer text = new StringBuffer(displayEquation.getText().toString());

            text.deleteCharAt(cursorEndPosition - 1);
            displayEquation.setText(text.toString());

            ChangeDisplayCharactersColor(displayEquation.getText());

            displayEquation.setSelection(cursorEndPosition - 1);
            equationStr = displayEquation.getText().toString();
        }
    }

    private void insertDisplayCharacter (String character) {

        if (clicks == 1){
            equationStr = equationStr + character;
            displayEquation.setText(equationStr);
            displayEquation.setSelection(displayEquation.getText().length());
            goToRight();
        }
        else {
            int cursorEndPosition = displayEquation.getSelectionEnd();


            if(cashMode) {
                if (ValidateCashInputCharachter(character, cursorEndPosition)) return;
            }

            StringBuffer text = new StringBuffer(equationStr);


            text.insert(cursorEndPosition, character);
            equationStr = text.toString();
            String beforeformatS = equationStr;
            if (equationStr.contains(",") && character.equals("0") ){
            }
            else if (!character.equals("+") && !character.equals("x") && !character.equals(":") && !character.equals("-") && !character.equals(",") ) {
                formatCalculation();
            }

            int count = equationStr.length() - beforeformatS.length();
            displayEquation.setText(equationStr);

            if (cursorEndPosition == equationStr.length()) {
                goToRight();
            }
            displayEquation.setSelection(cursorEndPosition + 1+count);

        }
        ChangeDisplayCharactersColor(displayEquation.getText());
    }

    private void ChangeDisplayCharactersColor(Editable editable) {
        int i = 0;
        if (editable != null) {
            for (Character character : editable.toString().toCharArray()) {
                if (operators.contains(character.toString())) {
                    editable.setSpan(new ForegroundColorSpan(Color.BLUE), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else if (character.equals(',')) {
                    editable.setSpan(new ForegroundColorSpan(Color.RED), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else if (character.equals('.')) {
                    editable.setSpan(new ForegroundColorSpan(Color.rgb(255, 215, 0)), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                i++;
            }
        }
    }

    private boolean ValidateCashInputCharachter(String character, int cursorEndPosition) {
        if (cursorEndPosition >= 3) {
            if (equationStr.substring(cursorEndPosition - 3, cursorEndPosition - 2).contains(",")) {

                if (!operatorsWithComma.contains(character) && !operatorsWithComma.contains((cursorEndPosition - 1 + ""))) {
                    if (!operatorsWithComma.contains(equationStr.charAt(cursorEndPosition - 1) + "") && !operatorsWithComma.contains(equationStr.charAt(cursorEndPosition - 2) + "")) {
                        return true;
                    }
                }
            } else {
                if (!operatorsWithComma.contains(character) && equationStr.charAt(cursorEndPosition - 1) == ',') {
                    if (equationStr.length() - 1 >= cursorEndPosition + 2) {
                        if (equationStr.length() - 1 >= cursorEndPosition + 2 && operatorsWithComma.contains(equationStr.charAt(cursorEndPosition + 2) + "")) {

                            if (!operatorsWithComma.contains(equationStr.charAt(cursorEndPosition - 1) + "") || !operatorsWithComma.contains(equationStr.charAt(cursorEndPosition - 2) + "")) {
                                return true;
                            }
                        }
                    }
                   else if (!operatorsWithComma.contains(character) && !operatorsWithComma.contains((equationStr.charAt(equationStr.length() - 1) + "")) && equationStr.length() - cursorEndPosition == 2) {
                        return true;
                    }
                } else if (!operatorsWithComma.contains(character) && equationStr.charAt(cursorEndPosition - 2) == ',' && equationStr.length() - cursorEndPosition >= 1 && !operatorsWithComma.contains(equationStr.charAt(cursorEndPosition) + "")) {
                    return true;
                }
            }
        } else {
            if (cursorEndPosition >= 2) {
                if (equationStr.substring(cursorEndPosition - 1, cursorEndPosition).contains(",")) {
                    if (!operatorsWithComma.contains(character) && !operatorsWithComma.contains((equationStr.charAt(equationStr.length() - 1) + "")) && (equationStr.length() - cursorEndPosition > 1)) {
                        if (!operatorsWithComma.contains(equationStr.charAt(cursorEndPosition - 1) + "") || !operatorsWithComma.contains(equationStr.charAt(cursorEndPosition - 2) + "")) {
                            return true;
                        }
                    }
                }
            }
        }
        if (cursorEndPosition >= 1) {
            if (operatorsWithComma.contains(equationStr.charAt(cursorEndPosition - 1) + "") && character.equals(",")) { return true; }
        }
        return false;
    }


    public void goToRight(){
        displayEquation.setMovementMethod(new ScrollingMovementMethod());
        sc.post(new Runnable() {
            public void run() {
                sc.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        });

    }

    public void goToLeft(){
        displayEquation.setMovementMethod(new ScrollingMovementMethod());
        sc.post(new Runnable() {
            public void run() {
                sc.fullScroll(HorizontalScrollView.FOCUS_LEFT);
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("som", equationStr);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        equationStr = savedInstanceState.getString("som");


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
            String spokenSum = "";

            spokenSum = replaceSpokenText(spokenText);

            for(String s: results){
                Log.v("spokentext",""+s);
                if(s.contains(" 100")){
                    for(int i = 1; i <= 99; i++){
                        if(s.equals(""+i+" 100")){
                            int itemp = i*100;
                            spokenText = ""+itemp;
                        }
                    }
                }
            }
            spokenSum = replaceSpokenText(spokenText);

            if(spokenSum.matches(regex1) || spokenSum.matches(regex2) || spokenSum.matches(regex3) || spokenSum.matches(regex4)) {
                equationStr = equationStr + spokenSum;
                formatCalculation();
                displayEquation.setText(equationStr);
                displayEquation.setSelection(displayEquation.getText().length());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public String replaceSpokenText(String spokenText){
        String spokenSum;

        spokenSum = spokenText.replaceAll(" ", "");

        spokenSum.toLowerCase();

        spokenSum = spokenSum.replaceAll("plus", "+");
        spokenSum = spokenSum.replaceAll("min", "-");
        spokenSum = spokenSum.replaceAll("keer", "x");
        spokenSum = spokenSum.replaceAll("maal", "x");
        spokenSum = spokenSum.replaceAll("gedeelddoor", ":");
        spokenSum = spokenSum.replaceAll("delendoor", ":");
        spokenSum = spokenSum.replaceAll("eenmiljoen", "1000000");
        spokenSum = spokenSum.replaceAll("een", "1");
        spokenSum = spokenSum.replaceAll("één", "1");
        spokenSum = spokenSum.replaceAll("Eén", "1");

        return spokenSum;
    }
}
