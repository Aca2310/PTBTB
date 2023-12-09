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

import java.util.ArrayList;

public class adapterhistory extends RecyclerView.Adapter<adapterhistory.ViewHolder> {

    private ArrayList<list_history> list_histories;
    private Context context;

    public adapterhistory(ArrayList<list_history> recyclerview_lists, Context context){
        this.list_histories = recyclerview_lists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(list_histories.get(position).getImage());
        holder.textView.setText(list_histories.get(position).getText());
        holder.textView.setText(list_histories.get(position).getText2());

        holder.join.setOnClickListener(e->{
            Intent intent = new Intent(context,descHistory.class);
            context.startActivity(intent);
        });

    }


    @Override
    public int getItemCount() {
        return list_histories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView textView;
        TextView textView2;
        Button join;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            imageView = itemView.findViewById(R.id.imageHistory);
            textView = itemView.findViewById(R.id.judul);
            textView2 = itemView.findViewById(R.id.subjudul);
            join = itemView.findViewById(R.id.join);

        }
    }


}
