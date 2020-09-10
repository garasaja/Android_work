package com.mini.lifecycleex02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private Context mContext = MainActivity.this;
    private Button btnNum, btnEmail;
    private TextView tvNum, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNum = findViewById(R.id.btn_num);
        btnEmail = findViewById(R.id.btn_email);
        tvNum = findViewById(R.id.tv_num);
        tvEmail = findViewById(R.id.tv_email);



        //인증번호 받기
        btnNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,SubActivity.class);
                startActivityForResult(intent,1);
                //startActivity(intent);

            }
        });

        //이메일 받기
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,Sub2Activity.class);
                startActivityForResult(intent,1);
                //startActivity(intent);
            }
        });
        
    }

    //콜백함수
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: 콜백받음");

        if(requestCode == 1) {
            if(resultCode == 10) {
                tvNum.setText(data.getStringExtra("number"));
            } else if (resultCode == 20) {
                tvEmail.setText(data.getStringExtra("email"));
            }
        }
    }
}
