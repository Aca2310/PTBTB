package com.example.ptbtb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class edit_profile extends AppCompatActivity {
    AppCompatImageView button_back;


    Button save_edit;
    EditText editName, editEmail, editUsername, editTelp, editAddres;
    String nameUser, emailUser, usernameUser, TelpUser, AddresUser;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        button_back = findViewById(R.id.button_back);

        reference = FirebaseDatabase.getInstance().getReference("users");
        editName = findViewById(R.id.namaRegist);
        editEmail = findViewById(R.id.email);
        editUsername = findViewById(R.id.username);
        editTelp= findViewById(R.id.telp);
        editAddres= findViewById(R.id.Address);
        save_edit = (Button) findViewById(R.id.save_edit);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String nama = intent.getStringExtra("nama");


        TextView textViewName = findViewById(R.id.nama_edit);
        TextView textViewUsername = findViewById(R.id.username_edit);


        textViewName.setText(nama);
        textViewUsername.setText("@"+username);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(edit_profile.this, profile.class));
            }
        });


        showData();

        save_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (isNamaChanged() || isUsernameChanged() || isEmailChanged()|| isTelpChanged() || isLokasiChanged()){
                    Toast.makeText(edit_profile.this, "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(edit_profile.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private boolean isUsernameChanged() {
        if (!usernameUser.equals(editUsername.getText().toString())){
            reference.child(usernameUser).child("username").setValue(editUsername.getText().toString());
            usernameUser = editUsername.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isNamaChanged() {
        if (!nameUser.equals(editName.getText().toString())){
            reference.child(usernameUser).child("nama").setValue(editName.getText().toString());
            nameUser = editName.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isEmailChanged() {
        if (!emailUser.equals(editEmail.getText().toString())){
            reference.child(usernameUser).child("email").setValue(editEmail.getText().toString());
            emailUser = editEmail.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isTelpChanged() {
        if (!TelpUser.equals(editTelp.getText().toString())){
            reference.child(usernameUser).child("telp").setValue(editTelp.getText().toString());
            TelpUser = editTelp.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isLokasiChanged() {
        if (!AddresUser.equals(editAddres.getText().toString())){
            reference.child(usernameUser).child("addres").setValue(editAddres.getText().toString());
            AddresUser = editAddres.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    public void showData(){
        Intent intent = getIntent();
        nameUser = intent.getStringExtra("nama");
        emailUser = intent.getStringExtra("email");
        usernameUser = intent.getStringExtra("username");
        TelpUser = intent.getStringExtra("telp");
        AddresUser = intent.getStringExtra("address");

        editName.setText(nameUser);
        editEmail.setText(emailUser);
        editUsername.setText(usernameUser);
        editTelp.setText(TelpUser);
        editAddres.setText(AddresUser);
    }


}