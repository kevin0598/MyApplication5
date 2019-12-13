package com.example.asus.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//Kelas Untuk Halaman register
public class Main4Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private DatabaseReference userdatabase;
    TextView view1;
    TextView view2;
    TextView view3;
    EditText text1;
    EditText text2;
    EditText text4;
    EditText text5;
    Spinner spinner1;
    RadioGroup grup1;
    Button button1;
    String[] status={"Mahasiswa","Dosen"};
    RadioButton radio1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        view1=(TextView) findViewById(R.id.view1);
        view2=(TextView) findViewById(R.id.view2);
        view3=(TextView) findViewById(R.id.view3);
        text1=(EditText) findViewById(R.id.text1);
        text2=(EditText) findViewById(R.id.text2);
        text4=(EditText) findViewById(R.id.text4);
        text5=(EditText) findViewById(R.id.text5);
        spinner1=(Spinner) findViewById(R.id.spinner1);
        button1=(Button) findViewById(R.id.button1);
        grup1=(RadioGroup) findViewById(R.id.grup1);
        ArrayAdapter<String> a= new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,status);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(a);
        userdatabase = FirebaseDatabase.getInstance().getReference().child("User");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userdatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(text2.getText().toString()).exists()) {
                            Toast.makeText(Main4Activity.this, "Data Sudah Ada", Toast.LENGTH_SHORT).show();
                        } else {
                            if (text4.getText().toString().equals(text5.getText().toString())) {
                                final int selectedid = grup1.getCheckedRadioButtonId();
                                radio1 = (RadioButton) findViewById(selectedid);
                                String Nomor;
                                String posisi;
                                user userid = new user();
                                Nomor = text2.getText().toString();
                                posisi = spinner1.getSelectedItem().toString();
                                userid.setNama(text1.getText().toString());
                                userid.setId(text2.getText().toString());
                                userid.setPass1(text4.getText().toString());
                                userid.setPass2(text5.getText().toString());
                                userid.setStatus(posisi);
                                userid.setJk(radio1.getText().toString());
                                userdatabase.child(Nomor).setValue(userid);
                                Toast.makeText(Main4Activity.this, "Data Berhasil di input", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Main4Activity.this, MainActivity.class);
                                String user = "";
                                intent.putExtra("key", user);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Main4Activity.this, "Password Tidak Sama", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {};
                });
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Anda Memilih: " + status[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Silahkan Pilih Negara", Toast.LENGTH_LONG).show();
    }

}
