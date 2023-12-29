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

        Picasso.get()
                .load(recyclerview_lists.get(position).getDataImage())
                .placeholder(R.drawable.dara) // Placeholder sementara gambar dimuat
                .into(holder.imageView);

        holder.textView.setText(recyclerview_lists.get(position).getDataTitle());

        holder.cardView.setOnClickListener(e -> {
            Intent intent = new Intent(context, desc.class);
            intent.putExtra("user_id", recyclerview_lists.get(position).getUser_id());
            intent.putExtra("username", recyclerview_lists.get(position).getUsername());
            intent.putExtra("dataTitle", recyclerview_lists.get(position).getDataTitle());
            intent.putExtra("dataDetail", recyclerview_lists.get(position).getDataDetail());
            intent.putExtra("dataBarter", recyclerview_lists.get(position).getDataBarter());
            intent.putExtra("dataImage", recyclerview_lists.get(position).getDataImage());
            intent.putExtra("telp", recyclerview_lists.get(position).getTelp());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return recyclerview_lists.size();
    }

    public void searchDataList(ArrayList<recyclerview_list> searchList){
        recyclerview_lists = searchList;
        notifyDataSetChanged();
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
