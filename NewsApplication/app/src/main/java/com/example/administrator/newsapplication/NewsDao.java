package com.example.administrator.newsapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.util.Log.i;

/**
 * Created by Administrator on 2017/12/19.
 */

public class NewsDao {
    private NewsSQLiteOpenHelper helper;

    public NewsDao(Context context) {
        helper=new NewsSQLiteOpenHelper(context);
    }
    public void add(News news)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("title",news.getTitle());
        values.put("words",news.getWords());
        values.put("imageURL",news.getImageURL());
        int id= (int) db.insert("news",null,values);
        news.setId(id);
        Log.i("qqq","add sucess");
        db.close();
    }
    public int delete(int id)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        int count=db.delete("news","id=?",new String[]{id+""});
        db.close();
        return count;
    }
    public int update(News news)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("title",news.getTitle());
        values.put("words",news.getWords());
        values.put("imageURL",news.getImageURL());
        int count=db.update("news",values,"id=?",new String[]{news.getId()+""});
        db.close();
        return count;
    }
    public List<News> queryAll(){
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor c=db.query("news",null,null,null,null,null,"id DESC");
        List<News> list=new ArrayList<News>();
        while(c.moveToNext())
        {
            int id=c.getInt(c.getColumnIndex("id"));
            String title=c.getString(1);
            String words=c.getString(2);
            String imageURL=c.getString(3);
            list.add(new News(title,words,imageURL));
        }
        c.close();
        db.close();
        return list;
    }
}
