package com.storycatcher.storycatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchActivity extends AppCompatActivity {
    private EditText searchField;
    private ImageButton searchImgBtn;
    private RecyclerView searchRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchField= findViewById(R.id.searchField);
        searchImgBtn= findViewById(R.id.searchimgbtn);
        searchRecyclerView= findViewById(R.id.searchRecyclerView);
        searchRecyclerView.setHasFixedSize(true);
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseSearch();
            }
        });


        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId((R.id.games));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),LibraryScreen.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.myLibrary:
                        startActivity(new Intent(getApplicationContext(),MyLibraryScreen.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.search:
                        return true;
                    case R.id.games:
                        startActivity(new Intent(getApplicationContext(),GamesScreen.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
    }

    private void firebaseSearch(){



    }
    public class SearchDataViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public SearchDataViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
        }

        public void setDetails(String searchName,String searchImage){
            TextView searchNames =(TextView) mView.findViewById(R.id.txtSearchName);
            ImageView searchImg = (ImageView) mView.findViewById(R.id.imgSearchItem);
        }
    }





}