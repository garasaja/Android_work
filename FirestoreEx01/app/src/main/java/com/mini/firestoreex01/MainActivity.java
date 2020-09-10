package com.mini.firestoreex01;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private FirebaseFirestore db;
    private List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance(); // 양방향

      // save();
       // findAll();
       // findById();
       // test();
        mStream();
    }

    private void mStream() { // DB에 변경된 내용을 push알림으로 알려줌
        final DocumentReference docRef = db.collection("user").document("1");
        //빨대꼽기
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                Log.d(TAG, "onEvent: 데이터 변경됨");
                User user = documentSnapshot.toObject(User.class);
                Log.d(TAG, "onEvent: user : " + user.getPassword());
            }
        });
    }


    private void save() {
        User user = User.builder()
                .id(4)
                .username("saja")
                .password("1234567811")
                .email("saja@nate.com")
                .createDate(Timestamp.now())
                .build();
       // userList.add(user);

        db.collection("user").document("4")
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: 인서트 잘됨");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: 인서트 실패 : " + e.getMessage());
                    }
                });
    }

    private void test() {
        db.document("user/3")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            User user = document.toObject(User.class);
                            Log.d(TAG, "onComplete: 이메일 : " + user.getEmail());
                        } else {
                            Log.d(TAG, "onComplete: findbyid실패 : " + task.getException());
                        }
                    }
                });
    }

    private void findById() {
        db.collection("user").document("3")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            User user = document.toObject(User.class);
                            Log.d(TAG, "onComplete: 이메일 : " + user.getEmail());
                        } else {
                            Log.d(TAG, "onComplete: findbyid실패 : " + task.getException());
                        }
                    }
                });
    }

    private void findAll() {
        db.collection("user").orderBy("id", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            //Log.d(TAG, "onComplete: task 성공 : " + task.getResult().getDocuments());
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User user = document.toObject(User.class);
                                userList.add(user);
                                Log.d(TAG, "onComplete: document : " + document.getId() + " => " + document.getData());
                                //뷰모델 레퍼런스 접근해서 data.setValue(컬렉션);
                            }
                        } else {
                            Log.d(TAG, "onComplete: task 실패" + task.getException());
                        }
                    }
                });
    }
}
