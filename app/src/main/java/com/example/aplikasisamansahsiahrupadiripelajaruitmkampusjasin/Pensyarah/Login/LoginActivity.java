package com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.MainActivity;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Activity.MainDashboardPensyarah;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Class.PensyarahClass;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Register.RegisterActivity;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    //DECLARE
    ImageView imageView_back;
    EditText editText_email,editText_password;
    Button button_login,button_daftar;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);

        //FIREBASE DATABASE REF
        mDatabase = FirebaseDatabase.getInstance().getReference("pensyarah");

        imageView_back = findViewById(R.id.imageView_back);
        editText_email = findViewById(R.id.editText_email);
        editText_password = findViewById(R.id.editText_password);
        button_login = findViewById(R.id.button_login);
        button_daftar = findViewById(R.id.button_daftar);


        //ON CLICK BACK
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //ON CLICK BUTTON DAFTAR
        button_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(next);
            }
        });

        //ON CLICK BUTTON LOGIN
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_email.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Sila masukkan emel",Toast.LENGTH_LONG).show();
                }else if(editText_password.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Sila masukkan katalaluan",Toast.LENGTH_LONG).show();
                }else{
                    login();
                }
            }
        });
    }

    //LOGIN FUNCTION
    private void login() {
        mDatabase.orderByChild("email").equalTo(editText_email.getText().toString())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot pensyarah: dataSnapshot.getChildren()) {
                                if(editText_password.getText().toString().equals(pensyarah.child("katalaluan").getValue().toString())){

                                    Intent next = new Intent(getApplicationContext(), MainDashboardPensyarah.class);
                                    next.putExtra("email",editText_email.getText().toString());
                                    next.putExtra("nama",pensyarah.child("name").getValue().toString());
                                    startActivity(next);
                                }else{
                                    Toast.makeText(getApplicationContext(), "Katalaluan tidak sah !!!", Toast.LENGTH_LONG).show();
                                }
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "Emel tidak didaftar !!!", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }

    //UNTUK BACK
    @Override
    public void onBackPressed() {
       Intent next = new Intent(getApplicationContext(), MainActivity.class);
       startActivity(next);
    }
}
