package com.example.angel.bdautomoviles.modelos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Angel on 07/05/2015.
 */
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{

    //Creamos el constructor
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    //Se crea la tabla
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table autos (n_serie integer primary key unique, marca text, modelo text, color text)");
    }

    //Borrar la tabla y crear la nueva tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists autos");
        db.execSQL("create table autos (n_serie integer primary key unique, marca text, modelo text, color text)");
    }
}
