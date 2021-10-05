package com.storycatcher.storycatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfilePictureActivity extends AppCompatActivity {

    private RecyclerView pictureRecyclerView;
    private CircleImageView mainPicImg;
    ProfilePictureViewHolder profilePictureViewHolder;
    ArrayList<ProfilePictureDataClass> profilePictureArrayList;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_picture);

        pictureRecyclerView = findViewById(R.id.pictureRecyclerView);
        mainPicImg = findViewById(R.id.mainPicImg);

        GridLayoutManager layoutManager1 = new GridLayoutManager(getApplicationContext(),2);
        pictureRecyclerView.setLayoutManager(layoutManager1);
        pictureRecyclerView.setHasFixedSize(true);

        fstore = FirebaseFirestore.getInstance();

        profilePictureArrayList = new ArrayList<>();

        profilePictureViewHolder = new ProfilePictureViewHolder(getApplicationContext(),profilePictureArrayList);
        pictureRecyclerView.setAdapter(profilePictureViewHolder);

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


    }
}