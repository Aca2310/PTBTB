package com.example.ptbtb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.squareup.picasso.Picasso;

public class desc extends AppCompatActivity {
    AppCompatImageView button_back;
    Button button_tukar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        button_tukar = findViewById(R.id.button_tukar);
        button_tukar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(desc.this, tukardengan.class);
                startActivity(intent);
                finish();



            }
        });


        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(desc.this, beranda.class);


                startActivity(intent);
                finish();
            }
        });

        ImageView imageView = findViewById(R.id.imageView8);
        TextView titleTextView = findViewById(R.id.judul);
        TextView detailTextView = findViewById(R.id.textView6);
        TextView locationTextView = findViewById(R.id.textView7);
        TextView barterInfoTextView = findViewById(R.id.textView8);
        TextView usernameInfo = findViewById(R.id.textView10);


        if (intent != null) {
            String title = intent.getStringExtra("dataTitle");
            String detail = intent.getStringExtra("dataDetail");
            String location = intent.getStringExtra("dataLocation");
            String barterInfo = intent.getStringExtra("dataBarter");
            String dataImage = intent.getStringExtra("dataImage");

            if (dataImage != null) {
                Picasso.get().load(dataImage).into(imageView);
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
            if (username != null) {
                usernameInfo.setText("@"+username);
            }
        }
    }
}