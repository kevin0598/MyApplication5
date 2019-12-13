package com.example.asus.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;
import java.util.List;

public class Main9Activity extends AppCompatActivity {
    ImageView image1;
    Button button1;
    EditText text4;
    Spinner spinner1;
    private DatabaseReference userdatabase1;
    private DatabaseReference userdatabase;
    List<matakuliah> nama=new ArrayList<>();
    adaptermatakuliah adaptermatakuliah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        image1=(ImageView) findViewById(R.id.image1);
        button1=(Button) findViewById(R.id.button1);
        text4=(EditText) findViewById(R.id.text1);
        spinner1=(Spinner) findViewById(R.id.spinner1);
        Intent receivedIntent = getIntent();
        final MultiFormatWriter multiFormatWriter = new MultiFormatWriter();


        userdatabase1 = FirebaseDatabase.getInstance().getReference();
        userdatabase1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot data=dataSnapshot.child("Matakuliah");
                for (DataSnapshot postsnapshot:data.getChildren()){
                    matakuliah mata=new matakuliah();
                    mata.setNama(postsnapshot.getKey());
                    //mata.setNama(postsnapshot.child("nama").getValue().toString());
                    nama.add(mata);
                }
                adaptermatakuliah.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(nama.get(position).getNama(),BarcodeFormat.QR_CODE,300,300);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    image1.setImageBitmap(bitmap);
                }catch (WriterException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adaptermatakuliah=new adaptermatakuliah(Main9Activity.this,nama);
        spinner1.setAdapter(adaptermatakuliah);

    }
}
