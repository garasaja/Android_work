package com.mini.calculater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    EditText et_result;
    Button num[] = new Button[10],btn_cal,btn_divide,btn_multiply,btn_minus,btn_add,btn_reset;
    private int number=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = et_result.getText().toString();
                Log.d(TAG, "onClick: result : " + result);
                et_result.setText(Eval.cal(result));
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_result.append("+");
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_result.append("-");
            }
        });

        btn_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_result.append("*");
            }
        });

        btn_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_result.append("/");
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_result.setText("");
            }
        });

        for(int i=0;i<10;i++){
            num[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button btn = (Button)v;
                    et_result.append(btn.getText().toString());
                }
            });
        }
    }

    private void init() {
        et_result = findViewById(R.id.et_result);
        num[0] = findViewById(R.id.num0);
        num[1] = findViewById(R.id.num1);
        num[2] = findViewById(R.id.num2);
        num[3] = findViewById(R.id.num3);
        num[4] = findViewById(R.id.num4);
        num[5] = findViewById(R.id.num5);
        num[6] = findViewById(R.id.num6);
        num[7] = findViewById(R.id.num7);
        num[8] = findViewById(R.id.num8);
        num[9] = findViewById(R.id.num9);
        btn_cal = findViewById(R.id.btn_cal);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_minus = findViewById(R.id.btn_minus);
        btn_divide = findViewById(R.id.btn_divide);
        btn_add = findViewById(R.id.btn_add);
        btn_reset = findViewById(R.id.btn_reset);

    }
}
