package com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pelajar.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pelajar.Activity.MainDashboardPelajar;
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
public class PelajarUtamaFragment extends Fragment {


    TextView textView_nama;
    private DatabaseReference mDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle("Laman Utama");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pelajar_utama, container, false);


        //FIREBASE DATABASE REF
        mDatabase = FirebaseDatabase.getInstance().getReference("pelajar");
        textView_nama = v.findViewById(R.id.textView_nama);


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
        mDatabase.orderByChild("id").equalTo(MainDashboardPelajar.student_id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot pelajar: dataSnapshot.getChildren()) {
                                textView_nama.setText(pelajar.child("name").getValue().toString());
                            }

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }

}
