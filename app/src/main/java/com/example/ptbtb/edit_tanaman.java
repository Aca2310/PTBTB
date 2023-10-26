package com.example.ptbtb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;


public class edit_tanaman extends AppCompatActivity {

    AppCompatImageView button_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tanaman);

        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(view -> startActivity(new Intent(edit_tanaman.this, desctp.class)));
    }
}