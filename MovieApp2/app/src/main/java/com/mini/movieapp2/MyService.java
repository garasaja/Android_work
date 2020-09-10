package com.mini.movieapp2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";
    private MediaPlayer mp;

    public MyService() {
    }

    // startService() 실행
    // bindService() 실행
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: 호출됨");
    }

    // startService() 실행 - activity가 종료되어도 백그라운드로 돌고잇음
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String musicName = intent.getStringExtra("musicName");
        if (musicName.equals("sample1")) {
            mp = MediaPlayer.create(this,R.raw.sample1);
        } else {
            mp = MediaPlayer.create(this,R.raw.sample2);
        }
        mp.start();

        int sec = mp.getDuration() / 1000;
        Log.d(TAG, "onStartCommand: 음악재생시간 : " + sec);
        return super.onStartCommand(intent, flags, startId);
    }

    // bindService() 실행 - activity에 바인드됨 , activity와 생명주기를 같이함
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: 호출됨");
        throw new UnsupportedOperationException("Not yet implemented");
    }


    // stopService() 일때 실행
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: 호출됨");
        mp.stop();
    }
}
