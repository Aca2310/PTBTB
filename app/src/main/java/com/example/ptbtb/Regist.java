package com.example.ptbtb;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Regist extends AppCompatActivity {
    Button button_signup;
    private TextView textView;

    private EditText etnama, etEmail, etPassword, etUsername, etTelp;
    private DatabaseReference database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        etnama = findViewById(R.id.namaRegist);
        etUsername = findViewById(R.id.usernameRegist);
        etEmail = findViewById(R.id.email);
        etTelp = findViewById(R.id.telp);
        etPassword = findViewById(R.id.passwordRegist);


        button_signup = (Button) findViewById(R.id.button_signup);
        textView = findViewById(R.id.textView5);


        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ptbtb-3ceea-default-rtdb.firebaseio.com/");

        database = FirebaseDatabase.getInstance().getReference("users");

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nama = etnama.getText().toString();
                final String username = etUsername.getText().toString();
                final String email = etEmail.getText().toString();
                final String telp = etTelp.getText().toString();
                final String password = etPassword.getText().toString();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (nama.isEmpty() || username.isEmpty() || email.isEmpty() || telp.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ada Data yang Masih Kosong!", Toast.LENGTH_LONG).show();
                } else if (!email.matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(), "Format Email Tidak Benar!", Toast.LENGTH_LONG).show();
                } else {
                    // Check if username already exists
                    database.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // Username sudah ada dalam database
                                Toast.makeText(getApplicationContext(), "Username sudah ada!", Toast.LENGTH_LONG).show();
                            } else {
                                // Username belum ada dalam database, lanjutkan proses registrasi
                                DatabaseReference newUserRef = database.child(username);
                                newUserRef.child("nama").setValue(nama);
                                newUserRef.child("username").setValue(username);
                                newUserRef.child("email").setValue(email);
                                newUserRef.child("telp").setValue(telp);
                                newUserRef.child("password").setValue(password);

                                Toast.makeText(getApplicationContext(), "Registrasi Berhasil!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(getApplicationContext(), "Terjadi kesalahan: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }

    public void redirectToLogin(View view) {
        Intent intent = new Intent(Regist.this, Login.class);
        startActivity(intent);
    }
}