package com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Admin.Fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Activity.MainDashboardPensyarah;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Admin.Adapter.SenaraiSamanAdapter;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Class.SenaraiSamanClass;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminSenaraiSamanFragment extends Fragment {


    private DatabaseReference mDatabaseSaman;
    RecyclerView recyclerView;

    List<SenaraiSamanClass> senaraiSamanClasses;

    private SenaraiSamanAdapter senaraiSamanAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle("Senarai Saman");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pensyarah_senarai_saman, container, false);

        mDatabaseSaman = FirebaseDatabase.getInstance().getReference("saman");
        recyclerView = v.findViewById(R.id.recyclerView);



        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDetails();
    }

    private void getDetails(){
        recyclerView.setHasFixedSize(false);
        senaraiSamanClasses = new ArrayList<>();
        senaraiSamanAdapter = new SenaraiSamanAdapter(getContext(), senaraiSamanClasses,getActivity());
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        recyclerView.setAdapter(senaraiSamanAdapter);

        mDatabaseSaman
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot saman: dataSnapshot.getChildren()) {
                                senaraiSamanClasses.add(new SenaraiSamanClass(
                                        saman.child("dateSaman").getValue().toString(),
                                        saman.child("imageSaman").getValue().toString(),
                                        saman.child("kesalahan").child("baju").getValue().toString(),
                                        saman.child("kesalahan").child("seluar").getValue().toString(),
                                        saman.child("kesalahan").child("kasut").getValue().toString(),
                                        saman.child("kesalahan").child("rambut").getValue().toString(),
                                        saman.child("pensyarahID").getValue().toString(),
                                        saman.child("pensyarahName").getValue().toString(),
                                        saman.child("statusBayaran").getValue().toString(),
                                        saman.child("statusDiscount").getValue().toString(),
                                        saman.child("studentID").getValue().toString(),
                                        saman.child("studentTel").getValue().toString(),
                                        saman.child("studentName").getValue().toString(),
                                        saman.child("studentKP").getValue().toString(),
                                        saman.child("studentKodProgram").getValue().toString()
                                ));

                                senaraiSamanAdapter = new SenaraiSamanAdapter(getContext(), senaraiSamanClasses,getActivity());
                                recyclerView.setAdapter(senaraiSamanAdapter);
                            }

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }
}
