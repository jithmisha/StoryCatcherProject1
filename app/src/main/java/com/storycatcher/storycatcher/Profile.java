package com.storycatcher.storycatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {
    FirebaseAuth objectFirebaseAuth;
    TextView textTV;
    private Button logOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        objectFirebaseAuth=FirebaseAuth.getInstance();
        logOutButton=findViewById(R.id.SignOutbutton);
        textTV=findViewById(R.id.text);
        if(objectFirebaseAuth!=null){
            String currentUser=objectFirebaseAuth.getCurrentUser().getEmail();
            textTV.setText(currentUser);
        }
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(objectFirebaseAuth!=null){
                    objectFirebaseAuth.signOut();
                    startActivity(new Intent(Profile.this,SignOrRegister.class));
                    finish();
                }
            }
        });
    }
}