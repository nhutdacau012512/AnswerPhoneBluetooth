package com.answerbluetoohphone.Base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.answerbluetoohphone.R;

/**
 * Created by MinhNhut on 2/18/17.
 */

public class FontLoader {
    public static Typeface extractTypeface(final Context context, final AttributeSet attrs) {
        final TypedArray args = context.obtainStyledAttributes(attrs, R.styleable.AccFont);
        final String family = args.getString(R.styleable.AccFont_accFontName);
        args.recycle();

        if (family == null) {
            return null;
        }
        return Typeface.createFromAsset(context.getAssets(), family);
    }
}
