package com.example.administrator.newsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/12/21.
 */

public class LoginActivity extends AppCompatActivity {
    private EditText et_userid;
    private EditText et_password;
    private UserDao userdao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        userdao=new UserDao(this);
        /*User user=new User("lx","123456");
        User user1=new User("xyh","123456");
        User user2=new User("ylw","123456");
        userdao.add(user);
        userdao.add(user1);
        userdao.add(user2);*/
        et_userid= ((EditText) findViewById(R.id.userId));
        et_password= ((EditText) findViewById(R.id.password));
    }
    public void introduce(View view){
        Intent intent=new Intent(getApplicationContext(),IntroduceActivity.class);
        startActivity(intent);
    }
    public void btnlogin(View view){
        final String userid=et_userid.getText().toString().trim();
        final String passwords=et_password.getText().toString().trim();
        String pwds=null;
        pwds=userdao.find(userid);
        if(pwds.equals(passwords))
        {
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);}
    }
}
