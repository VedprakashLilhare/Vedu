package com.ved.Vedu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ved.mysafety.R;

public class SignupActivity extends AppCompatActivity {

    EditText signupName, signupEmail, addphone, signupPassword;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        addphone = findViewById(R.id.phone);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("user");
                String name = signupName.getText().toString();
                String email = signupEmail.getText().toString();
                String phone = addphone.getText().toString();
                String password = signupPassword.getText().toString();
                HelperClass helperClass = new HelperClass(name, email,phone, password);
                reference.child(email).setValue(helperClass);
                Toast.makeText(SignupActivity.this, "You added user successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignupActivity.this, admin.class);
                startActivity(intent);
                finish();
            }
        });
    }
}