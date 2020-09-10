package com.mini.countingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private Button btnAdd, btnMinus;
    private TextView tvcount;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btn_plus);
        btnMinus = findViewById(R.id.btn_minus);
        tvcount = findViewById(R.id.tv_count);
        tvcount.setText(count+"");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                tvcount.setText(count+"");
                Log.d(TAG, "onClick: btnadd"+v.getLayerType());
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count<=0) {
                    Toast.makeText(getApplicationContext(),"숫자가 0보다 작으므로 감소가 불가능 합니다.",Toast.LENGTH_SHORT).show();
                } else {
                    count--;
                    tvcount.setText(count+"");
                }
            }
        });
    }
}
