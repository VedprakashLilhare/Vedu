package com.ved.Vedu;

import static android.content.ContentValues.TAG;

import android.Manifest;


import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.ved.mysafety.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SoS extends AppCompatActivity {
    private LocationManager locationManager;
    private LocationListener locationListener;
    EditText editTextName, editTextLocation, editTextPhone,editTextRemarks;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editTextName = findViewById(R.id.editTextName);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        editTextRemarks=findViewById(R.id.editRemarks);
        editTextLocation = findViewById(R.id.editTextLocation);
        editTextPhone = findViewById(R.id.editTextPhone);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference mDatabase;
        FirebaseUser user = mAuth.getCurrentUser();
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // Handle location update
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                String coordinates =  latitude + " , " + longitude;

// Find the EditText by its ID
                EditText editTextLocation = findViewById(R.id.editTextLocation);

// Set the coordinates to the EditText
                editTextLocation.setText(coordinates);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_so_s);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        assert user != null;
        mDatabase.child("user").child(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {

                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    String data=String.valueOf(task.getResult().getValue());
                    String[] keyValuePairs = data.substring(1, data.length() - 1).split(", ");
                    String name = null, location = null, id = null, phone = null;

                    for (String pair : keyValuePairs) {
                        String[] entry = pair.split("=");
                        String key = entry[0];
                        String value = entry[1];
                        switch (key) {
                            case "name":
                                name = value;
                                break;
                            case "location":
                                location = value;
                                break;
                            case "id":
                                id = value;
                                break;
                            case "phone":
                                phone = value;
                                break;
                        }
                    }

                    // Set values to TextView and EditText fields
                    TextView textViewHeading = findViewById(R.id.textViewHeading);
                    EditText editTextName = findViewById(R.id.editTextName);
                    EditText editTextLocation = findViewById(R.id.editTextLocation);
                    EditText editTextID = findViewById(R.id.editTextID);
                    EditText editTextPhone = findViewById(R.id.editTextPhone);
                    // Initialize LocationManager and LocationListener
                    textViewHeading.setText("SOS Request Details");

                    if (name != null) {
                        editTextName.setText(name);
                    }

                    if (location != null) {
                        editTextLocation.setText(location);
                    }

                    if (id != null) {
                        editTextID.setText(id);
                    }

                    if (phone != null) {
                        editTextPhone.setText(phone);
                    }
                }
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, start requesting location updates
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                }
            }
        }
    }
    public void senddata(View view){
        long time= System.currentTimeMillis();
EditText editTextID=findViewById(R.id.editTextName);
        EditText phonenumberEdit = findViewById(R.id.editTextPhone);
        String phonenumber = phonenumberEdit.getText().toString();

        EditText locationEdit = findViewById(R.id.editTextLocation);
        String livelocation = locationEdit.getText().toString();

        EditText editremark = findViewById(R.id.editRemarks);
        String remarks = editremark.getText().toString();

        EditText idedit = findViewById(R.id.editTextID);
        String liveid = idedit.getText().toString();

        Map<String, Object> updates = new HashMap<>();
        String notice = editTextID.getText().toString();

        updates.put("timestamp", ServerValue.TIMESTAMP);
        updates.put("Name",notice);
        updates.put("location",livelocation);
        updates.put("id",liveid);
        updates.put("remark",remarks);
        updates.put("phone",phonenumber);



        mDatabase.child("org").child("sos").push().setValue(updates);
        Toast.makeText(this, "Report Sent!", Toast.LENGTH_SHORT).show();
        finish();

    }
    public void notice(View view){
        EditText noticeEditText = findViewById(R.id.editTextName);
        EditText phonenumberEdit = findViewById(R.id.editTextPhone);

        EditText dateEditText = findViewById(R.id.editTextLocation);
        String notice = noticeEditText.getText().toString();
        String phonenumber = phonenumberEdit.getText().toString();

        String date = dateEditText.getText().toString();
        Map<String, Object> updates = new HashMap<>();
        updates.put("timestamp", ServerValue.TIMESTAMP);
        updates.put("notice", notice);
        updates.put("phone", phonenumber);
        mDatabase.child("org").child("sos").push().setValue(updates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Display a toast message on success
                        Toast.makeText(SoS.this, "Data saved successfully.", Toast.LENGTH_SHORT).show();

                        // Reset the EditText value
                        //   noticeEditText.setText("");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failure
                        Toast.makeText(SoS.this, "Failed to save data.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}