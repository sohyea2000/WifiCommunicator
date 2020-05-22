package com.example.wificommunicator;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import cz.msebera.android.httpclient.*;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;

import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;


public class MainActivity extends AppCompatActivity {
    public String ip_address;
    Button forward;
    Button back;
    Button right;
    Button left;
    String status;
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ip_address = "192.168.4.1";


        forward = findViewById(R.id.forward);
        back = findViewById(R.id.back);
        right = findViewById(R.id.right);
        left = findViewById(R.id.left);
        forward.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = "FORWARD";
                String serverAddress = ip_address+":"+"80" ;
                HttpRequestTask requestTask = new HttpRequestTask(serverAddress);
                requestTask.execute(status);

            }
        });
        back.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = "FORWARD";
                String serverAddress = ip_address + ":" + "80";
                HttpRequestTask requestTask = new HttpRequestTask(serverAddress);
                requestTask.execute(status);

            }
        });
        right.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = "RIGHT";
                String serverAddress = ip_address + ":" + "80";
                HttpRequestTask requestTask = new HttpRequestTask(serverAddress);
                requestTask.execute(status);

            }
        });
        left.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = "LEFT";
                String serverAddress = ip_address + ":" + "80";
                HttpRequestTask requestTask = new HttpRequestTask(serverAddress);
                requestTask.execute(status);

            }
        });
    }

    public void insert(View view) {
        Intent i = new Intent(this, Activity2.class);
        startActivity(i);
    }

    public void connect(View view) {
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
    }


    @SuppressLint("StaticFieldLeak")
    class HttpRequestTask extends AsyncTask<String, Void, String> {


        private String serverAddress;
        private String serverResponse = "";
        //private AlertDialog dialog;




        HttpRequestTask(String serverAddress) {
            this.serverAddress = serverAddress;


           /* AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("HTTP Response from Ip Address:");
            builder.setCancelable(true);
            dialog = builder
                    .create();*/
        }


        @Override
        protected void onPostExecute(String s) {
           // dialog.setMessage(serverResponse);


           // if (!dialog.isShowing())
               // dialog.show();
        }


        @Override
        public String doInBackground(String... params) {
            //dialog.setMessage("Data sent , waiting response from server...");


           /* if (!dialog.isShowing())
                dialog.show();*/


            String val = params[0];
            final String url = "http://" + serverAddress + "/wifi/" + val;
            try {
                HttpClient client = HttpClientBuilder.create().build();
                HttpGet getRequest = new HttpGet();
                getRequest.setURI(new URI(url));
                HttpResponse response = client.execute(getRequest);


                InputStream inputStream;
                inputStream = response.getEntity().getContent();
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(inputStream));


                serverResponse = bufferedReader.readLine();
                inputStream.close();


            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
                serverResponse = e.getMessage();
            }


            return serverResponse;
        }


        @Override
        protected void onPreExecute() {
           // dialog.setMessage("Sending data to server, please wait...");


            /*if (!dialog.isShowing())
                dialog.show();*/
        }
    }

}


