package com.example.administrator.newsapplication;

/**
 * Created by Administrator on 2017/12/21.
 */

public class User {
    private  int id;
    private String name;
    private  String paswords;

    public User(String name, String paswords) {
        this.name = name;
        this.paswords = paswords;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaswords() {
        return paswords;
    }

    public void setPaswords(String paswords) {
        this.paswords = paswords;
    }
}
