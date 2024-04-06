package com.ved.Vedu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ved.mysafety.R;

public class admin extends AppCompatActivity {

    Button a1,a2,a3,a4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);
        a1 =findViewById(R.id.add_user_button);
//        a2 =findViewById(R.id.upload_video_button);
//        a3 =findViewById(R.id.upload_notification_button);
//        a4 =findViewById(R.id.ph);
        a1.setOnClickListener(v -> {
            Intent intent = new Intent(admin.this, SignupActivity.class);
            startActivity(intent);
        });
//        a2.setOnClickListener(v -> {
//            Intent intent = new Intent(admin.this,SignupActivity.class);
//            startActivity(intent);
//        });
//        a3.setOnClickListener(v -> {
//            Intent intent = new Intent(admin.this,SignupActivity.class);
//            startActivity(intent);
//        });
//        a4.setOnClickListener(v -> {
//            Intent intent = new Intent(admin.this,SignupActivity.class);
//            startActivity(intent);
//        });
    }
}