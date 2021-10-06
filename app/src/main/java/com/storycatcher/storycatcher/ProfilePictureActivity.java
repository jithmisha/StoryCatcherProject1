package com.storycatcher.storycatcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Transaction;

import java.security.PrivateKey;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfilePictureActivity extends AppCompatActivity {

    private RecyclerView pictureRecyclerView;
    private CircleImageView mainPicImg;
    ProfilePictureViewHolder profilePictureViewHolder;
    ArrayList<ProfilePictureDataClass> profilePictureArrayList;
    FirebaseFirestore fstore;
    String selectedPicUrl, selectedPicID, kidID;
    private Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_picture);
        saveButton = findViewById(R.id.savebtn);
        fstore = FirebaseFirestore.getInstance();

        pictureRecyclerView = findViewById(R.id.pictureRecyclerView);
        mainPicImg = findViewById(R.id.mainPicImg);

        //Getting kids ID
        Intent intent = getIntent();
        kidID = intent.getStringExtra("kidID");
        //Toast.makeText(ProfilePictureActivity.this, kidID, Toast.LENGTH_SHORT).show();

        GridLayoutManager layoutManager1 = new GridLayoutManager(getApplicationContext(),2);
        pictureRecyclerView.setLayoutManager(layoutManager1);
        pictureRecyclerView.setHasFixedSize(true);

        profilePictureArrayList = new ArrayList<>();

        profilePictureViewHolder = new ProfilePictureViewHolder(getApplicationContext(),profilePictureArrayList);
        pictureRecyclerView.setAdapter(profilePictureViewHolder);

        //displaying pictures
        fstore.collection("ProfilePictures").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document:task.getResult()){
                        ProfilePictureDataClass data = document.toObject(ProfilePictureDataClass.class);
                        profilePictureArrayList.add(data);
                        profilePictureViewHolder.notifyDataSetChanged();
                    }
                }
            }
        });

        //select profile pic
        profilePictureViewHolder.onRecyclerViewClick(new ProfilePictureViewHolder.onRecyclerViewClick() {
            @Override
            public void onItemClick(int position) {
                selectedPicID = profilePictureArrayList.get(position).getPicID();
                selectedPicUrl = profilePictureArrayList.get(position).getPicUrl();
                if(selectedPicUrl != null ){
                    Glide.with(getApplicationContext()).load(selectedPicUrl).into(mainPicImg);
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference sfDocRef = fstore.collection("Kids").document(kidID);
                fstore.runTransaction(new Transaction.Function<Void>() {
                    @Nullable
                    @Override
                    public Void apply(@NonNull Transaction transaction) throws FirebaseFirestoreException {
                        DocumentSnapshot snapshot = transaction.get(sfDocRef);
                        transaction.update(sfDocRef,"picID",selectedPicID);
                        transaction.update(sfDocRef,"picUrl",selectedPicUrl);
                        return null;
                    }
                }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ProfilePictureActivity.this, "Profile Picture Added successfully", Toast.LENGTH_SHORT).show();
                        Toast.makeText(ProfilePictureActivity.this, "Profile Created Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ProfilePictureActivity.this, UserList.class));
                    }
                });
            }
        });


    }


}