package com.example.wificommunicator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

    }
    public void info(View view){
        Intent i = new Intent(this,Activity4.class);
        startActivity(i);
    }
    public void login(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
