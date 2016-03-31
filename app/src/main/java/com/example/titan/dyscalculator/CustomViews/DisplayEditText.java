package com.example.titan.dyscalculator.CustomViews;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.HorizontalScrollView;

/**
 * Created by Joris on 30-3-2016.
 */
public class DisplayEditText extends EditText {
    private int _maxTextSize;
    private int _minTextSize;

    public HorizontalScrollView sc;

    public DisplayEditText(final Context context) {
        this(context, null, 0);
        Init();
    }

    public DisplayEditText(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
        Init();
    }

    public DisplayEditText(final Context context, final AttributeSet attrs,
                           final int defStyle) {
        super(context, attrs, defStyle);
        Init();
    }

    private void Init()
    {
        _maxTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, getTextSize(),getResources().getDisplayMetrics());
        _minTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, getTextSize() - 30,getResources().getDisplayMetrics());
        Log.d("test", "MaxTextSize: " + _maxTextSize);
        Log.d("test", "MinTextSize: " + _minTextSize);
    }

    private void adjustTextSize() {

        if (sc != null ) {

//            final int displayTextMeasuredWidth = getMeasuredWidth() - getCompoundPaddingLeft()
//                    - getCompoundPaddingRight();
//
//            final int displayTextWidth = getWidth() - getCompoundPaddingLeft()
//                    - getCompoundPaddingRight();
//            Log.d("test", "displayTextWidth: " + displayTextWidth);

            //Berekening van de scrollView breedte, deze breedte hangt van het onderliggende edittext af.
            sc.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            final int scrollViewMeasuredWidth = sc.getMeasuredWidth();

            //De statische breedte van de scrollView in verhouding met de parent layout, deze breedte wordt eenmalig gezet bij inflatie van de layout.
            final int scrollViewWidth = sc.getWidth();

            Log.d("test", "ScrollViewWidth: " + scrollViewWidth);
            Log.d("test", "ScrollMeasuredViewWidth: " + scrollViewMeasuredWidth);
            //Log.d("test", "displayTextMeasuredWidth: " + displayTextMeasuredWidth);

            float schermdrievierdeBreedte = scrollViewWidth / 4  *  3;
            Log.d("test", "schermDrieVierdeBreedte: " + schermdrievierdeBreedte);
            Log.d("test", "TextLength: " + getText().length());
                if (scrollViewMeasuredWidth > schermdrievierdeBreedte && scrollViewMeasuredWidth > scrollViewWidth) {
                    super.setTextSize(TypedValue.COMPLEX_UNIT_PX, _minTextSize);
                    Log.d("test", "stap 1: ");
                } else if (scrollViewMeasuredWidth > schermdrievierdeBreedte && scrollViewMeasuredWidth < scrollViewWidth && (getText().length() * getTextSize()) > schermdrievierdeBreedte) {

                    float newTextSize = getTextSize();

                    float currentTextSize = getTextSize();
                    Log.d("test", "currentTextSize: " + currentTextSize);

                    if (currentTextSize >= _minTextSize && currentTextSize <= _maxTextSize) {

                        float displayWithPart = scrollViewWidth / (_maxTextSize - _minTextSize);
                        Log.d("test", "DisplayWithPart: " + displayWithPart);

                        float totalDisplayTextWidth = scrollViewWidth - scrollViewMeasuredWidth;
                        Log.d("test", "TotalDisplayTextWidth: " + totalDisplayTextWidth);
                        newTextSize = _minTextSize + (totalDisplayTextWidth / displayWithPart);
                        Log.d("test", "newTextSize: " + newTextSize);
                    }

                    super.setTextSize(TypedValue.COMPLEX_UNIT_PX, newTextSize);

                    Log.d("test", "stap 2: ");
                } else if (scrollViewMeasuredWidth < schermdrievierdeBreedte && (getText().length() * getTextSize()) > schermdrievierdeBreedte) {
                    //super.setTextSize(TypedValue.COMPLEX_UNIT_PX, _maxTextSize);
                    Log.d("test", "stap 3: " + (getText().length() * getTextSize()));
                    //if (displayTextMeasuredWidth < scrollViewWidth) {
                   //     super.setTextSize(TypedValue.COMPLEX_UNIT_PX, _maxTextSize);
                   // }

                } else if (scrollViewMeasuredWidth < schermdrievierdeBreedte && (getText().length() * getTextSize()) < schermdrievierdeBreedte) {
                    super.setTextSize(TypedValue.COMPLEX_UNIT_PX, _maxTextSize);
                    Log.d("test", "stap 4: " + (getText().length() * getTextSize()));
                }
                 else if (scrollViewMeasuredWidth < schermdrievierdeBreedte) {
                //    super.setTextSize(TypedValue.COMPLEX_UNIT_PX, _maxTextSize);
                    Log.d("test", "stap 5: ");
                }

            Log.d("test", "------------------------- ");
        }
    }

    @Override
    public void onTextChanged(final CharSequence text, final int start,
                                 final int before, final int after) {
       // super.onTextChanged(text, start, before, after);
        adjustTextSize();
    }

    @Override
    protected void onSizeChanged(final int width, final int height,
                                 final int oldwidth, final int oldheight) {
      // super.onSizeChanged(width, height, oldwidth, oldheight);
    }

    public void setHorizontalScrollView(HorizontalScrollView view) { sc = view;}
}