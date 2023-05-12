package com.example.mirae.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mirae.Model.Todo;
import com.example.mirae.R;
import com.example.mirae.ViewModel.TodoViewModel;
import com.example.mirae.databinding.ActivityInsertTodoBinding;

import java.util.Calendar;
import java.util.Date;

public class InsertTodoActivity extends AppCompatActivity {

    ActivityInsertTodoBinding binding;
    String priority="1";
    String title, subtitle, todo;
    TodoViewModel todoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertTodoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);

        binding.greenPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(R.drawable.baseline_done_24);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);

            priority="1";
        });

        binding.yellowPriority.setOnClickListener(v -> {
            binding.yellowPriority.setImageResource(R.drawable.baseline_done_24);
            binding.greenPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);

            priority="2";
        });

        binding.redPriority.setOnClickListener(v -> {
            binding.redPriority.setImageResource(R.drawable.baseline_done_24);
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);

            priority="3";
        });

        //when the doneTodoBtn is clicked
        //first, validate whether the fields are empty or not
        binding.doneTodoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = binding.todoTitle.getText().toString();
                subtitle = binding.todoSubtitle.getText().toString();
                todo = binding.todoData.getText().toString();

                createTodo(title, subtitle, todo);
            }
        });
    }

    private void createTodo(String title, String subtitle, String todo) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy", date.getTime());

        Todo todo1 = new Todo();
        todo1.todoTitle = title;
        todo1.todoSubtitle = subtitle;
        todo1.todoDate = sequence.toString();
        todo1.todo = todo;
        todo1.todoPriority = priority;

        todoViewModel.insertTodos(todo1);

        Toast.makeText(this, "Todo Created Successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }
}