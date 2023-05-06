package com.example.mirae.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mirae.R;
import com.example.mirae.databinding.ActivityInsertTodoBinding;

public class InsertTodoActivity extends AppCompatActivity {

    ActivityInsertTodoBinding binding;
    String title, subtitle, todo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertTodoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //when the doneTodoBtn is clicked
        binding.doneTodoBtn.setOnClickListener(v -> {
            title = binding.todoTitle.getText().toString();
            subtitle = binding.todoSubtitle.getText().toString();
            todo = binding.todoData.getText().toString();

        });
    }
}