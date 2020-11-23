package com.example.sqliteproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT, MARKS TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean inserData(String name, String surname, String marks) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues tableValues = new ContentValues();
        tableValues.put(COL_2, name);
        tableValues.put(COL_3, surname);
        tableValues.put(COL_4, marks);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, tableValues);
        if (result == -1) {
            return false;
        } else {
            return true;

        }


    }

    public Cursor getDatabaseData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from " + TABLE_NAME, null);
        return cursor;
    }

    public boolean UpdateData(String id, String name, String surName, String marks) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues tableValues = new ContentValues();
        tableValues.put(COL_1, id);
        tableValues.put(COL_2, name);
        tableValues.put(COL_3, surName);
        tableValues.put(COL_4, marks);
        sqLiteDatabase.update(TABLE_NAME, tableValues, "ID==?", new String[]{id});
        return true;
    }

    public Integer delete(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, "ID==?", new String[]{id});
    }
}
