package com.mini.musicplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView btnPlayStop;
    private SeekBar seekBar;
    private TextView tvTime;

    private MusicService musicService;
    private MediaPlayer mp;

    private int isPlay = -1; // 1일시 음악재생  -1일시 음악일시정지

    private Handler handler = new Handler();
    //private boolean threadStatus = false;
    private Thread uiHandleThread;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.LocalBinder binder = (MusicService.LocalBinder) service;
            musicService = binder.getService();
            mp = musicService.getMp();
            seekbarinit();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mp.stop();
            mp.release();
        }
    };

    private void seekbarinit() {
        seekBar.setMax(mp.getDuration()); // 음악 길이와 동기화
        seekBar.setProgress(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initObject();
        initBinding();
        initListener();

        //isPlay = isPlay * -1;
    }

    private void initListener() {
        btnPlayStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPlay = isPlay *-1; //1일시 음악재생  -1일시 음악일시정지 초기값 -1
                if (isPlay == 1) {
                    musicStart();
                } else {
                    musicPause();
                }

                //seekbar 그림 그리기
                uiHandleThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (isPlay == 1) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {

                                    seekBar.setProgress(mp.getCurrentPosition());
                                    if (mp.getCurrentPosition() >= mp.getDuration()) {
                                        musicStop();
                                    }
                                }
                            });
                            try {
                                Thread.sleep(1000);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } // while문 종료료
                        try {
                           Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        uiHandleThread.interrupt(); // 스레드를 종료하려면 0.001초라도 멈춰야 종료가능

                    }
                });
                uiHandleThread.start();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 유저가 SeekBar를 클릭할 때
                if (fromUser) {
                    mp.seekTo(progress);
                }

                int m = mp.getCurrentPosition() / 60000;
                int s = (mp.getCurrentPosition() %60000) / 1000;
                String strTime = String.format("%02d:%02d", m, s);
                tvTime.setText(strTime);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void musicStart() {
        mp.start();
        btnPlayStop.setImageResource(R.drawable.ic_pause);
    }
    private void musicPause() {
        mp.pause();
        btnPlayStop.setImageResource(R.drawable.ic_play);
    }
    private void musicStop() {
        mp.seekTo(0);
        seekBar.setProgress(0);
        btnPlayStop.setImageResource(R.drawable.ic_play);
        isPlay = -1;
        //threadStatus = true;
    }

    private void initBinding() {
        Intent intent = new Intent(MainActivity.this,MusicService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);
    }

    private void initObject() {
        btnPlayStop = findViewById(R.id.btn_play_stop);
        seekBar = findViewById(R.id.seekBar);
        tvTime = findViewById(R.id.tv_time);
    }
}
