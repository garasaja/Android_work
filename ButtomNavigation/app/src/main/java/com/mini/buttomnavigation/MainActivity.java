package com.mini.buttomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new Frag1()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Log.d(TAG, "onNavigationItemSelected: " + item.getItemId());
                switch (item.getItemId()) {
                    case R.id.nav_search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new Frag1()).commit();
                        break;
                    case R.id.nav_settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new Frag2()).commit();
                        break;
                    case R.id.nav_navigation:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new Frag3()).commit();
                        break;
                }

                return true; // true로 해야 그림이 다시 그려짐
            }
        });
    }
}
