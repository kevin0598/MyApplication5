package com.example.asus.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main13Activity extends AppCompatActivity {
    private DatabaseReference userdatabase;
    private  DatabaseReference databaseReference;
    TextView view1;
    EditText text1;
    EditText text2;
    EditText text4;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);
        view1=(TextView) findViewById(R.id.view1);
        text1=(EditText) findViewById(R.id.text1);
        text2=(EditText) findViewById(R.id.text2);
        text4=(EditText) findViewById(R.id.text4);
        button1=(Button) findViewById(R.id.button1);
        userdatabase = FirebaseDatabase.getInstance().getReference().child("User");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Matakuliah");

        button1.setOnClickListener(new View.OnClickListener() {
            private String kode;
            @Override
            public void onClick(View v) {
                userdatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(text4.getText().toString()).exists()){
                            Toast.makeText(Main13Activity.this, "Mata Kuliah Sudah Ada", Toast.LENGTH_SHORT).show();
                        } else{
                        matakuliah mata=new matakuliah();
                        kode = text4.getText().toString();
                        if (dataSnapshot.child(text2.getText().toString()).exists()){
                            mata.setKode(text1.getText().toString());
                            mata.setNik(text2.getText().toString());
                            mata.setNama(text4.getText().toString());
                            databaseReference.child(kode).setValue(mata);
                            Toast.makeText(Main13Activity.this, "Data Berhasil di input", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Main13Activity.this, Main3Activity.class);
                            String user = "";
                            intent.putExtra("key", user);
                            startActivity(intent);
                        } else{
                            Toast.makeText(Main13Activity.this, "Kode NIK Salah", Toast.LENGTH_SHORT).show();
                        }
                    }}

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
