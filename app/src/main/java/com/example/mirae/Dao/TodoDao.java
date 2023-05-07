package com.example.mirae.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mirae.Model.Todo;

import java.util.List;

@Dao
public interface TodoDao {
    @Query("SELECT * FROM Todo_Database")
    LiveData<List<Todo>> getAllTodo();

    @Insert
    void insertTodo(Todo... todo);

    @Query("DELETE FROM Todo_Database WHERE id=:id")
    void deleteTodo(int id);

    @Update
    void updateTodo(Todo todo);
}
