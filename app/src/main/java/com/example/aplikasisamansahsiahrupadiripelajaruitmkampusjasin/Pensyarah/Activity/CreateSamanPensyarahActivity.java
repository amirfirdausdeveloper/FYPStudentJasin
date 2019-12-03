package com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pelajar.Activity.MainDashboardPelajar;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateSamanPensyarahActivity extends AppCompatActivity {

    String studentID,jantina;
    TextView textView_nama,textView_no_tel,textView_no_pelajar,textView_no_kp,textView_kod_program,textView_tarikh_masa;
    private DatabaseReference mDatabase;

    //DECLARE UTK KESALAH LELAKI
    LinearLayout linear_lelaki;
    ImageView imageView_baju,imageView_seluar,imageView_kasut,imageView_rambut;
    RadioGroup radioGroup_baju,radioGroup_seluar,radioGroup_kasut,radioGroup_rambut;

    //DECLARE UTK KESALAH PEREMPUAN
    LinearLayout linear_perempuan;
    ImageView imageView_baju_p,imageView_seluar_p,imageView_kasut_p,imageView_rambut_p;
    RadioGroup radioGroup_baju_p,radioGroup_seluar_p,radioGroup_kasut_p,radioGroup_rambut_p;

    String clickbaju="no",clickseluar="no",clickkasut="no",clickrambut="no";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_saman_pensyarah);

        mDatabase = FirebaseDatabase.getInstance().getReference("pelajar");
        studentID = getIntent().getStringExtra("carian_id");
        textView_nama = findViewById(R.id.textView_nama);
        textView_no_tel = findViewById(R.id.textView_no_tel);
        textView_no_pelajar = findViewById(R.id.textView_no_pelajar);
        textView_no_kp = findViewById(R.id.textView_no_kp);
        textView_kod_program = findViewById(R.id.textView_kod_program);
        textView_tarikh_masa = findViewById(R.id.textView_tarikh_masa);

        linear_lelaki = findViewById(R.id.linear_lelaki);
        imageView_baju = findViewById(R.id.imageView_baju);
        imageView_seluar = findViewById(R.id.imageView_seluar);
        imageView_kasut = findViewById(R.id.imageView_kasut);
        imageView_rambut = findViewById(R.id.imageView_rambut);
        radioGroup_baju = findViewById(R.id.radioGroup_baju);
        radioGroup_seluar = findViewById(R.id.radioGroup_seluar);
        radioGroup_kasut = findViewById(R.id.radioGroup_kasut);
        radioGroup_rambut = findViewById(R.id.radioGroup_rambut);

        linear_perempuan = findViewById(R.id.linear_perempuan);
        imageView_baju_p = findViewById(R.id.imageView_baju_p);
        imageView_seluar_p = findViewById(R.id.imageView_seluar_p);
        imageView_kasut_p = findViewById(R.id.imageView_kasut_p);
        imageView_rambut_p = findViewById(R.id.imageView_rambut_p);
        radioGroup_baju_p = findViewById(R.id.radioGroup_baju_p);
        radioGroup_seluar_p = findViewById(R.id.radioGroup_seluar_p);
        radioGroup_kasut_p = findViewById(R.id.radioGroup_kasut_p);
        radioGroup_rambut_p = findViewById(R.id.radioGroup_rambut_p);

        linear_lelaki.setVisibility(View.GONE);
        radioGroup_baju.setVisibility(View.GONE);
        radioGroup_seluar.setVisibility(View.GONE);
        radioGroup_kasut.setVisibility(View.GONE);
        radioGroup_rambut.setVisibility(View.GONE);

        linear_perempuan.setVisibility(View.GONE);
        radioGroup_baju_p.setVisibility(View.GONE);
        radioGroup_seluar_p.setVisibility(View.GONE);
        radioGroup_kasut_p.setVisibility(View.GONE);
        radioGroup_rambut_p.setVisibility(View.GONE);

        hideShowLelaki();
        hideShowPerempuan();


        //get now date n time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        textView_tarikh_masa.setText(currentDateandTime);
        getDetails();

    }

    private void hideShowPerempuan() {
        imageView_baju_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickbaju.equals("no")){
                    radioGroup_baju_p.setVisibility(View.VISIBLE);
                    clickbaju = "yes";
                }else{
                    radioGroup_baju_p.setVisibility(View.GONE);
                    clickbaju = "no";
                }
            }
        });

        imageView_seluar_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickseluar.equals("no")){
                    radioGroup_seluar_p.setVisibility(View.VISIBLE);
                    clickseluar = "yes";
                }else{
                    radioGroup_seluar_p.setVisibility(View.GONE);
                    clickseluar = "no";
                }
            }
        });

        imageView_kasut_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickkasut.equals("no")){
                    radioGroup_kasut_p.setVisibility(View.VISIBLE);
                    clickkasut = "yes";
                }else{
                    radioGroup_kasut_p.setVisibility(View.GONE);
                    clickkasut = "no";
                }
            }
        });

        imageView_rambut_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickrambut.equals("no")){
                    radioGroup_rambut_p.setVisibility(View.VISIBLE);
                    clickrambut = "yes";
                }else{
                    radioGroup_rambut_p.setVisibility(View.GONE);
                    clickrambut = "no";
                }
            }
        });
    }

    private void hideShowLelaki() {
        imageView_baju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickbaju.equals("no")){
                    radioGroup_baju.setVisibility(View.VISIBLE);
                    clickbaju = "yes";
                }else{
                    radioGroup_baju.setVisibility(View.GONE);
                    clickbaju = "no";
                }
            }
        });

        imageView_seluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickseluar.equals("no")){
                    radioGroup_seluar.setVisibility(View.VISIBLE);
                    clickseluar = "yes";
                }else{
                    radioGroup_seluar.setVisibility(View.GONE);
                    clickseluar = "no";
                }
            }
        });

        imageView_kasut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickkasut.equals("no")){
                    radioGroup_kasut.setVisibility(View.VISIBLE);
                    clickkasut = "yes";
                }else{
                    radioGroup_kasut.setVisibility(View.GONE);
                    clickkasut = "no";
                }
            }
        });

        imageView_rambut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickrambut.equals("no")){
                    radioGroup_rambut.setVisibility(View.VISIBLE);
                    clickrambut = "yes";
                }else{
                    radioGroup_rambut.setVisibility(View.GONE);
                    clickrambut = "no";
                }
            }
        });
    }

    private void getDetails() {
        mDatabase.orderByChild("id").equalTo(studentID)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot pelajar: dataSnapshot.getChildren()) {
                                textView_nama.setText(pelajar.child("name").getValue().toString());
                                textView_no_tel.setText(pelajar.child("notel").getValue().toString());
                                textView_no_pelajar.setText(pelajar.child("id").getValue().toString());
                                textView_no_kp.setText(pelajar.child("nokp").getValue().toString());
                                textView_kod_program.setText(pelajar.child("kodprogram").getValue().toString());
                                jantina = pelajar.child("jantina").getValue().toString();

                                if(jantina.toLowerCase().equals("lelaki")){
                                    linear_lelaki.setVisibility(View.VISIBLE);
                                    linear_perempuan.setVisibility(View.GONE);
                                }else{
                                    linear_lelaki.setVisibility(View.GONE);
                                    linear_perempuan.setVisibility(View.VISIBLE);
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
