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

public class adapterProfile extends RecyclerView.Adapter<adapterProfile.ViewHolder> {

    private ArrayList<list_profile> list_profiles;
    private Context context;
    String key ;

    public adapterProfile(ArrayList<list_profile> list_profiles, Context context) {
        this.list_profiles = list_profiles;
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
                .load(list_profiles.get(position).getDataImage())
                .placeholder(R.drawable.dara) // Placeholder sementara gambar dimuat
                .into(holder.imageView);

        holder.textView.setText(list_profiles.get(position).getDataTitle());

        holder.cardView.setOnClickListener(e -> {
            Intent intent = new Intent(context, desctp.class);
            intent.putExtra("user_id", list_profiles.get(position).getUser_id());
            intent.putExtra("username", list_profiles.get(position).getUsername());
            intent.putExtra("dataTitle", list_profiles.get(position).getDataTitle());
            intent.putExtra("dataDetail", list_profiles.get(position).getDataDetail());
            intent.putExtra("dataBarter", list_profiles.get(position).getDataBarter());
            intent.putExtra("dataImage", list_profiles.get(position).getDataImage());
            intent.putExtra("Key",list_profiles.get(position).getKey());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list_profiles.size();
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