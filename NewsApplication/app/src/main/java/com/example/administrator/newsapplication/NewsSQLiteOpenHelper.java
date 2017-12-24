package com.example.administrator.newsapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 2017/12/19.
 */

public class NewsSQLiteOpenHelper extends SQLiteOpenHelper {
    public NewsSQLiteOpenHelper(Context context) {
        super(context, "mews.db", null, 5);
    }

    @Override //数据库第一次被创建时调用该方法
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table news(id integer primary key autoincrement,"+"title varchar(50),"+"words varchar(200),"+"imageURL varchar(60)) ");
    }

    @Override //数据库的版本号增加时调用
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("alter table news add account ");
    }
}
