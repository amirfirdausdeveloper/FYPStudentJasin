package com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pelajar.Activity.MainDashboardPelajar;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Class.PensyarahClass;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Login.LoginActivity;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.R;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.StandardProgressDialog;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CreateSamanPensyarahActivity extends AppCompatActivity {

    String studentID,jantina;
    TextView textView_nama,textView_no_tel,textView_no_pelajar,textView_no_kp,textView_kod_program,
            textView_tarikh_masa,textView_pensyarahName,textView_hargas;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabaseSaman;
    private StorageReference mStorageRef;
    ImageView imageView_back;

    //DECLARE UTK KESALAH LELAKI
    LinearLayout linear_lelaki;
    ImageView imageView_baju,imageView_seluar,imageView_kasut,imageView_rambut;
    LinearLayout radioGroup_baju,radioGroup_seluar,radioGroup_kasut,radioGroup_rambut;
    CheckBox checkbox_L_baju_1,checkbox_L_baju_2,checkbox_L_baju_3,checkbox_L_baju_4,checkbox_L_baju_5;
    CheckBox checkbox_L_seluar_1,checkbox_L_seluar_2;
    CheckBox checkbox_L_kasut_1;
    CheckBox checkbox_L_rambut_1,checkbox_L_rambut_2;

    String kesalahanBajuL = "",kesalahanSeluarL = "",kesalahanKasutL = "",kesalahanRambutL = "";
    //DECLARE UTK KESALAH PEREMPUAN
    LinearLayout linear_perempuan;
    ImageView imageView_baju_p,imageView_seluar_p,imageView_kasut_p,imageView_rambut_p;
    LinearLayout radioGroup_baju_p,radioGroup_seluar_p,radioGroup_kasut_p,radioGroup_rambut_p;
    CheckBox checkbox_p_baju_1,checkbox_p_baju_2,checkbox_p_baju_3,checkbox_p_baju_4,checkbox_p_baju_5,checkbox_p_baju_6;
    CheckBox checkbox_p_seluar_1,checkbox_p_seluar_2;
    CheckBox checkbox_p_kasut_1;
    CheckBox checkbox_p_rambut_1,checkbox_p_rambut_2;

    String clickbaju="no",clickseluar="no",clickkasut="no",clickrambut="no";
    Button button_saman;
    ImageView imageView_saman;
    Uri imageSaman;
    boolean statusImageSaman = false;
    Bitmap selected_image;
    StandardProgressDialog standardProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_saman_pensyarah);

        mDatabase = FirebaseDatabase.getInstance().getReference("pelajar");
        mDatabaseSaman = FirebaseDatabase.getInstance().getReference("saman");
        mStorageRef = FirebaseStorage.getInstance().getReference();
        standardProgressDialog = new StandardProgressDialog(this.getWindow().getContext());

        studentID = getIntent().getStringExtra("carian_id");
        imageView_back = findViewById(R.id.imageView_back);
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        declareRation();

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


        //ONCLICK BUTTON SAMAN
        button_saman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSaman();
            }
        });

        //ONCLICK CAMERA SAMAN
        imageView_saman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });

    }

    public void itemClicked(View v) {
        if(checkbox_p_baju_1.isChecked() || checkbox_p_baju_2.isChecked()|| checkbox_p_baju_3.isChecked()|| checkbox_p_baju_4.isChecked()
                || checkbox_p_baju_5.isChecked()|| checkbox_p_baju_6.isChecked()
                || checkbox_p_seluar_1.isChecked()|| checkbox_p_seluar_2.isChecked()
                || checkbox_p_kasut_1.isChecked()
                || checkbox_p_rambut_1.isChecked()|| checkbox_p_rambut_2.isChecked()
                || checkbox_L_baju_1.isChecked()|| checkbox_L_baju_2.isChecked()|| checkbox_L_baju_3.isChecked()|| checkbox_L_baju_4.isChecked()
                || checkbox_L_baju_5.isChecked()
                || checkbox_L_seluar_1.isChecked()|| checkbox_L_seluar_2.isChecked()
                || checkbox_L_kasut_1.isChecked()
                || checkbox_L_rambut_1.isChecked()|| checkbox_L_rambut_2.isChecked()){
            textView_hargas.setText("RM 50");

        }else {
            textView_hargas.setText("RM 0");
        }
    }


    //ALL DECLARE AT XML
    private void declareRation() {
        textView_hargas =  findViewById(R.id.textView_hargas);
        textView_pensyarahName= findViewById(R.id.textView_pensyarahName);
        textView_pensyarahName.setText(MainDashboardPensyarah.pensyarah_name);
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


        checkbox_p_baju_1 = findViewById(R.id.checkbox_p_baju_1);
        checkbox_p_baju_2 = findViewById(R.id.checkbox_p_baju_2);
        checkbox_p_baju_3 = findViewById(R.id.checkbox_p_baju_3);
        checkbox_p_baju_4 = findViewById(R.id.checkbox_p_baju_4);
        checkbox_p_baju_5 = findViewById(R.id.checkbox_p_baju_5);
        checkbox_p_baju_6 = findViewById(R.id.checkbox_p_baju_6);

        checkbox_p_seluar_1 = findViewById(R.id.checkbox_p_seluar_1);
        checkbox_p_seluar_2 = findViewById(R.id.checkbox_p_seluar_2);

        checkbox_p_kasut_1 = findViewById(R.id.checkbox_p_kasut_1);

        checkbox_p_rambut_1 = findViewById(R.id.checkbox_p_rambut_1);
        checkbox_p_rambut_2 = findViewById(R.id.checkbox_p_rambut_2);

        checkbox_L_baju_1 = findViewById(R.id.checkbox_L_baju_1);
        checkbox_L_baju_2 = findViewById(R.id.checkbox_L_baju_2);
        checkbox_L_baju_3 = findViewById(R.id.checkbox_L_baju_3);
        checkbox_L_baju_4 = findViewById(R.id.checkbox_L_baju_4);
        checkbox_L_baju_5 = findViewById(R.id.checkbox_L_baju_5);

        checkbox_L_seluar_1 = findViewById(R.id.checkbox_L_seluar_1);
        checkbox_L_seluar_2 = findViewById(R.id.checkbox_L_seluar_2);

        checkbox_L_kasut_1 = findViewById(R.id.checkbox_L_kasut_1);

        checkbox_L_rambut_1 = findViewById(R.id.checkbox_L_rambut_1);
        checkbox_L_rambut_2 = findViewById(R.id.checkbox_L_rambut_2);

        button_saman = findViewById(R.id.button_saman);
        imageView_saman = findViewById(R.id.imageView_saman);
    }

    //CAMERA ACTIVITY
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == 1) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                selected_image = Bitmap.createScaledBitmap(thumbnail, 750, 1000, true);
                imageView_saman.setImageBitmap(selected_image);
                statusImageSaman = true;

            }
        }else {
            statusImageSaman = false;
        }

    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "saman", null);
        return Uri.parse(path);
    }

    //CHECKING MASA BUTTON SAMAN ONCLICK
    private void checkSaman(){
        if(checkbox_p_baju_1.isChecked() || checkbox_p_baju_2.isChecked()|| checkbox_p_baju_3.isChecked()|| checkbox_p_baju_4.isChecked()
                || checkbox_p_baju_5.isChecked()|| checkbox_p_baju_6.isChecked()
                || checkbox_p_seluar_1.isChecked()|| checkbox_p_seluar_2.isChecked()
                || checkbox_p_kasut_1.isChecked()
                || checkbox_p_rambut_1.isChecked()|| checkbox_p_rambut_2.isChecked()
                || checkbox_L_baju_1.isChecked()|| checkbox_L_baju_2.isChecked()|| checkbox_L_baju_3.isChecked()|| checkbox_L_baju_4.isChecked()
                || checkbox_L_baju_5.isChecked()
                || checkbox_L_seluar_1.isChecked()|| checkbox_L_seluar_2.isChecked()
                || checkbox_L_kasut_1.isChecked()
                || checkbox_L_rambut_1.isChecked()|| checkbox_L_rambut_2.isChecked()){

            if (statusImageSaman){
                saman();
            }else {
                Toast.makeText(getApplicationContext(),"Sila upload gambar kesalahan",Toast.LENGTH_LONG).show();
            }


        }else {
            Toast.makeText(getApplicationContext(),"Sila pilih satu kesalahan",Toast.LENGTH_LONG).show();
        }
    }

    //DIALOG SAMAN
    private void saman(){
        new AlertDialog.Builder(CreateSamanPensyarahActivity.this)
                .setCancelable(true)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Jumlah saman RM 50. Anda pasti untuk saman?")
                .setPositiveButton("Saman", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        standardProgressDialog.show();
                        if(jantina.toLowerCase().equals("lelaki")){
                            if(checkbox_L_baju_1.isChecked()){
                                kesalahanBajuL = "Jarang";
                            }
                            if(checkbox_L_baju_2.isChecked()){
                                kesalahanBajuL = kesalahanBajuL +",Tidak berkolar pada waktu pejabat";
                            }
                            if(checkbox_L_baju_3.isChecked()){
                                kesalahanBajuL = kesalahanBajuL +",Tidak dimasukkan ke dalam seluar (Baju jenis masuk ke dalam)";
                            }
                            if(checkbox_L_baju_4.isChecked()){
                                kesalahanBajuL = kesalahanBajuL +",Tidak Berlengan";
                            }
                            if(checkbox_L_baju_5.isChecked()){
                                kesalahanBajuL = kesalahanBajuL +",Mempunyai tulisan,perkataaan atau gambar liar/lucah.";
                            }

                            if(checkbox_L_seluar_1.isChecked()){
                                kesalahanSeluarL = "Memakai seluar pendek";
                            }
                            if(checkbox_L_seluar_2.isChecked()){
                                kesalahanSeluarL = kesalahanSeluarL +",Ketak/koyak/lusuh/jarang";
                            }

                            if(checkbox_L_kasut_1.isChecked()){
                                kesalahanKasutL = "Memakai selipar";
                            }

                            if(checkbox_L_rambut_1.isChecked()){
                                kesalahanRambutL = "Panjang / tidak kemas";
                            } if(checkbox_L_rambut_2.isChecked()){
                                kesalahanRambutL = kesalahanRambutL +"Mewarnakan rambut";
                            }
                        }else{
                            if(checkbox_p_baju_1.isChecked()){
                                kesalahanBajuL = "Jarang";
                            }
                            if(checkbox_p_baju_2.isChecked()){
                                kesalahanBajuL = kesalahanBajuL +",Singkat (Tidak menutup punggung) ";
                            }
                            if(checkbox_p_baju_3.isChecked()){
                                kesalahanBajuL = kesalahanBajuL +",Mempunyai tulisan,perkataaan atau gambar liar/lucah.";
                            }
                            if(checkbox_p_baju_4.isChecked()){
                                kesalahanBajuL = kesalahanBajuL +",Lengan baju tidak sampai ke paras siku";
                            }
                            if(checkbox_p_baju_5.isChecked()){
                                kesalahanBajuL = kesalahanBajuL +",Baju masuk ke dalam (tuck in)";
                            }if(checkbox_p_baju_6.isChecked()){
                                kesalahanBajuL = kesalahanBajuL +",Memakai purdah";
                            }
                            if(checkbox_p_seluar_1.isChecked()){
                                kesalahanSeluarL = "Terlalu ketat";
                            }
                            if(checkbox_p_seluar_2.isChecked()){
                                kesalahanSeluarL = kesalahanSeluarL +",Labuh seluar tidak sampai ke buku lali";
                            }

                            if(checkbox_p_kasut_1.isChecked()){
                                kesalahanKasutL = "Memakai selipar";
                            }

                            if(checkbox_p_rambut_1.isChecked()){
                                kesalahanRambutL = "Panjang / tidak kemas";
                            } if(checkbox_p_rambut_2.isChecked()){
                                kesalahanRambutL = kesalahanRambutL +"Mewarnakan rambut";
                            }
                        }
                        insertImageSaman();

                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    //INSERT IMAGE IN FIREBASE
    private void insertImageSaman(){
        Uri file = getImageUri(getApplicationContext(),selected_image);
        StorageReference riversRef = mStorageRef.child("imagesSaman/"+textView_tarikh_masa.getText().toString()+"/"+studentID+".jpg");

        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
                                String download_url = o.toString();
                                insertSaman(download_url);
                            }
                        });

