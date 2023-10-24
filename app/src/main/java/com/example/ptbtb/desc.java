package com.example.ptbtb;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class desc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);

        ImageView imageView = findViewById(R.id.imageView8);
        Intent intent = getIntent();
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