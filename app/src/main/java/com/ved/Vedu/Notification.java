package com.ved.Vedu;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.ved.mysafety.R;

import java.util.HashMap;
import java.util.Map;

public class Notification extends AppCompatActivity {
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notification);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void notice(View view){
        EditText noticeEditText = findViewById(R.id.editTextNotification);
        EditText dateEditText = findViewById(R.id.editTextDate);
        String notice = noticeEditText.getText().toString();
        String date = dateEditText.getText().toString();
        Map<String, Object> updates = new HashMap<>();
        updates.put("timestamp", ServerValue.TIMESTAMP);
        updates.put("notice", notice);
        mDatabase.child("org").child("Notice").push().setValue(updates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Display a toast message on success
                        Toast.makeText(Notification.this, "Data saved successfully.", Toast.LENGTH_SHORT).show();

                        // Reset the EditText value
                        noticeEditText.setText("");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failure
                        Toast.makeText(Notification.this, "Failed to save data.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}