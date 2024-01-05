package com.example.ptbtb;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class edit_tanaman extends AppCompatActivity {

    AppCompatImageView button_back;
    EditText editTitle, editDetail,  editBarter;
    ImageView editImage;
    Button saveButton,deleteButton;
    String key, oldImageURL;
    StorageReference storageReference;
    Uri uri;
    String imageURL;
    String title, detail, barter;
    DatabaseReference mDatabase;
    private String user_id, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tanaman);

        editTitle = findViewById(R.id.editTextTextTitle);
        editDetail = findViewById(R.id.editTextTextDetail);
        //editLocation = findViewById(R.id.editTextTextLocation);
        editBarter = findViewById(R.id.editTextTextBarter);
        saveButton = findViewById(R.id.button_save);
        editImage = findViewById(R.id.imageView2);
        deleteButton = findViewById(R.id.button_delete);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            editImage.setImageURI(uri);
                        } else {
                            Toast.makeText(edit_tanaman.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            editDetail.setText(bundle.getString("dataDetail"));
            editTitle.setText(bundle.getString("dataTitle"));
            editBarter.setText(bundle.getString("dataBarter"));
            username = bundle.getString("username");
            user_id = bundle.getString("user_id");
            key = bundle.getString("Key");
            oldImageURL = bundle.getString("dataImage");
            Picasso.get().load(bundle.getString("dataImage")).into(editImage);

        }

        if (key != null) {
            mDatabase = FirebaseDatabase.getInstance().getReference("Postingan").child(key);

        } else {
            // Tanggapan yang sesuai jika existingKey bernilai null
            // Misalnya, Anda bisa membatalkan pembuatan objek mDatabase atau memberikan nilai default.
            Toast.makeText(this, "existingKey is null", Toast.LENGTH_SHORT).show();
            // Tambahkan tanggapan atau log sesuai kebutuhan aplikasi Anda.
        }
        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();

            }

            public void saveData(){
                if (uri != null) {
                    storageReference = FirebaseStorage.getInstance().getReference().child("Images Postingan").child(uri.getLastPathSegment());

                    // Lanjutkan dengan operasi upload file
                    AlertDialog.Builder builder = new AlertDialog.Builder(edit_tanaman.this);
                    builder.setCancelable(false);
                    builder.setView(R.layout.progress_layout);
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> uriTask) {
                                    if (uriTask.isSuccessful()) {
                                        Uri urlImage = uriTask.getResult();
                                        imageURL = urlImage.toString();
                                        updateData();
                                        dialog.dismiss();

                                        // Setelah berhasil memperbarui data, kembali ke aktivitas edit_tanaman
                                        Intent intent = new Intent(edit_tanaman.this, profile.class);
                                        intent.putExtra("dataDetail", detail);
                                        intent.putExtra("dataTitle", title);
                                        intent.putExtra("dataBarter", barter);
                                        intent.putExtra("username", username);
                                        intent.putExtra("user_id", user_id);
                                        intent.putExtra("Key", key);
                                        intent.putExtra("dataImage", imageURL);
                                        startActivity(intent);
                                    } else {
                                        dialog.dismiss();
                                        Toast.makeText(edit_tanaman.this, "Failed to get download URL", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            dialog.dismiss();
                            Toast.makeText(edit_tanaman.this, "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }


        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Panggil metode untuk menghapus data
                deleteData();
            }

            private void deleteData() {
                AlertDialog.Builder builder = new AlertDialog.Builder(edit_tanaman.this);
                builder.setTitle("Delete Confirmation");
                builder.setMessage("Are you sure you want to delete this data?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Panggil metode untuk menghapus data setelah pengguna menekan "Yes"
                        performDelete();
                    }

                    private void performDelete() {
                        if (mDatabase != null) {
                            mDatabase.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // Hapus juga gambar dari Firebase Storage
                                        StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageURL);
                                        reference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(edit_tanaman.this, "Data deleted successfully", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(edit_tanaman.this, profile.class);
                                                intent.putExtra("username", username);
                                                intent.putExtra("user_id", user_id);  // Sertakan data yang dibutuhkan untuk profil jika ada
                                                startActivity(intent);                                                finish(); // Kembali ke halaman sebelumnya atau tutup aktivitas
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(edit_tanaman.this, "Failed to delete image: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    } else {
                                        Toast.makeText(edit_tanaman.this, "Failed to delete data: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(edit_tanaman.this, "mDatabase is null", Toast.LENGTH_SHORT).show();
                        }           }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Batal penghapusan, tutup dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.show();            }
        });

    }
    private void updateData() {
        if (mDatabase != null) {
            title = editTitle.getText().toString().trim();
            detail = editDetail.getText().toString().trim();
            barter = editBarter.getText().toString();
            list_profile dataClass = new list_profile(user_id, username,title, detail, barter, imageURL);
            mDatabase.setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageURL);
                        reference.delete();
                        Toast.makeText(edit_tanaman.this, "Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(edit_tanaman.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(edit_tanaman.this, "mDatabase is null", Toast.LENGTH_SHORT).show();
        }
    }




}
