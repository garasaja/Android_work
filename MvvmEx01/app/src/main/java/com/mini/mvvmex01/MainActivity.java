package com.mini.mvvmex01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main_Activity";
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "db-cos")
                .fallbackToDestructiveMigration() // 스키마의 버전 변경가능
                .allowMainThreadQueries() // 메인쓰레드에서 DB에 IO를 가능하게 해줌
                .build();

        userRepository = db.userRepository();

        User user = new User("DONG MIN", "KIM");
        userRepository.insert(user);

        Log.d(TAG, "onCreate: 데이터가 잘 저장됨 user : " + user);

        List<User> users = userRepository.findAll();
        Log.d(TAG, "onCreate: users : " + users);

        User userEntity = userRepository.findById(1);
        Log.d(TAG, "onCreate: 1번유저 : " + userEntity);

    }
}
