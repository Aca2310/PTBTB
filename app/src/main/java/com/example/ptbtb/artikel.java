package com.example.ptbtb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class artikel extends AppCompatActivity {
    ArrayList<listArtikel> lists;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        lists = new ArrayList<listArtikel>();
        lists.add(new listArtikel(R.drawable.bawang,"bawang","a"));
        lists.add(new listArtikel(R.drawable.jeruk,"jeruk","b"));




        adapterArtikel adapter = new adapterArtikel(lists, this);
        recyclerView.setAdapter(adapter);

    }
}