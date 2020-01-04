package com.example.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;


public class myHelper extends SQLiteOpenHelper {
    public myHelper(Context context)
    {
        super(context,"test.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE users(account CHAR(10) PRIMARY KEY NOT NULL UNIQUE,psw CHAR(10) NOT NULL,sex CHAR(10) NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
