package com.example.myapplication;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class TextViewFragment extends Fragment {

    private TextView inputTextView;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private TextView errorTextView;
    private EditText editText;
    private List<TypefaceWithFontName> fonts;


    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<TypefaceWithFontName> getFonts(View root) {
        List<TypefaceWithFontName> fonts = new ArrayList<TypefaceWithFontName>();
        /*ResourcesCompat.getFont*/
        fonts.add(new TypefaceWithFontName(ResourcesCompat.getFont(root.getContext(), R.font.murrey_c), "MurreyC"));
        fonts.add(new TypefaceWithFontName(ResourcesCompat.getFont(root.getContext(), R.font.arialblack), "Arial Black"));
        fonts.add(new TypefaceWithFontName(ResourcesCompat.getFont(root.getContext(), R.font.helvetica), "Helvetica"));
        fonts.add(new TypefaceWithFontName(Typeface.SANS_SERIF, "Sans Serif"));

        System.out.println("[[[["+ fonts.get(3).getFontName() + "]]]]");
        return fonts;
    }

    private List<String> getFontNames(List<TypefaceWithFontName> fonts) {
        List<String> names = new ArrayList<String>();
        for (TypefaceWithFontName font : fonts) {
            names.add(font.getFontName());
            System.out.println(" !!!! " + font.getFont().getStyle());
        }
        return names;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.text_view_layout, container, false);
        inputTextView = (TextView) root.findViewById(R.id.result);

        fonts = this.getFonts(root);
        spinner = root.findViewById(R.id.spinner);
        errorTextView = root.findViewById(R.id.error);
        editText = root.findViewById(R.id.editText1);
        adapter = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_spinner_item, getFontNames(fonts));

        // адаптер
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        // заголовок
//        spinner.setPrompt("Title");
        // выделяем элемент
        spinner.setSelection(2);

        // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                for (TypefaceWithFontName font : fonts) {

                    if (spinner.getSelectedItem().toString().equals(font.getFontName())) {
                        inputTextView.setTypeface(font.getFont());
                        inputTextView.setTextSize(50);
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        return root;

    }

    public void setText(int buttonIndex) {

        String str = editText.getText().toString();

        if (str.length() != 0) {

            if (buttonIndex == 1) {
                inputTextView.setText(str);

            } else if (buttonIndex == 2) {
                inputTextView.setText("");
                editText.setText("");
                errorTextView.setText("");
            }

        } else if ((buttonIndex == 1 || buttonIndex == 2)) {
            errorTextView.setText("Input string please");
        }
    }


}
