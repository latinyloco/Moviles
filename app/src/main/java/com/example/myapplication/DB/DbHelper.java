package com.example.myapplication.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;     //versión de la db
    private static final String DATABASE_NOMBRE = "Perfil";
    public static final String TABLE_PARTIDAS = "Partidas";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {   //lo que se ejecuta en el momento que creamos nuestra db

        db.execSQL("CREATE TABLE " + TABLE_PARTIDAS + "(" +
                "fecha TEXT NOT NULL," +
                "nombre TEXT NOT NULL," +
                "pts INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}