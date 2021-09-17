package com.storycatcher.storycatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Profile extends AppCompatActivity {
    private ImageButton BackButton;
    private EditText userEmail, currentKidsName, currentKidsId, currentKidsAge;
    FirebaseAuth mAuth;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BackButton=findViewById(R.id.imgBackBtnProfile);
        userEmail=findViewById(R.id.UserEmail);
        currentKidsName =findViewById(R.id.kidsNamePro);
        currentKidsId =findViewById(R.id.kidsIdPro);
        currentKidsAge =findViewById(R.id.kidsAgePro);
        mAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

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