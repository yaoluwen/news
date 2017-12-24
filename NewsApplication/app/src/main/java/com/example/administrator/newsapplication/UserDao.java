package com.example.administrator.newsapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/21.
 */

public class UserDao {
    private UserSQLiteOpenHelper helper;

    public UserDao(Context context) {
        helper=new UserSQLiteOpenHelper(context);
    }

    public void add(User user)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",user.getName());
        values.put("passwords",user.getPaswords());
        int id= (int) db.insert("user",null,values);
        user.setId(id);
        Log.i("qqq","add user sucess");
        db.close();
    }
    public int delete(int id)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        int count=db.delete("user","id=?",new String[]{id+""});
        db.close();
        return count;
    }
    public int update(User user)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",user.getName());
        values.put("passwords",user.getPaswords());
        int count=db.update("user",values,"id=?",new String[]{user.getId()+""});
        db.close();
        return count;
    }
    public String find(String name)
    {
        String pwd=null;
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from user where name=?",new String[]{name});
        if(cursor.moveToNext())
        {
            int nameColumnIndex = cursor.getColumnIndex("passwords");
            pwd = cursor.getString(nameColumnIndex);
        }
        cursor.close();
        db.close();
        return pwd;
    }
}
