package com.example.ptbtb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class rating extends AppCompatActivity {
    Button button_rating;
    RatingBar ratingBar;
    EditText feedbackEditText;
    float myRating = 0;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    AppCompatImageView button_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("ratings");

        // Initialize FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        // Dapatkan referensi ImageView
        ImageView imageViewRating = findViewById(R.id.imageViewRating);

        // Setel gambar di ImageView
        imageViewRating.setImageResource(R.drawable.bplant); // Ganti dengan sumber gambar sesuai kebutuhan

        button_rating = findViewById(R.id.button_rating);
        ratingBar = findViewById(R.id.ratingBar);
        feedbackEditText = findViewById(R.id.feedbackEditText);

        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(rating.this, detail_rating.class);
                startActivity(intent);
                finish();
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                int rating = (int) v;
                String message = null;

                myRating = ratingBar.getRating();
                switch (rating) {
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

        // Inside your onClick method
        button_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the current user
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();

                // Check if the user is logged in
                if (currentUser != null) {
                    // User is logged in, get user_id
                    String user_id = currentUser.getUid();

                    // Retrieve username from Realtime Database
                    DatabaseReference usersReference = FirebaseDatabase.getInstance().getReference("users").child(user_id);
                    usersReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // User exists in the "users" node
                                String username = dataSnapshot.child("username").getValue(String.class);

                                // Continue with your existing code
                                String finalMessage;
                                String feedbackText = feedbackEditText.getText().toString();

                                switch ((int) myRating) {
                                    case 1:
                                        finalMessage = "Kecewa";
                                        break;
                                    case 2:
                                        finalMessage = "Kurang Baik";
                                        break;
                                    case 3:
                                        finalMessage = "Biasa";
                                        break;
                                    case 4:
                                        finalMessage = "Baik";
                                        break;
                                    case 5:
                                        finalMessage = "Luar Biasa";
                                        break;
                                    default:
                                        finalMessage = "Tidak Ada Rating";
                                        break;
                                }

                                // Save to Firebase
                                saveToFirebase(user_id, username, String.valueOf(myRating), finalMessage, feedbackText);

                                // Intent to navigate to Home activity
                                Intent intent = new Intent(rating.this, Home.class);
                                startActivity(intent);
                                finish(); // Optional: finish the current activity if you don't want to come back to it with the back button
                            } else {
                                // User not found in the "users" node
                                Toast.makeText(rating.this, "User not found in the database.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Handle any errors during data retrieval
                            Toast.makeText(rating.this, "Error retrieving user data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    // User is not logged in, handle accordingly
                    Toast.makeText(rating.this, "User not logged in.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveToFirebase(String user_id, String username, String rating, String feedback, String feedbackText) {
        // Create a unique key for each rating
        String key = databaseReference.push().getKey();

        // Create a Rating object
        datarating newRating = new datarating(user_id, username, rating, feedback, feedbackText);

        // Save the Rating object to Firebase
        databaseReference.child(key).setValue(newRating);
    }

}
