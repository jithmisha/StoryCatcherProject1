package com.storycatcher.storycatcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;

public class  UserList extends AppCompatActivity {
    
    RecyclerView recyclerView;
    FirebaseAuth mAuth;
    UserAdapter userAdapter;
    ArrayList<User> list;
    FirebaseFirestore fStore;
    private ImageButton backbutton;
    private Button createNewProfilebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        recyclerView = findViewById(R.id.userListrecyclerView);
        fStore = FirebaseFirestore.getInstance();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        backbutton=findViewById(R.id.imgBackBtnSelectProfile);
        createNewProfilebtn=findViewById(R.id.ProfileCreatebtn);

        list = new ArrayList<>();
        userAdapter =new UserAdapter(this,list);
        recyclerView.setAdapter(userAdapter);

        EventChangeListerner();

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

    private void EventChangeListerner() {
        mAuth=FirebaseAuth.getInstance();
        fStore.collection("Kids").whereEqualTo("parentID",mAuth.getCurrentUser().getUid()).
                addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    if(error !=null){
                       Log.e("Firestore error", error.getMessage());
                       return;
                    }
                    for(DocumentChange dc: value.getDocumentChanges()){
                        if(dc.getType() == DocumentChange.Type.ADDED){
                            list.add(dc.getDocument().toObject(User.class));
                        }
                        userAdapter.notifyDataSetChanged();

                    }
            }

        });

    }

}