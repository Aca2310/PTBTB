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

public class recyclerview_adapter extends RecyclerView.Adapter<recyclerview_adapter.ViewHolder> {

    private ArrayList<recyclerview_list> recyclerview_lists;
    private Context context;



    public recyclerview_adapter(ArrayList<recyclerview_list> recyclerview_lists, Context context) {
        this.recyclerview_lists = recyclerview_lists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageResource(recyclerview_lists.get(position).getImage());
        holder.textView.setText(recyclerview_lists.get(position).getText());

        holder.cardView.setOnClickListener(e->{
            Intent intent = new Intent(context,pages.class);
            intent.putExtra("id",position);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return recyclerview_lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
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
