package com.example.myapplication;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
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
    private TextView successTextView;
    private DBHelper dbHelper;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.view_layout, container, false);
        inputTextView = root.findViewById(R.id.result);
        errorTextView = root.findViewById(R.id.error);
        successTextView = root.findViewById(R.id.success);
        dbHelper = new DBHelper(root.getContext());

        return root;
    }

    public void setText(int buttonIndex, StringWithTypeface phrase) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if (buttonIndex == -1) {
            errorTextView.setText("Input string please");
        }
        if (buttonIndex == 1) {
            contentValues.put(DBHelper.KEY_PHRASE, phrase.getPhrase());
            inputTextView.setText(phrase.getPhrase());
            inputTextView.setTypeface(phrase.getTypeface());
            inputTextView.setTextSize(50);
            errorTextView.setText("");

            database.insert(DBHelper.TABLE_NAME, null, contentValues);
            successTextView.setText("Your phrase was written");
        }
        if (buttonIndex == 2) {
            inputTextView.setText("");
            errorTextView.setText("");
            successTextView.setText("");
        }
        dbHelper.close();
    }
}


