package com.apkglobal.smsgatway;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
EditText mobileno,etmessage;
Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mobileno=findViewById(R.id.mobileno);
        etmessage=findViewById(R.id.etmessage);
        send=findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //copy the code for textlocal send sms via java
                try {
                    // Construct data
                    String apiKey = "apikey=" + "YsnProwD1gM-48cIrPrpIVwmZEsppNDINbJMGWIm7V";
                    String message = "&message=" + etmessage.getText().toString();
                    String sender = "&sender=" + "TXTLCL";
                    String numbers = "&numbers=" + mobileno.getText().toString();

                    // Send data
                    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
                    String data = apiKey + numbers + message + sender;
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                    conn.getOutputStream().write(data.getBytes("UTF-8"));
                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    final StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        Toast.makeText(MainActivity.this, line.toString(),Toast.LENGTH_LONG).show();
                    }
                    rd.close();


                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.toString() , Toast.LENGTH_LONG).show();
                }

            }
        });
        //out side the coclick method
        StrictMode.ThreadPolicy st=new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(st);
    }
}
