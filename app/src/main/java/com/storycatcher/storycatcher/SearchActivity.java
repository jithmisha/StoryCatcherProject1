package com.storycatcher.storycatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private EditText searchField;
    private ImageButton searchImgBtn;
    private RecyclerView searchRecyclerView;
    FirebaseFirestore fstore;
    ArrayList<SearchDataClass> searchDataClassArrayList;
    SearchDataViewHolder searchDataViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        String currentKidID = getIntent().getStringExtra("currentKid_ID");
        searchField = findViewById(R.id.searchField);
        searchImgBtn = findViewById(R.id.searchimgbtn);
        searchRecyclerView = findViewById(R.id.searchRecyclerView);
        fstore = FirebaseFirestore.getInstance();

        searchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchRecyclerView.setHasFixedSize(true);

        searchDataClassArrayList = new ArrayList<>();

        searchDataViewHolder = new SearchDataViewHolder(getApplicationContext(), searchDataClassArrayList);
        searchRecyclerView.setAdapter(searchDataViewHolder);


        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().isEmpty()){
                    searchDataClassArrayList.clear();
                    searchDataViewHolder.notifyDataSetChanged();
                }else{
                    searchData(s.toString());
                }
            }
        });

        // Playing video
        searchDataViewHolder.onRecyclerViewClick(new SearchDataViewHolder.onRecyclerViewClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getApplicationContext(), VideoPlayer.class);
                intent.putExtra("video",searchDataClassArrayList.get(position).getURL());
                startActivity(intent);
            }
        });

        searchImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //searchData();
            }
        });

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigation);
        Menu menu =bottomNavigationView.getMenu();
        MenuItem menuItem=menu.getItem(2);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Intent intent0=new Intent(SearchActivity.this,LibraryScreen.class);
                        intent0.putExtra("currentKid_ID",currentKidID);
                        intent0.putExtra("currentKid_ID",currentKidID);
                        startActivity(intent0);
                        finish();
                        overridePendingTransition(0,0);
                        break;

                    case R.id.myLibrary:
                        Intent intent1=new Intent(SearchActivity.this,MyLibraryScreen.class);
                        intent1.putExtra("currentKid_ID",currentKidID);
                        startActivity(intent1);
                        finish();
                        overridePendingTransition(0,0);
                        break;

                    case R.id.search:
                        break;

                    case R.id.games:
                        Intent intent3=new Intent(SearchActivity.this,GamesScreen.class);
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
        Intent intent0=new Intent(SearchActivity.this,LibraryScreen.class);
        intent0.putExtra("currentKid_ID",currentKidID);
        startActivity(intent0);
        finish();
    }


    private void searchData(String type) {
        Query query=fstore.collection("Full Library").orderBy("Title").startAt(type).endAt(type+ "\uf8ff");
        if(!type.isEmpty()){
            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful() && task.getResult() != null){
                        searchDataClassArrayList.clear();
                        searchDataViewHolder.notifyDataSetChanged();
                        for(DocumentSnapshot doc: task.getResult().getDocuments()){
                            SearchDataClass searchDataClass =doc.toObject(SearchDataClass.class);
                            searchDataClassArrayList.add(searchDataClass);
                            searchDataViewHolder.notifyDataSetChanged();
                        }
                    }
                }
            });
        }else{
            searchDataClassArrayList.clear();
            searchDataViewHolder.notifyDataSetChanged();
        }
    }

}