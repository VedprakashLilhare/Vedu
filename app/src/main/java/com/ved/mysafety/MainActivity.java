package com.ved.mysafety;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageView c1,c2,l1,l2,l3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c1 = findViewById(R.id.imageView3);
        c2 = findViewById(R.id.imageView4);
        l1 =findViewById(R.id.imageView8);
        l2 =findViewById(R.id.imageView9);
        l3 =findViewById(R.id.imageView10);

        c1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,Complaint.class);
            startActivity(intent);
        });

        c2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,help.class);
            startActivity(intent);
        });

        l1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,number.class);
            startActivity(intent);
        });

        l2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, embassy.class);
            startActivity(intent);
        });

        l3.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,near.class);
            startActivity(intent);
        });
        WebView mywebview = (WebView) findViewById(R.id.webView);
        mywebview.loadUrl("https://news.un.org/en/");


    }
}