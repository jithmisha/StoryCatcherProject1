package com.storycatcher.storycatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 2000;//2seconds


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        //Variables
        Animation logoAnim, WordAnim;
        ImageView logo, wrd;

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);



        //Animation
        logoAnim= AnimationUtils.loadAnimation(this,R.anim.logo_animation);
        WordAnim= AnimationUtils.loadAnimation(this,R.anim.wrd_animation);

        //Assigning variable to id
        logo=findViewById(R.id.logo);
        wrd=findViewById(R.id.word);

        //setting
        logo.setAnimation(logoAnim);
        wrd.setAnimation(WordAnim);

        //Splash Screen
        new Handler(). postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(SplashScreen.this,SignOrRegister.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);


    }
}