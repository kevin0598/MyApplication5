package com.example.asus.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Main11Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);

        Intent receivedIntent = getIntent();

        if (receivedIntent.hasExtra("key")){
            String username= receivedIntent.getStringExtra("key");
            Log.d("My_DATA",username);
        }
    }
}
