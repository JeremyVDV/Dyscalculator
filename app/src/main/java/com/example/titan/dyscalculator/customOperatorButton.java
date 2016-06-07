package com.example.titan.dyscalculator;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Ruben on 22-3-2016.
 */
public class customOperatorButton extends Button {

    public customOperatorButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/TitilliumWeb-Light.ttf"));
        this.setTextSize(40);
        this.setTextColor(Color.BLUE);
    }
}
