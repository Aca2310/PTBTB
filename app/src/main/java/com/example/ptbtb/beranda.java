package com.example.ptbtb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class beranda extends AppCompatActivity {

    ArrayList<recyclerview_list> recyclerview_lists;
    RecyclerView recyclerView;

    SearchView searchView;
    recyclerview_adapter recyclerview_adapter;
    ValueEventListener eventListener;

    AppCompatImageView button_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);


        Intent intent = getIntent();

        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(beranda.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        recyclerview_lists = new ArrayList<>();

        // Mengambil data dari Firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Postingan");
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                recyclerview_lists.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    recyclerview_list data = snapshot.getValue(recyclerview_list.class);
                    recyclerview_lists.add(data);
                }

                // Setelah mendapatkan data, inisialisasi adapter dan set ke RecyclerView
                recyclerview_adapter = new recyclerview_adapter(recyclerview_lists, beranda.this);
                recyclerView.setAdapter(recyclerview_adapter);
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
        ArrayList<recyclerview_list>  searchList = new ArrayList<>();
        for (recyclerview_list recyclerview_list: recyclerview_lists){
            if (recyclerview_list.getDataTitle().toLowerCase().contains(text.toLowerCase())){
                searchList.add(recyclerview_list);
            }
        }
        recyclerview_adapter.searchDataList(searchList);
    }
}
