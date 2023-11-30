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

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String username = intent.getStringExtra("username");
        String savedName = intent.getStringExtra("nama");
        String savedAddress = intent.getStringExtra("addres");
        String savedTelp = intent.getStringExtra("telp");
        String imageUrl = intent.getStringExtra("imageUrl");

        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(komunitas.this, Home.class);

                intent.putExtra("email", email);
                intent.putExtra("username", username);
                intent.putExtra("nama", savedName);
                intent.putExtra("telp", savedTelp);
                intent.putExtra("addres", savedAddress);
                intent.putExtra("imageUrl", imageUrl);

                startActivity(intent);
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        lists = new ArrayList<list>();
        lists.add(new list(R.drawable.grub28,"Inspirasi Tanaman Hias","Grub ini akan memberikan sensasi kekeluargaan untuk anda. -Dengan wadah yang kondusif......"));
        lists.add(new list(R.drawable.grub29,"Belajar Menanam","Grup ini adalah wadah bagi siapa saja yang ingin belajar dan berbagi ilmu tentang tanaman hias "));
        lists.add(new list(R.drawable.grub30,"KOLEKTOR TANAMAN","TUJUAN dibuat mempererat antar sesama pecinta kolektor tanaman hias indonesia."));

        adapter adapter = new adapter(lists, this);
        recyclerView.setAdapter(adapter);
    }
}