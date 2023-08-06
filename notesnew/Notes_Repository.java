package com.example.notesnew;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Notes_Repository {

    private Notes_Dao dao;
    private LiveData<List<Notes_Entity>> notesList;

    public Notes_Repository(Application application){
        Notes_Database database = Notes_Database.getInstance(application);
        dao = database.notesDao();
        notesList = dao.getAllNotes();
    }


    public void insertNotes(Notes_Entity notes){
        new InsertTask(dao).execute(notes);
    }
    public void updateNotes(Notes_Entity notes){
        new UpdateTask(dao).execute(notes);
    }
    public void deleteNotes(Notes_Entity notes){
        new DeleteTask(dao).execute(notes);
    }
    public LiveData<List<Notes_Entity>> getNotesList(){
        return notesList;
    }

    //===========


    private static class InsertTask extends AsyncTask<Notes_Entity, Void, Void>{
        Notes_Dao dao;

        public InsertTask(Notes_Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Notes_Entity... notesEntities) {
            dao.insertNotes(notesEntities[0]);
            return null;
        }
    }
    private static class UpdateTask extends  AsyncTask<Notes_Entity, Void,Void>{
        Notes_Dao dao;

        public UpdateTask(Notes_Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Notes_Entity... notesEntities) {
            dao.updateNotes(notesEntities[0]);
            return null;
        }
    }
    private static class DeleteTask extends AsyncTask<Notes_Entity, Void, Void>{
        Notes_Dao dao;

        public DeleteTask(Notes_Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Notes_Entity... notesEntities) {
            dao.deleteNotes(notesEntities[0]);
            return null;
        }
    }




}
