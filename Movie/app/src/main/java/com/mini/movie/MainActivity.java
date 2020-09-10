package com.mini.movie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private Context mContext = MainActivity.this;
    private ListView listView;
    private ArrayAdapter<String> adapter; // 나중에는 안쓴다
    private List<String> mid = Arrays.asList( "가","나","다","라","마","바","사","아",
            "가","나","다","라","마","바","사","아",
            "가","나","다","라","마","바","사","아",
            "가","나","다","라","마","바","사","아" );



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);
        // 어댑터는 액티비티/ 디자인(사이즈) / 데이터
        adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, mid);
        listView.setAdapter(adapter);


    }
}
