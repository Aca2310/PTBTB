package com.example.ptbtb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class desctp extends AppCompatActivity {
    AppCompatImageView button_back;
    Button button_edit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desctp);

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
                Intent intent = new Intent(desctp.this, profile.class);

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

        ImageView imageView = findViewById(R.id.imageViewD);
        TextView titleTextView = findViewById(R.id.judul);
        TextView detailTextView = findViewById(R.id.textViewDetail);
        TextView locationTextView = findViewById(R.id.textViewLocation);
        TextView barterInfoTextView = findViewById(R.id.textViewBarter);


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
        }

        button_edit = (Button) findViewById(R.id.button_edit);

        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), edit_tanaman.class);
                startActivity(intent);
            }
        });
    }
}