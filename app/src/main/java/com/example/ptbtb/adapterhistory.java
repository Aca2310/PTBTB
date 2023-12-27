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

public class adapterhistory extends RecyclerView.Adapter<adapterhistory.ViewHolder> {

    private ArrayList<HistoryData> list_histories;
    private Context context;

    public adapterhistory(ArrayList<HistoryData> list_histories, Context context){
        this.list_histories = list_histories;
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
        Picasso.get().load(list_histories.get(position).getDataImagePenerima()).into(holder.imageView);

        holder.textView.setText(list_histories.get(position).getDataTitlePenerima());
        holder.textView2.setText(list_histories.get(position).getDataTitleTukar());
        holder.textView3.setText(list_histories.get(position).getDate());

        holder.join.setOnClickListener(e -> {
            Intent intent = new Intent(context, descHistory.class);
            intent.putExtra("id", position);

            intent.putExtra("user_idPenerima", list_histories.get(position).getUser_idPenerima());
            intent.putExtra("usernamePenerima", list_histories.get(position).getUsernamePenerima());
            intent.putExtra("dataTitlePenerima", list_histories.get(position).getDataTitlePenerima());
            intent.putExtra("dataDetailPenerima", list_histories.get(position).getDataDetailPenerima());
            intent.putExtra("dataBarterPenerima", list_histories.get(position).getDataBarterPenerima());
            intent.putExtra("dataImagePenerima", list_histories.get(position).getDataImagePenerima());

            // Menyimpan data user_idTukar ke dalam intent
            intent.putExtra("user_idTukar", list_histories.get(position).getUser_idTukar());
            intent.putExtra("usernameTukar", list_histories.get(position).getUsernameTukar());
            intent.putExtra("dataTitleTukar", list_histories.get(position).getDataTitleTukar());
            intent.putExtra("dataDetailTukar", list_histories.get(position).getDataDetailTukar());
            intent.putExtra("dataBarterTukar", list_histories.get(position).getDataBarterTukar());
            intent.putExtra("dataImageTukar", list_histories.get(position).getDataImageTukar());
            intent.putExtra("status",list_histories.get(position).getStatus());
            intent.putExtra("Key",list_histories.get(position).getKey());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list_histories.size();
    }

    public void searchDataList(ArrayList<HistoryData> searchList){
        list_histories = searchList;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView textView;
        TextView textView2;
        TextView textView3;
        Button join;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            imageView = itemView.findViewById(R.id.imageHistory);
            textView = itemView.findViewById(R.id.judul);
            textView2 = itemView.findViewById(R.id.subjudul);
            textView3 = itemView.findViewById(R.id.subjudul2);
            join = itemView.findViewById(R.id.join);

        }
    }


}
