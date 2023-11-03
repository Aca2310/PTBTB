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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    Button button_login;
    private EditText etEmail, etPassword;
    private TextView textView;

    private DatabaseReference database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button_login = (Button) findViewById(R.id.button_login);

        etEmail = findViewById(R.id.emailLogin);
        etPassword = findViewById(R.id.passwordLogin);

        textView = findViewById(R.id.textView3);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                database = FirebaseDatabase.getInstance().getReference("users");


                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Email dan password salah!", Toast.LENGTH_LONG).show();
                }else {
                    database.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.getChildrenCount() > 0) {
                                for (DataSnapshot userSnapshot : snapshot.getChildren()) {

                                    String savedUsername = userSnapshot.child("username").getValue(String.class);
                                    String savedName = userSnapshot.child("nama").getValue(String.class);
                                    String savedEmail = userSnapshot.child("email").getValue(String.class);
                                    String savedPassword = userSnapshot.child("password").getValue(String.class);
                                    String savedTelp = userSnapshot.child("telp").getValue(String.class);
                                    String savedAddress = userSnapshot.child("alamat").getValue(String.class);

                                    if ((savedUsername != null && savedUsername.equals(email)) ||
                                            (savedEmail != null && savedEmail.equals(email))) {
                                        if (savedPassword != null && savedPassword.equals(password)) {
                                            Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();
                                            Intent masuk = new Intent(getApplicationContext(), Home.class);

                                            masuk.putExtra("email", savedEmail);
                                            masuk.putExtra("username", savedUsername);
                                            masuk.putExtra("nama", savedName);
                                            masuk.putExtra("telp", savedTelp);
                                            masuk.putExtra("address", savedAddress);
                                            startActivity(masuk);
                                            return;
                                        }
                                    }
                                }
                                Toast.makeText(getApplicationContext(), "Username/Email atau password salah!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Tidak ada pengguna terdaftar.", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getApplicationContext(), "Terjadi kesalahan: " + error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }



            }
        });
    }

    public void redirectToRegist(View view) {
        Intent intent = new Intent(Login.this, Regist.class);
        startActivity(intent);
    }
}