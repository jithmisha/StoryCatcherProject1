package com.storycatcher.storycatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {
    private ImageButton BackButton;
    private Button saveButton;
    private EditText userEmail, currentKidsName, currentKidsId, currentKidsAge;
    FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    String kidID;
    private ImageButton mainPicImg;
    private int del= 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BackButton = findViewById(R.id.imgBackBtnProfile);
        userEmail = findViewById(R.id.UserEmail);
        currentKidsName = findViewById(R.id.kidsNamePro);
        currentKidsId = findViewById(R.id.kidsIdPro);
        currentKidsAge = findViewById(R.id.kidsAgePro);
        saveButton = findViewById(R.id.btnSavePro);
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        mainPicImg = findViewById(R.id.imgProfilePicture);

        //Read from shared preferences
        SharedPreferences sharedPref = this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        kidID = sharedPref.getString("kidID", "error");


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void displayData(){
        currentKidsId.setText(kidID);
        currentKidsId.setEnabled(false);

        String mail=mAuth.getCurrentUser().getEmail();
        userEmail.setText(mail);
        userEmail.setEnabled(false);

        fstore.collection("Kids").document(kidID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot=task.getResult();
                    String kidNameResult = task.getResult().getString("kidsName");
                    String kidAgeResult  = task.getResult().getString("kidsAge");
                    String kidProfilePic = task.getResult().getString("picUrl");

                    currentKidsName.setText(kidNameResult);
                    currentKidsName.setSelection(kidNameResult.length());

                    currentKidsAge.setText(kidAgeResult);

                    Glide.with(getApplicationContext()).load(kidProfilePic).into(mainPicImg);
                }else{
                    Toast.makeText(getApplicationContext(),"No such document", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public void updateProfile(){
        String kidNameUpdate = currentKidsName.getText().toString();
        String kidAgeUpdate = currentKidsAge.getText().toString();

        final DocumentReference sfDocRef = fstore.collection("Kids").document(kidID);
        fstore.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);
                transaction.update(sfDocRef,"kidsName",kidNameUpdate);
                transaction.update(sfDocRef,"kidsAge",kidAgeUpdate);
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid)
            {
                Toast.makeText(getApplicationContext(),"Update Successfull",Toast.LENGTH_SHORT).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayData();
    }
}