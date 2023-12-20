package com.example.ptbtb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class notif extends AppCompatActivity {

    ArrayList<TawarData> listTawardata;
    RecyclerView recyclerView;
    AppCompatImageView button_back;

    ValueEventListener eventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference tawarRef = database.getReference("tawar");

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(notif.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listTawardata = new ArrayList<TawarData>();
        String loggedInUserId = currentUser.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("tawar");

        eventListener = databaseReference.orderByChild("user_idPenerima").equalTo(loggedInUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listTawardata.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    TawarData data = snapshot.getValue(TawarData.class);
                    data.setKey(snapshot.getKey());
                    listTawardata.add(data);
                }

                adapter adapter = new adapter(listTawardata, notif.this);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }
}