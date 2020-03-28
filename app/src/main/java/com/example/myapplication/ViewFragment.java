package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ViewFragment extends Fragment {

    private TextView inputTextView;
    private TextView errorTextView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.view_layout, container, false);
        inputTextView =  root.findViewById(R.id.result);
        errorTextView = root.findViewById(R.id.error);

        return root;
    }

    public void setText(int buttonIndex, StringWithTypeface phrase) {

        if (buttonIndex == -1) {
            errorTextView.setText("Input string please");
        }  if (buttonIndex == 1) {
            inputTextView.setText(phrase.getPhrase());
            inputTextView.setTypeface(phrase.getTypeface());
            inputTextView.setTextSize(50);
            errorTextView.setText("");

        }  if( buttonIndex == 2){
            inputTextView.setText("");
            errorTextView.setText("");
        }

    }
}


