package com.storycatcher.storycatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GamesScreen extends AppCompatActivity {

    private ImageButton settingBtn,imgBtnGame1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_screen);

        settingBtn = findViewById(R.id.settingsbtn);
        imgBtnGame1 = findViewById(R.id.imgBtnGame1);

        String  currentKidID = getIntent().getStringExtra("currentKid_ID");

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
                        intent0.putExtra("currentKid_ID",currentKidID);
                        startActivity(intent0);
                        finish();
                        overridePendingTransition(0,0);
                        break;

                    case R.id.myLibrary:
                        Intent intent1=new Intent(GamesScreen.this,MyLibraryScreen.class);
                        intent1.putExtra("currentKid_ID",currentKidID);
                        startActivity(intent1);
                        finish();
                        overridePendingTransition(0,0);
                        break;

                    case R.id.search:
                        Intent intent2=new Intent(GamesScreen.this,SearchActivity.class);
                        intent2.putExtra("currentKid_ID",currentKidID);
                        startActivity(intent2);
                        finish();
                        overridePendingTransition(0,0);
                        break;

                    case R.id.games:
                        break;
                }
                return false;
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GamesScreen.this,Settings.class);
                startActivity(intent);
            }
        });

        imgBtnGame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GamesScreen.this,GameLevels.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        String currentKidID = getIntent().getStringExtra("currentKid_ID");
        Intent intent0=new Intent(GamesScreen.this,LibraryScreen.class);
        intent0.putExtra("currentKid_ID",currentKidID);
        startActivity(intent0);
        finish();
    }

}