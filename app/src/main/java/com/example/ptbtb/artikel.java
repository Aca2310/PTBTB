package com.example.ptbtb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class artikel extends AppCompatActivity {
    ArrayList<listArtikel> lists;
    RecyclerView recyclerView;

    AppCompatImageView button_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);

        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(artikel.this, Home.class));
            }
        });

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