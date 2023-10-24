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
        lists.add(new list(R.drawable.grub28,"Grub ini akan memberikan sensasi kekeluargaan untuk anda. -Dengan wadah yang kondusif","Inspirasi Tanaman Hias"));
        lists.add(new list(R.drawable.grub29,"Grup ini adalah wadah bagi siapa saja yang ingin belajar dan berbagi ilmu tentang tanaman hias ","Belajar Menanam"));
        lists.add(new list(R.drawable.grub30,"TUJUAN dibuat mempererat antar sesama pecinta kolektor tanaman hias indonesia.","KOLEKTOR TANAMAN"));


        adapter adapter = new adapter(lists, this);
        recyclerView.setAdapter(adapter);
    }
}