package com.example.notesnew;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Notes_Dao {
    @Insert
    public void insertNotes(Notes_Entity notes);
    @Update
    public void updateNotes(Notes_Entity notes);
    @Delete
    public void deleteNotes(Notes_Entity notes);
    @Query(" SELECT * FROM my_notes ")
    public LiveData<List<Notes_Entity>> getAllNotes();

}
