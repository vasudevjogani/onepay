package com.udux.ioslikeactionsheet;

/**
 * Created by hb on 24/8/17.
 */

public class Menuitem {
    public String text;
    public int drawableResID;

    public Menuitem(String text, int drawableResID) {
        this.text = text;
        this.drawableResID = drawableResID;
    }

    public Menuitem(String text) {
        this.text = text;
        this.drawableResID = 0;
    }
}
