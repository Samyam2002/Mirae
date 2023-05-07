package com.example.mirae;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Database;

import android.content.Intent;
import android.os.Bundle;

import com.example.mirae.Activity.InsertTodoActivity;
import com.example.mirae.Database.TodoDatabase;
import com.example.mirae.Repository.TodoRepository;
import com.example.mirae.ViewModel.TodoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newTodoBtn;
    TodoViewModel todoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);

        newTodoBtn = findViewById(R.id.newTodoBtn);

        newTodoBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, InsertTodoActivity.class));
        });

        todoViewModel.getAllTodo.observe(this, todo -> {

        });
    }
}