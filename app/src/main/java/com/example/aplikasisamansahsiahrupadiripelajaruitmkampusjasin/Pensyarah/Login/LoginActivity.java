package com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.MainActivity;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Register.RegisterActivity;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.R;

public class LoginActivity extends AppCompatActivity {

    //DECLARE
    ImageView imageView_back;
    EditText editText_email,editText_password;
    Button button_login,button_daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);

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

                }
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
