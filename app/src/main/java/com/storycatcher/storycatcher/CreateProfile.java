package com.storycatcher.storycatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateProfile extends AppCompatActivity {
    FirebaseAuth objectFirebaseAuth;
    TextView textTV;
    private EditText kidname;
    private Button logOutButton,createButton;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        objectFirebaseAuth=FirebaseAuth.getInstance();
        logOutButton=findViewById(R.id.SignOutbutton);
        createButton=findViewById(R.id.create);
        kidname=findViewById(R.id.createkidsName);
        textTV=findViewById(R.id.text);

        String kidsname=kidname.getText().toString();
        if(objectFirebaseAuth!=null){
            String currentUser=objectFirebaseAuth.getCurrentUser().getEmail();
            textTV.setText(currentUser);
        }

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileClass profile=new ProfileClass(kidsname);
                reference=rootNode.getReference("User");
                reference.child(kidsname).setValue(profile);
            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(objectFirebaseAuth!=null){
                    objectFirebaseAuth.signOut();
                    startActivity(new Intent(CreateProfile.this,SignOrRegister.class));
                    finish();
                }
            }
        });
    }
}