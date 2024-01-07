package com.example.ptbtb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapterRating extends RecyclerView.Adapter<adapterRating.ViewHolder> {

    private ArrayList<datarating> listRatings;
    private Context context;

    public adapterRating(ArrayList<datarating> listRatings, Context context) {
        this.listRatings = listRatings;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_rating,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.user.setText(listRatings.get(position).getUsername());
        holder.rating.setText(listRatings.get(position).getRating());
        holder.feedback.setText(listRatings.get(position).getFeedbackText());

    }

    @Override
    public int getItemCount() {
        return listRatings.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView user, rating, feedback;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            user = itemView.findViewById(R.id.user);
            rating = itemView.findViewById(R.id.rating);
            feedback = itemView.findViewById(R.id.feedback);

        }
        }


}