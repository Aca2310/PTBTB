package com.example.ptbtb;

import static android.content.Intent.ACTION_PICK;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class newpost extends AppCompatActivity {

    private static final String CHANNEL_ID = "MyChannelId";

    ImageView uploadImage;
    Button buttonup;
    EditText Nptitle, Npdetail, Npbarter, Nplokasi;
    String imageURL;
    Uri uri;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private static final int REQUEST_CODE_MAPS = 123;
    private Intent data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpost);

        uploadImage = findViewById(R.id.uploadImage);
        Nptitle = findViewById(R.id.Nptitle);
        Npdetail = findViewById(R.id.Npdetail);
        Nplokasi = findViewById(R.id.Nplokasi);
        Npbarter = findViewById(R.id.Npbarter);
        buttonup = findViewById(R.id.buttonup);


        Button buttonAddLocation = findViewById(R.id.buttonAddLocation);
        buttonAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Panggil fungsi ketika tombol "Add Location" diklik
                onAddLocationButtonClick(view);
            }
        });

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            uri = data.getData();
                            uploadImage.setImageURI(uri);
                        } else {
                            Toast.makeText(newpost.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        buttonup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    //aa
    public void onAddLocationButtonClick(View v) {
        // Start MapsActivity langsung menggunakan Intent
        Intent intent = new Intent(newpost.this, MapsActivity.class);
        startActivityForResult(intent, 123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_MAPS && resultCode == RESULT_OK) {
            // Terima data yang dikirimkan dari MapsActivity
            double latitude = data.getDoubleExtra("latitude", 0.0);
            double longitude = data.getDoubleExtra("longitude", 0.0);
            String locationName = data.getStringExtra("locationName");

            // Pemeriksaan null sebelum menggunakan data
            if (locationName != null) {
                // Gunakan data untuk menampilkan atau menyimpan informasi lokasi
                Nplokasi.setText(locationName);
            } else {
                Toast.makeText(this, "Nama lokasi tidak tersedia", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void saveData() {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Images Postingan")
                .child(uri.getLastPathSegment());
        AlertDialog.Builder builder = new AlertDialog.Builder(newpost.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                uriTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri urlImage = task.getResult();
                            imageURL = urlImage.toString();
                            String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                            // Mengambil username dari Realtime Database
                            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(currentUserUid);
                            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        String username = dataSnapshot.child("username").getValue(String.class);
                                        String telp = dataSnapshot.child("telp").getValue(String.class);
                                        // Sekarang Anda memiliki username, Anda dapat menggunakannya di DataClass
                                        uploadData(currentUserUid, username, telp);
                                        dialog.dismiss();
                                    } else {
                                        // Menangani kasus di mana data pengguna tidak ditemukan
                                        dialog.dismiss();
                                        Toast.makeText(newpost.this, "Data pengguna tidak ditemukan", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    dialog.dismiss();
                                    Toast.makeText(newpost.this, "Error mengambil data pengguna: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            dialog.dismiss();
                            Toast.makeText(newpost.this, "Gagal mendapatkan URL gambar", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(newpost.this, "Gagal mengunggah gambar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadData(String user_id, String username, String telp) {
        String title = Nptitle.getText().toString();
        String detail = Npdetail.getText().toString();
        String barter = Npbarter.getText().toString();
        String location = Nplokasi.getText().toString();

        DataClass dataClass = new DataClass(user_id, username, title, detail, location, barter, imageURL, telp);

        DatabaseReference postReference = FirebaseDatabase.getInstance().getReference("Postingan").push();
        postReference.setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(newpost.this, "Tersimpan", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(newpost.this, "Gagal menyimpan data: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        showNotification("Postingann", "Yeee postingan anda berhasil.");
    }

    private void showNotification(String title, String message) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Create a notification channel (required for Android Oreo and above)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "My Notification Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(channel);
        }

        // Set the sound for the notification
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        // Create the notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_send)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSound(defaultSoundUri)
                .setAutoCancel(true);

        // Show the notification
        notificationManager.notify(0, notificationBuilder.build());
    }
}
