package com.mini.lifecycleex02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubActivity extends AppCompatActivity {

    private static final String TAG = "Sub_Activity";
    private Button btnNum;
    private EditText etNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        btnNum = findViewById(R.id.btn_num);
        etNum = findViewById(R.id.et_num);

        btnNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("number",etNum.getText().toString());
                setResult(10,intent);
                finish();
            }
        });
    }
}
