package com.storycatcher.storycatcher;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

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

        /*ActivityResultLauncher<Intent> libraryScreenResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();
                            // get the user name
                            String name=data.getStringExtra("name");
                        }
                    }
                });
*/
        userAdapter.onRecyclerViewClick(new UserAdapter.onRecyclerViewClick() {
            @Override
            public void onItemClick(int position) {
                //Toast.makeText(UserList.this,"Position: "+position,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UserList.this, LibraryScreen.class);
                intent.putExtra("currentKid_ID", list.get(position).getKidID());
                intent.putExtra("currentKid_Name", list.get(position).getKidsName());
                //libraryScreenResultLauncher.launch(intent);
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
                            list.add(dc.getDocument().toObject(User.class));
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