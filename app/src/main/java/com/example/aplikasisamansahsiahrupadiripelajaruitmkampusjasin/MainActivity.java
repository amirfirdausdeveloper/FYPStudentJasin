package com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    //DECLARATION
    Button button_pensyarah,button_admin,button_pelajar;
    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.WAKE_LOCK,
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},1);

        button_pensyarah = findViewById(R.id.button_pensyarah);
        button_admin = findViewById(R.id.button_admin);
        button_pelajar = findViewById(R.id.button_pelajar);

        //ONCLICK BUTTON
        button_pensyarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(next);
            }
        });

        button_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(getApplicationContext(), com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Admin.Login.LoginActivity.class);
                startActivity(next);
            }
        });

        button_pelajar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(getApplicationContext(), com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pelajar.Login.LoginActivity.class);
                startActivity(next);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis())  moveTaskToBack(true);
        else Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }
}
