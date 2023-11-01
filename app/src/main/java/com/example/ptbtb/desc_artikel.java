package com.example.ptbtb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class desc_artikel extends AppCompatActivity {
    AppCompatImageView button_back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_artikel);

        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(desc_artikel.this, artikel.class));

            }
        });

        ImageView imageView = findViewById(R.id.imageArtikel);
        TextView titleTextView = findViewById(R.id.judul);  // Gunakan ID yang sesuai

        Intent intent = getIntent();
        if (intent != null) {
            int imageId = intent.getIntExtra("image", -1);  // Gunakan kunci yang benar
            String title = intent.getStringExtra("title");    // Gunakan kunci yang benar

            if (imageId != -1) {
                imageView.setImageResource(imageId);
            }
            if (title != null) {
                titleTextView.setText(title);
            }
        }
    }
}