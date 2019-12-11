package com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Admin.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailsSamanAdminActivity extends AppCompatActivity {

    TextView textView_nama,textView_tarikh_masa,textView_no_pelajar,textView_no_tel,textView_kesalahan_baju,
            textView_kesalahan_seluar,textView_kesalahan_kasut,textView_kesalahan_rambut,textView_saman_oleh,textView_harga;
    ImageView imageView_saman,imageView_back;
    String status_discount;
    Button button_terima,button_tolak;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_saman_admin);

        mDatabase = FirebaseDatabase.getInstance().getReference("saman");

        textView_nama = findViewById(R.id.textView_nama);
        textView_tarikh_masa = findViewById(R.id.textView_tarikh_masa);
        textView_no_pelajar = findViewById(R.id.textView_no_pelajar);
        textView_no_tel = findViewById(R.id.textView_no_tel);
        textView_kesalahan_baju = findViewById(R.id.textView_kesalahan_baju);
        textView_kesalahan_seluar = findViewById(R.id.textView_kesalahan_seluar);
        textView_kesalahan_kasut = findViewById(R.id.textView_kesalahan_kasut);
        textView_kesalahan_rambut = findViewById(R.id.textView_kesalahan_rambut);
        textView_saman_oleh = findViewById(R.id.textView_saman_oleh);
        imageView_saman = findViewById(R.id.imageView_saman);
        textView_harga = findViewById(R.id.textView_harga);
        imageView_back = findViewById(R.id.imageView_back);
        button_terima = findViewById(R.id.button_terima);
        button_tolak = findViewById(R.id.button_tolak);

        textView_nama.setText(getIntent().getStringExtra("1"));
        textView_tarikh_masa.setText(getIntent().getStringExtra("2"));
        textView_no_pelajar.setText(getIntent().getStringExtra("3"));
        textView_no_tel.setText(getIntent().getStringExtra("4"));
        textView_kesalahan_baju.setText(getIntent().getStringExtra("5"));
        textView_kesalahan_seluar.setText(getIntent().getStringExtra("6"));
        textView_kesalahan_kasut.setText(getIntent().getStringExtra("7"));
        textView_kesalahan_rambut.setText(getIntent().getStringExtra("8"));
        textView_saman_oleh.setText(getIntent().getStringExtra("9"));
        Picasso.get().load(getIntent().getStringExtra("10")).into(imageView_saman);
        textView_harga.setText(getIntent().getStringExtra("11"));
        status_discount = getIntent().getStringExtra("12");

        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if(status_discount.equals("0")){
            button_terima.setText("TIADA PERMOHONAN DISKAUN");
            button_tolak.setVisibility(View.GONE);
            button_terima.setBackgroundColor(Color.BLUE);
        }
        if(status_discount.equals("1")){
            button_terima.setText("PERMOHONAN DISKAUN DITERIMA");
            button_terima.setBackgroundColor(Color.BLUE);
            button_tolak.setVisibility(View.GONE);
        }
        if(status_discount.equals("2")){
            button_tolak.setText("PERMOHONAN DISKAUN DITOLAK");
            button_tolak.setBackgroundColor(Color.RED);
            button_terima.setVisibility(View.GONE);
        }
        if(status_discount.equals("3")){
            button_terima.setText("DITERIMA");
            button_tolak.setText("DITOLAK");

            button_terima.setBackgroundColor(Color.BLUE);
            button_tolak.setBackgroundColor(Color.RED);
        }


        button_terima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button_terima.getText().toString().equals("DITERIMA")){
                    button_terima.setText("PERMOHONAN DISKAUN DITERIMA");
                    button_tolak.setVisibility(View.GONE);
                    terimaDiskaun();
                }
            }
        });

        button_tolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button_tolak.getText().toString().equals("DITOLAK")){
                    button_tolak.setText("PERMOHONAN DISKAUN DITOLAK");
                    button_terima.setVisibility(View.GONE);
                    tolakDiskaun();
                }
            }
        });
    }

    private void terimaDiskaun(){
        mDatabase.orderByChild("studentID").equalTo(textView_no_pelajar.getText().toString())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot saman: dataSnapshot.getChildren()) {
                                if(saman.child("dateSaman").getValue().toString().equals(textView_tarikh_masa.getText().toString())){
                                    saman.getRef().child("statusDiscount").setValue("1");
                                }
                            }

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }

    private void tolakDiskaun(){
        mDatabase.orderByChild("studentID").equalTo(textView_no_pelajar.getText().toString())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot saman: dataSnapshot.getChildren()) {
                                if(saman.child("dateSaman").getValue().toString().equals(textView_tarikh_masa.getText().toString())){
                                    saman.getRef().child("statusDiscount").setValue("2");
                                }
                            }

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }
}
