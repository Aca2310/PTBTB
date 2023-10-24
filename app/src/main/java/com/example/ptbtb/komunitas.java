package com.example.ptbtb;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class komunitas extends AppCompatActivity {

    ArrayList<list> lists;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komunitas);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        lists = new ArrayList<list>();
        lists.add(new list(R.drawable.lidahbuaya,"lidah buaya","a"));
        lists.add(new list(R.drawable.dara,"dara","b"));
        lists.add(new list(R.drawable.aglonema,"aglonema","c"));
        lists.add(new list(R.drawable.cactus,"kaktus", "d"));
        lists.add(new list(R.drawable.jandabolong,"janda bolong","e"));
        lists.add(new list(R.drawable.zinnae,"zinnae", "f"));



        adapter adapter = new adapter(lists, this);
        recyclerView.setAdapter(adapter);
    }
}