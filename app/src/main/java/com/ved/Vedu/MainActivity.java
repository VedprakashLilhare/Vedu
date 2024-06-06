package com.ved.Vedu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import com.google.gson.*;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ved.mysafety.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    CardView c1;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    ImageView l1,l2,l3;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> listItems;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        c1 = findViewById(R.id.v1);
      //  c2 = findViewById(R.id.v2);
        l1 =findViewById(R.id.b1);
        l2 =findViewById(R.id.b2);
        l3 =findViewById(R.id.b3);

        c1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,Complaint.class);
            startActivity(intent);
        });

//        c2.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this,SoS.class);
//            startActivity(intent);
//        });

        l1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,number.class);
            startActivity(intent);
        });

        l2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Learn.class);
            startActivity(intent);
        });

        l3.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,SoS.class);
            startActivity(intent);
        });



        listView = findViewById(R.id.listviewnotice);
        listItems = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
        mDatabase.child("org").child("Notice").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {

                    Log.e("firebase Noice", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase Notice", String.valueOf(task.getResult().getValue()));
                    //String json = "{\"date\":2151882, \"-NvkgXgLoXtiBUhrVUIr\":{\"notice\":\"tomorrow is holiday\" , \"timestamp\":1713435192423}, \"-Nus_JGEy4eVDZN4sbkL\":{\"notice\":\"56667ygy\", \"timestamp\":1712493774210}, \"-Nzfo4nnCNbx5txa6CUu\":{\"notice\":\"check\" , \"timestamp\":1717648254321}, \"-Nus_rw40Sg9J5ef4XM3\":{\"notice\":\"tvrhrv4g4g4g\", \"timestamp\":1712493921625}}";
                    String json=String.valueOf(task.getResult().getValue());
                    processFirebaseData(task.getResult());


//                    String data=String.valueOf(task.getResult().getValue());
//                    String[] items = data.split(",");
//                    ArrayList<String> listItems = new ArrayList<>();

//                    for (String item : items) {
//                        String[] idTitle = item.split("=");
//                        String date = dateConverter(idTitle[0].replace("{", ""));
//                        String notice = idTitle[1].replace("}", "");
//                        listItems.add(date + " - " + notice);
//                    }
//                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, list);
//
//                    ListView listView = findViewById(R.id.listviewnotice);
//                    listView.setAdapter(adapter);

                }
            }
        });

    }
    public String dateConverter(Long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }
    public void signout(View view){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }
    private void processFirebaseData(DataSnapshot dataSnapshot) {
        try {
            Map<String, Map<String, Object>> notices = (Map<String, Map<String, Object>>) dataSnapshot.getValue();
            if (notices != null) {
                for (Map.Entry<String, Map<String, Object>> entry : notices.entrySet()) {
                    Map<String, Object> noticeData = entry.getValue();
                    long timestamp = (long) noticeData.get("timestamp");
                    String notice = (String) noticeData.get("notice");
                    String listItem =  dateConverter(timestamp)+" " +  notice;
                    listItems.add(listItem);
                }
                adapter.notifyDataSetChanged();
            }
        } catch (ClassCastException e) {
            Log.e("firebase Notice", "Error casting data", e);
        }
    }
}
