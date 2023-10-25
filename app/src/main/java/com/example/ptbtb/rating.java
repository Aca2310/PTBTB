package com.example.ptbtb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class rating extends AppCompatActivity {
    Button button_rating;
    RatingBar ratingBar;
    float myRating=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        button_rating = findViewById(R.id.button_rating);
        ratingBar = findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                int rating = (int) v;
                String message = null;

                myRating = ratingBar.getRating();
                switch (rating){
                    case 1:
                        message = "Kecewa";
                        break;
                    case 2:
                        message = "Kurang Baik";
                        break;
                    case 3:
                        message = "Biasa";
                        break;
                    case 4:
                        message = "Baik";
                        break;
                    case 5:
                        message = "Luar Biasa";
                        break;
                }
                Toast.makeText(rating.this, message, Toast.LENGTH_SHORT).show();
            }
        });
        button_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(rating.this, "Your Rating Is" + myRating, Toast.LENGTH_SHORT).show();
            }
        });
    }
}