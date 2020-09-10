package com.mini.mvvmex01;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class NoteRepository {

    // LiveData는 함수가 getter만 있다. , 변경 불가 db빨대꼽을때
    // MutableLiveData 는 getter, setter가지고있다.  레트로핏쓸때는 이거씀
    private MutableLiveData<List<Note>> allNotes = new MutableLiveData<>();

    public NoteRepository() {
        // 값 초기화
        Note note = new Note("Title", "Description", 1);
        List<Note> notes = new ArrayList<>();
        notes.add(note);
        allNotes.setValue(notes);
    }

    public LiveData<List<Note>> findAll() {
        return allNotes;
    }

    public void delete(Note note) {
        List<Note> notes = allNotes.getValue();
        notes.remove(note);
        allNotes.setValue(notes);
    }

    public void save(Note note) {
        List<Note> notes = allNotes.getValue();
        notes.add(note);
        allNotes.setValue(notes);
    }

}
