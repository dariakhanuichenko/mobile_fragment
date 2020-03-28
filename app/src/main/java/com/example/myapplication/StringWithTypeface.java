package com.example.myapplication;

import android.graphics.Typeface;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;

public class StringWithTypeface {

    private String phrase;
    private Typeface typeface;

    public StringWithTypeface(View root) {
        this.phrase = "";
        this.typeface = ResourcesCompat.getFont(root.getContext(), R.font.arialblack);
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }
}
