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

public class DatabaseKhoanChi extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "khoanchi_manager";
    private static final String TABLE_NAME = "khoanchi";
    private static final String ID = "id";
    private static final String LYDOCHI = "lydochi";
    private static final String SOTIENCHI = "sotienchi";
    private static final int VERSION = 1;

    private String SQLQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            LYDOCHI + " TEXT, " +
            SOTIENCHI + " INTEGER)";

    public DatabaseKhoanChi(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void addKhoanChi(KhoanChi khoanchi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LYDOCHI, khoanchi.getLydochi());
        values.put(SOTIENCHI, khoanchi.getSotienchi());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<KhoanChi> getAllKhoanChi(){
        List<KhoanChi> listKhoanChi = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                KhoanChi khoanchi = new KhoanChi();
                khoanchi.setId(cursor.getInt(0));
                khoanchi.setLydochi(cursor.getString(1));
                khoanchi.setSotienchi(cursor.getString(2));
                listKhoanChi.add(khoanchi);

            }while (cursor.moveToNext());
        }
        db.close();
        return listKhoanChi;
    }

    public int EditKhoanChi(KhoanChi khoanChi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LYDOCHI, khoanChi.getLydochi());
        values.put(SOTIENCHI, khoanChi.getSotienchi());
        int number = db.update(TABLE_NAME, values, ID + "=?", new String[]{String.valueOf(khoanChi.getId())});
        return number;
    }

    public int deleteChi(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, ID+"=?",new String[]{String.valueOf(id)});
    }
}