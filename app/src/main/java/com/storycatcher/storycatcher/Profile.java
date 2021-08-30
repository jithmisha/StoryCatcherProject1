package com.storycatcher.storycatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {
    private ImageButton BackButton;
    private EditText userEmail,kidsName,kidsId,kidsAge;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        BackButton=findViewById(R.id.imgBackBtnProfile);
        userEmail=findViewById(R.id.UserEmail);
        kidsName=findViewById(R.id.kidsNamePro);
        kidsId=findViewById(R.id.kidsIdPro);
        kidsAge=findViewById(R.id.kidsAgePro);
        mAuth=FirebaseAuth.getInstance();
        String mail=mAuth.getCurrentUser().getEmail();
        userEmail.setText(mail);
        userEmail.setEnabled(false);

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Settings.class));
            }
        });
    }
}