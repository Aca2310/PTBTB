package com.example.ptbtb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.app.Activity;
import android.content.BroadcastReceiver;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class edit_profile extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;

    private AppCompatImageView button_back;
    private ImageView imageViewProfile;
    private Uri imageUri;
    private Uri previousImageUri;

    private Button save_edit;
    private EditText editName, editEmail, editUsername, editTelp, editAddres;
    private String nameUser, emailUser, usernameUser, TelpUser, AddresUser;
    private DatabaseReference reference;
    private StorageReference storageReference;
    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        button_back = findViewById(R.id.button_back);
        imageViewProfile = findViewById(R.id.imageView8);
        editName = findViewById(R.id.namaRegist);
        editEmail = findViewById(R.id.email);
        editUsername = findViewById(R.id.username);
        editTelp = findViewById(R.id.telp);
        editAddres = findViewById(R.id.Address);
        save_edit = findViewById(R.id.save_edit);
        TextView TambahFoto = findViewById(R.id.textView14);

        reference = FirebaseDatabase.getInstance().getReference("users");
        storageReference = FirebaseStorage.getInstance().getReference("profile_pictures");

        Intent intent = getIntent();
        usernameUser = intent.getStringExtra("username");
        String nama = intent.getStringExtra("nama");

        editUsername.setFocusable(false);
        editUsername.setFocusableInTouchMode(false);
        editUsername.setClickable(false);

        TextView etnama = findViewById(R.id.nama_edit);
        TextView textViewUsername = findViewById(R.id.username_edit);

        etnama.setText(nama);
        textViewUsername.setText("@" + usernameUser);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        TambahFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImagePicker();
            }
        });

        save_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNamaChanged() || isUsernameChanged() || isEmailChanged() || isTelpChanged() || isLokasiChanged()) {
                    Toast.makeText(edit_profile.this, "Data tersimpan", Toast.LENGTH_SHORT).show();
                    saveProfileChanges();
                } else {
                    Toast.makeText(edit_profile.this, "Tidak ada perubahan ditemukan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        showData();
        loadProfileImage();
    }

    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), PICK_IMAGE_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            previousImageUri = imageUri;
            imageViewProfile.setImageURI(imageUri);
        }
    }

    private boolean isProfileChanged() {
        return isNamaChanged() || isUsernameChanged() || isEmailChanged() || isTelpChanged() || isLokasiChanged() || isProfileImageChanged();
    }

    private void updateProfileInformation() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String userId = currentUser.getUid();

            reference.child(userId).child("nama").setValue(editName.getText().toString());
            reference.child(userId).child("email").setValue(editEmail.getText().toString());
            reference.child(userId).child("telp").setValue(editTelp.getText().toString());
            reference.child(userId).child("addres").setValue(editAddres.getText().toString());
        }
    }

    private void uploadImage() {
        if (imageUri != null) {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();

            if (currentUser != null) {
                String userId = currentUser.getUid();

                StorageReference fileReference = storageReference.child(userId + "." + getFileExtension(imageUri));

                fileReference.putFile(imageUri)
                        .addOnSuccessListener(taskSnapshot -> {
                            Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                            downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if (task.isSuccessful()) {
                                        String downloadUrl = task.getResult().toString();
                                        updateImageUrl(downloadUrl);
                                        loadProfileImage();
                                        Toast.makeText(edit_profile.this, "Gambar profil berhasil diunggah", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(edit_profile.this, "Gagal mendapatkan URL gambar", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(edit_profile.this, "Gagal mengunggah gambar", Toast.LENGTH_SHORT).show();
                        });
            }
        }
    }

    private void updateImageUrl(String downloadUrl) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String userId = currentUser.getUid();

            reference.child(userId).child("imageUrl").setValue(downloadUrl).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        sendBroadcast(new Intent("UPDATE_PROFILE_IMAGE"));
                    }
                }
            });
        }
    }

    private void loadProfileImage() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String userId = currentUser.getUid();

            reference.child(userId).child("imageUrl").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful()) {
                        String imageUrl = task.getResult().getValue(String.class);
                        if (imageUrl != null && !imageUrl.isEmpty()) {
                            Picasso.get().invalidate(imageUrl);
                            Picasso.get().load(imageUrl).into(imageViewProfile);
                        } else {
                            Picasso.get().load(R.drawable.profile).into(imageViewProfile);
                        }
                    }
                }
            });
        }
    }

    private boolean isUsernameChanged() {
        return !usernameUser.equals(editUsername.getText().toString());
    }

    private boolean isProfileImageChanged() {
        return !Objects.equals(imageUri, previousImageUri);
    }

    private boolean isNamaChanged() {
        return !nameUser.equals(editName.getText().toString());
    }

    private boolean isEmailChanged() {
        return !emailUser.equals(editEmail.getText().toString());
    }

    private boolean isTelpChanged() {
        return !TelpUser.equals(editTelp.getText().toString());
    }

    private boolean isLokasiChanged() {
        String newAddress = editAddres.getText().toString();

        if (AddresUser == null || AddresUser.isEmpty()) {
            return true;
        } else {
            return !AddresUser.equals(newAddress);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void saveProfileChanges() {
        if (isProfileChanged()) {
            updateProfileInformation();
            uploadImage();
            Toast.makeText(edit_profile.this, "Data tersimpan", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(edit_profile.this, "Tidak ada perubahan ditemukan", Toast.LENGTH_SHORT).show();
        }
    }

    private void showData() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String userId = currentUser.getUid();

            Intent intent = getIntent();
            nameUser = intent.getStringExtra("nama");
            emailUser = intent.getStringExtra("email");
            usernameUser = intent.getStringExtra("username");
            TelpUser = intent.getStringExtra("telp");
            AddresUser = intent.getStringExtra("addres");

            editName.setText(nameUser);
            editEmail.setText(emailUser);
            editUsername.setText(usernameUser);
            editTelp.setText(TelpUser);
            editAddres.setText(AddresUser);

            reference.child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        if (dataSnapshot.exists()) {
                            nameUser = dataSnapshot.child("nama").getValue(String.class);
                            emailUser = dataSnapshot.child("email").getValue(String.class);
                            TelpUser = dataSnapshot.child("telp").getValue(String.class);
                            AddresUser = dataSnapshot.child("addres").getValue(String.class);

                            editName.setText(nameUser);
                            editEmail.setText(emailUser);
                            editTelp.setText(TelpUser);
                            editAddres.setText(AddresUser);
                        }
                    }
                }
            });
        }
    }
}
