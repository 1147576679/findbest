package com.example.zjy.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by zjy on 2017/3/20.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DataBaseHelper";
    private String CREATE_USER = "create table user(" +
            "id integer primary key," +
            "user_phone text unique," +
            "user_password text)";

    public DataBaseHelper(Context context) {
        super(context, "banTang.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
        Log.i(TAG, "用户表创建成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
