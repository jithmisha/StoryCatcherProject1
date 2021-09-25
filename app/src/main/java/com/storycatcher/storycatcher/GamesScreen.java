package com.storycatcher.storycatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GamesScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_screen);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigation);
        Menu menu =bottomNavigationView.getMenu();
        MenuItem menuItem=menu.getItem(3);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Intent intent0=new Intent(GamesScreen.this,LibraryScreen.class);
                        startActivity(intent0);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.myLibrary:
                        Intent intent1=new Intent(GamesScreen.this,MyLibraryScreen.class);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.search:
                        Intent intent2=new Intent(GamesScreen.this,SearchActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.games:

                        break;
                }
                return false;
            }
        });
    }
}