package com.ved.Vedu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.ved.mysafety.R;

public class MainActivity extends AppCompatActivity {
    CardView c1,c2;
    private FirebaseAuth mAuth;
    ImageView l1,l2,l3;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        c1 = findViewById(R.id.v1);
        c2 = findViewById(R.id.v2);
        l1 =findViewById(R.id.b1);
        l2 =findViewById(R.id.b2);
        l3 =findViewById(R.id.b3);

        c1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,Complaint.class);
            startActivity(intent);
        });

        c2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,SoS.class);
            startActivity(intent);
        });

        l1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,number.class);
            startActivity(intent);
        });

        l2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Learn.class);
            startActivity(intent);
        });

        l3.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,near.class);
            startActivity(intent);
        });


    }
    public void signout(View view){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }
}