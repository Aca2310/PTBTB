package com.example.ptbtb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Home extends AppCompatActivity {
    ImageView imageBarter;
    ImageView imageProfile;
    ImageView imageCommunity;
    ImageView imageArtikel;
    ImageView imageHistory;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String username = intent.getStringExtra("username");
        String savedName = intent.getStringExtra("nama");
        String savedAddress = intent.getStringExtra("addres");
        String savedTelp = intent.getStringExtra("telp");


        imageBarter = findViewById(R.id.imageBarter);
        imageBarter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, beranda.class));
            }
        });

        imageProfile = findViewById(R.id.imageProfile);
        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, profile.class);
                intent.putExtra("email", email);
                intent.putExtra("username", username);
                intent.putExtra("nama", savedName);
                intent.putExtra("telp", savedTelp);
                intent.putExtra("addres", savedAddress);
                startActivity(intent);
            }
        });

        imageCommunity = findViewById(R.id.imageCommunity);
        imageCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {startActivity(new Intent(Home.this, komunitas.class));}
        });

        imageArtikel = findViewById(R.id.imageArtikel);

        imageArtikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {startActivity(new Intent(Home.this, artikel.class));}
        });

        imageHistory = findViewById(R.id.imageHistory);
        imageHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {startActivity(new Intent(Home.this, history.class));}
        });

    }
}