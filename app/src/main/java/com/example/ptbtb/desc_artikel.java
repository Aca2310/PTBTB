package com.example.ptbtb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class desc_artikel extends AppCompatActivity {
    AppCompatImageView button_back;
    String key ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_artikel);

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
                Intent intent = new Intent(desc_artikel.this, artikel.class);

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

        ImageView imageView = findViewById(R.id.imageArtikel);
        TextView titleTextView = findViewById(R.id.nama_artikel);  // Gunakan ID yang sesuai
        TextView descTextView = findViewById(R.id.tentang);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            titleTextView.setText(bundle.getString("judul"));
            descTextView.setText(bundle.getString("desc"));
            key = bundle.getString("Key");
            Picasso.get().load(bundle.getString("imageArtikel")).into(imageView);
        }else{
            Toast.makeText(desc_artikel.this, "null", Toast.LENGTH_SHORT).show();
        }
    }
}