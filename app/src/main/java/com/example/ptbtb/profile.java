package com.example.ptbtb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;

public class profile extends AppCompatActivity {

    ArrayList<recyclerview_list> recyclerview_lists;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        recyclerview_lists = new ArrayList<recyclerview_list>();
        recyclerview_lists.add(new recyclerview_list(R.drawable.lidahbuaya,"lidah buaya"));
        recyclerview_lists.add(new recyclerview_list(R.drawable.dara,"dara"));
        recyclerview_lists.add(new recyclerview_list(R.drawable.aglonema,"aglonema"));




        recyclerview_adapter recyclerview_adapter = new recyclerview_adapter(recyclerview_lists, this);
        recyclerView.setAdapter(recyclerview_adapter);
    }
}