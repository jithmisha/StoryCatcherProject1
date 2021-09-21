package com.storycatcher.storycatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Profile extends AppCompatActivity {
    private ImageButton BackButton;
    private EditText userEmail, currentKidsName, currentKidsId, currentKidsAge;
    FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    String kidID;


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

        kidID = getIntent().getStringExtra("currentKid_ID");
        Toast.makeText(Profile.this,"Hi: "+kidID,Toast.LENGTH_SHORT).show();

        String mail=mAuth.getCurrentUser().getEmail();
        userEmail.setText(mail);
        userEmail.setEnabled(false);


        fstore.collection("Kids").document(kidID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot=task.getResult();
                    String kidNameResult=task.getResult().getString("kidsName");
                    String val=task.getResult().getString(String.valueOf("kidAge"));
                    //int val=task.getResult().get(String.valueOf("kidAge"));

                    // String kidAgeResult=task.getResult().getString(String.valueOf(Integer.parseInt("kidsAge")));
                    //kidAge=documentSnapshot.getString(Integer.parseInt("kidsAge"));
                    //kidID=documentSnapshot.getString("kidsID");

                    currentKidsName.setText(kidNameResult);
                    currentKidsAge.setText(val);
                    //currentKidsId.setText(kidID);

                    //currentKidsId.setEnabled(false);
                }else{
                    Toast.makeText(getApplicationContext(),"No such document", Toast.LENGTH_SHORT).show();
                }
            }
        });

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}