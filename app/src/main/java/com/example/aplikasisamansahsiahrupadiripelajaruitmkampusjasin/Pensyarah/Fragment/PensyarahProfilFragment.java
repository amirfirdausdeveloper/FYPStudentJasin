package com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Fragment;


import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Activity.MainDashboardPensyarah;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class PensyarahProfilFragment extends Fragment {

    //DECLARE
    TextView textView_nama,textView_email,textView_phone,textView_jawatan,textView_katalaluan;
    private DatabaseReference mDatabase;
    EditText editText_katalaluan;
    Button button_update;
    String save = "kemaskini";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle("Profil");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pensyarah_profil, container, false);

        //FIREBASE DATABASE REF
        mDatabase = FirebaseDatabase.getInstance().getReference("pensyarah");

        textView_nama = v.findViewById(R.id.textView_nama);
        textView_email = v.findViewById(R.id.textView_email);
        textView_phone = v.findViewById(R.id.textView_phone);
        textView_jawatan = v.findViewById(R.id.textView_jawatan);
        textView_katalaluan = v.findViewById(R.id.textView_katalaluan);
        editText_katalaluan = v.findViewById(R.id.editText_katalaluan);
        button_update = v.findViewById(R.id.button_update);

        editText_katalaluan.setVisibility(View.GONE);

        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(save.equals("kemaskini")){
                    button_update.setText("SIMPAN");
                    save = "saved";
                    textView_katalaluan.setVisibility(View.GONE);
                    editText_katalaluan.setVisibility(View.VISIBLE);
                }else {
                    save = "kemaskini";
                    button_update.setText("KEMASKINI");
                    textView_katalaluan.setVisibility(View.VISIBLE);
                    editText_katalaluan.setVisibility(View.GONE);
                    savePassword();
                }
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                getDetails();
            }
        }, 1000);
        getDetails();
    }

    private void getDetails() {
        mDatabase.orderByChild("email").equalTo(MainDashboardPensyarah.pensyarah_email)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot pensyarah: dataSnapshot.getChildren()) {
                                textView_nama.setText(pensyarah.child("name").getValue().toString());
                                textView_email.setText(pensyarah.child("email").getValue().toString());
                                textView_phone.setText(pensyarah.child("phone").getValue().toString());
                                textView_jawatan.setText(pensyarah.child("jawatan").getValue().toString());
                                textView_katalaluan.setText(pensyarah.child("katalaluan").getValue().toString());
                                editText_katalaluan.setText(pensyarah.child("katalaluan").getValue().toString());
                            }

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }

    private void savePassword(){
        mDatabase.orderByChild("email").equalTo(MainDashboardPensyarah.pensyarah_email)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot pensyarah: dataSnapshot.getChildren()) {
                                pensyarah.getRef().child("katalaluan").setValue(editText_katalaluan.getText().toString());
                                textView_katalaluan.setText(editText_katalaluan.getText().toString());
                            }

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }

}
