package com.example.ptbtb;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class history extends AppCompatActivity {

    ArrayList<list_history> lists;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

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