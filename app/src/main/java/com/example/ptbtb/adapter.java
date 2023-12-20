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

public class  adapter extends RecyclerView.Adapter<adapter.ViewHolder> {

    private ArrayList<TawarData> listTawardata;
    private Context context;

    public adapter(ArrayList<TawarData> listTawardata, Context context) {
        this.listTawardata = listTawardata;
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

        Picasso.get().load(listTawardata.get(position).getDataImagePenerima()).into(holder.imageView);
        holder.textView.setText(listTawardata.get(position).getUsernameTukar());

        holder.detail.setOnClickListener(e->{
            Intent intent = new Intent(context, desc_notif.class);
            intent.putExtra("id", position);

            intent.putExtra("user_idPenerima", listTawardata.get(position).getUser_idPenerima());
            intent.putExtra("usernamePenerima", listTawardata.get(position).getUsernamePenerima());
            intent.putExtra("dataTitlePenerima", listTawardata.get(position).getDataTitlePenerima());
            intent.putExtra("dataDetailPenerima", listTawardata.get(position).getDataDetailPenerima());
            intent.putExtra("dataBarterPenerima", listTawardata.get(position).getDataBarterPenerima());
            intent.putExtra("dataImagePenerima", listTawardata.get(position).getDataImagePenerima());

            // Menyimpan data user_idTukar ke dalam intent
            intent.putExtra("user_idTukar", listTawardata.get(position).getUser_idTukar());
            intent.putExtra("usernameTukar", listTawardata.get(position).getUsernameTukar());
            intent.putExtra("dataTitleTukar", listTawardata.get(position).getDataTitleTukar());
            intent.putExtra("dataDetailTukar", listTawardata.get(position).getDataDetailTukar());
            intent.putExtra("dataBarterTukar", listTawardata.get(position).getDataBarterTukar());
            intent.putExtra("dataImageTukar", listTawardata.get(position).getDataImageTukar());
            intent.putExtra("Key",listTawardata.get(position).getKey());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listTawardata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView textView;
        TextView textView2;
        Button detail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            imageView = itemView.findViewById(R.id.imagekomunitas);
            textView = itemView.findViewById(R.id.judul);
            textView2 = itemView.findViewById(R.id.subjudul);
            detail = itemView.findViewById(R.id.detail);
        }
    }


}
