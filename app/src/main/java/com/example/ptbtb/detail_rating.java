package com.example.ptbtb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class detail_rating extends AppCompatActivity {
    ArrayList<datarating> listRatings;
    RecyclerView recyclerView;
    AppCompatImageView button_back;
    adapterRating adapterRating;
    ValueEventListener eventListener;

    Button buttonRating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_rating);
        Intent intent = getIntent();

        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(detail_rating.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listRatings = new ArrayList<>();
        // Mengambil data dari Firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ratings");
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listRatings.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    datarating data = snapshot.getValue(datarating.class);
                    listRatings.add(data);
                }

                // Setelah mendapatkan data, inisialisasi adapter dan set ke RecyclerView
                adapterRating = new adapterRating(listRatings, detail_rating.this);
                recyclerView.setAdapter(adapterRating);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });

        buttonRating = (Button) findViewById(R.id.buttonRating);

        buttonRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(detail_rating.this, rating.class);
                startActivity(intent);
            }
        });



}

}