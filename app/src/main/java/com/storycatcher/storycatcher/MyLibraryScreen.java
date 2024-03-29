package com.storycatcher.storycatcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MyLibraryScreen extends AppCompatActivity {
    private RecyclerView myLibraryRecyclerView;
    FirebaseFirestore fstore;
    ArrayList<MyLibraryDataClass> myLibraryDataList;
    MyLibraryDataViewHolder myLibraryDataViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_library_screen);
        String currentKidID = getIntent().getStringExtra("currentKid_ID");
        fstore = FirebaseFirestore.getInstance();

        myLibraryRecyclerView = findViewById(R.id.myLibraryRecyclerView);


        GridLayoutManager layoutManager1=new GridLayoutManager(getApplicationContext(),3);
        myLibraryRecyclerView.setLayoutManager(layoutManager1);
        myLibraryRecyclerView.setHasFixedSize(true);

        myLibraryDataList = new ArrayList<>();

        myLibraryDataViewHolder = new MyLibraryDataViewHolder(getApplicationContext(), myLibraryDataList);
        myLibraryRecyclerView.setAdapter(myLibraryDataViewHolder);


        /*fstore.collection("Kids").document(currentKidID).collection("Favourites")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot doc:task.getResult()){
                        MyLibraryDataClass data = doc.toObject(MyLibraryDataClass.class);
                        myLibraryDataList.add(data);
                        myLibraryDataViewHolder.notifyDataSetChanged();
                    }
                }
            }
        });*/
        CollectionReference colRef = fstore.collection("Kids").document(currentKidID).collection("Favourites");
        colRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for(DocumentChange dc: value.getDocumentChanges()){
                    if(dc.getType() == DocumentChange.Type.ADDED){
                        myLibraryDataList.add(dc.getDocument().toObject(MyLibraryDataClass.class));
                    }
                    myLibraryDataViewHolder.notifyDataSetChanged();
                }
            }
        });
        //Playing Video
        myLibraryDataViewHolder.onRecyclerViewClick(new MyLibraryDataViewHolder.onRecyclerViewClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getApplicationContext(), VideoPlayer.class);
                intent.putExtra("video",myLibraryDataList.get(position).getURL());
                intent.putExtra("ID",myLibraryDataList.get(position).getID());
                intent.putExtra("Title",myLibraryDataList.get(position).getTitle());
                intent.putExtra("imageUrl",myLibraryDataList.get(position).getImageUrl());
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigation);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Intent intent0=new Intent(MyLibraryScreen.this,LibraryScreen.class);
                        intent0.putExtra("currentKid_ID",currentKidID);
                        startActivity(intent0);
                        finish();
                        overridePendingTransition(0,0);
                        break;

                    case R.id.myLibrary:
                        break;

                    case R.id.search:
                        Intent intent2=new Intent(MyLibraryScreen.this,SearchActivity.class);
                        intent2.putExtra("currentKid_ID",currentKidID);
                        startActivity(intent2);
                        finish();
                        overridePendingTransition(0,0);
                        break;

                    case R.id.games:
                        Intent intent3=new Intent(MyLibraryScreen.this,GamesScreen.class);
                        intent3.putExtra("currentKid_ID",currentKidID);
                        startActivity(intent3);
                        finish();
                        overridePendingTransition(0,0);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        String currentKidID = getIntent().getStringExtra("currentKid_ID");
        Intent intent0=new Intent(MyLibraryScreen.this,LibraryScreen.class);
        intent0.putExtra("currentKid_ID",currentKidID);
        startActivity(intent0);
        finish();
    }
}