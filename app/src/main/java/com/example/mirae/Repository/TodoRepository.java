package com.example.mirae.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mirae.Dao.TodoDao;
import com.example.mirae.Database.TodoDatabase;
import com.example.mirae.Model.Todo;
import com.example.mirae.R;

import java.util.List;

public class TodoRepository {
    public TodoDao todoDao;
    public LiveData<List<Todo>> getAllTodo;

    public TodoRepository(Application application){
        TodoDatabase database = TodoDatabase.getDatabaseInstance(application);
        todoDao = database.todoDao();
        getAllTodo = todoDao.getAllTodo();
    }

    public void insertTodo(Todo todo){
        todoDao.insertTodo(todo);
    }

    public void deleteTodo(int id){
        todoDao.deleteTodo(id);
    }

    public void updateTodo(Todo todo){
        todoDao.updateTodo(todo);
    }
}
