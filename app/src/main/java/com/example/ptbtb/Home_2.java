package com.example.ptbtb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Home_2 extends AppCompatActivity {

    RecyclerView recyclerView;
    adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<model>data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(RecyclerView.LayoutManager);

        data = new ArrayList<>();
        for (int i = 0; i<Myitem.tumbuhan.lenght; i++){
            data.add(new model(
                    Myitem.namatumbuhan(i),
                    Myitem.tumbuhan(i)
            ));
        }

        adapter = new adapter(data);
        recyclerView.setAdapter(adapter);
    }


}