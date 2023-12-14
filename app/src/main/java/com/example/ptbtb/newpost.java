package com.example.ptbtb;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.time.Instant;

public class newpost extends AppCompatActivity {

    ImageView uploadImage;
    Button buttonup;
    EditText Nptitle, Npdetail, Npbarter;
    String imageURL;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpost);

        uploadImage = findViewById(R.id.uploadImage);
        Nptitle = findViewById(R.id.Nptitle);
        Npdetail = findViewById(R.id.Npdetail);
        Npbarter = findViewById(R.id.Npbarter);
        buttonup = findViewById(R.id.buttonup);

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
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        buttonup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }

            public void saveData() {
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
                                                // Sekarang Anda memiliki username, Anda dapat menggunakannya di DataClass
                                                uploadData(currentUserUid, username);
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

            public void uploadData(String user_id, String username) {
                String title = Nptitle.getText().toString();
                String detail = Npdetail.getText().toString();
                String barter = Npbarter.getText().toString();

                DataClass dataClass = new DataClass(user_id, username, title, detail, barter, imageURL);

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
            }
        });
    }
}
