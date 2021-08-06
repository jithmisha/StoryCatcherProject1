package com.storycatcher.storycatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateProfile extends AppCompatActivity {
   //FirebaseAuth objectFirebaseAuth;
    //TextView textTV;
    //private EditText kidname;
    private Button createButton;
    private ImageButton backButton;
   // private FirebaseDatabase rootNode;
    //private DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        createButton=findViewById(R.id.ProfileCreatebtn);
        backButton=findViewById(R.id.imgBackBtnProfile);


        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateProfile.this,SignOrRegister.class));
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateProfile.this,SignOrRegister.class));
            }
        });

       // objectFirebaseAuth=FirebaseAuth.getInstance();
       // createButton=findViewById(R.id.ProfileCreatebtn);
       // textTV=findViewById(R.id.text);
       // rootNode=FirebaseDatabase.getInstance();


       // if(objectFirebaseAuth!=null){
         //   String currentUser=objectFirebaseAuth.getCurrentUser().getEmail();
         //   textTV.setText(currentUser);
        }

       /* createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val=kidname.getText().toString();
                ProfileClass profile=new ProfileClass(val);
                reference=rootNode.getReference("User");
                reference.child(val).setValue(profile);

            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(objectFirebaseAuth!=null){
                    objectFirebaseAuth.signOut();
                    startActivity(new Intent(CreateProfile.this,SignOrRegister.class));
                    finish();
                }
            }
        });
    }*/
}