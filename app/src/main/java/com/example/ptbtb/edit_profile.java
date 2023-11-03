package com.example.ptbtb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class edit_profile extends AppCompatActivity {
    AppCompatImageView button_back;


    Button save_edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        button_back = findViewById(R.id.button_back);

        Intent intent = getIntent();
        String nama = intent.getStringExtra("username");
        String username = intent.getStringExtra("email");


        TextView textViewName = findViewById(R.id.nama_edit);
        TextView textViewUsername = findViewById(R.id.username_edit);


        textViewName.setText(username);
        textViewUsername.setText(nama);

        save_edit = (Button) findViewById(R.id.save_edit);


        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(edit_profile.this, profile.class));
            }
        });
    }

}