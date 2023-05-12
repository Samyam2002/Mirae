package com.example.mirae.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mirae.MainActivity;
import com.example.mirae.Model.Todo;
import com.example.mirae.Repository.TodoRepository;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {

    public TodoRepository repository;
    public LiveData<List<Todo>> getAllTodo;
    public LiveData<List<Todo>> hightolow;
    public LiveData<List<Todo>> lowtohigh;

    public TodoViewModel(Application application) {
        super(application);

        repository = new TodoRepository(application);
        getAllTodo = repository.getAllTodo;
        hightolow = repository.hightolow;;
        lowtohigh = repository.lowtohigh;
    }

    public void insertTodos(Todo todo) {
        repository.insertTodo(todo);
    }
    public void deleteTodos(int id) {
        repository.deleteTodo(id);
    }

    public void updateTodos(Todo todo) {
        repository.updateTodo(todo);
    }
}