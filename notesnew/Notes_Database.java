package com.example.notesnew;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Notes_Entity.class, version = 1,exportSchema = false)
public abstract class Notes_Database extends RoomDatabase {

    private static final String DATABASE_NAME = "notes_db";
    private static Notes_Database db_Instance;

    public static synchronized Notes_Database getInstance(Context context){
        if (db_Instance == null){
            db_Instance = Room.databaseBuilder(context.getApplicationContext(), Notes_Database.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
        }
        return db_Instance;
    }

    public abstract Notes_Dao notesDao();

}
