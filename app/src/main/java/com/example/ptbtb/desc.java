package com.example.ptbtb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

public class desc extends AppCompatActivity {
    AppCompatImageView button_back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String username = intent.getStringExtra("username");
        String savedName = intent.getStringExtra("nama");
        String savedAddress = intent.getStringExtra("addres");
        String savedTelp = intent.getStringExtra("telp");
        String imageUrl = intent.getStringExtra("imageUrl");

        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(desc.this, beranda.class);

                intent.putExtra("email", email);
                intent.putExtra("username", username);
                intent.putExtra("nama", savedName);
                intent.putExtra("telp", savedTelp);
                intent.putExtra("addres", savedAddress);
                intent.putExtra("imageUrl", imageUrl);

                startActivity(intent);
                finish();
            }
        });

        ImageView imageView = findViewById(R.id.imageView8);
        TextView titleTextView = findViewById(R.id.judul);
        TextView detailTextView = findViewById(R.id.textView6); // Teks detail
        TextView locationTextView = findViewById(R.id.textView7); // Teks lokasi
        TextView barterInfoTextView = findViewById(R.id.textView8); // Teks informasi barter

        if (intent != null) {
            int imageId = intent.getIntExtra("image", -1);
            String title = intent.getStringExtra("title");
            String detail = intent.getStringExtra("detail");
            String location = intent.getStringExtra("location");
            String barterInfo = intent.getStringExtra("barterInfo");

            if (imageId != -1) {
                imageView.setImageResource(imageId);
            }
            if (title != null) {
                titleTextView.setText(title);
            }
            if (detail != null) {
                detailTextView.setText(detail);
            }
            if (location != null) {
                locationTextView.setText(location);
            }
            if (barterInfo != null) {
                barterInfoTextView.setText(barterInfo);
            }
        }
    }
    }