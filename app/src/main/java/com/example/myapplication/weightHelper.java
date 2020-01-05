package com.example.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;


public class weightHelper extends SQLiteOpenHelper {
    public weightHelper(Context context)
    {
        super(context,"test.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE weight_info(account CHAR(10) NOT NULL,height FLOAT(10) NOT NULL DEFAULT 0,weight FLOAT(10) NOT NULL DEFAULT 0,date DATE(15) NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}