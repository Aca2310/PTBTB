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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class desc extends AppCompatActivity {
    AppCompatImageView button_back;
    Button button_tukar;

    public static String tempDataTitle;
    public static String tempDataDetail;
    public static String tempDataBarter;
    public static String tempDataImage;
    public static String tempUsername;
    public static String tempUserId;
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
        String user_id = intent.getStringExtra("user_id");

        button_tukar = findViewById(R.id.button_tukar);
        button_tukar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempDataTitle = intent.getStringExtra("dataTitle");
                tempDataDetail = intent.getStringExtra("dataDetail");
                tempDataBarter = intent.getStringExtra("dataBarter");
                tempDataImage = intent.getStringExtra("dataImage");
                tempUsername = intent.getStringExtra("username");
                tempUserId = intent.getStringExtra("user_id");

                // Pindah ke aktivitas tukardengan atau lakukan tindakan lain yang diperlukan setelah menyimpan data sementara
                Intent tukarintent = new Intent(desc.this, tukardengan.class);
                startActivity(tukarintent);
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

                // Menambahkan OnClickListener ke TextView untuk membuka profilpenukar
                usernameInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent profileIntent = new Intent(desc.this, ProfilPenukar.class);

                        // Mengirim data pengguna ke profilpenukar
                        profileIntent.putExtra("user_id", user_id);
                        profileIntent.putExtra("nama", savedName);
                        profileIntent.putExtra("username", username);
                        profileIntent.putExtra("telp", savedTelp);
                        profileIntent.putExtra("email", email);
                        profileIntent.putExtra("addres", savedAddress);
                        profileIntent.putExtra("imageUrl", imageUrl);

                        startActivity(profileIntent);                    }
                });
            }
        }
    }
}