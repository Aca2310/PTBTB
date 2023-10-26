package com.example.ptbtb;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

public class desctp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desctp);
        ImageView imageView = findViewById(R.id.imageViewD);
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