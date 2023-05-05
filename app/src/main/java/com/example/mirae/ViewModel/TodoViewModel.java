package com.example.mirae.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mirae.Model.Todo;
import com.example.mirae.Repository.TodoRepository;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {

    public TodoRepository repository;
    public LiveData<List<Todo>> getAllTodo;

    public TodoViewModel(Application application) {
        super(application);

        TodoRepository repository = new TodoRepository(application);
        getAllTodo = repository.getAllTodo;
    }

    void insertTodos(Todo todo) {
        repository.insertTodo(todo);
    }

    void deleteTodos(int id) {
        repository.deleteTodo(id);
    }

    void updateTodos(Todo todo) {
        repository.updateTodo(todo);
    }
}