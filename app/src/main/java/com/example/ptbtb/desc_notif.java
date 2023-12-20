package com.example.ptbtb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class desc_notif extends AppCompatActivity {

    AppCompatImageView button_back;
    Button terimaButton;
    String key = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_notif);

        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(desc_notif.this, notif.class);
                startActivity(intent);
                finish();
            }
        });

        ImageView imagePenerima = findViewById(R.id.imageView4);
        TextView titlePenerima = findViewById(R.id.namaTumbuhan);
        TextView detailPenerima = findViewById(R.id.textView);
        TextView locationPenerima = findViewById(R.id.textView15);
        ImageView imageViewTukar = findViewById(R.id.namaTumbuhan2);
        TextView titleTukar = findViewById(R.id.namaTumbuhan1);
        TextView detailTukar = findViewById(R.id.textView13);
        TextView locationTukar = findViewById(R.id.lokasi2);
        TextView usernameText = findViewById(R.id.usernameNotif);

        Intent intent = getIntent();
        if (intent != null) {
            String DatatitlePenerima = intent.getStringExtra("dataTitlePenerima");
            String DatadetailPenerima = intent.getStringExtra("dataDetailPenerima");
            String DatalocationPenerima = intent.getStringExtra("dataLocationPenerima");
            String dataImagePenerima = intent.getStringExtra("dataImagePenerima");
            String title = intent.getStringExtra("dataTitleTukar");
            String detail = intent.getStringExtra("dataDetailTukar");
            String location = intent.getStringExtra("dataLocationTukar");
            String usernamePenerima = intent.getStringExtra("usernameTukar");
            String dataImage = intent.getStringExtra("dataImageTukar");
            key = intent.getStringExtra("Key");

            if (dataImage != null) {
                Picasso.get().load(dataImage).into(imageViewTukar);
            }
            if (dataImagePenerima != null) {
                Picasso.get().load(dataImagePenerima).into(imagePenerima);
            }

            if (title != null) {
                titleTukar.setText(title);
            }
            if (DatatitlePenerima != null) {
                titlePenerima.setText(DatatitlePenerima);
            }
            if (DatadetailPenerima != null) {
                detailPenerima.setText(DatadetailPenerima);
            }
            if (detail != null) {
                detailTukar.setText(detail);
            }
            if (DatalocationPenerima != null) {
                locationPenerima.setText(DatalocationPenerima);
            }
            if (location != null) {
                locationTukar.setText(location);
            }
            if (usernamePenerima != null) {
                usernameText.setText("@" + usernamePenerima);
            }

        }
        
        terimaButton = findViewById(R.id.terima);
        terimaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStatus();
            }

            private void updateStatus() {
                if (key != null){
                    Task<Void> reference = FirebaseDatabase.getInstance().getReference("tawar").child(key).child("status").setValue("diterima");;

                    Toast.makeText(desc_notif.this, "Status Tawaran Diterima", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
