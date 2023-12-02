package com.example.ptbtb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class profile extends AppCompatActivity {

    ArrayList<list_profile> list_profiles;
    RecyclerView recyclerView;
    private AlertDialog dialog;

    Button button_profile;
    ValueEventListener eventListener;
    Button button_ubah;
    AppCompatImageView button_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ImageView imageViewProfile = findViewById(R.id.imageView8);

        Intent intent = getIntent();
        String nama = intent.getStringExtra("nama");
        String username = intent.getStringExtra("username");
        String telp = intent.getStringExtra("telp");
        String email = intent.getStringExtra("email");
        String addres = intent.getStringExtra("addres");
        String imageUrl = intent.getStringExtra("imageUrl");

        Picasso.get().load(imageUrl).into(imageViewProfile);

        TextView etnama = findViewById(R.id.nama_user);
        TextView textViewUsername = findViewById(R.id.usernamenya);


        etnama.setText(nama);
        textViewUsername.setText("@" + username);


        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, Home.class);
                intent.putExtra("nama", nama);
                intent.putExtra("username", username);
                intent.putExtra("telp", telp);
                intent.putExtra("email", email);
                intent.putExtra("addres", addres);
                intent.putExtra("imageUrl", imageUrl);
                startActivity(intent);
            }
        });

        button_profile = (Button) findViewById(R.id.button_profile);

        button_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, edit_profile.class);

                intent.putExtra("nama", nama);
                intent.putExtra("username", username);
                intent.putExtra("telp", telp);
                intent.putExtra("email", email);
                intent.putExtra("addres", addres);
                intent.putExtra("imageUrl", imageUrl);
                startActivity(intent);
            }
        });

        Button button_ubah = findViewById(R.id.ubahPassword);
        button_ubah.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(profile.this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_password, null);
                EditText pass1 = dialogView.findViewById(R.id.passwordIni);
                EditText pass2 = dialogView.findViewById(R.id.passwordBaru);
                EditText pass3 = dialogView.findViewById(R.id.passwordBaru2);
                builder.setView(dialogView);

                Button ubahpassword = dialogView.findViewById(R.id.btnReset);  // Menggunakan dialogView

                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                ubahpassword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String passbaru = pass2.getText().toString().trim();
                        String passconf = pass3.getText().toString().trim();
                        String pass = pass1.getText().toString().trim();

                        if (TextUtils.isEmpty(pass)) {
                            Toast.makeText(profile.this, "Enter your current password...", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (passbaru.length() < 6) {
                            Toast.makeText(profile.this, "password harus minimal 6 karater", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (passbaru.equals(passconf)) {
                            ubahpassword(pass, passbaru, passconf);
                        } else {
                            Toast.makeText(profile.this, "Konfirmasi password baru tidak cocok", Toast.LENGTH_SHORT).show();
                        }

                        ubahpassword(pass, passbaru, passconf);

                    }
                });

            }
        });


        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), newpost.class);
                startActivity(intent);
            }
        });


        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        list_profiles = new ArrayList<>();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String loggedInUserId = currentUser.getUid();

// Mengambil data dari Firebase berdasarkan user_id
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Postingan");
        eventListener = databaseReference.orderByChild("user_id").equalTo(loggedInUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list_profiles.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    list_profile data = snapshot.getValue(list_profile.class);
                    list_profiles.add(data);
                }

                // Setelah mendapatkan data, inisialisasi adapter dan set ke RecyclerView
                adapterProfile adapterProfile = new adapterProfile(list_profiles, profile.this);
                recyclerView.setAdapter(adapterProfile);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }

    private void ubahpassword(String pass, String passbaru, String passconf) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), pass);
            user.reauthenticate(credential)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                if (passbaru.equals(passconf)) {
                                    user.updatePassword(passbaru)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(profile.this, "Password berhasil diubah", Toast.LENGTH_SHORT).show();
                                                        dialog.dismiss();
                                                    } else {
                                                        Toast.makeText(profile.this, "Gagal mengubah password", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                } else {
                                    Toast.makeText(profile.this, "Konfirmasi password baru tidak cocok", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(profile.this, "Password saat ini salah", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(profile.this, "Pengguna belum login", Toast.LENGTH_SHORT).show();
        }
    }


}