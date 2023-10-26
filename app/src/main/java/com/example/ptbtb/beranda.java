package com.example.ptbtb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class beranda extends AppCompatActivity {

    ArrayList<recyclerview_list> recyclerview_lists;
    RecyclerView recyclerView;

   AppCompatImageView button_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(beranda.this, Home.class));
            }
        });

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        recyclerview_lists = new ArrayList<recyclerview_list>();
        recyclerview_lists.add(new recyclerview_list(R.drawable.jandabolong,"janda bolong","Daun hijau tua\n" +
                "Berukuran 30-35cm.\n" +
                "Kondisi : baru\n" +
                "Penyiraman 1x sehari\n" +
                "Pemupukan 1x sebulan", "Padang, Sumatera Barat","Silver Monstera\n" +
                "Monstera Variegata"));
        recyclerview_lists.add(new recyclerview_list(R.drawable.lidahbuaya,"lidah buaya","d","Padang, Sumatera Barat","g"));
        recyclerview_lists.add(new recyclerview_list(R.drawable.dara,"dara","d","a","g"));
        recyclerview_lists.add(new recyclerview_list(R.drawable.aglonema,"aglonema",  "Maggi", "Padang, Sumatera Barat", "Pancake" ));
        recyclerview_lists.add(new recyclerview_list(R.drawable.cactus,"kaktus",  "kaktus hijau", "45 mins","10 mins"));
        recyclerview_lists.add(new recyclerview_list(R.drawable.zinnae,"zinnae", "Maggi", "Cake", "Pancake"));



        recyclerview_adapter recyclerview_adapter = new recyclerview_adapter(recyclerview_lists, this);
        recyclerView.setAdapter(recyclerview_adapter);


    }
}