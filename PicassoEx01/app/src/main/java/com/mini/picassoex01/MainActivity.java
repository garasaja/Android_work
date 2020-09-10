package com.mini.picassoex01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private ImageView ivPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivPoster = findViewById(R.id.iv_poster);
        Picasso.get().load("http://localhost:8080/images/store/6.png").into(ivPoster);
    }
}
