package com.example.mirae;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.mirae.Activity.InsertTodoActivity;
import com.example.mirae.Adapter.TodoAdapter;
import com.example.mirae.Database.TodoDatabase;
import com.example.mirae.Model.Todo;
import com.example.mirae.Repository.TodoRepository;
import com.example.mirae.ViewModel.TodoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newTodoBtn;
    TodoViewModel todoViewModel;
    TodoAdapter adapter;
    TextView nofilter, hightolow, lowtohigh;
    RecyclerView todoRecycler;
    List<Todo> filterAllTodoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newTodoBtn = findViewById(R.id.newTodoBtn);
        todoRecycler = findViewById(R.id.todoRecycler);

        nofilter = findViewById(R.id.nofilter);
        hightolow = findViewById(R.id.hightolow);
        lowtohigh = findViewById(R.id.lowtohigh);

        //to make sure 'no filter' is selected when the user first opens the screen
        nofilter.setBackgroundResource(R.drawable.filter_selected_shape);

        //the border appears on the sort option that is clicked
        nofilter.setOnClickListener(v -> {
            loadData(0);
            nofilter.setBackgroundResource(R.drawable.filter_selected_shape);
            hightolow.setBackgroundResource(R.drawable.filter_un_shape);
            lowtohigh.setBackgroundResource(R.drawable.filter_un_shape);
        });

        hightolow.setOnClickListener(v -> {
            loadData(1);
            hightolow.setBackgroundResource(R.drawable.filter_selected_shape);
            nofilter.setBackgroundResource(R.drawable.filter_un_shape);
            lowtohigh.setBackgroundResource(R.drawable.filter_un_shape);
        });

        lowtohigh.setOnClickListener(v -> {
            loadData(2);
            lowtohigh.setBackgroundResource(R.drawable.filter_selected_shape);
            nofilter.setBackgroundResource(R.drawable.filter_un_shape);
            hightolow.setBackgroundResource(R.drawable.filter_un_shape);
        });

        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);

        newTodoBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, InsertTodoActivity.class));
        });

        todoViewModel.getAllTodo.observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todo) {
                setAdapter(todo);
                filterAllTodoList = todo;
            }
        });
    }

    private void loadData(int i) {
        if (i==0){
            todoViewModel.getAllTodo.observe(this, new Observer<List<Todo>>() {
                @Override
                public void onChanged(List<Todo> todo) {
                    setAdapter(todo);
                    filterAllTodoList = todo;
                }
            });
        } else if ((i==1)) {
            todoViewModel.hightolow.observe(this, new Observer<List<Todo>>() {
                @Override
                public void onChanged(List<Todo> todo) {
                    setAdapter(todo);
                    filterAllTodoList = todo;
                }
            });
        } else if (i==2) {
            todoViewModel.lowtohigh.observe(this, new Observer<List<Todo>>() {
                @Override
                public void onChanged(List<Todo> todo) {
                    setAdapter(todo);
                    filterAllTodoList = todo;
                }
            });
        }
    }

    private void setAdapter(List<Todo> todo) {
        todoRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TodoAdapter(MainActivity.this, todo);
        todoRecycler.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_todo, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search your Todo task here!");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                todoFilter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void todoFilter(String newText) {
        ArrayList<Todo> filterTodo = new ArrayList<>();

        for (Todo todo:this.filterAllTodoList){
            if (todo.todoTitle.contains(newText) || todo.todoSubtitle.contains(newText)){
                filterTodo.add(todo);
            }
        }
        this.adapter.searchTodo(filterTodo);
    }
}