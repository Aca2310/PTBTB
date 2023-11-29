package com.example.ptbtb;

import static com.example.ptbtb.R.id.button_back;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class history extends AppCompatActivity {

    ArrayList<list_history> lists;
    RecyclerView recyclerView;
    AppCompatImageView button_back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

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
                Intent intent = new Intent(history.this, Home.class);

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

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        lists = new ArrayList<list_history>();
        lists.add(new list_history(R.drawable.dara,"20 Oktober 2023", "Tapak Dara"));
        lists.add(new list_history(R.drawable.cactus,"21 Oktober 2023", "Cactus"));
        lists.add(new list_history(R.drawable.aglonema,"22 Oktober 2023", "Aglonema"));


        adapterhistory adapterhistory = new adapterhistory(lists, this);
        recyclerView.setAdapter(adapterhistory);
    }
}