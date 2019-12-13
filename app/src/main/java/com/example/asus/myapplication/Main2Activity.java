package com.example.asus.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

//Kelas Untuk Halaman depan Mahasiswa
public class Main2Activity extends AppCompatActivity {
    String userdata;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button1=(Button) findViewById(R.id.button1);
        button2=(Button) findViewById(R.id.button2);
        button3=(Button) findViewById(R.id.button3);
        button4=(Button) findViewById(R.id.button4);
        Intent receivedIntent = getIntent();

        if (receivedIntent.hasExtra("key")){
            String username= receivedIntent.getStringExtra("key");
            userdata=getIntent().getStringExtra("key");
            Log.d("My_DATA",username);
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,Main5Activity.class);
                String user =userdata;
                intent.putExtra("key",user);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,Main6Activity.class);
                String user="";
                intent.putExtra("key",user);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,Main7Activity.class);
                String user="";
                intent.putExtra("key",user);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,Main8Activity.class);
                String user="";
                intent.putExtra("key",user);
                startActivity(intent);
            }
        });
    }
}
