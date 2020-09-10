package com.mini.activityex01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    private Button btnFinishSub;
    private TextView tvSub;
    private static final String TAG = "Sub_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        
        btnFinishSub = findViewById(R.id.btn_finish_sub);
        btnFinishSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        String result = intent.getStringExtra("name");

        User user = (User)intent.getSerializableExtra("user");
        Log.d(TAG, "onCreate: username : " + user.getUsername());
        Log.d(TAG, "onCreate: password : " + user.getPassword());
        Log.d(TAG, "onCreate: name : " + result);
    }
}
