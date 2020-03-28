package com.example.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class TextFragment extends Fragment implements View.OnClickListener {

    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private EditText editText;
    private List<TypefaceWithFontName> fonts; // шрифты
    private StringWithTypeface phrase; // фраза и шрифт для передачи в другой фрагмент

    private Intent intent; //для перехода на другое активити


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.text_layout, container, false);
        phrase = new StringWithTypeface(root);


        fonts = this.getFonts(root);
        spinner = root.findViewById(R.id.spinner);
        editText = root.findViewById(R.id.editText1);
        adapter = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_spinner_item, getFontNames(fonts));
        intent = new Intent(root.getContext(), Activity2.class);
        Button create = root.findViewById(R.id.button);
        Button cancel = root.findViewById(R.id.cancel);
        Button getAll = root.findViewById(R.id.viewAll);

        create.setOnClickListener(this);
        cancel.setOnClickListener(this);
        getAll.setOnClickListener(this);
        // адаптер
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
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
                        phrase.setTypeface(font.getFont());
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

    private int translateIdToIndex(int button_id) {
        phrase.setPhrase(editText.getText().toString());
        int index = -1;
        if (button_id == R.id.button) {
            if (!phrase.getPhrase().isEmpty()) {
                // строчка не пустая
                index = 1;
                editText.setText("");
            }
            // строчка пустая
            else index = -1;
        } else if (button_id == R.id.cancel) {
            //отменить
            editText.setText("");
            index = 2;
        } else if (button_id == R.id.viewAll) {
            //показать все с бд
            startActivity(intent);
            index = 3;
        }

        return index;
    }

    @Override
    public void onClick(View v) {
        int buttonIndex = translateIdToIndex(v.getId());

        TextFragment.OnSelectedButtonListener listener = (TextFragment.OnSelectedButtonListener) getActivity();
        listener.onButtonSelected(buttonIndex, phrase);
    }

    public interface OnSelectedButtonListener {

        void onButtonSelected(int buttonIndex, StringWithTypeface phrase);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<TypefaceWithFontName> getFonts(View root) {
        List<TypefaceWithFontName> fonts = new ArrayList<TypefaceWithFontName>();
        /*ResourcesCompat.getFont*/
        fonts.add(new TypefaceWithFontName(ResourcesCompat.getFont(root.getContext(), R.font.murrey_c), "MurreyC"));
        fonts.add(new TypefaceWithFontName(ResourcesCompat.getFont(root.getContext(), R.font.arialblack), "Arial Black"));
        fonts.add(new TypefaceWithFontName(ResourcesCompat.getFont(root.getContext(), R.font.helvetica), "Helvetica"));
        fonts.add(new TypefaceWithFontName(Typeface.SANS_SERIF, "Sans Serif"));

        return fonts;
    }

    private List<String> getFontNames(List<TypefaceWithFontName> fonts) {
        List<String> names = new ArrayList<String>();
        for (TypefaceWithFontName font : fonts) {
            names.add(font.getFontName());
        }
        return names;
    }
}
