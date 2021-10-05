package com.storycatcher.storycatcher;

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
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class  UserList extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseAuth mAuth;
    UserAdapter userAdapter;
    ArrayList<UserDataClass> list;
    FirebaseFirestore fStore;
    private ImageButton backbutton;
    private Button createNewProfilebtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        fStore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.userListrecyclerView);
        backbutton=findViewById(R.id.imgBackBtnSelectProfile);
        createNewProfilebtn=findViewById(R.id.ProfileCreatebtn);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Array list
        list = new ArrayList<>();

        //Setting Adapter
        userAdapter =new UserAdapter(this,list);
        recyclerView.setAdapter(userAdapter);

        EventChangeListerner();

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

        userAdapter.onRecyclerViewClick(new UserAdapter.onRecyclerViewClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(UserList.this, LibraryScreen.class);
                intent.putExtra("currentKid_ID", list.get(position).getKidID());
                intent.putExtra("currentKid_Name", list.get(position).getKidsName());
                startActivity(intent);
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
                       Log.e("Fire Store error", error.getMessage());
                       return;
                    }
                    for(DocumentChange dc: value.getDocumentChanges()){
                        if(dc.getType() == DocumentChange.Type.ADDED){
                            list.add(dc.getDocument().toObject(UserDataClass.class));
                        }
                        userAdapter.notifyDataSetChanged();
                    }
            }
        });
    }

    public void onStart() {
        super.onStart();

    }

}