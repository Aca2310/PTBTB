package com.example.ptbtb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class desctp extends AppCompatActivity {
    AppCompatImageView button_back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desctp);
        ImageView imageView = findViewById(R.id.imageViewD);
        Intent intent = getIntent();

        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(desctp.this, profile.class));
            }
        });

        if (intent != null) {
            int imageId = intent.getIntExtra("image", -1);
            String title = intent.getStringExtra("title");

            if (imageId != -1) {
                imageView.setImageResource(imageId);
            }
            if (title != null) {
                TextView titleTextView = findViewById(R.id.judul);
                titleTextView.setText(title);
            }
        }
    }
}