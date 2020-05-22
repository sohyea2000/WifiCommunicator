package com.example.wificommunicator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Activity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        TextView text1 = findViewById(R.id.text1);
        TextView text2 = findViewById(R.id.text2);
        String ip_address = "IP Address : 192.168.4.1";
        String server = "PORT : 80";
        text1.setText(ip_address);
        text2.setText(server);
    }
}
