package com.example.ptbtb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class tukardengan extends AppCompatActivity {
    ArrayList<list_tukardengan> list;
    RecyclerView recyclerView;
    ValueEventListener eventListener;
    AppCompatImageView button_back;

    Button button_tukar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tukardengan);


        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tukardengan.this, Home.class);
                startActivity(intent);
            }

        });

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String loggedInUserId = currentUser.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Postingan");

        eventListener = reference.orderByChild("user_id").equalTo(loggedInUserId).addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    list_tukardengan data = snapshot.getValue(list_tukardengan.class);
                    list.add(data);
                }
                // Setelah mendapatkan data, inisialisasi adapter dan set ke RecyclerView
                adapterTukardengan adapterTukardengan = new adapterTukardengan(tukardengan.this, list);
                recyclerView.setAdapter(adapterTukardengan);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
    }
}