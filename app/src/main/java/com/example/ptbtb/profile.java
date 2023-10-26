package com.example.ptbtb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class profile extends AppCompatActivity {

    ArrayList<list_profile> list_profiles;
    RecyclerView recyclerView;

    Button button_profile;
    AppCompatImageView button_back;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(profile.this, Home.class));
            }
        });

        button_profile = (Button) findViewById(R.id.button_profile);

        button_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), edit_profile.class);
                startActivity(intent);
            }
        });

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), newpost.class);
                startActivity(intent);
            }
        });


        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        list_profiles = new ArrayList<list_profile>();
        list_profiles.add(new list_profile(R.drawable.lidahbuaya,"lidah buaya","b","d","a","g"));
        list_profiles.add(new list_profile(R.drawable.dara,"dara","b","d","a","g"));
        list_profiles.add(new list_profile(R.drawable.aglonema,"aglonema", "Pasta", "Maggi", "Cake", "Pancake" ));


        adapterProfile adapterProfile = new adapterProfile(list_profiles, this);
        recyclerView.setAdapter(adapterProfile);
    }
}