package com.example.wificommunicator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import cz.msebera.android.httpclient.*;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;

import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;


public class Activity2 extends AppCompatActivity {
    String ip_address = "192.168.4.1";
    String text1;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        context = Activity2.this;
    }

    public void add(View view) {
        LinearLayout layout = findViewById(R.id.myLayout);
        Button b1 = new Button(Activity2.this);
        new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        EditText text = new EditText(Activity2.this);
        new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        layout.addView(b1);
        layout.addView(text);
        text1 = text.getText().toString();
        b1.setText(text1);
        b1.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String serverAddress = ip_address + ":" + "80";
                        HttpRequestTasks requestTask = new HttpRequestTasks(serverAddress);
                        requestTask.execute(text1);
                    }
                }
        );
    }


    @SuppressLint("StaticFieldLeak")

    class HttpRequestTasks extends AsyncTask<String, Void, String> {


        private String serverAddress;
        private String serverResponse = "";
       // private AlertDialog dialog;



        HttpRequestTasks(String serverAddress) {
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


           /* if (!dialog.isShowing())
                dialog.show();*/
        }


        @Override
        public String doInBackground(String... params) {
           // dialog.setMessage("Data sent , waiting response from server...");


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


           /* if (!dialog.isShowing())
                dialog.show();*/
        }
    }
}
