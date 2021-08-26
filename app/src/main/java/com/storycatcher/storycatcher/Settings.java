package com.storycatcher.storycatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LibraryScreen.class));
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