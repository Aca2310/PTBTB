package com.example.ptbtb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class beranda extends AppCompatActivity {

    Button button_button;
    ArrayList<recyclerview_list> recyclerview_lists;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        button_button= (Button) findViewById(R.id.button);

        button_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), komunitas.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        recyclerview_lists = new ArrayList<recyclerview_list>();
        recyclerview_lists.add(new recyclerview_list(R.drawable.lidahbuaya,"lidah buaya"));
        recyclerview_lists.add(new recyclerview_list(R.drawable.dara,"dara"));
        recyclerview_lists.add(new recyclerview_list(R.drawable.aglonema,"aglonema"));
        recyclerview_lists.add(new recyclerview_list(R.drawable.cactus,"kaktus"));
        recyclerview_lists.add(new recyclerview_list(R.drawable.jandabolong,"janda bolong"));
        recyclerview_lists.add(new recyclerview_list(R.drawable.zinnae,"zinnae"));



        recyclerview_adapter recyclerview_adapter = new recyclerview_adapter(recyclerview_lists, this);
        recyclerView.setAdapter(recyclerview_adapter);


    }
}