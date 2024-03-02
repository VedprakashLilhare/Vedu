package com.ved.mysafety;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;

public class near extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near);


        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) WebView mywebview = (WebView) findViewById(R.id.web2);
        mywebview.loadUrl("https://www.google.com/maps/d/u/0/edit?mid=1GgFyrIxa9VFvZiKLxp9Vwp5NxXY1S4o&usp=sharing");
    }
}