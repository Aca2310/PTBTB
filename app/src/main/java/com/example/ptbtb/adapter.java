package com.example.ptbtb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {

    ArrayList<model> dataItem;

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button namatumbuhan;
        ImageView tumbuhan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            namatumbuhan = itemView.findViewById(R.id.namatumbuhan);
            tumbuhan = itemView.findViewById(R.id.tumbuhan);
        }
    }

    adapter(ArrayList<model> data){
        this.dataItem = data;
    }


    @NonNull
    @Override
    public adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home2, parent, false);
        return new ViewHolder();
    }

    @Override
    public void onBindViewHolder(@NonNull adapter.ViewHolder holder, int position) {

        Button namatumbuhan = holder.namatumbuhan;
        ImageView tumbuhan = holder.tumbuhan;

        namatumbuhan.setText(dataItem.get(position).getName());
        tumbuhan.setImageResource(dataItem.get(position).getTumbuhan());
    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }


}
