package com.example.todolist;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotesDao {
    @Query("SELECT * FROM note")
    List<Note> getNote();

    @Insert
    void add(Note note);

    @Query("DELETE FROM note WHERE id = :id")
    void remove(int id);
}
