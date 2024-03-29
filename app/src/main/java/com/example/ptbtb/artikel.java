package com.example.ptbtb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class artikel extends AppCompatActivity {

    private ArrayList<listArtikel> list;
    private RecyclerView recyclerView;
    ValueEventListener eventListener;
    AppCompatImageView button_back;
    SearchView searchView;
    adapterArtikel adapterArtikel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);

        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(artikel.this, Home.class);

                startActivity(intent);
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("artikel");

        eventListener = reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    listArtikel data = snapshot.getValue(listArtikel.class);
                    data.setKey(snapshot.getKey());
                    list.add(data);
                }

                // Setelah mendapatkan data, inisialisasi adapter dan set ke RecyclerView
                adapterArtikel = new adapterArtikel(artikel.this, list);
                recyclerView.setAdapter(adapterArtikel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return false;
            }
        });



    }
    public void searchList(String text){
        ArrayList<listArtikel> searchList = new ArrayList<>();
        for (listArtikel listArtikel: list){
            if (listArtikel.getJudul().toLowerCase().contains(text.toLowerCase())){
                searchList.add(listArtikel);
            }
        }
        adapterArtikel.searchDataList(searchList);
    }


}

