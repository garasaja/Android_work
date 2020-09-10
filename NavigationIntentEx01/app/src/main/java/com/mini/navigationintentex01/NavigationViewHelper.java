package com.mini.navigationintentex01;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.navigation.NavigationView;

public class NavigationViewHelper {
    public static void enableNavigation(final Context context, NavigationView view) {
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_1:
                        Toast.makeText(context, "메뉴1클릭됨 메인액티비티 전환", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); //
                        context.startActivity(intent);
                        break;

                    case R.id.menu_2:
                        Toast.makeText(context, "메뉴2클릭됨 서브액티비티 전환", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(context,SubActivity.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        context.startActivity(intent2);
                        break;
                }
                return true;
            }
        });
    }
}
