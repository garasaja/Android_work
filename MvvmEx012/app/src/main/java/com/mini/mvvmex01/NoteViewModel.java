package com.mini.mvvmex01;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository noteRepository = new NoteRepository();

    //viewModel 가진 데이터터
   private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);

    }

    public LiveData<List<Note>> 구독하기() { // 빨대꼽기
        return allNotes;
    }

    public void 추가하기(Note note) {
        noteRepository.save(note);
    }

    public void 삭제하기(Note note) {
        noteRepository.delete(note);
    }

    //쓸일이 없다. 처음에 한번 띄어주고 계속 갱신이 되기 때문에
//    public LiveData<List<Note>> 전체보기() {
//        return noteRepository.findAll();
//    }
}
