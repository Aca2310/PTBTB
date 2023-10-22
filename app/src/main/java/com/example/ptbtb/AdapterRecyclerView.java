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

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder> {

    ArrayList<ItemModel> dataItem;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namatumbuhan;
        ImageView tumbuhan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            namatumbuhan = itemView.findViewById(R.id.text_judul);
            tumbuhan = itemView.findViewById(R.id.image_tumbuhan);
        }
    }

    AdapterRecyclerView(ArrayList<ItemModel> data){
        this.dataItem = data;
    }


    @NonNull
    @Override
    public AdapterRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_beranda, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerView.ViewHolder holder, int position) {

        TextView namatumbuhan = holder.namatumbuhan;
        ImageView tumbuhan = holder.tumbuhan;

        namatumbuhan.setText(dataItem.get(position).getName());
        tumbuhan.setImageResource(dataItem.get(position).getPoster());
    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }


}