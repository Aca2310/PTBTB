package com.example.ptbtb;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapterArtikel extends RecyclerView.Adapter<adapterArtikel.ViewHolder> {

    private ArrayList<listArtikel> listArtikels;
    private Context context;

    public adapterArtikel(ArrayList<listArtikel> recyclerview_lists, Context context) {
        this.listArtikels = recyclerview_lists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_artikel,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageResource(listArtikels.get(position).getImage());
        holder.textView.setText(listArtikels.get(position).getText());

        holder.cardView.setOnClickListener(e->{
            Intent intent = new Intent(context, desc_artikel.class);
            intent.putExtra("id",position);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listArtikels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView textView;
        TextView textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            imageView = itemView.findViewById(R.id.imageArtikel);
            textView = itemView.findViewById(R.id.judul);
            textView2 = itemView.findViewById(R.id.subjudul);
        }
    }


}
