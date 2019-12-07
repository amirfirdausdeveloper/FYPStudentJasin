package com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.R;
import com.squareup.picasso.Picasso;

public class DetailsSamanPensyarahActivity extends AppCompatActivity {

    TextView textView_nama,textView_tarikh_masa,textView_no_pelajar,textView_no_tel,textView_kesalahan_baju,
            textView_kesalahan_seluar,textView_kesalahan_kasut,textView_kesalahan_rambut,textView_saman_oleh,textView_harga;
    ImageView imageView_saman,imageView_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_saman_pensyarah);

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
        textView_saman_oleh.setText(getIntent().getStringExtra("11"));

        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
