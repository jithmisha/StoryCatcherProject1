package com.storycatcher.storycatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;

public class UserList extends AppCompatActivity {
    
    RecyclerView recyclerView;
    DatabaseReference database;
    UserAdapter userAdapter;
    ArrayList<User> list;
    private ImageButton backbutton;
    private Button createNewProfilebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        recyclerView = findViewById(R.id.userListrecyclerView);
        //database = FirebaseDatabase.getInstance().getReference( "User");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        backbutton=findViewById(R.id.imgBackBtnSelectProfile);
        createNewProfilebtn=findViewById(R.id.ProfileCreatebtn);

        list = new ArrayList<>();
        userAdapter =new UserAdapter(this,list);
        recyclerView.setAdapter(userAdapter);

        /*database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren() ) {

                    User user = dataSnapshot.getValue(User.class);
                    list.add(user);
                }
                userAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserList.this,SignOrRegister.class));
            }
        });

        createNewProfilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserList.this,CreateProfile.class));
            }
        });
    }
}