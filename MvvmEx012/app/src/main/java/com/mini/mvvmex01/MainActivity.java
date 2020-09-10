package com.mini.mvvmex01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Random;

// AAC는 데이터 변경을 관찰하기 위해서 사용하는 것이 아니다.
// 데이터가 변경되었을 때 UI변경을 트리거 하기 위해 사용하는 것이다.
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";

    private NoteViewModel noteViewModel;
    private NoteAdapter adapter;
    private RecyclerView recyclerView;

    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        // 누군가가 List 데이터를 추가, 삭제, 변경하면 observe가 알아챈다.
        // 이를 통해 데이터 변경과 UI변경을 동기화 시킬 수 있다.
        noteViewModel.구독하기().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                // update RecyclerView
                Log.d(TAG, "getAllNotes: 구독하고있는 데이터 변경 됨");
                adapter.setNotes(notes);
                // 화면 위치 변경 해주자.
//                if(noteViewModel.구독하기().getValue().size() !=0){
//                    recyclerView.smoothScrollToPosition(noteViewModel.구독하기().getValue().size()-1);
//                }
            }
        });

        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //saveNote();
                int priority = new Random().nextInt(100)+1;
                noteViewModel.추가하기(new Note("제목1"+priority,"설명1"+priority,priority));
            }
        });

        // 리사이클러뷰의 각 ViewHolder에 리스너를 달 필요없다. 왜냐하면
        // 리사이클러뷰가 있는 엑티비티에서 이벤트를 콜백 받을 수 있기 때문이다.
//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
//                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                // insert 하는 순간 DB에서 삭제되고 getAllNote()를 지켜보는 옵저버가 실행된다.
//                noteViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
//            }
//        }).attachToRecyclerView(recyclerView);
    }

//    private void saveNote(){
//        Random r = new Random();
//        int num = r.nextInt(10)+1;
//        String title = "Title "+num;
//        String description = "Description "+num;
//        int priority = num;
//
//        Note note = new Note(title, description, priority);
//        noteViewModel.insert(note); // insert 하는 순간 DB에 저장되고 getAllNote()를 지켜보는 옵저버가 실행된다.
//    }
}
