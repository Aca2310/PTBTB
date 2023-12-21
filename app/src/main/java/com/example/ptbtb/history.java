package com.example.ptbtb;

import static com.example.ptbtb.R.id.button_back;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

public class history extends AppCompatActivity {

    ArrayList<HistoryData> list_histories;
    RecyclerView recyclerView;
    AppCompatImageView button_back;

    ValueEventListener eventListener;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference historyref = database.getReference("history");
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(history.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list_histories = new ArrayList<HistoryData>();
        String loggedInUserId = currentUser.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("history");

        eventListener = databaseReference.orderByChild("user_idPenerima").equalTo(loggedInUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list_histories.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HistoryData data = snapshot.getValue(HistoryData.class);
                    data.setKey(snapshot.getKey());
                    list_histories.add(data);
                }

                databaseReference.orderByChild("user_idTukar")
                        .equalTo(loggedInUserId)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshotTukar) {
                                for (DataSnapshot snapshot : dataSnapshotTukar.getChildren()) {
                                    HistoryData data = snapshot.getValue(HistoryData.class);
                                    data.setKey(snapshot.getKey());
                                    list_histories.add(data);
                                }

                                // Sekarang, list_histories berisi data dari user_idPenerima dan user_idTukar
                                adapterhistory adapterhistory = new adapterhistory(list_histories, history.this);
                                recyclerView.setAdapter(adapterhistory);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                // Tangani kesalahan
                            }
                        });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Tangani kesalahan
            }
        });
    }
}