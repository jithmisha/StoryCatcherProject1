package com.storycatcher.storycatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MyLibraryScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_library_screen);
        String currentKidID = getIntent().getStringExtra("currentKid_ID");

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
                        overridePendingTransition(0,0);
                        break;

                    case R.id.myLibrary:
                        break;

                    case R.id.search:
                        Intent intent2=new Intent(MyLibraryScreen.this,SearchActivity.class);
                        intent2.putExtra("currentKid_ID",currentKidID);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.games:
                        Intent intent3=new Intent(MyLibraryScreen.this,GamesScreen.class);
                        intent3.putExtra("currentKid_ID",currentKidID);
                        startActivity(intent3);
                        overridePendingTransition(0,0);
                        break;
                }
                return false;
            }
        });
    }
}