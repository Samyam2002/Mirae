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

    @Query("SELECT * FROM Todo_Database ORDER BY todo_priority DESC")
    LiveData<List<Todo>> highToLow();

    @Query("SELECT * FROM Todo_Database ORDER BY todo_priority ASC")
    LiveData<List<Todo>> lowToHigh();

    @Insert
    void insertTodo(Todo... todo);

    @Query("DELETE FROM Todo_Database WHERE id=:id")
    void deleteTodo(int id);

    @Update
    void updateTodo(Todo todo);
}
