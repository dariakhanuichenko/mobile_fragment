package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {

    private DBHelper dbHelper;
    private TextView getAllTextView;
    private TextView emptyDBTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        dbHelper = new DBHelper(this);

        getAllTextView = this.findViewById(R.id.allRows);
        emptyDBTextView = this.findViewById(R.id.emptyDB);
        getAllRows();
    }

    private void getAllRows() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);

        String allRows = String.format("%-3s %-20s\n", "id", "phrase");

        if (cursor.moveToFirst()) {
            emptyDBTextView.setText("Your database");
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int phraseIndex = cursor.getColumnIndex(DBHelper.KEY_PHRASE);

            do {
                allRows += String.format("%-3d %-20s\n", cursor.getInt(idIndex), cursor.getString(phraseIndex));
//                System.out.println(allRows);

            } while (cursor.moveToNext() && !cursor.isLast());
            getAllTextView.setText(allRows);
        } else {
            emptyDBTextView.setText("0 row exists");
        }
        cursor.close();

        dbHelper.close();
    }
}
