package com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pelajar.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Activity.MainDashboardPensyarah;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Locale;

public class DetailsSamanPelajarActivity extends AppCompatActivity {

    TextView textView_nama,textView_tarikh_masa,textView_no_pelajar,textView_no_tel,textView_kesalahan_baju,
            textView_kesalahan_seluar,textView_kesalahan_kasut,textView_kesalahan_rambut,textView_saman_oleh,textView_harga;
    ImageView imageView_saman,imageView_back;
    String status_discount;
    Button button_discount;
    String current_date,current_month,saman_date,saman_month;
    int days_diff;
    private DatabaseReference mDatabase;

    int JAN = 31,FEB = 28,MAR = 31,APR = 30,MAY = 31,JUN = 30,JUL = 31,AUG = 31,SEP = 30,OCT = 31,NOV = 30,DEC = 31;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_saman_pelajar);

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
        button_discount = findViewById(R.id.button_discount);

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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm", Locale.getDefault());
        current_date = sdf.format(new Date()).substring(9,11);
        current_month = sdf.format(new Date()).substring(5,8);

        saman_date = getIntent().getStringExtra("2").substring(9,11);
        saman_month = getIntent().getStringExtra("2").substring(5,8);

        checkingDaysBetweenDate(current_date,current_month,saman_date,saman_month);

        if(days_diff <= 5){
            if(status_discount.equals("0")){
                button_discount.setText("MOHON DISKAUN");
                button_discount.setBackgroundColor(Color.BLUE);
            }
            if(status_discount.equals("1")){
                button_discount.setText("PERMOHONAN DISKAUN DITERIMA");
                button_discount.setBackgroundColor(Color.GREEN);
            }
            if(status_discount.equals("2")){
                button_discount.setText("PERMOHONAN DISKAUN DITERIMA");
                button_discount.setBackgroundColor(Color.RED);
            }
            if(status_discount.equals("3")){
                button_discount.setText("PERMOHONAN DISKAUN DIPROSES");
                button_discount.setBackgroundColor(Color.MAGENTA);
            }
        }else {
            button_discount.setVisibility(View.GONE);
        }


        //BUTTON DISCOUNT CLICK
        button_discount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button_discount.getText().toString().equals("MOHON DISKAUN")){
                    mohonDiskaun();
                    button_discount.setText("PERMOHONAN DISKAUN DIPROSES");
                    button_discount.setBackgroundColor(Color.MAGENTA);
                }
            }
        });
    }


    private void checkingDaysBetweenDate(String current_date, String current_month, String saman_date, String saman_month){
        if(current_month.toLowerCase().equals(saman_month.toLowerCase())){
            days_diff = Integer.parseInt(current_date) - Integer.parseInt(saman_date);
        }else{
            if(saman_month.toLowerCase().equals("jan")){
                days_diff = (Integer.parseInt(saman_date) + JAN) - Integer.parseInt(current_date);
            }
            if(saman_month.toLowerCase().equals("feb")){
                days_diff = (Integer.parseInt(current_date) + FEB) - Integer.parseInt(saman_date);
            }
            if(saman_month.toLowerCase().equals("mar")){
                days_diff = (Integer.parseInt(current_date) + MAR) - Integer.parseInt(saman_date);
            }
            if(saman_month.toLowerCase().equals("apr")){
                days_diff = (Integer.parseInt(current_date) + APR) - Integer.parseInt(saman_date);
            }
            if(saman_month.toLowerCase().equals("may")){
                days_diff = (Integer.parseInt(current_date) + MAY) - Integer.parseInt(saman_date);
            }
            if(saman_month.toLowerCase().equals("jun")){
                days_diff = (Integer.parseInt(current_date) + JUN) - Integer.parseInt(saman_date);
            }
            if(saman_month.toLowerCase().equals("jul")){
                days_diff = (Integer.parseInt(current_date) + JUL) - Integer.parseInt(saman_date);
            }
            if(saman_month.toLowerCase().equals("aug")){
                days_diff = (Integer.parseInt(current_date) + AUG) - Integer.parseInt(saman_date);
            }
            if(saman_month.toLowerCase().equals("sep")){
                days_diff = (Integer.parseInt(current_date) + SEP) - Integer.parseInt(saman_date);
            }
            if(saman_month.toLowerCase().equals("oct")){
                days_diff = (Integer.parseInt(current_date) + OCT) - Integer.parseInt(saman_date);
            }
            if(saman_month.toLowerCase().equals("nov")){
                days_diff = (Integer.parseInt(current_date) + NOV) - Integer.parseInt(saman_date);
            }
            if(saman_month.toLowerCase().equals("dec")){
                days_diff = (Integer.parseInt(current_date) + DEC) - Integer.parseInt(saman_date);
            }

            Log.d("DIFF ", String.valueOf(days_diff));

        }
    }

    private void mohonDiskaun(){
        mDatabase.orderByChild("studentID").equalTo(textView_no_pelajar.getText().toString())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot saman: dataSnapshot.getChildren()) {
                                if(saman.child("dateSaman").getValue().toString().equals(textView_tarikh_masa.getText().toString())){
                                    saman.getRef().child("statusDiscount").setValue("3");
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
