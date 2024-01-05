package com.example.ptbtb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Regist extends AppCompatActivity {
    Button button_signup;
    private TextView textView;

    private EditText etnama, etEmail, etPassword, etUsername, etTelp,etLokasi;
    private DatabaseReference database;
    private FirebaseAuth auth;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        etnama = findViewById(R.id.namaRegist);
        etUsername = findViewById(R.id.usernameRegist);
        etEmail = findViewById(R.id.email);
        etTelp = findViewById(R.id.telp);
        etPassword = findViewById(R.id.passwordRegist);
        etLokasi = findViewById(R.id.nplokasi);

        button_signup = (Button) findViewById(R.id.button_signup);
        textView = findViewById(R.id.textView5);

        database = FirebaseDatabase.getInstance().getReference("users");
        auth = FirebaseAuth.getInstance();

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Jika belum memiliki izin, minta izin
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Jika sudah memiliki izin, dapatkan lokasi
            getLastLocation();
        }

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nama = etnama.getText().toString();
                final String username = etUsername.getText().toString();
                final String email = etEmail.getText().toString();
                final String originalTelp = etTelp.getText().toString();
                final  String lokasi =etLokasi.getText().toString();
                final String password = etPassword.getText().toString();



                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String telp = formatNomorTelepon(originalTelp);

                if (nama.isEmpty() || username.isEmpty() || email.isEmpty() || telp.isEmpty()  || lokasi.isEmpty()|| password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ada Data yang Masih Kosong!", Toast.LENGTH_LONG).show();
                } else if (!email.matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(), "Format Email Tidak Benar!", Toast.LENGTH_LONG).show();
                } else {
                    // Check if username already exists
                    database.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // Username sudah ada dalam database
                                Toast.makeText(getApplicationContext(), "Username sudah terdaftar!", Toast.LENGTH_LONG).show();
                            } else {
                                // Check if email already exists
                                database.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            // Email sudah ada dalam database
                                            Toast.makeText(getApplicationContext(), "Email sudah terdaftar!", Toast.LENGTH_LONG).show();
                                        } else {
                                            auth.createUserWithEmailAndPassword(email, password)
                                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                                            if (task.isSuccessful()) {
                                                                FirebaseUser currentUser = auth.getCurrentUser();
                                                                String uid = currentUser.getUid();

                                                                DatabaseReference newUserRef = database.child(uid);
                                                                newUserRef.child("nama").setValue(nama);
                                                                newUserRef.child("username").setValue(username);
                                                                newUserRef.child("email").setValue(email);
                                                                newUserRef.child("telp").setValue(telp);
                                                                newUserRef.child("addres").setValue(lokasi);

                                                                Toast.makeText(getApplicationContext(), "Registrasi Berhasil!", Toast.LENGTH_SHORT).show();
                                                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                                                startActivity(intent);
                                                            } else {
                                                                Toast.makeText(getApplicationContext(), "Gagal mendaftarkan pengguna: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                                            }
                                                        }
                                                    });
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        Toast.makeText(getApplicationContext(), "Terjadi kesalahan: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                });
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

    private String formatNomorTelepon(String nomorTelepon) {
        // Hapus karakter non-digit
        String hanyaDigit = nomorTelepon.replaceAll("[^0-9]", "");

        // Hapus '0' di depan
        hanyaDigit = hanyaDigit.replaceFirst("^0+", "");

        // Tambahkan kode negara jika tidak ada
        if (!hanyaDigit.startsWith("62")) {
            hanyaDigit = "62" + hanyaDigit;
        }

        return hanyaDigit;
    }

    public void redirectToLogin(View view) {
        Intent intent = new Intent(Regist.this, Login.class);
        startActivity(intent);
    }
    private void getLastLocation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // If permission is not granted, request it
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // If permission is granted, get the location
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            // Convert location to location name and display it in EditText
                            String locationName = getLocationName(location.getLatitude(), location.getLongitude());
                            etLokasi.setText(locationName);
                        } else {
                            Toast.makeText(this, "Location not available", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(this, e -> {
                        Toast.makeText(this, "Failed to get location", Toast.LENGTH_SHORT).show();
                    });
        }
    }


    private String getLocationName(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                // Mengambil nama lokasi dari alamat
                return address.getLocality();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Lokasi Tidak Diketahui";
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Jika izin diberikan, dapatkan lokasi
                getLastLocation();
            } else {
                Toast.makeText(this, "Izin lokasi ditolak", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
