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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreateProfile extends AppCompatActivity {

    private Button createButton;
    private ImageButton backButton;
    private EditText kidID, kidName, kidAge;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        kidID=findViewById(R.id.txtKidsId);
        kidName=findViewById(R.id.txtKidsName);
        kidAge=findViewById(R.id.txtKidsAge);
        createButton=findViewById(R.id.ProfileCreatebtn);
        backButton=findViewById(R.id.imgBackBtnProfile);
        mAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();


        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kidsID=kidID.getText().toString();
                String kidsName=kidName.getText().toString();
                String kidsAge=kidAge.getText().toString();
                String parentID=mAuth.getCurrentUser().getUid();


                if(kidsID.isEmpty()){
                    kidID.setError("Please enter a kids ID");
                    kidID.requestFocus();
                    return;
                }
                if(kidsName.isEmpty()){
                    kidName.setError("Please enter kids name");
                    kidName.requestFocus();
                    return;
                }
                if(kidsAge.isEmpty()){
                    kidAge.setError("Enter kids age");
                    kidAge.requestFocus();
                    return;
                }

                try {
                    CreateProfileClass kidsProfile=new CreateProfileClass(kidsID,kidsName,Integer.parseInt(kidsAge),parentID);
                    DocumentReference documentReference=fStore.collection("Kids").document(kidsID);
                    documentReference.set(kidsProfile).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(CreateProfile.this, "Profile created successfully", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(CreateProfile.this,SignOrRegister.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(CreateProfile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }catch(Exception e){
                    Toast.makeText(CreateProfile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateProfile.this,UserList.class));
            }
        });



       // textTV=findViewById(R.id.text);



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