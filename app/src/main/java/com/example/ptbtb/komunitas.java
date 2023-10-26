package com.example.ptbtb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class komunitas extends AppCompatActivity {

    ArrayList<list> lists;
    RecyclerView recyclerView;
    AppCompatImageView button_back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komunitas);

        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(komunitas.this, Home.class));
            }
        });

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        lists = new ArrayList<list>();
        lists.add(new list(R.drawable.grub28,"aa","a"));
        lists.add(new list(R.drawable.grub29,"dara","b"));
        lists.add(new list(R.drawable.grub30,"aglonema","c"));

        adapter adapter = new adapter(lists, this);
        recyclerView.setAdapter(adapter);
    }
}