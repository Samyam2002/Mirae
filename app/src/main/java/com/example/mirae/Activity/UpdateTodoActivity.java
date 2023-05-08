package com.example.mirae.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mirae.R;
import com.example.mirae.databinding.ActivityUpdateTodoBinding;

public class UpdateTodoActivity extends AppCompatActivity {
    ActivityUpdateTodoBinding binding;
    String sid, stitle, ssubtitle, stodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateTodoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sid=getIntent().getStringExtra("id");
        stitle=getIntent().getStringExtra("title");
        ssubtitle=getIntent().getStringExtra("subtitle");
        stodo=getIntent().getStringExtra("todo");

        binding.upTitle.setText(stitle);
        binding.upSubtitle.setText(ssubtitle);
        binding.upData.setText(stodo);

        binding.updateTodoBtn.setOnClickListener(v -> {

        });
    }
}