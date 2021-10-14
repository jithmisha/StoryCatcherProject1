package com.storycatcher.storycatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class GameLevels extends AppCompatActivity {
    Button btnEasy,btnIntermediate,btnAdvanced;
    private ImageButton imgBackBtnLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_levels);

        btnEasy= findViewById(R.id. btnEasy);
        btnIntermediate= findViewById(R.id.btnIntermediate);
        btnAdvanced= findViewById(R.id.btnAdvanced);
        imgBackBtnLevel=findViewById(R.id.imgBackBtnLevel);

        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameLevels.this, GameEasy.class);
                startActivity(intent);
            }
        });

        btnIntermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameLevels.this, GameIntermediate.class);
                startActivity(intent);
            }
        });

        btnAdvanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameLevels.this, GameAdvanced.class);
                startActivity(intent);
            }
        });

        imgBackBtnLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameLevels.this, GamesScreen.class);
                startActivity(intent);
            }
        });
    }
}