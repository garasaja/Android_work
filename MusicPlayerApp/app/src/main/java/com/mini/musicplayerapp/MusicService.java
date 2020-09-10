package com.mini.musicplayerapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service {

    private static final String TAG = "MusicService";
    private final IBinder binder = new LocalBinder();
    private MediaPlayer mp;

    class LocalBinder extends Binder { // AIDL 따라한 것 쉽게 적용가능
        MusicService getService() {
            return MusicService.this;
        }
    }

    public MediaPlayer getMp() {
        return mp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this,R.raw.sample1);
    }

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return binder;
    }
}
