package com.storycatcher.storycatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import de.hdodenhof.circleimageview.CircleImageView;

public class profilePictureActivity extends AppCompatActivity {

    private RecyclerView pictureRecyclerView;
    private CircleImageView mainPicImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_picture);

        pictureRecyclerView = findViewById(R.id.pictureRecyclerView);
        mainPicImg=findViewById(R.id.mainPicImg);

        GridLayoutManager layoutManager1=new GridLayoutManager(getApplicationContext(),2);
        pictureRecyclerView.setLayoutManager(layoutManager1);
        pictureRecyclerView.setHasFixedSize(true);




    }
}