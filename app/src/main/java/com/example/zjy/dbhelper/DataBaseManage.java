package com.example.zjy.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by zjy on 2017/3/20.
 */

public class DataBaseManage {

    private static DataBaseManage DATA_BASE_MANAGE;

    private static SQLiteDatabase db;

    private DataBaseManage(Context context){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        db = dataBaseHelper.getReadableDatabase();
    }

    public static DataBaseManage newInstance(Context context){
        if(DATA_BASE_MANAGE == null){
            synchronized (DataBaseManage.class){
                if(DATA_BASE_MANAGE == null){
                    DATA_BASE_MANAGE = new DataBaseManage(context);
                }
            }
        }
        return DATA_BASE_MANAGE;
    }

    public static void savaUser(User user){
        if(user != null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("user_phone",user.getUserPhone());
            contentValues.put("user_password",user.getUserPassword());
            db.insert("user",null,contentValues);
        }
    }

    //根据电话号码查询用户密码
    public static String getUser(String userPhone){
        Cursor cursor = db.rawQuery(
                "select * from user where user_phone = ?",
                new String[]{userPhone});
        while (cursor.moveToNext() && cursor.getCount() > 0){
            return cursor.getString(cursor.getColumnIndex("user_phone"));
        }
        return "";
    }
}
