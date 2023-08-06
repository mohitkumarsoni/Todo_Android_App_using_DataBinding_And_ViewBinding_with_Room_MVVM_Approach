package com.example.notesnew;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class Notes_ViewModel extends AndroidViewModel {

    private Notes_Repository repository;
    private LiveData<List<Notes_Entity>> noteList;


    public Notes_ViewModel(@NonNull Application application) {
        super(application);
        repository = new Notes_Repository(application);
        noteList = repository.getNotesList();
    }

    public void insertNotes(Notes_Entity notes){
        repository.insertNotes(notes);
    }
    public void deleteNotes(Notes_Entity notes){
        repository.deleteNotes(notes);
    }
    public void updateNotes(Notes_Entity notes){
        repository.updateNotes(notes);
    }
    public LiveData<List<Notes_Entity>> getNoteList(){
        return noteList;
    }


}
