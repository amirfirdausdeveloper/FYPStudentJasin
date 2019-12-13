package com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Admin.Fragment;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Admin.Activity.MainDashboardAdmin;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Admin.Adapter.PensyarahListAdapter;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Admin.Adapter.SenaraiSamanAdapter;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Admin.Class.PensyarahListClass;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Admin.Class.SwipeToDeleteCallback;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Activity.CreateSamanPensyarahActivity;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Barcode.ScanActivity;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Class.SenaraiSamanClass;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.R;
import com.google.android.material.snackbar.Snackbar;
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
public class AdminSenaraiPensyarahFragment extends Fragment {


    private DatabaseReference mDatabase;
    List<PensyarahListClass> pensyarahListClasses;
    private PensyarahListAdapter adapter;
    RecyclerView recyclerView;
    LinearLayout linear_coordinatora;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle("SENARAI PENSYARAH");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_admin_senarai_pensyarah, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference("pensyarah");
        recyclerView = v.findViewById(R.id.recyclerView);
        linear_coordinatora = v.findViewById(R.id.linear_coordinatora);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPensyarah();
        enableSwipeToDeleteAndUndo();
    }

    //GET PENSYARAH ALL
    private void getPensyarah() {
        recyclerView.setHasFixedSize(false);
        pensyarahListClasses = new ArrayList<>();
        adapter = new PensyarahListAdapter(getContext(), pensyarahListClasses,getActivity());
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        recyclerView.setAdapter(adapter);

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot pensyarah: dataSnapshot.getChildren()) {
                                pensyarahListClasses.add(new PensyarahListClass(
                                        pensyarah.child("email").getValue().toString(),
                                        pensyarah.child("jawatan").getValue().toString(),
                                        pensyarah.child("katalaluan").getValue().toString(),
                                        pensyarah.child("name").getValue().toString(),
                                        pensyarah.child("phone").getValue().toString()
                                ));

                                adapter = new PensyarahListAdapter(getContext(), pensyarahListClasses,getActivity());
                                recyclerView.setAdapter(adapter);
                            }

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(getActivity()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                final int position = viewHolder.getAdapterPosition();
                final PensyarahListClass pensyarah =  PensyarahListAdapter.senaraiSamanClassList.get(position);

                new AlertDialog.Builder(getActivity())
                        .setCancelable(true)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setMessage("Adakah anda ingit padam maklumat "+pensyarah.getName()+ "?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deletePensyarah(pensyarah.getName(),pensyarah.getEmail(),position);
                            }
                        }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        adapter.notifyItemRangeChanged(position, adapter.getItemCount());
                    }
                }).show();



            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }

    private void deletePensyarah(final String name, final String email, final int position) {
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot pensyarah: dataSnapshot.getChildren()) {
                        if(name.equals(pensyarah.child("name").getValue().toString()) && email.equals(pensyarah.child("email").getValue().toString())){
                            pensyarah.getRef().removeValue();
                            adapter.removeItem(position);
                            adapter.notifyDataSetChanged();
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
