package com.example.ptbtb;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tukardengan,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterTukardengan.ViewHolder holder, int position) {
        Picasso.get()
                .load(list.get(position).getDataImage())
                .placeholder(R.drawable.dara) // Placeholder sementara gambar dimuat
                .into(holder.imageView);

        holder.textView.setText(list.get(position).getDataTitle());

        holder.button_pilih.setOnClickListener(e -> {
            showDialog("Penawaran anda akan diproses", position);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        Button button_pilih;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            imageView = itemView.findViewById(R.id.imageTukardengan);
            textView = itemView.findViewById(R.id.judul);
            button_pilih= itemView.findViewById(R.id.button_tukar);
        }
    }
    private void showDialog(String message, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> {
                    // Lakukan tindakan yang sesuai saat tombol "OK" diklik
                    // Contoh: Pindah ke aktivitas home
                    Intent intent = new Intent(context, Home.class);
                    intent.putExtra("user_id", list.get(position).getUser_id());
                    intent.putExtra("dataTitle", list.get(position).getDataTitle());
                    intent.putExtra("dataImage", list.get(position).getDataImage());
                    context.startActivity(intent);
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
