package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ButtonFragment extends Fragment  implements View.OnClickListener  {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.button_layout, container, false);

        Button create = view.findViewById(R.id.button);
        Button cancel = view.findViewById(R.id.cancel);

        create.setOnClickListener(this);
        cancel.setOnClickListener(this);

        return view;
    }

    public void onClickSave(View view, EditText editText, TextView inputTextView, TextView errortextView) {
        String str = editText.getText().toString();
        if (str.length() == 0) {
            errortextView.setText("Input string please");
        } else
            inputTextView.setText(str);
    }

    public void onClickCancel(View view, EditText editText, TextView inputTextView, TextView errorTextView) {

        inputTextView.setText("");
        editText.setText("");
        errorTextView.setText("");
    }

    @Override
    public void onClick(View v) {
        int buttonIndex = translateIdToIndex(v.getId());

        OnSelectedButtonListener listener = (OnSelectedButtonListener) getActivity();
        listener.onButtonSelected(buttonIndex);
    }


    private int translateIdToIndex(int id) {
        int index = -1;
        switch (id) {
            case R.id.button:
                index = 1;
                break;
            case R.id.cancel:
                index = 2;
                break;
        }
        return index;
    }

    public interface OnSelectedButtonListener {
        void onButtonSelected(int buttonIndex);
    }
}


