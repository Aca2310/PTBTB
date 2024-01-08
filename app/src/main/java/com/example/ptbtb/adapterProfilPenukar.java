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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterProfilPenukar extends RecyclerView.Adapter<adapterProfilPenukar.ViewHolder> {

    private ArrayList<com.example.ptbtb.list_profilpenukar> list_profilpenukar;
    private Context context;

    public adapterProfilPenukar(ArrayList<com.example.ptbtb.list_profilpenukar> list_profilpenukar, Context context) {
        this.list_profilpenukar = list_profilpenukar;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.get()
                .load(list_profilpenukar.get(position).getDataImage())
                .placeholder(R.drawable.dara)
                .into(holder.imageView);

        holder.textView.setText(list_profilpenukar.get(position).getDataTitle());

        holder.cardView.setOnClickListener(e -> {
            Intent intent = new Intent(context, descpp.class);
            intent.putExtra("user_id", list_profilpenukar.get(position).getUser_id());
            intent.putExtra("username", list_profilpenukar.get(position).getUsername());
            intent.putExtra("dataTitle", list_profilpenukar.get(position).getDataTitle());
            intent.putExtra("dataDetail", list_profilpenukar.get(position).getDataDetail());
            intent.putExtra("dataLocation", list_profilpenukar.get(position).getDataLocation());
            intent.putExtra("dataBarter", list_profilpenukar.get(position).getDataBarter());
            intent.putExtra("dataImage", list_profilpenukar.get(position).getDataImage());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list_profilpenukar.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
