package com.cdp.agenda.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NOMBRE = "pruebaCrud.db";
    public static final String TABLE_CLIENTE = "cliente";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CLIENTE + "(" +
                "cli_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cli_nombre TEXT NOT NULL," +
                "cli_apelli TEXT NOT NULL," +
                "cli_cedula TEXT NOT NULL," +
                "cli_telefo TEXT NOT NULL," +
                "cli_email TEXT," +
                "cli_edad INTEGER NOT NULL," +
                "cli_fechaNacimiento TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CLIENTE);
        onCreate(sqLiteDatabase);

    }
}
