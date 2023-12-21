package com.example.ptbtb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class descHistory extends AppCompatActivity {

    AppCompatImageView button_back;
    Button button_delete;
    String key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_history);

        Intent intent = getIntent();

        button_back = findViewById(R.id.button_back);
        button_delete = findViewById(R.id.delet);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(descHistory.this, history.class);

                startActivity(intent);
                finish();
            }
        });

        ImageView imagePenerima = findViewById(R.id.imageView4);
        TextView titlePenerima = findViewById(R.id.namaTumbuhan);
        TextView detailPenerima = findViewById(R.id.textView);
        TextView locationPenerima = findViewById(R.id.textView15);
        ImageView imageViewTukar = findViewById(R.id.namaTumbuhan2);
        TextView titleTukar = findViewById(R.id.namaTumbuhan1);
        TextView detailTukar = findViewById(R.id.textView13);
        TextView locationTukar = findViewById(R.id.lokasi2);
        TextView usernametukar = findViewById(R.id.usernameNotif);
        TextView usernamepenerima = findViewById(R.id.usernameNotif2);
        TextView status = findViewById(R.id.barter3);

        if (intent != null) {
            String DatatitlePenerima = intent.getStringExtra("dataTitlePenerima");
            String DatadetailPenerima = intent.getStringExtra("dataDetailPenerima");
            String DatalocationPenerima = intent.getStringExtra("dataLocationPenerima");
            String dataImagePenerima = intent.getStringExtra("dataImagePenerima");
            String title = intent.getStringExtra("dataTitleTukar");
            String detail = intent.getStringExtra("dataDetailTukar");
            String location = intent.getStringExtra("dataLocationTukar");
            String DatausernamePenerima = intent.getStringExtra("usernamePenerima");
            String Datausernametukar= intent.getStringExtra("usernameTukar");
            String dataImage = intent.getStringExtra("dataImageTukar");
            String statuss = intent.getStringExtra("status");
            key = intent.getStringExtra("Key");

            if (dataImage != null) {
                Picasso.get().load(dataImage).into(imageViewTukar);
            }
            if (dataImagePenerima != null) {
                Picasso.get().load(dataImagePenerima).into(imagePenerima);
            }
            if (title != null) {
                titleTukar.setText(title);
            }
            if (DatatitlePenerima != null) {
                titlePenerima.setText(DatatitlePenerima);
            }
            if (DatadetailPenerima != null) {
                detailPenerima.setText(DatadetailPenerima);
            }
            if (detail != null) {
                detailTukar.setText(detail);
            }
            if (DatalocationPenerima != null) {
                locationPenerima.setText(DatalocationPenerima);
            }
            if (location != null) {
                locationTukar.setText(location);
            }
            if (DatausernamePenerima != null) {
                usernamepenerima.setText("@" + DatausernamePenerima);
            }
            if (Datausernametukar != null) {
                usernametukar.setText("@" + Datausernametukar);
            }
            if (statuss!= null) {
                status.setText(statuss);
            }

        }

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show a confirmation dialog before deleting
                showDeleteConfirmationDialog();
            }

            private void showDeleteConfirmationDialog() {
                AlertDialog.Builder builder = new AlertDialog.Builder(descHistory.this);
                builder.setTitle("Confirmation")
                        .setMessage("Are you sure you want to delete this data?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Call a method to delete the data
                                deleteData();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Dismiss the dialog if "No" is clicked
                                dialog.dismiss();
                            }
                        })
                        .show();
            }

            private void deleteData() {
                if (key != null) {
                    // Remove the data from the "tawar" node
                    DatabaseReference HistoryReference = FirebaseDatabase.getInstance().getReference("history").child(key);
                    HistoryReference.removeValue();

                    Toast.makeText(descHistory.this, "Data Deleted", Toast.LENGTH_SHORT).show();

                    navigateToNotifPage();
                }
            }

            private void navigateToNotifPage() {
                Intent intent = new Intent(descHistory.this, notif.class);
                startActivity(intent);
                finish();
            }
        });

    }
}