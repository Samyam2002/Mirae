package com.example.mirae;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.content.Intent;
import android.os.Bundle;

import com.example.mirae.Activity.InsertTodoActivity;
import com.example.mirae.Adapter.TodoAdapter;
import com.example.mirae.Database.TodoDatabase;
import com.example.mirae.Repository.TodoRepository;
import com.example.mirae.ViewModel.TodoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newTodoBtn;
    TodoViewModel todoViewModel;
    TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView todoRecycler;

        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);

        newTodoBtn = findViewById(R.id.newTodoBtn);
        todoRecycler = findViewById(R.id.todoRecycler);

        newTodoBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, InsertTodoActivity.class));
        });

        todoViewModel.getAllTodo.observe(this, todo -> {
            todoRecycler.setLayoutManager(new LinearLayoutManager(this));
            adapter = new TodoAdapter(MainActivity.this, todo);
            todoRecycler.setAdapter(adapter);
        });
    }
}