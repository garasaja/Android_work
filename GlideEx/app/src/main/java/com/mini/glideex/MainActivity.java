package com.mini.glideex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    ImageView ivPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivPoster = findViewById(R.id.iv_poster);
        Glide.with(this).load("http://i.imgur.com/DvpvklR.png").into(ivPoster);
    }
}
