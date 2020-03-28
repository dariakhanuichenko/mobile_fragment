package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "app";

    public static final String TABLE_NAME = "main";
    public static final String KEY_ID = "_id";
    public static final String KEY_PHRASE = "phrase";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + TABLE_NAME + " ("
                + KEY_ID + " integer primary key, "
                + KEY_PHRASE + " text)");
    }

    // при изменении бд
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL(" drop table if exists " + TABLE_NAME);
//        onCreate(db);
    }
}
