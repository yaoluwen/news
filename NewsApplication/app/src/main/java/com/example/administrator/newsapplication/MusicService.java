package com.example.administrator.newsapplication;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Administrator on 2017/12/22.
 */

public class MusicService extends Service {
    private static final String TAG="MusicService";
    public MediaPlayer mediaPlayer;
    class MyBinder extends Binder{
        public void play(){
            MusicService.this.play();
        }
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.i(TAG,"onCreate");
        mediaPlayer=MediaPlayer.create(this,R.raw.news);
    }
    public void play(){
        mediaPlayer.start();
    }
    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {

        Log.i(TAG,"onstartcommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"ondestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onbind");
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"onunbimd");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(TAG,"onrebind");
    }
}
