package com.mini.movieapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etTitle;
    private Button btnStart, btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initObject();
        initListener();


    }

    private void initListener() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String musicName = etTitle.getText().toString();
                Intent intent = new Intent(MainActivity.this,MyService.class);
                intent.putExtra("musicName",musicName);
                startService(intent);
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MyService.class);
                stopService(intent);
            }
        });
    }

    private void initObject() {
        etTitle = findViewById(R.id.et_title);
        btnStart = findViewById(R.id.btn_Start);
        btnFinish = findViewById(R.id.btn_Stop);
    }
}
