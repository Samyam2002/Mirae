package com.example.mirae.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.example.mirae.Model.Todo;
import com.example.mirae.R;
import com.example.mirae.ViewModel.TodoViewModel;
import com.example.mirae.databinding.ActivityInsertTodoBinding;

import java.util.Date;

public class InsertTodoActivity extends AppCompatActivity {

    ActivityInsertTodoBinding binding;
    String title, subtitle, todo;
    TodoViewModel todoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertTodoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);

        //when the doneTodoBtn is clicked
        binding.doneTodoBtn.setOnClickListener(v -> {
            title = binding.todoTitle.getText().toString();
            subtitle = binding.todoSubtitle.getText().toString();
            todo = binding.todoData.getText().toString();

            createTodo(title, subtitle, todo);
        });
    }

    private void createTodo(String title, String subtitle, String todo) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,YYYY", date.getTime());

        Todo todo1 = new Todo();
        todo1.todoTitle = title;
        todo1.todoSubtitle = subtitle;
        todo1.todoDate = sequence.toString();
        todo1.todo = todo;

        todoViewModel.insertTodos(todo1);

        Toast.makeText(this, "Todo Created Successfully!", Toast.LENGTH_SHORT).show();

        finish();
    }
}