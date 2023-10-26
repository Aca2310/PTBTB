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

public class adapterProfile extends RecyclerView.Adapter<adapterProfile.ViewHolder> {

    private ArrayList<list_profile> list_profiles;
    private Context context;

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

        holder.imageView.setImageResource(list_profiles.get(position).getImage());
        holder.textView.setText(list_profiles.get(position).getText());

        holder.cardView.setOnClickListener(e -> {
            Intent intent = new Intent(context, desctp.class);
            intent.putExtra("id", position);
            intent.putExtra("image", list_profiles.get(position).getImage()); // Mengirim ID gambar
            intent.putExtra("title", list_profiles.get(position).getText()); // Mengirim judul
            intent.putExtra("detail", list_profiles.get(position).getDetail());
            intent.putExtra("location", list_profiles.get(position).getLokasi()); // Mengirim lokasi
            intent.putExtra("barterInfo", list_profiles.get(position).getBarter()); // Mengirim informasi barter

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
