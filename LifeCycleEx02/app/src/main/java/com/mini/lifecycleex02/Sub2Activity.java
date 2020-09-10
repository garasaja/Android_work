package com.mini.lifecycleex02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Sub2Activity extends AppCompatActivity {

    private Button btnEmail;
    private EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);

        btnEmail = findViewById(R.id.btn_email);
        etEmail = findViewById(R.id.et_email);

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("email",etEmail.getText().toString());
                setResult(20,intent);
                finish();
            }
        });
    }
}
