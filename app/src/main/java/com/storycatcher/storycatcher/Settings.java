package com.storycatcher.storycatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    private Button profileBtn,screentimeBtn,changepasswordBtn,aboutBtn,logoutBtn;
    private ImageButton backButton;
    String currentKidID;


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

        currentKidID = getIntent().getStringExtra("currentKid_ID");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                startActivity(new Intent(getApplicationContext(), About.class));
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(), Logout.class));
         }
        });

        changepasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ChangePassword.class));
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this,Profile.class);
                intent.putExtra("currentKid_ID",currentKidID);
                startActivity(intent);
            }
        });

        screentimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScreenTime.class));
            }
        });

    }


}