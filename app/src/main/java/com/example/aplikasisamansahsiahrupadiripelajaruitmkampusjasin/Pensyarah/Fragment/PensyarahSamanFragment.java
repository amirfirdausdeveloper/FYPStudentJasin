package com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Fragment;


import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Activity.CreateSamanPensyarahActivity;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Barcode.ScanActivity;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class PensyarahSamanFragment extends Fragment {


    EditText editText_student_id;
    Button button_carian,button_barcode;
    private DatabaseReference mDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle("Saman");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pensyarah_saman, container, false);

        ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, 1);
        //FIREBASE DATABASE REF
        mDatabase = FirebaseDatabase.getInstance().getReference("pelajar");

        editText_student_id = v.findViewById(R.id.editText_student_id);
        button_carian = v.findViewById(R.id.button_carian);
        button_barcode = v.findViewById(R.id.button_barcode);


        //CLICK BUTTON CARIAN
        button_carian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_student_id.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"Sila masukkan student id untuk carian",Toast.LENGTH_LONG).show();
                }else{
                    carianStudent();
                }
            }
        });

        //CLICK BUTTON SCAN
        button_barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ScanActivity.class);
                startActivity(intent);
            }
        });


        return v;
    }

    private void carianStudent() {
        mDatabase.orderByChild("id").equalTo(editText_student_id.getText().toString())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Intent next = new Intent(getActivity(), CreateSamanPensyarahActivity.class);
                            next.putExtra("carian_id",editText_student_id.getText().toString());
                            startActivity(next);
                        } else {
                            Toast.makeText(getContext(), "No Pelajar tidak didaftar !!!", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }

}
