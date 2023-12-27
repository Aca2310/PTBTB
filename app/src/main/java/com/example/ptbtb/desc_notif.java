package com.example.ptbtb;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import android.app.AlertDialog;

public class desc_notif extends AppCompatActivity {

    AppCompatImageView button_back;
    Button terimaButton,button_tolak, button_wa, button_delete;
    String key = "";
    private static final String CHANNEL_ID = "MyNotificationChannel";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_notif);
        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(desc_notif.this, notif.class);
                startActivity(intent);
                finish();
            }
        });
        ImageView imagePenerima = findViewById(R.id.imageView4);
        TextView titlePenerima = findViewById(R.id.namaTumbuhan);
        TextView detailPenerima = findViewById(R.id.textView);
        TextView locationPenerima = findViewById(R.id.textView15);
        ImageView imageViewTukar = findViewById(R.id.namaTumbuhan2);
        TextView titleTukar = findViewById(R.id.namaTumbuhan1);
        TextView detailTukar = findViewById(R.id.textView13);
        TextView locationTukar = findViewById(R.id.lokasi2);
        TextView usernameText = findViewById(R.id.usernameNotif);
        Intent intent = getIntent();
        if (intent != null) {
            String DatatitlePenerima = intent.getStringExtra("dataTitlePenerima");
            String DatadetailPenerima = intent.getStringExtra("dataDetailPenerima");
            String DatalocationPenerima = intent.getStringExtra("dataLocationPenerima");
            String dataImagePenerima = intent.getStringExtra("dataImagePenerima");
            String title = intent.getStringExtra("dataTitleTukar");
            String detail = intent.getStringExtra("dataDetailTukar");
            String location = intent.getStringExtra("dataLocationTukar");
            String usernamePenerima = intent.getStringExtra("usernameTukar");
            String dataImage = intent.getStringExtra("dataImageTukar");
            key = intent.getStringExtra("Key");

            if (dataImage != null) {
                Picasso.get().load(dataImage).into(imageViewTukar);
            }
            if (dataImagePenerima != null) {
                Picasso.get().load(dataImagePenerima).into(imagePenerima);
            }
            if (title != null) {
                titleTukar.setText(title);
            }
            if (DatatitlePenerima != null) {
                titlePenerima.setText(DatatitlePenerima);
            }
            if (DatadetailPenerima != null) {
                detailPenerima.setText(DatadetailPenerima);
            }
            if (detail != null) {
                detailTukar.setText(detail);
            }
            if (DatalocationPenerima != null) {
                locationPenerima.setText(DatalocationPenerima);
            }
            if (location != null) {
                locationTukar.setText(location);
            }
            if (usernamePenerima != null) {
                usernameText.setText("@" + usernamePenerima);
            }

        }

        button_tolak = findViewById(R.id.tolak);
        button_tolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStatus();
                navigateToNotifPage();
            }
            private void navigateToNotifPage() {
                Intent intent = new Intent(desc_notif.this, notif.class);
                startActivity(intent);
                finish();
            }

            private void updateStatus() {
                if (key != null) {
                    // Update the status in the "tawar" node
                    DatabaseReference tawarReference = FirebaseDatabase.getInstance().getReference("tawar").child(key);
                    tawarReference.child("status").setValue("tolak");

                    // Save data in history
                    DatabaseReference historyReference = FirebaseDatabase.getInstance().getReference("history");
                    String historyKey = historyReference.push().getKey();

                    HistoryData historyItem = new HistoryData(
                            getIntent().getStringExtra("user_idPenerima"),
                            getIntent().getStringExtra("usernamePenerima"),
                            getIntent().getStringExtra("dataTitlePenerima"),
                            getIntent().getStringExtra("dataDetailPenerima"),
                            getIntent().getStringExtra("dataBarterPenerima"),
                            getIntent().getStringExtra("dataImagePenerima"),
                            getIntent().getStringExtra("user_idTukar"),
                            getIntent().getStringExtra("usernameTukar"),
                            getIntent().getStringExtra("dataTitleTukar"),
                            getIntent().getStringExtra("dataDetailTukar"),
                            getIntent().getStringExtra("dataBarterTukar"),
                            getIntent().getStringExtra("dataImageTukar"),
                            "tolak"
                    );

                    historyReference.child(historyKey).setValue(historyItem);

                    Toast.makeText(desc_notif.this, "Status Tawaran Ditolak", Toast.LENGTH_SHORT).show();
                }
            }
        });

        terimaButton = findViewById(R.id.terima);
        terimaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStatus();
                navigateToNotifPage();
            }
            private void navigateToNotifPage() {
                Intent intent = new Intent(desc_notif.this, notif.class);
                startActivity(intent);
                finish();
            }

            private void updateStatus() {
                if (key != null) {
                    // Update the status in the "tawar" node
                    DatabaseReference tawarReference = FirebaseDatabase.getInstance().getReference("tawar").child(key);
                    tawarReference.child("status").setValue("diterima");

                    // Save data in history
                    DatabaseReference historyReference = FirebaseDatabase.getInstance().getReference("history");
                    String historyKey = historyReference.push().getKey();

                    HistoryData historyItem = new HistoryData(
                            getIntent().getStringExtra("user_idPenerima"),
                            getIntent().getStringExtra("usernamePenerima"),
                            getIntent().getStringExtra("dataTitlePenerima"),
                            getIntent().getStringExtra("dataDetailPenerima"),
                            getIntent().getStringExtra("dataBarterPenerima"),
                            getIntent().getStringExtra("dataImagePenerima"),
                            getIntent().getStringExtra("user_idTukar"),
                            getIntent().getStringExtra("usernameTukar"),
                            getIntent().getStringExtra("dataTitleTukar"),
                            getIntent().getStringExtra("dataDetailTukar"),
                            getIntent().getStringExtra("dataBarterTukar"),
                            getIntent().getStringExtra("dataImageTukar"),
                            "diterima"
                    );

                    historyReference.child(historyKey).setValue(historyItem);

                    showNotification("Tawaran Diterima", "Anda telah menerima tawaran.");

                    Toast.makeText(desc_notif.this, "Status Tawaran Diterima", Toast.LENGTH_SHORT).show();
                }
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
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(desc_notif.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_send)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setSound(defaultSoundUri)
                        .setAutoCancel(true);

                // Show the notification
                notificationManager.notify(0, notificationBuilder.build());
            }
        });
        button_delete = findViewById(R.id.delet);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteConfirmationDialog();
            }

            private void showDeleteConfirmationDialog() {
                AlertDialog.Builder builder = new AlertDialog.Builder(desc_notif.this);
                builder.setTitle("Confirmation")
                        .setMessage("Are you sure you want to delete this data?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteData();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }

            private void deleteData() {
                if (key != null) {
                    // Remove the data from the "tawar" node
                    DatabaseReference tawarReference = FirebaseDatabase.getInstance().getReference("tawar").child(key);
                    tawarReference.removeValue();

                    Toast.makeText(desc_notif.this, "Data Deleted", Toast.LENGTH_SHORT).show();

                    navigateToNotifPage();
                }
            }

            private void navigateToNotifPage() {
                Intent intent = new Intent(desc_notif.this, notif.class);
                startActivity(intent);
                finish();
            }
        });


        button_wa = findViewById(R.id.viaWA);
        button_wa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (key != null) {
                    DatabaseReference tawarReference = FirebaseDatabase.getInstance().getReference("tawar").child(key);
                    tawarReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                String phoneNumber = dataSnapshot.child("telp").getValue(String.class); // Ganti "telp" dengan kunci yang sesuai di tabel "tawar"

                                if (phoneNumber != null) {
                                    // Buat Uri untuk WhatsApp API
                                    String message = "Halo saya dari bplant, ingin melakukan penawaran terkait dengan tumbuhan anda";
                                    String uri = "https://api.whatsapp.com/send?phone=" + phoneNumber + "&text=" + Uri.encode(message);

                                    // Buat Intent dengan Uri
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setData(Uri.parse(uri));

                                    // Mulai aktivitas untuk membuka WhatsApp
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(desc_notif.this, "Nomor telepon tidak tersedia", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(desc_notif.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(desc_notif.this, "Key tidak tersedia", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}