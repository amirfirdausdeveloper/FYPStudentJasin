package com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Admin.Register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Admin.Class.AdminClass;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Admin.Login.LoginActivity;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Class.PensyarahClass;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    //DECLARE
    ImageView imageView_back;
    EditText editText_email, editText_jawatan, editText_no_telefon, editText_nama, editText_kata_laluan;
    Button button_daftar;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);
        //FIREBASE DATABASE REF
        mDatabase = FirebaseDatabase.getInstance().getReference("admin");

        imageView_back = findViewById(R.id.imageView_back);
        editText_email = findViewById(R.id.editText_email);
        editText_jawatan = findViewById(R.id.editText_jawatan);
        editText_no_telefon = findViewById(R.id.editText_no_telefon);
        editText_nama = findViewById(R.id.editText_nama);
        editText_kata_laluan = findViewById(R.id.editText_kata_laluan);
        button_daftar = findViewById(R.id.button_daftar);

        //ONCLICK UNTUK BACK
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //ONCLICK DAFTAR BUTTON
        button_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText_email.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Sila masukkan emel", Toast.LENGTH_LONG).show();
                } else if (editText_jawatan.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Sila masukkan jawatan", Toast.LENGTH_LONG).show();
                } else if (editText_no_telefon.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Sila masukkan no telefon", Toast.LENGTH_LONG).show();
                } else if (editText_nama.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Sila masukkan nama", Toast.LENGTH_LONG).show();
                } else if (editText_kata_laluan.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Sila masukkan katalaluan", Toast.LENGTH_LONG).show();
                } else {

                    //FUNCTION ADD PENSYARAH
                    addAdmin();
                }
            }
        });
    }

    private void addAdmin() {
        mDatabase.orderByChild("email").equalTo(editText_email.getText().toString())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Toast.makeText(getApplicationContext(), "Emel sudah didaftar !!!", Toast.LENGTH_LONG).show();
                        } else {
                            String id = mDatabase.push().getKey();
                            AdminClass adminClass = new AdminClass(
                                    editText_email.getText().toString(),
                                    editText_jawatan.getText().toString(),
                                    editText_kata_laluan.getText().toString(),
                                    editText_nama.getText().toString(),
                                    editText_no_telefon.getText().toString());

                            //Saving the pensyasrah
                            mDatabase.child(id).setValue(adminClass);

                            //setting edittext to blank again
                            editText_email.setText("");
                            editText_jawatan.setText("");
                            editText_kata_laluan.setText("");
                            editText_nama.setText("");
                            editText_no_telefon.setText("");

                            //displaying a success toast
                            Toast.makeText(getApplicationContext(), "Daftar berjaya. Sila log masuk", Toast.LENGTH_LONG).show();
                            Intent next = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(next);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });


    }

    //ONBACK

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}