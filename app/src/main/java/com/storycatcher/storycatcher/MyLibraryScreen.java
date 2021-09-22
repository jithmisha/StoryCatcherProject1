package com.storycatcher.storycatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MyLibraryScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_library_screen);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId((R.id.myLibrary));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),LibraryScreen.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.myLibrary:
                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(),SearchActivity.class));
                        overridePendingTransition(0,0);
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
}