package com.example.asus.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//Untuk Kelas Login
public class MainActivity extends AppCompatActivity {
    private DatabaseReference userdatabase;
    TextView view1;
    EditText text1;
    EditText text2;
    CheckBox check1;
    Spinner spinner1;
    Button button1;
    Button button2;
    String[] status={"Mahasiswa","Dosen"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view1=(TextView) findViewById(R.id.view1);
        text1=(EditText) findViewById(R.id.text1);
        text2=(EditText) findViewById(R.id.text2);
        check1=(CheckBox) findViewById(R.id.check1);
        spinner1=(Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> a= new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,status);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(a);
        button1=(Button) findViewById(R.id.button1);
        button2=(Button) findViewById(R.id.button2);
        userdatabase = FirebaseDatabase.getInstance().getReference().child("User");

        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    text2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    text2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userdatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String posisi;
                        if (dataSnapshot.child((text1.getText().toString())).exists()){
                                user User = dataSnapshot.child(text1.getText().toString()).getValue(user.class);
                                if (User.getPass1().equals(text2.getText().toString())){
                                posisi=spinner1.getSelectedItem().toString();
                                if (User.getStatus().equals(posisi)){
                                if (posisi.equals("Mahasiswa")){
                                    Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                                    String user=text1.getText().toString();
                                    intent.putExtra("key",user);
                                    startActivity(intent);
                                    Toast.makeText(MainActivity.this, "Berhasil Log in", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "Anda Bukan Mahasiswa", Toast.LENGTH_SHORT).show();
                                }
                                if (posisi.equals("Dosen")){
                                    String user=text1.getText().toString();
                                    Intent intent2=new Intent(MainActivity.this,Main3Activity.class);
                                        intent2.putExtra("key",user);
                                        startActivity(intent2);
                                    Toast.makeText(MainActivity.this, "Berhasil Log in", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "Anda Bukan Dosen", Toast.LENGTH_SHORT).show();
                                }}
                            } else{
                                Toast.makeText(MainActivity.this, "Password Anda Salah", Toast.LENGTH_SHORT).show();
                            }
                        } else{
                            Toast.makeText(MainActivity.this, "Data Belum Ada", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1.setText("");
                text2.setText("");
                check1.setChecked(false);
                Intent intent=new Intent(MainActivity.this,Main4Activity.class);
                intent.putExtra("key","100");
                startActivity(intent);
            }
        });

    }
}
