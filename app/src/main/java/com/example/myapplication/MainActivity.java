package com.example.myapplication;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


public class MainActivity extends AppCompatActivity implements TextFragment.OnSelectedButtonListener  {



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onButtonSelected(int buttonIndex, StringWithTypeface phrase) {
        // подключаем FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Получаем ссылку на второй фрагмент по ID
        ViewFragment fragment2 = (ViewFragment) fragmentManager
                .findFragmentById(R.id.button_layout);
// TODO: db
        // Выводим нужную информацию
        if (fragment2 != null)
            fragment2.setText(buttonIndex, phrase);
    }
}
