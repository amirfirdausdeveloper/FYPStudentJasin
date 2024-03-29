package com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Admin.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Admin.Activity.DetailsSamanAdminActivity;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Activity.DetailsSamanPensyarahActivity;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Class.SenaraiSamanClass;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SenaraiSamanAdapter extends RecyclerView.Adapter<SenaraiSamanAdapter.ProductViewHolder> {

    private Context mCtx;
    public static List<SenaraiSamanClass> senaraiSamanClassList;
    Activity activity;

    public SenaraiSamanAdapter(Context mCtx, List<SenaraiSamanClass> senaraiSamanClassList, Activity activity) {
        this.mCtx = mCtx;
        this.senaraiSamanClassList = senaraiSamanClassList;
        this.activity = activity;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.custom_adapter_senarai_saman_admin, null,false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ProductViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
        final SenaraiSamanClass senaraiSamanClass = senaraiSamanClassList.get(position);

        holder.textView_nama.setText(senaraiSamanClass.getStudentName());
        holder.textView_tarikh_masa.setText(senaraiSamanClass.getDateSaman());
        holder.textView_no_pelajar.setText(senaraiSamanClass.getStudentID());
        holder.textView_no_tel.setText(senaraiSamanClass.getStudentTel());
        holder.textView_kesalahan_baju.setText(senaraiSamanClass.getKesalahanBaju());
        holder.textView_kesalahan_seluar.setText(senaraiSamanClass.getKesalahanSeluar());
        holder.textView_kesalahan_kasut.setText(senaraiSamanClass.getKesalahanKasut());
        holder.textView_kesalahan_rambut.setText(senaraiSamanClass.getKesalahanRambut());
        Picasso.get().load(senaraiSamanClass.getImageSaman()).into(holder.imageView_saman);

        if(senaraiSamanClass.getStatusDiscount().equals("0")){
            holder.textView_harga.setText("50");
        }else if(senaraiSamanClass.getStatusDiscount().equals("1")){
            holder.textView_harga.setText("25");
        }else if(senaraiSamanClass.getStatusDiscount().equals("2")){
            holder.textView_harga.setText("50");
        }else if(senaraiSamanClass.getStatusDiscount().equals("3")){
            holder.textView_harga.setText("50");
        }


        if(senaraiSamanClass.getStatusBayaran().equals("0")){
            holder.textView_saman_oleh.setText("Belum");
            holder.textView_saman_oleh.setTextColor(Color.RED);
        }else{
            holder.textView_saman_oleh.setText(senaraiSamanClass.getStatusBayaran());
            holder.textView_saman_oleh.setText("Sudah");
            holder.textView_saman_oleh.setTextColor(Color.GREEN);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(mCtx, DetailsSamanAdminActivity.class);
                next.putExtra("1",senaraiSamanClass.getStudentName());
                next.putExtra("2",senaraiSamanClass.getDateSaman());
                next.putExtra("3",senaraiSamanClass.getStudentID());
                next.putExtra("4",senaraiSamanClass.getStudentTel());
                next.putExtra("5",senaraiSamanClass.getKesalahanBaju());
                next.putExtra("6",senaraiSamanClass.getKesalahanSeluar());
                next.putExtra("7",senaraiSamanClass.getKesalahanKasut());
                next.putExtra("8",senaraiSamanClass.getKesalahanRambut());
                next.putExtra("9",senaraiSamanClass.getPensyarahName());
                next.putExtra("10",senaraiSamanClass.getImageSaman());
                if(senaraiSamanClass.getStatusDiscount().equals("0")){
                    next.putExtra("11","50");
                }else if(senaraiSamanClass.getStatusDiscount().equals("1")){
                    next.putExtra("11","25");
                }else if(senaraiSamanClass.getStatusDiscount().equals("2")){
                    next.putExtra("11","50");
                }else if(senaraiSamanClass.getStatusDiscount().equals("3")){
                    next.putExtra("11","50");
                }
                next.putExtra("12",senaraiSamanClass.getStatusDiscount());
                mCtx.startActivity(next);
            }
        });
    }



    @Override
    public int getItemCount() {
        return senaraiSamanClassList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textView_nama,textView_tarikh_masa,textView_no_pelajar,textView_no_tel,textView_kesalahan_baju,
                textView_kesalahan_seluar,textView_kesalahan_kasut,textView_kesalahan_rambut,textView_saman_oleh,textView_harga;
        ImageView imageView_saman;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textView_nama = itemView.findViewById(R.id.textView_nama);
            textView_tarikh_masa = itemView.findViewById(R.id.textView_tarikh_masa);
            textView_no_pelajar = itemView.findViewById(R.id.textView_no_pelajar);
            textView_no_tel = itemView.findViewById(R.id.textView_no_tel);
            textView_kesalahan_baju = itemView.findViewById(R.id.textView_kesalahan_baju);
            textView_kesalahan_seluar = itemView.findViewById(R.id.textView_kesalahan_seluar);
            textView_kesalahan_kasut = itemView.findViewById(R.id.textView_kesalahan_kasut);
            textView_kesalahan_rambut = itemView.findViewById(R.id.textView_kesalahan_rambut);
            textView_saman_oleh = itemView.findViewById(R.id.textView_saman_oleh);
            imageView_saman = itemView.findViewById(R.id.imageView_saman);
            textView_harga = itemView.findViewById(R.id.textView_harga);




        }
    }


}