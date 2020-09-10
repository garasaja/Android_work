package com.mini.asynctaskex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main_Activity";
    private Button btnExecute,btnStop,btnRestart;
    private ProgressBar progressBar;
    private int value = 0;
    private BackgroundTask task;

    class BackgroundTask extends AsyncTask<Integer,Integer,Integer> {

        @Override
        protected void onPreExecute() { // 타겟 호출 직전


            super.onPreExecute();
            Log.d(TAG, "onPreExecute: ");
        }

        @Override
        protected Integer doInBackground(Integer... integers) { // 타겟 run임 , 스레드 실행시 인수 받기
            while (isCancelled() == false) {
                Log.d(TAG, "doInBackground: ");
                if (value <100) {


                    value = value + 5;
                    publishProgress(value);


                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }

            }

            //정상일시 1 아닐시 -1
            return 1;

        }

        @Override
        protected void onProgressUpdate(Integer[] values) { // UI쓰레드에 그림을 그려주는 메서드 , publishprogress 인수 받기
            super.onProgressUpdate(values);
            Log.d(TAG, "onProgressUpdate: value  : " + value);

            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) { // 타겟 호출 이후 , doinbackground 리턴 값 받기
            super.onPostExecute(integer);
            Log.d(TAG, "onPostExecute: value = " + value);
            Toast.makeText(MainActivity.this, "다운로드 완료!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        minit();
//        task = new BackgroundTask();
        mlistener();

    }

    private void minit() {
        btnExecute = findViewById(R.id.btn_excute);
        btnStop = findViewById(R.id.btn_stop);
        btnRestart = findViewById(R.id.btn_restart);
        progressBar = findViewById(R.id.progressBar);
    }

    private void mlistener() {
        btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(task == null || task.isCancelled() == true){
                    task = new BackgroundTask();
                    task.execute();
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.cancel(true);

            }
        });

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = 0;
                task.cancel(true);
                Integer[] a = {0};
                task.onProgressUpdate(a);
            }
        });
    }


}
