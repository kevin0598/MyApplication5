package com.example.asus.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Main5Activity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private String absen;
    private DatabaseReference user;
    private DatabaseReference mata;
    private DatabaseReference database;
    private DatabaseReference database1;
    private DatabaseReference database2;
    private ZXingScannerView mScannerView;
    private String userdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
        Intent receivedIntent = getIntent();
        database=FirebaseDatabase.getInstance().getReference().child("Absen");
        database1=FirebaseDatabase.getInstance().getReference().child("User");
        database2=FirebaseDatabase.getInstance().getReference().child("Matakuliah");
        if (receivedIntent.hasExtra("key")){
            String username= receivedIntent.getStringExtra("key");
            userdata=getIntent().getStringExtra("key");
            Log.d("My_DATA",username);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(final Result rawResult) {
        Log.v("TAG", rawResult.getText()); // Prints scan results
        Log.v("TAG", rawResult.getBarcodeFormat().toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(rawResult.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();
        database2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String NIM;
                absen absen=new absen();
                NIM=rawResult.getText().toString();
                absen.setNik(userdata);
                absen.setMatakuliah(rawResult.getText().toString());
                database.child(NIM).setValue(absen);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mScannerView.resumeCameraPreview(this);
    }
}
