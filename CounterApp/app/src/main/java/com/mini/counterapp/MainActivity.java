package com.mini.counterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private TextView tvCount;
    private Button btnStart, btnStop;
    private ICounterService binder;
    private boolean running = true;
    private Handler handler = new Handler();
    private int startCount = 0;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            binder = ICounterService.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            binder = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initObject();
        initListener();
    }

    private void initObject(){
        tvCount = findViewById(R.id.tvCounter);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
    }

//     - BIND_AUTO_CREATE : Component와 연결되있는 동안 비정상적으로 종료시 자동으로 다시 시작.
//     - BIND_DEBUG_UNBIND : 비정상적으로 연결이 끊어지면 로그를 남긴다 (디버깅용)
//    - BIND_NOT_FOREGROUND : 백그라운드로만 동작한다. 만약 Activity에서 생성한 경우
//    Activity와 생성주기를 같이 한다.


    private void initListener(){
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: btnStart 클릭됨");
                Intent intent = new Intent(MainActivity.this, CounterService.class);
                intent.putExtra("startCount", startCount);
                bindService(intent, connection, BIND_AUTO_CREATE);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        running = true;
                        while(running){
                            if(binder != null){
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            tvCount.setText(binder.getCount()+"");
                                            Log.d(TAG, "run: 그림그려짐");
                                            if(binder.getCount() == 20){
                                                running = false;
                                            }
                                        } catch (RemoteException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }).start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startCount = binder.getCount();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                unbindService(connection);
                running = false;
            }
        });
    }



}


