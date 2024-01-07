package com.example.ptbtb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class desctp extends AppCompatActivity {
    AppCompatImageView button_back;
    Button button_edit;
    String username, user_id;
    String key = "";
    String imageURL = "";
    public static String tempDataTitle;
    public static String tempDataDetail;
    public static String tempDataBarter;
    public static String tempDataImage;
    public static String tempUsername, tempTelp, tempDataLocation;
    public static String tempUserId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desctp);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String savedName = intent.getStringExtra("nama");
        String savedAddress = intent.getStringExtra("addres");
        String savedTelp = intent.getStringExtra("telp");

        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(desctp.this, profile.class);
                startActivity(intent);
                finish();
            }
        });

        ImageView imageView = findViewById(R.id.imageViewD);
        TextView titleTextView = findViewById(R.id.judul);
        TextView detailTextView = findViewById(R.id.textViewDetail);
        TextView locationTextView = findViewById(R.id.textViewLocation);
        TextView barterInfoTextView = findViewById(R.id.textViewBarter);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            detailTextView.setText(bundle.getString("dataDetail"));
            titleTextView.setText(bundle.getString("dataTitle"));
            locationTextView.setText(bundle.getString("dataLocation"));
            barterInfoTextView.setText(bundle.getString("dataBarter"));
            username = bundle.getString("username");
            user_id = bundle.getString("user_id");
            key = bundle.getString("Key");
            imageURL = bundle.getString("dataImage");
            Picasso.get().load(bundle.getString("dataImage")).into(imageView);

            String locationValue = bundle.getString("dataLocation");
            Log.d("LocationDebug", "Location Value: " + locationValue);
            locationTextView.setText(locationValue);

            // Open map on location text click
            locationTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(locationValue));
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps"); // Use Google Maps

                    if (mapIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(mapIntent);
                    } else {
                        Toast.makeText(desctp.this, "Map application not installed.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            button_edit = findViewById(R.id.button_edit);
            button_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent editIntent = new Intent(desctp.this, edit_tanaman.class)
                            .putExtra("dataTitle", titleTextView.getText().toString())
                            .putExtra("dataDetail", detailTextView.getText().toString())
                            .putExtra("dataLocation", locationTextView.getText().toString())
                            .putExtra("dataBarter", barterInfoTextView.getText().toString())
                            .putExtra("dataImage", imageURL)
                            .putExtra("username", username)
                            .putExtra("user_id", user_id)
                            .putExtra("Key", key);
                    startActivity(editIntent);
                }
            });
        }
    }
}
