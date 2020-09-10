package com.mini.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "Main_Activity"; // logt
    private ProgressBar pgbDownload;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: 테스트입니다");
        //컴포넌트 초기화
        initComponent();
        //다운로드5초
        imageDownload();


    }

    private void initComponent() {
        pgbDownload = findViewById(R.id.pgb_download);
    }
    private void imageDownload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: 다운로드 쓰레드 시작");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "run: 다운로드 쓰레드 종료");
                pgbDownload.setVisibility(View.INVISIBLE);
            }
        }).start();
    }
}
