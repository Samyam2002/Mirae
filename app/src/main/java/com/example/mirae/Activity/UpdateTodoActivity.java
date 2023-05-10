package com.example.mirae.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mirae.Model.Todo;
import com.example.mirae.R;
import com.example.mirae.ViewModel.TodoViewModel;
import com.example.mirae.databinding.ActivityUpdateTodoBinding;

import java.util.Date;

public class UpdateTodoActivity extends AppCompatActivity {
    ActivityUpdateTodoBinding binding;
    String priority="1";
    String stitle, ssubtitle, stodo, spriority;
    Integer iid;
    TodoViewModel todoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateTodoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        iid=getIntent().getIntExtra("id", 0);
        stitle=getIntent().getStringExtra("title");
        ssubtitle=getIntent().getStringExtra("subtitle");
        stodo=getIntent().getStringExtra("todo");
        spriority=getIntent().getStringExtra("priority");

        binding.upTitle.setText(stitle);
        binding.upSubtitle.setText(ssubtitle);
        binding.upTodo.setText(stodo);

        if (spriority.equals("1")){
            binding.greenPriority.setImageResource(R.drawable.baseline_done_24);

        }
        else if (spriority.equals("2")){
            binding.yellowPriority.setImageResource(R.drawable.baseline_done_24);
        }
        else if (spriority.equals("3")){
            binding.redPriority.setImageResource(R.drawable.baseline_done_24);
        }

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

        //when the updateTodoBtn is clicked
        binding.updateTodoBtn.setOnClickListener(v -> {
            String title = binding.upTitle.getText().toString();
            String subtitle = binding.upSubtitle.getText().toString();
            String todo = binding.upTodo.getText().toString();

            updateTodo(title, subtitle, todo);
        });
    }

    private void updateTodo(String title, String subtitle, String todo) {
        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy", date.getTime());

        Todo updateTodo = new Todo();
        updateTodo.id = iid;
        updateTodo.todoTitle = title;
        updateTodo.todoSubtitle = subtitle;
        updateTodo.todoDate = sequence.toString();
        updateTodo.todo = todo;
        updateTodo.todoPriority = priority;

        todoViewModel.updateTodos(updateTodo);

        Toast.makeText(this, "Todo Updated Successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete){

        }

        return true;
    }
}