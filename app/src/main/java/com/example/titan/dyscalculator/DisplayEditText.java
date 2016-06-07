package com.example.titan.dyscalculator;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
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
    }

    private void adjustTextSize() {

        if (sc != null) {
            //Berekening van de scrollView breedte, deze breedte hangt van het onderliggende edittext af.
            sc.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            final int scrollViewMeasuredWidth = sc.getMeasuredWidth();

            //De statische breedte van de scrollView in verhouding met de parent layout, deze breedte wordt eenmalig gezet bij inflatie van de layout.
            final int screenWidth = getContext().getResources().getDisplayMetrics().widthPixels;

            float schermDrieVierdeBreedte = screenWidth / 4 * 3;

            if (scrollViewMeasuredWidth > schermDrieVierdeBreedte && scrollViewMeasuredWidth > screenWidth) {
                super.setTextSize(TypedValue.COMPLEX_UNIT_PX, _minTextSize);
            } else if (scrollViewMeasuredWidth > schermDrieVierdeBreedte && scrollViewMeasuredWidth < screenWidth && (getText().length() * getTextSize()) > schermDrieVierdeBreedte) {
                float newTextSize = getTextSize();


                float currentTextSize = getTextSize();

                if (currentTextSize >= _minTextSize && currentTextSize <= _maxTextSize) {

                    float displayWithPart = screenWidth / (_maxTextSize - _minTextSize);

                    float totalDisplayTextWidth = screenWidth - scrollViewMeasuredWidth;
                    newTextSize = _minTextSize + (totalDisplayTextWidth / displayWithPart);
                }

                super.setTextSize(TypedValue.COMPLEX_UNIT_PX, newTextSize);

            } else if (scrollViewMeasuredWidth < schermDrieVierdeBreedte && (getText().length() * getTextSize()) < schermDrieVierdeBreedte) {
                super.setTextSize(TypedValue.COMPLEX_UNIT_PX, _maxTextSize);
            }
        }
    }

    @Override
    public void onTextChanged(final CharSequence text, final int start,
                                 final int before, final int after) {
        super.onTextChanged(text, start, before, after);
        adjustTextSize();
    }

    @Override
    protected void onSizeChanged(final int width, final int height,
                                 final int oldwidth, final int oldheight) {
       super.onSizeChanged(width, height, oldwidth, oldheight);
        if (sc != null) {
            setMovementMethod(new ScrollingMovementMethod());
            sc.post(new Runnable() {
                public void run() {
                    sc.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                }
            });
        }
    }

    public void setHorizontalScrollView(HorizontalScrollView view) { sc = view;}
}