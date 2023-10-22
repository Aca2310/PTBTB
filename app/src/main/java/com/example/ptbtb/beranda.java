package com.example.ptbtb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class beranda extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterRecyclerView adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ItemModel>data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        data = new ArrayList<>();
        for (int i = 0; i<MyItem.poster.length; i++){
            data.add(new ItemModel(
                    MyItem.namatumbuhan[i],
                    MyItem.poster[i]
            ));
        }

        adapter = new AdapterRecyclerView(data);
        recyclerView.setAdapter(adapter);
    }
}