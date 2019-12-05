package com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

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
        View view = inflater.inflate(R.layout.custom_adapter_senarai_saman_pensyarah, null,false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ProductViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
        final SenaraiSamanClass senaraiSamanClass = senaraiSamanClassList.get(position);

        holder.textView_nama.setText(senaraiSamanClass.getStudentName());
        holder.textView_tarikh_masa.setText(senaraiSamanClass.getStudentName());
        holder.textView_no_pelajar.setText(senaraiSamanClass.getStudentID());
        holder.textView_no_tel.setText(senaraiSamanClass.getStudentTel());
        holder.textView_kesalahan_baju.setText(senaraiSamanClass.getKesalahanBaju());
        holder.textView_kesalahan_seluar.setText(senaraiSamanClass.getKesalahanSeluar());
        holder.textView_kesalahan_kasut.setText(senaraiSamanClass.getKesalahanKasut());
        holder.textView_kesalahan_rambut.setText(senaraiSamanClass.getKesalahanRambut());
        holder.textView_saman_oleh.setText(senaraiSamanClass.getPensyarahName());
        Picasso.get().load(senaraiSamanClass.getImageSaman()).into(holder.imageView_saman);
    }



    @Override
    public int getItemCount() {
        return senaraiSamanClassList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textView_nama,textView_tarikh_masa,textView_no_pelajar,textView_no_tel,textView_kesalahan_baju,
                textView_kesalahan_seluar,textView_kesalahan_kasut,textView_kesalahan_rambut,textView_saman_oleh;
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




        }
    }


}