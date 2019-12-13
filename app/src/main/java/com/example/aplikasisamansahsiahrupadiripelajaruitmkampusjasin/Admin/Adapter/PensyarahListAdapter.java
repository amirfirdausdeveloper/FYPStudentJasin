package com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Admin.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Admin.Class.PensyarahListClass;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class PensyarahListAdapter extends RecyclerView.Adapter<PensyarahListAdapter.ProductViewHolder> {

    private Context mCtx;
    public static List<PensyarahListClass> senaraiSamanClassList;
    Activity activity;
    private DatabaseReference mDatabase;

    public PensyarahListAdapter(Context mCtx, List<PensyarahListClass> senaraiSamanClassList, Activity activity) {
        this.mCtx = mCtx;
        this.senaraiSamanClassList = senaraiSamanClassList;
        this.activity = activity;
        mDatabase = FirebaseDatabase.getInstance().getReference("pensyarah");
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.custom_adapter_pensyarah_admin, null,false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ProductViewHolder(view);


    }


    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
        final PensyarahListClass pensyarah = senaraiSamanClassList.get(position);

        holder.textView_name.setText(pensyarah.getName());
        holder.textView_jawatan.setText(pensyarah.getJawatan());
        holder.textView_email.setText(pensyarah.getEmail());
        holder.textView_phone.setText(pensyarah.getPhone());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(activity)
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
                    }
                }).show();

            }
        });
    }

    public void removeItem(int position) {
        senaraiSamanClassList.remove(position);
        notifyItemRemoved(position);
    }

    private void deletePensyarah(final String name, final String email, final int position) {
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot pensyarah: dataSnapshot.getChildren()) {
                        if(name.equals(pensyarah.child("name").getValue().toString()) && email.equals(pensyarah.child("email").getValue().toString())){
                            pensyarah.getRef().removeValue();
                            removeItem(position);
                            notifyDataSetChanged();
                        }
                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return senaraiSamanClassList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textView_name,textView_jawatan,textView_email,textView_phone;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textView_name = itemView.findViewById(R.id.textView_name);
            textView_jawatan = itemView.findViewById(R.id.textView_jawatan);
            textView_email = itemView.findViewById(R.id.textView_email);
            textView_phone = itemView.findViewById(R.id.textView_phone);



        }
    }


}