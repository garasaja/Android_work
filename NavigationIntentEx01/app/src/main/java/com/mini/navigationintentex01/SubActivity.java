package com.mini.navigationintentex01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class SubActivity extends AppCompatActivity {

    private NavigationView nav;
    private Context mContext = SubActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        init();

        NavigationViewHelper.enableNavigation(mContext,nav);
    }

    private void init() {
        nav = findViewById(R.id.nav);
    }
}
