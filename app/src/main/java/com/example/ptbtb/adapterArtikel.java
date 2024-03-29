package com.example.ptbtb;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterArtikel extends RecyclerView.Adapter<adapterArtikel.MyViewHolder> {
    private Context context;
    private ArrayList<listArtikel> list;




    public adapterArtikel(Context context, ArrayList<listArtikel> list){
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_artikel,parent,false);
        return new adapterArtikel.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get()
                .load(list.get(position).getImageArtikel())
                .placeholder(R.drawable.dara) // Placeholder sementara gambar dimuat
                .into(holder.imageArtikel);

        holder.judul.setText(list.get(position).getJudul());

        holder.imageArtikel.setOnClickListener(e -> {
            Intent intent = new Intent(context, desc_artikel.class);
            intent.putExtra("desc", list.get(position).getDesc());
            intent.putExtra("judul", list.get(position).getJudul());
            intent.putExtra("imageArtikel", list.get(position).getImageArtikel());
            intent.putExtra("Key", list.get(position).getKey());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void searchDataList(ArrayList<listArtikel> searchList){
        list = searchList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView judul, desc;
        ImageView imageArtikel;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            judul = itemView.findViewById(R.id.judul);
            desc = itemView.findViewById(R.id.desc);
            imageArtikel = itemView.findViewById(R.id.imageArtikel);
        }
    }

}