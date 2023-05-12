package com.example.mirae.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mirae.Activity.UpdateTodoActivity;
import com.example.mirae.MainActivity;
import com.example.mirae.Model.Todo;
import com.example.mirae.R;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    MainActivity mainActivity;
    List<Todo> todo;
    List<Todo> allTodoItem;

    public TodoAdapter(MainActivity mainActivity, List<Todo> todo) {
        this.mainActivity=mainActivity;
        this.todo=todo;
        allTodoItem=new ArrayList<>(todo);
    }

    public void searchTodo(List<Todo> filteredTodo){
        this.todo=filteredTodo;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TodoViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_todo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todos = todo.get(position);

        if(todos.todoPriority.equals("1")){
            holder.todoPriority.setBackgroundResource(R.drawable.green_shape);
        } else if (todos.todoPriority.equals("2")) {
            holder.todoPriority.setBackgroundResource(R.drawable.yellow_shape);
        } else if(todos.todoPriority.equals("3")){
            holder.todoPriority.setBackgroundResource(R.drawable.red_shape);
        }

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
                intent.putExtra("todo", todos.todo);
                intent.putExtra("priority", todos.todoPriority);

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
        View todoPriority;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.todoTitle);
            subtitle = itemView.findViewById(R.id.todoSubtitle);
            todoDate = itemView.findViewById(R.id.todoDate);
            todoPriority = itemView.findViewById(R.id.todoPriority);
        }
    }
}
