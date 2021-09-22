package com.storycatcher.storycatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GamesScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_screen);

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
                        startActivity(new Intent(getApplicationContext(),SearchActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.games:
                        return true;

                }
                return false;
            }
        });
    }
}