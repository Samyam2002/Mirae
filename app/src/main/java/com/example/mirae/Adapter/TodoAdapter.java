package com.example.mirae.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mirae.Activity.UpdateTodoActivity;
import com.example.mirae.MainActivity;
import com.example.mirae.Model.Todo;
import com.example.mirae.R;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    MainActivity mainActivity;
    List<Todo> todo;

    public TodoAdapter(MainActivity mainActivity, List<Todo> todo) {
        this.mainActivity=mainActivity;
        this.todo=todo;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TodoViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_todo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todos = todo.get(position);
        holder.title.setText(todos.todoTitle);
        holder.subtitle.setText(todos.todoSubtitle);
        holder.todoDate.setText(todos.todoDate);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity, UpdateTodoActivity.class);
                intent.putExtra("id", todos.id);
                intent.putExtra("title", todos.todoTitle);
                intent.putExtra("subtitle", todos.todoSubtitle);

                mainActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return todo.size();
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder {

        TextView title, subtitle, todoDate;
        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.todoTitle);
            subtitle = itemView.findViewById(R.id.todoSubtitle);
            todoDate = itemView.findViewById(R.id.todoDate);
        }
    }
}
