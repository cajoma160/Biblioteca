package com.example.angel.biblioteca;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by angel on 09/05/2015.
 */
public class AdmiSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdmiSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table biblioteca(dni integer primary key, nombre text, numero text, nombre_li text, fecha text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int anterior, int nueva) {

        db.execSQL("drop table if exists biblioteca");
        db.execSQL("create table biblioteca(dni integer primary key, nombre text, numero text, nombre_li text, fecha text)");

    }
}
