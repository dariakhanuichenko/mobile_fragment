package com.example.myapplication;

import android.graphics.Typeface;

public class TypefaceWithFontName {

    private Typeface font;
    private String fontName;


    public TypefaceWithFontName(Typeface font, String fontName) {
        this.font = font;
        this.fontName = fontName;
        System.out.println("Constructor" + this.getFont().toString());
    }

    public Typeface getFont() {
        return font;
    }

    public void setFont(Typeface font) {
        this.font = font;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }
}
