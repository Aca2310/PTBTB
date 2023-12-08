package com.example.ptbtb;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterTukardengan extends RecyclerView.Adapter<adapterTukardengan.ViewHolder> {
    private Context context;
    private ArrayList<list_tukardengan> list;
    public adapterTukardengan(Context context, ArrayList<list_tukardengan> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public adapterTukardengan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull adapterTukardengan.ViewHolder holder, int position) {
        Picasso.get()
                .load(list.get(position).getDataImage())
                .placeholder(R.drawable.dara) // Placeholder sementara gambar dimuat
                .into(holder.imageView);

        holder.textView.setText(list.get(position).getDataTitle());

        holder.imageView.setOnClickListener(e -> {
            Intent intent = new Intent(context, beranda.class);
            intent.putExtra("dataTitle", list.get(position).getDataTitle());
            intent.putExtra("dataBarter", list.get(position).getDataBarter());
            intent.putExtra("dataImage", list.get(position).getDataImage());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        Button button_back;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            imageView = itemView.findViewById(R.id.imageTukardengan);
            textView = itemView.findViewById(R.id.judul);
            button_back = itemView.findViewById(R.id.button_tukar);
        }
    }
}
