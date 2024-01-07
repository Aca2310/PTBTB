package com.example.ptbtb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Home extends AppCompatActivity {
    ImageView imageBarter;
    ImageView imageProfile;
    ImageView imageNotif;
    ImageView imageArtikel;
    ImageView imageHistory;
    ImageView imageLogout;
    ImageView imageRate;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageRate = findViewById(R.id.imageRate);
        imageRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, rating.class);
                startActivity(intent);
            }
        });

        imageBarter = findViewById(R.id.imageBarter);
        imageBarter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, beranda.class);
                startActivity(intent);
            }
        });

        imageProfile = findViewById(R.id.imageProfile);
        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, profile.class);

                startActivity(intent);
            }
        });

        imageNotif = findViewById(R.id.imageNotif);
        imageNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, notif.class);
                startActivity(intent);
            }
        });

        imageArtikel = findViewById(R.id.imgArtikel);

        imageArtikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,artikel.class);
                startActivity(intent);
            }
        });

        imageHistory = findViewById(R.id.imageHistory);
        imageHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, history.class);
                startActivity(intent);
            }
        });

        imageLogout = findViewById(R.id.imageLogout);
        imageLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Login.class);
                showLogoutConfirmationDiaglog();

            }
        });


    }

    private void showLogoutConfirmationDiaglog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Jika pengguna menekan tombol "Ya", panggil metode logout
                        logout();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Jika pengguna menekan tombol "Tidak", tutup dialog
                        dialogInterface.dismiss();
                    }
                });

        // Tampilkan dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Metode untuk melakukan logout
    private void logout() {
        // Tambahkan logika untuk melakukan logout di sini

        // Contoh: Kembali ke activity login
        Intent intent = new Intent(Home.this, Login.class);
        startActivity(intent);
        finish();  // Menutup activity beranda setelah logout
    }
    }
