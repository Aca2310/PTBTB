package com.example.ptbtb;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class pages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pages);

        int pageId = getIntent().getIntExtra("id", 0);

        TextView textPageId = findViewById(R.id.textPageId);
        textPageId.setText("PAGE : "+ pageId);
    }
}