package com.ved.Vedu;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ved.mysafety.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    CardView c1,c2;
    private DatabaseReference mDatabase;
    ImageView l1,l2,l3;
    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
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

        mDatabase.child("org").child("Notice").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {

                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });
    }
    public String dateconverter(String string){
        long unixTime = Long.parseLong(string); // Convert string to long

        Date date = new Date(unixTime * 1000L); // Convert Unix time to milliseconds

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        String formattedDate = sdf.format(date);
        return formattedDate;

    }
    public void signout(View view){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }
}