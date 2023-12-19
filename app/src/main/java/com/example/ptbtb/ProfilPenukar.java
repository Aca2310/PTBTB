package com.example.ptbtb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfilPenukar extends AppCompatActivity {

    ArrayList<list_profilpenukar> list_profilpenukar;
    ArrayList<list_profilpenukar> list_postingan;
    RecyclerView recyclerView;
    ValueEventListener eventListenerUsers;
    ValueEventListener eventListenerPostingan;
    DatabaseReference databaseReferenceUsers;
    DatabaseReference databaseReferencePostingan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_penukar);

        Intent intent = getIntent();
        String userIdToShow = intent.getStringExtra("user_id");

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        list_profilpenukar = new ArrayList<>();
        list_postingan = new ArrayList<>();

        // Mengambil data dari Firebase berdasarkan user_id di "users"
        databaseReferenceUsers = FirebaseDatabase.getInstance().getReference("users");
        eventListenerUsers = databaseReferenceUsers.orderByChild("user_id").equalTo(userIdToShow).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list_profilpenukar.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    list_profilpenukar data = snapshot.getValue(list_profilpenukar.class);
                    list_profilpenukar.add(data);
                }

                if (!list_profilpenukar.isEmpty()) {
                    list_profilpenukar ProfilPenukar = list_profilpenukar.get(0);
                    TextView namaUserTextView = findViewById(R.id.nama_user);
                    TextView usernameTextView = findViewById(R.id.usernamepp);

                    namaUserTextView.setText(ProfilPenukar.getNama());
                    usernameTextView.setText(ProfilPenukar.getUsername());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Tangani kesalahan
            }
        });

        // Mengambil data dari Firebase berdasarkan user_id di "postingan"
        databaseReferencePostingan = FirebaseDatabase.getInstance().getReference("Postingan");
        eventListenerPostingan = databaseReferencePostingan.orderByChild("user_id").equalTo(userIdToShow).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list_postingan.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Perubahan tipe data menjadi list_postingan
                    list_postingan data = snapshot.getValue(list_postingan.class);
                    list_postingan.add(data);
                }

                // Perubahan nama adapter sesuai dengan adapter yang baru dibuat
                adapterProfilPenukar adapter = new adapterProfilPenukar(list_postingan, ProfilPenukar.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Tangani kesalahan
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Hapus listener saat activity dihancurkan
        if (eventListenerUsers != null) {
            databaseReferenceUsers.removeEventListener(eventListenerUsers);
        }
        if (eventListenerPostingan != null) {
            databaseReferencePostingan.removeEventListener(eventListenerPostingan);
        }
    }
}
