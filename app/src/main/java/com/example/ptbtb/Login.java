package com.example.ptbtb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    Button button_login;
    private EditText etEmail, etPassword;
    private TextView textView;
    TextView forgotPassword;

    private DatabaseReference database;
    private FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button_login = (Button) findViewById(R.id.button_login);

        etEmail = findViewById(R.id.emailLogin);
        etPassword = findViewById(R.id.passwordLogin);

        textView = findViewById(R.id.textView3);
        forgotPassword = findViewById(R.id.forgetPassword);

        auth = FirebaseAuth.getInstance();


        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailOrUsername = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                database = FirebaseDatabase.getInstance().getReference("users");

                if (!TextUtils.isEmpty(emailOrUsername) && !TextUtils.isEmpty(password)) {
                    if (Patterns.EMAIL_ADDRESS.matcher(emailOrUsername).matches()) {
                        // Jika input adalah email
                        auth.signInWithEmailAndPassword(emailOrUsername, password)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users");
                                        userRef.orderByChild("email").equalTo(emailOrUsername).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.exists()) {
                                                    DataSnapshot userSnapshot = snapshot.getChildren().iterator().next();
                                                    String savedUsername = userSnapshot.child("username").getValue(String.class);
                                                    String savedName = userSnapshot.child("nama").getValue(String.class);
                                                    String savedTelp = userSnapshot.child("telp").getValue(String.class);
                                                    String savedAddress = userSnapshot.child("addres").getValue(String.class);

                                                    Intent masuk = new Intent(Login.this, Home.class);
                                                    masuk.putExtra("email", emailOrUsername);
                                                    masuk.putExtra("username", savedUsername);
                                                    masuk.putExtra("nama", savedName);
                                                    masuk.putExtra("telp", savedTelp);
                                                    masuk.putExtra("addres", savedAddress);
                                                    startActivity(masuk);
                                                    finish();
                                                } else {
                                                    Toast.makeText(Login.this, "User data not found.", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                Toast.makeText(Login.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Login.this, "Email atau Password salah", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        // Jika input adalah username
                        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users");
                        userRef.orderByChild("username").equalTo(emailOrUsername).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    DataSnapshot userSnapshot = snapshot.getChildren().iterator().next();
                                    String savedEmail = userSnapshot.child("email").getValue(String.class);
                                    String savedName = userSnapshot.child("nama").getValue(String.class);
                                    String savedTelp = userSnapshot.child("telp").getValue(String.class);
                                    String savedAddress = userSnapshot.child("addres").getValue(String.class);

                                    auth.signInWithEmailAndPassword(savedEmail, password)
                                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                                @Override
                                                public void onSuccess(AuthResult authResult) {
                                                    Intent masuk = new Intent(Login.this, Home.class);
                                                    masuk.putExtra("email", savedEmail);
                                                    masuk.putExtra("username", emailOrUsername);
                                                    masuk.putExtra("nama", savedName);
                                                    masuk.putExtra("telp", savedTelp);
                                                    masuk.putExtra("addres", savedAddress);
                                                    startActivity(masuk);
                                                    finish();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                } else {
                                    Toast.makeText(Login.this, "User data not found.", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(Login.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else {
                    Toast.makeText(Login.this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_forget, null);
                EditText emailBox = dialogView.findViewById(R.id.emailBox);
                builder.setView(dialogView);
                AlertDialog dialog = builder.create();
                dialogView.findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String userEmail = emailBox.getText().toString();
                        if (TextUtils.isEmpty(userEmail) || !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                            Toast.makeText(Login.this, "Masukkan alamat email yang terdaftar", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        auth.sendPasswordResetEmail(userEmail)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Login.this, "Cek email Anda", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        } else {
                                            Toast.makeText(Login.this, "Gagal mengirim email reset kata sandi", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                });

                dialogView.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                if (dialog.getWindow() != null) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                dialog.show();
            }
        });
    }


    public void redirectToRegist(View view) {
        Intent intent = new Intent(Login.this, Regist.class);
        startActivity(intent);
    }
}