//
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }

    //INSERT SAMAN IN FIREBASE
    private void insertSaman(final String url){
        mDatabaseSaman.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Map<String, Object> mapSaman = new HashMap<>();
                        Map<String, Object> kesalahanBaju = new HashMap<>();

                        mapSaman.put("studentID", textView_no_pelajar.getText().toString());
                        mapSaman.put("studentTel", textView_no_tel.getText().toString());
                        mapSaman.put("studentName", textView_nama.getText().toString());
                        mapSaman.put("studentKP", textView_no_kp.getText().toString());
                        mapSaman.put("studentKodProgram", textView_kod_program.getText().toString());
                        mapSaman.put("pensyarahID", MainDashboardPensyarah.pensyarah_email);
                        mapSaman.put("pensyarahName", MainDashboardPensyarah.pensyarah_name);
                        mapSaman.put("dateSaman", textView_tarikh_masa.getText().toString());
                        mapSaman.put("statusBayaran", "0");
                        mapSaman.put("statusDiscount", "0");
                        mapSaman.put("imageSaman", url);
                        kesalahanBaju.put("baju",kesalahanBajuL);
                        kesalahanBaju.put("seluar",kesalahanSeluarL);
                        kesalahanBaju.put("kasut",kesalahanKasutL);
                        kesalahanBaju.put("rambut",kesalahanRambutL);
                        mapSaman.put("kesalahan",kesalahanBaju);
                        String id = mDatabaseSaman.push().getKey();
                        mDatabaseSaman.child(id).setValue(mapSaman);

                        Toast.makeText(getApplicationContext(),"Saman diterima",Toast.LENGTH_LONG).show();
                        onBackPressed();
                        standardProgressDialog.dismiss();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
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

    //GET DETAILS STUDENT YANG DISEARCH
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
