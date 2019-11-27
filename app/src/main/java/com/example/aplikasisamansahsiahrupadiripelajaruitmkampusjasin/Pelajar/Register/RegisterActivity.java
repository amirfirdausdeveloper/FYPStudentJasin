package com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pelajar.Register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pelajar.Class.PelajarClass;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Class.PensyarahClass;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Login.LoginActivity;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    //DECLARE
    ImageView imageView_back;
    EditText editText_email,editText_no_pelajar,editText_no_kp,editText_no_telefon,editText_kod_program,editText_nama,
            editText_kata_laluan;
    RadioGroup radioGroup;
    String jantina = "";
    Button button_daftar;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        //FIREBASE DATABASE REF
        mDatabase = FirebaseDatabase.getInstance().getReference("pelajar");

        imageView_back = findViewById(R.id.imageView_back);
        editText_email = findViewById(R.id.editText_email);
        editText_no_pelajar = findViewById(R.id.editText_no_pelajar);
        editText_no_kp = findViewById(R.id.editText_no_kp);
        editText_no_telefon = findViewById(R.id.editText_no_telefon);
        editText_kod_program = findViewById(R.id.editText_kod_program);
        editText_nama = findViewById(R.id.editText_nama);
        editText_kata_laluan = findViewById(R.id.editText_kata_laluan);
        radioGroup = findViewById(R.id.radioGroup);
        button_daftar = findViewById(R.id.button_daftar);

        //ON CLICK IMAGE BUTTON BACK
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                jantina = radioButton.getText().toString();
            }
        });


        //ON CLICK BUTTON DAFTAR
        button_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_email.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Sila masukkan emel",Toast.LENGTH_LONG).show();
                }else if(editText_no_pelajar.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Sila masukkan No pelajar",Toast.LENGTH_LONG).show();
                }else if(editText_no_telefon.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Sila masukkan no telefon",Toast.LENGTH_LONG).show();
                }else if(editText_nama.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Sila masukkan nama",Toast.LENGTH_LONG).show();
                }else if(editText_kata_laluan.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Sila masukkan katalaluan",Toast.LENGTH_LONG).show();
                }else if(editText_no_kp.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Sila masukkan No kad pengenalan",Toast.LENGTH_LONG).show();
                }else if(editText_kod_program.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Sila masukkan Kod program",Toast.LENGTH_LONG).show();
                }else if(radioGroup.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getApplicationContext(),"Sila Pilih jantina",Toast.LENGTH_LONG).show();
                }else{

                    //FUNCTION ADD PENSYARAH
                    addStudent();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void addStudent() {
        mDatabase.orderByChild("id").equalTo(editText_no_pelajar.getText().toString())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Toast.makeText(getApplicationContext(), "No Pelajar sudah didaftar !!!", Toast.LENGTH_LONG).show();
                        } else {
                            String id = mDatabase.push().getKey();
                            PelajarClass pensyarah = new PelajarClass(
                                    editText_email.getText().toString(),
                                    editText_no_pelajar.getText().toString(),
                                    jantina,
                                    editText_kata_laluan.getText().toString(),
                                    editText_kod_program.getText().toString(),
                                    editText_nama.getText().toString(),
                                    editText_no_kp.getText().toString(),
                                    editText_no_telefon.getText().toString());

                            //Saving the pelajar
                            mDatabase.child(id).setValue(pensyarah);

                            //setting edittext to blank again
                            editText_email.setText("");
                            editText_no_pelajar.setText("");
                            jantina = "";
                            editText_kata_laluan.setText("");
                            editText_kod_program.setText("");
                            editText_nama.setText("");
                            editText_no_kp.setText("");
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
}
