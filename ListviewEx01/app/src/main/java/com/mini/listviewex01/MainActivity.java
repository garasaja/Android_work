package com.mini.listviewex01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main_Activity";

    private Context mContext = MainActivity.this;
    private Button btnSnack, btnToast;
    private View mainView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Test.callToast(mContext);

        btnSnack = findViewById(R.id.btn_snack);
        btnToast = findViewById(R.id.btn_toast);
        mainView = findViewById(R.id.main_view);

        Log.d(TAG, "onCreate: mainview의 context는 ? : " + mainView.getContext());

        btnSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"스낵바나와랏", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });

        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"토스트메시지 나와라",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
