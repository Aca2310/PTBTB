package com.example.ptbtb;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Regist extends AppCompatActivity {
    Button button_signup;
    private TextView textView;

    private EditText etnama, etEmail, etPassword, etUsername;
    private DatabaseReference database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        etnama = findViewById(R.id.namaRegist);
        etUsername = findViewById(R.id.usernameRegist);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.passwordRegist);


        button_signup = (Button) findViewById(R.id.button_signup);
        textView = findViewById(R.id.textView5);


        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ptbtb-3ceea-default-rtdb.firebaseio.com/");


        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = etnama.getText().toString();
                String username = etUsername.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (nama.isEmpty()  || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ada Data yang Masih Kosong!", Toast.LENGTH_LONG).show();
                }else {
                    database = FirebaseDatabase.getInstance().getReference("users");
                    database.child(username).child("nama").setValue(nama);
                    database.child(username).child("username").setValue(username);
                    database.child(username).child("email").setValue(email);
                    database.child(username).child("password").setValue(password);


                    Toast.makeText(getApplicationContext(), "Register Berhasil!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);
                }

            }
        });
    }
    public void redirectToLogin(View view) {
        Intent intent = new Intent(Regist.this, Login.class); // Ganti "Home" dengan nama aktivitas home Anda
        startActivity(intent);
    }
}