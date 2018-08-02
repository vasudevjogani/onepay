package com.example.hb.invest.invest.utiles.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.hb.invest.R;

/**
 */
public class CustomTextView extends TextView {
    private int font;

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFont(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initFont(context, attrs);
    }

    @Override
    public void setTextAppearance(Context context, int resId) {
        super.setTextAppearance(context, resId);
        initFont(getContext(), resId);
    }

    private void initFont(Context context, int resId) {
        TypedArray ta = context.obtainStyledAttributes(resId, R.styleable.CustomTextView);
        font = ta.getInt(R.styleable.CustomTextView_font_type, 0);
        ta.recycle();
        setTypeFace();
    }

    private void initFont(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        font = ta.getInt(R.styleable.CustomTextView_font_type, 0);
        ta.recycle();
        setTypeFace();
    }

    public void setDynamicFont(int font) {
        this.font = font;
        setTypeFace();
    }

    private void setTypeFace() {
        try {
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}