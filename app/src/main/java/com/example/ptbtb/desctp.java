package com.example.ptbtb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class desctp extends AppCompatActivity {
    AppCompatImageView button_back;
    Button button_edit;
     String username, user_id;

    String key = "";
    String imageURL = "";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desctp);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
       String username1 = intent.getStringExtra("username");
        String user_id1 = intent.getStringExtra("user_id");
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
                intent.putExtra("username", username1);
                intent.putExtra("nama", savedName);
                intent.putExtra("user_id", user_id1);
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
      //  TextView locationTextView = findViewById(R.id.textViewLocation);
        TextView barterInfoTextView = findViewById(R.id.textViewBarter);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailTextView.setText(bundle.getString("dataDetail"));
            titleTextView.setText(bundle.getString("dataTitle"));
            barterInfoTextView.setText(bundle.getString("dataBarter"));
            username = bundle.getString("username");
            user_id = bundle.getString("user_id");
            key = bundle.getString("Key");
            imageURL = bundle.getString("dataImage");
            Picasso.get().load(bundle.getString("dataImage")).into(imageView);
        }
        button_edit = findViewById(R.id.button_edit);
        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(desctp.this, edit_tanaman.class)
                        .putExtra("dataTitle",  titleTextView.getText().toString())
                        .putExtra("dataDetail", detailTextView.getText().toString())
                        .putExtra("dataBarter",  barterInfoTextView.getText().toString())
                        .putExtra("dataImage", imageURL)
                        .putExtra("username", username)
                        .putExtra("user_id", user_id)
                        .putExtra("Key", key);
                startActivity(intent);
            }
        });



    }}