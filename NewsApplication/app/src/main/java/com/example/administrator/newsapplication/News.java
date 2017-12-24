package com.example.administrator.newsapplication;

/**
 * Created by Administrator on 2017/12/18.
 */

public class News {

    private int id;
    private String title;
    private String words;
    private String imageURL;

    public News( String title, String words, String imageURL) {
        this.title = title;
        this.words = words;
        this.imageURL = imageURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "[序号: "+id+",新闻标题: "+title+", 新闻内容: "+words+", 新闻: "+imageURL+"]";
    }
}
