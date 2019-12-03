package com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Barcode;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Activity.CreateSamanPensyarahActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;
    private DatabaseReference mDatabase;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mDatabase = FirebaseDatabase.getInstance().getReference("pelajar");
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }



    @Override
    public void handleResult(Result result) {
        carianStudent(result.getText().toString());
    }

    private void carianStudent(final String studentID) {
        mDatabase.orderByChild("id").equalTo(studentID)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Intent next = new Intent(getApplicationContext(), CreateSamanPensyarahActivity.class);
                            next.putExtra("carian_id",studentID);
                            startActivity(next);
                        } else {
                            Toast.makeText(getApplicationContext(), "No Pelajar tidak didaftar !!!", Toast.LENGTH_LONG).show();
                            onBackPressed();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }
}
