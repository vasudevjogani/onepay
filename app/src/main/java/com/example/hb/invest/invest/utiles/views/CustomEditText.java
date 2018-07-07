package com.example.hb.invest.invest.utiles.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;

import com.example.hb.invest.R;

/**
 * Created by hb on 15/10/15.
 */
public class CustomEditText extends EditText {
    private int font;

    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFont(context, attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initFont(context, attrs);
    }

    private void initFont(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomEditText);
        font = ta.getInt(R.styleable.CustomEditText_font_type, 0);
        if (!isInEditMode()) {
            switch (font) {
                case 1:
                    setTypeface(TypeFaceProvider.getTypeFace(getContext(), "fonts/roboto_light.ttf"));
                    break;
                case 2:
                    setTypeface(TypeFaceProvider.getTypeFace(getContext(), "fonts/roboto_medium.ttf"));
                    break;
                case 3:
                    setTypeface(TypeFaceProvider.getTypeFace(getContext(), "fonts/roboto_regular.ttf"));
                    break;
                default:
                    setTypeface(TypeFaceProvider.getTypeFace(getContext(), "fonts/roboto_light.ttf"));
                    break;
            }
            ta.recycle();
        }
    }


}