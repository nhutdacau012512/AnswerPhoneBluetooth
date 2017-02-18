package com.answerbluetoohphone.Base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.answerbluetoohphone.R;

/**
 * Created by MinhNhut on 2/18/17.
 */

public class CustomFontTextView extends TextView {
    public CustomFontTextView(Context context) {
        super(context);
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadCustomFont(context,attrs);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadCustomFont(context,attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomFontTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        loadCustomFont(context,attrs);
    }

    private void loadCustomFont(Context context,AttributeSet attrs) {
        Typeface typeface = FontLoader.extractTypeface(context, attrs);
        setTypeface(typeface);
    }
}
