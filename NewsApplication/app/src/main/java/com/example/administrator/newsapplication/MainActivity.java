package com.example.administrator.newsapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {
    private MyServiceConnection conn;
    MusicService.MyBinder binder;
    MediaPlayer mediaPlayer;
    private List<News> newsList;
    private NewsAdapter newsAdapter;
    private Bitmap bitmap;
    private ListView listView;
    private TextView titleTV;
    private TextView wordsTV;
    private ImageView imageview;
    private NewsDao dao;
    class NewsAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return newsList.size();
        }

        @Override
        public Object getItem(int position) {
            return newsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return newsList.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final News news = newsList.get(position);
            View view=View.inflate(MainActivity.this, R.layout.list_item, null);
            ((TextView) view.findViewById(R.id.textView3)).setText(news.getTitle());
            ((TextView) view.findViewById(R.id.textView2)).setText(news.getWords());
            final ImageView imageview=((ImageView) view.findViewById(R.id.imageView));
            final Handler handler=new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if(msg.what==1){
                        Bitmap bitmap= (Bitmap) msg.obj;
                        imageview.setImageBitmap(bitmap);
                    }else if(msg.what==2){
                        Toast.makeText(MainActivity.this,"显示图片错误",Toast.LENGTH_SHORT).show();
                    }
                }
            };
            new Thread(new Runnable() {
                private HttpURLConnection conn;

                @Override
                public void run() {
                    try {
                        URL url = new URL(news.getImageURL());
                        conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setConnectTimeout(5000);
                        int code = conn.getResponseCode();
                        if (code == 200) {
                            InputStream is = conn.getInputStream();
                            bitmap = BitmapFactory.decodeStream(is);
                            Message msg=new Message();
                            msg.what=1;
                            msg.obj=bitmap;
                            handler.sendMessage(msg);
                        }
                        else
                        {
                            Message msg=new Message();
                            msg.what=2;
                            handler.sendMessage(msg);
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            return view;
        }
    }
    public void bang(){
        if(conn==null){
            conn=new MyServiceConnection();
            Log.i("qqqq","绑定服务1111");
        }
        Intent intent=new Intent(this,MusicService.class);
        bindService(intent,conn,BIND_AUTO_CREATE);

    }
    public void bofang(){
        if(binder==null){
            Log.i("qqqq","还没有绑定服务");
            return;
        }
        binder.play();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer=MediaPlayer.create(this,R.raw.news);
        bang();
        //bofang();
        initView();
        dao=new NewsDao(this);
        News news=new News("中央定了！2018年将重点抓这些大事 关乎你的生活","中央经济工作会议12月18日至20日在北京举行。会议总结党的十八大以来我国经济发展历程，分析当前经济形势，并部署2018年经济工作。","https://mmbiz.qpic.cn/mmbiz_jpg/oq1PymRl9D6SIwHQCvldxgb5S5G66hE4BGXwrxla5r1DCfJRMxyL2uFvB3u3k16UMznj4333dLUyoPTrf7H3oQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
        News news1=new News("联合国大会通过关于耶路撒冷地位问题的决议","联合国大会当天以１２８票对９票的结果通过决议，３５个国家投了弃权票。决议要求以色列和巴勒斯坦通过谈判决定耶路撒冷的地位。","http://images.shobserver.com/news/690_390/2017/12/20/0b2062a1-a90d-4032-8902-bfb7f90a465d.jpg");
        News news2=new News("95后嫩模开豪车醉驾撞死人被判赔112万元 至今仍拒绝赔偿","今年3月28日凌晨，东莞樟木头一名女司机黄某醉酒驾驶保时捷豪车，撞上了三名行人，造成1人当场死亡，2人受伤。肇事司机是一名女模特，事发时年仅21岁。","http://n.sinaimg.cn/translate/w600h410/20171221/_qXH-fypvuqe7648747.jpg");
        News news3=new News("杭州保姆纵火案辩护律师：退庭不是临时起意，开庭前就已决定","12月21日上午，“杭州保姆纵火案”在杭州市中级人民法院9时开庭审理后仅30分钟，就由于被告莫焕晶的辩护律师党琳山多次提出管辖权异议未被采纳而退庭，导致审理中止，审判长宣布延期审理。","http://image.thepaper.cn/wap/image/6/579/420.jpg");
        News news4=new News("硅谷新能源车革命中的中国身影","无论是特斯拉，还是成败未知的FF，他们都树立了一个概念——未来汽车更偏向于是一个互联网产品，强调人机交互，而不仅仅是驾驶的工具。","http://images.tmtpost.com/uploads/images/2017/12/20171222090344504.jpg");
        News news5=new News("调查显示日本下半年诈骗网站案约达2万起","由信息安全公司和网络公司等组成的“日本网络犯罪对策中心”（JC3）的调查结果显示，伪装成网店并以商品名义等诈骗货款的“诈骗网站”案仅今年7月到12月就达到19834起。与JC3合作的日本警察厅21日透露了上述消息。","http://i9.hexun.com/2017-12-21/192045290.jpg");
        dao.add(news);
        dao.add(news1);
        dao.add(news2);
        dao.add(news3);
        dao.add(news4);
        dao.add(news5);
        newsList=dao.queryAll();
        newsAdapter=new NewsAdapter();
        listView.setAdapter(newsAdapter);

    }
    private void initView() //初始化控件
    {
        listView= ((ListView) findViewById(R.id.lv));
        titleTV= ((TextView) findViewById(R.id.textView3));
        wordsTV= ((TextView) findViewById(R.id.textView2));
        imageview= ((ImageView) findViewById(R.id.imageView));
        listView.setOnItemClickListener(new MyOnItemClickListener());
    }
    private class MyOnItemClickListener implements AdapterView.OnItemClickListener{ //item添加点击事件

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            News n= (News) parent.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(),n.toString(),Toast.LENGTH_SHORT).show();
            //bofang();
        }
    }
    private class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder= (MusicService.MyBinder) service;
            Log.i("qqqq","绑定服务成功，地址："+binder.toString());
            bofang();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
