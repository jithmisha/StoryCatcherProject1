package com.storycatcher.storycatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Settings extends AppCompatActivity {
    private Button profileBtn,screentimeBtn,changepasswordBtn,aboutBtn,logoutBtn;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        profileBtn=findViewById(R.id.settingsprofilebutton);
        screentimeBtn=findViewById(R.id.settingsscreentimebutton);
        changepasswordBtn=findViewById(R.id.settingschangepasswordbutton);
        aboutBtn=findViewById(R.id.settingaboutbutton);
        logoutBtn=findViewById(R.id.settingslogoutbutton);
        backButton=findViewById(R.id.imgBackBtnSettings);
        final MediaPlayer mp=MediaPlayer.create(this,R.raw.goat);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LibraryScreen.class));
            }
        });

        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(), Logout.class));
        }
    });


    }
}