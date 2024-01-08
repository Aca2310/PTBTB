package com.example.ptbtb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.squareup.picasso.Picasso;

public class desctp extends AppCompatActivity {
    AppCompatImageView button_back;
    Button button_edit;
    String username, user_id;
    String key = "";
    String imageURL = "";
    ImageView imageView;
    TextView titleTextView, detailTextView, locationTextView, barterInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desctp);

        initializeViews();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            setDataFromBundle(bundle);
        }
    }

    private void initializeViews() {
        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(desctp.this, profile.class);
                startActivity(intent);
                finish();
            }
        });

        imageView = findViewById(R.id.imageViewD);
        titleTextView = findViewById(R.id.judul);
        detailTextView = findViewById(R.id.textViewDetail);
        locationTextView = findViewById(R.id.textViewLocation);
        barterInfoTextView = findViewById(R.id.textViewBarter);

        button_edit = findViewById(R.id.button_edit);
        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startEditActivity();
            }
        });
    }

    private void setDataFromBundle(Bundle bundle) {
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
        locationTextView.setText(locationValue);

        // Open map on location text click
        locationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap(locationValue);
            }
        });
    }

    private void startEditActivity() {
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

    private void openMap(String locationValue) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(locationValue));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps"); // Use Google Maps

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(desctp.this, "Map application not installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
