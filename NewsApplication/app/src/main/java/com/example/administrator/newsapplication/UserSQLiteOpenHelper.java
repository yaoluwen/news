package com.example.administrator.newsapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 2017/12/21.
 */

public class UserSQLiteOpenHelper extends SQLiteOpenHelper {

    public UserSQLiteOpenHelper(Context context) {
        super(context, "users.db", null,5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(id integer primary key autoincrement,"+"name varchar(50),"+"passwords varchar(60)) ");
        Log.i("qqq","onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("alter table news add account ");
    }
}
