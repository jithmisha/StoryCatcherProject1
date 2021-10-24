package com.storycatcher.storycatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

public class ScreenTime extends AppCompatActivity {

    private ImageButton backBtn, min15 ,min20, min25, min30, min35, min40;
    private Button savePauseBtn, resetBtn;
    private TextView timeText;
    private CountDownTimer countDownTimer;
    private boolean timeRunning;
    private  long startTime;
    private  long timeLeftInMilliseconds;
    private long endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_time);

        backBtn = findViewById(R.id.imgBackBtnScreenTime);
        min15 = findViewById(R.id.Screentime15);
        min20 = findViewById(R.id.Screentime20);
        min25 = findViewById(R.id.Screentime25);
        min30 = findViewById(R.id.Screentime30);
        min35 = findViewById(R.id.Screentime35);
        min40 = findViewById(R.id.Screentime40);
        savePauseBtn = findViewById(R.id.btnSaveScreenTime);
        resetBtn = findViewById(R.id.btnResetScreenTime);
        timeText = findViewById(R.id.timeText);

        //Back Button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //save or pause button
        savePauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timeRunning) {
                    pauseTimer();
                } else {
                    starTimer();
                }
            }
        });

        min15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime(10000);
             }
        });

        min20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime(20*60000);
            }
        });

        min25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime(25*60000);
            }
        });

        min30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime(30*60000);
            }
        });

        min35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime(35*60000);
            }
        });

        min40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime(40*60000);
            }
        });
        //reset Button
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
    }

    public  void setTime(long milliSeconds){
        startTime = milliSeconds;
        resetTimer();
    }

    private void starTimer(){
        endTime = System.currentTimeMillis() + timeLeftInMilliseconds;
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliseconds = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                timeRunning = false;
                updateButtons();

            }
        }.start();
        timeRunning = true;
        updateButtons();

    }

    private void pauseTimer(){
        countDownTimer.cancel();
        timeRunning = false;
        updateButtons();
    }

    private void resetTimer(){
        timeLeftInMilliseconds = startTime;
        updateCountDownText();
        updateButtons();
    }

    public void updateCountDownText(){
        /*int minutes= (int)timeLeftInMilliseconds/ 60000;
        int seconds= (int)timeLeftInMilliseconds% 60000/1000;*/
        int minutes = (int) (timeLeftInMilliseconds / 1000) / 60;
        int seconds = (int) (timeLeftInMilliseconds / 1000) % 60;

        String timeLeftText = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);

        timeText.setText(timeLeftText);
    }

    private  void updateButtons(){
        if(timeRunning){
            min15.setEnabled(false);
            resetBtn.setVisibility(View.INVISIBLE);
            savePauseBtn.setText("PAUSE");
        }else{
            min15.setEnabled(true);
            savePauseBtn.setText("SAVE");
            if(timeLeftInMilliseconds < 1000){
                savePauseBtn.setVisibility(View.INVISIBLE);
            }else {
                savePauseBtn.setVisibility(View.VISIBLE);
            }
            if(timeLeftInMilliseconds < startTime){
                resetBtn.setVisibility(View.VISIBLE);
            }else {
                resetBtn.setVisibility(View.INVISIBLE);
            }
        }

    }


    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putLong("startTime", startTime);
        editor.putLong("millisLeft", timeLeftInMilliseconds);
        editor.putBoolean("timerRunning", timeRunning);
        editor.putLong("endTime", endTime);

        editor.apply();

        if(countDownTimer != null){
            countDownTimer.cancel();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        startTime = prefs.getLong("startTime", 0);
        timeLeftInMilliseconds = prefs.getLong("millisLeft", startTime);
        timeRunning = prefs.getBoolean("timerRunning", false);
        updateButtons();
        updateButtons();

        if(timeRunning){
            endTime = prefs.getLong("endTime", 0);
            timeLeftInMilliseconds = endTime - System.currentTimeMillis();

            if(timeLeftInMilliseconds < 0){
                timeLeftInMilliseconds = 0;
                timeRunning = false;
                updateCountDownText();
                updateButtons();
            }else {
                starTimer();
            }
        }
    }


}