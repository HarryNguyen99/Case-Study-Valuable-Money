package com.example.valuablemoney.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.valuablemoney.model.KhoanChi;
import com.example.valuablemoney.model.KhoanThu;

import java.util.ArrayList;
import java.util.List;

public class DatabaseKhoanThu extends SQLiteOpenHelper {
    private final String TAG = "DatabaseKhoanThu";
    private static final String DATABASE_NAME = "khoanthu_manager";
    private static final String TABLE_NAME = "khoanthu";
    private static final String ID = "id";
    private static final String NGUONTHU = "nguoanthu";
    private static final String SOTIEN = "sotien";
    private static final int VERSION = 1;

    private String SQLQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NGUONTHU + " TEXT, " +
            SOTIEN + " INTEGER)";

    public DatabaseKhoanThu(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        Log.d(TAG, "DatabaseKhoanThu");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade");
    }

    public void addKhoanThu(KhoanThu khoanthu) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NGUONTHU, khoanthu.getNguonthu());
        values.put(SOTIEN, khoanthu.getSotien());
        db.insert(TABLE_NAME, null, values);
        db.close();
        Log.d(TAG, "addKhoanThu Successfuly");
    }

    public List<KhoanThu> getAllKhoanThu(){
        List<KhoanThu> listKhoanThu = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                KhoanThu khoanThu = new KhoanThu();
                khoanThu.setNguonthu(cursor.getString(1));
                khoanThu.setSotien(cursor.getString(2));
                listKhoanThu.add(khoanThu);

            }while (cursor.moveToNext());
        }
        db.close();
        return listKhoanThu;
    }

    public int EditKhoanThu(KhoanThu khoanThu){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NGUONTHU, khoanThu.getNguonthu());
        values.put(SOTIEN, khoanThu.getSotien());
        return db.update(TABLE_NAME, values, ID +"=?",new String[]{String.valueOf(khoanThu.getId())});
    }
}