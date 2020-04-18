package com.example.valuablemoney.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.valuablemoney.model.AccountSignUp;

public class DatabaseAcc extends SQLiteOpenHelper {
    private final String TAG = "DatabaseAcc";
    private static final String DATABASE_NAME = "account_manager";
    private static final String TABLE_NAME = "account";
    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String PASS1 = "pass1";
    private static final String PASS2 = "pass2";
    private static final int VERSION = 1;

    private String SQLQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USERNAME + " TEXT, " +
            PASS1 + " TEXT, " +
            PASS2 + " TEXT)";

    public DatabaseAcc(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        Log.d(TAG, "DatabaseAcc");
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

    public void addAccount(AccountSignUp account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME, account.getUsername());
        values.put(PASS1, account.getPass1());
        values.put(PASS2, account.getPass2());

        db.insert(TABLE_NAME, null, values);
        db.close();
        Log.d(TAG, "addAccount Successfuly");
    }
}