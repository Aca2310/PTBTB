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

public class  adapter extends RecyclerView.Adapter<adapter.ViewHolder> {

    private ArrayList<list> lists;
    private Context context;

    public adapter(ArrayList<list> recyclerview_lists, Context context) {
        this.lists = recyclerview_lists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_notif,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageResource(lists.get(position).getImage());
        holder.textView.setText(lists.get(position).getJudul());
        holder.textView2.setText(lists.get(position).getSubjudl());

        holder.cardView.setOnClickListener(e->{
            Intent intent = new Intent(context,chat.class);
            intent.putExtra("id",position);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView textView;
        TextView textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            imageView = itemView.findViewById(R.id.imagekomunitas);
            textView = itemView.findViewById(R.id.judul);
            textView2 = itemView.findViewById(R.id.subjudul);
        }
    }


}
