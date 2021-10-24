package com.storycatcher.storycatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

public class ScreenTime extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 6000;
    private ImageButton backBtn, min15 ,min20, min25, min30, min35, min40;
    private Button savePauseBtn, resetBtn;
    private TextView timeText;
    private CountDownTimer countDownTimer;
    private boolean timeRunning;
    //private long startTime;
    private  long timeLeftInMilliseconds = START_TIME_IN_MILLIS;
    //private long endTime;

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

        //reset Button
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountDownText();
    }

    private void starTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliseconds = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                timeRunning = false;
                savePauseBtn.setText("SAVE");
                savePauseBtn.setVisibility(View.INVISIBLE);
                resetBtn.setVisibility(View.VISIBLE);

            }
        }.start();
        timeRunning = true;
        savePauseBtn.setText("PAUSE");
        resetBtn.setVisibility(View.INVISIBLE);

    }

    private void pauseTimer(){
        countDownTimer.cancel();
        timeRunning = false;
        savePauseBtn.setText("SAVE");
        resetBtn.setVisibility(View.VISIBLE);
    }

    private void resetTimer(){
        timeLeftInMilliseconds = START_TIME_IN_MILLIS;
        updateCountDownText();
        resetBtn.setVisibility(View.INVISIBLE);
        savePauseBtn.setVisibility(View.VISIBLE);

    }

    public void updateCountDownText(){
        int minutes= (int)timeLeftInMilliseconds/ 60000;
        int seconds= (int)timeLeftInMilliseconds% 60000/1000;
        String timeLeftText = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        timeText.setText(timeLeftText);
    }
}