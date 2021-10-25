package com.storycatcher.storycatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameAdvanced extends AppCompatActivity {

    private TextView txtViewWord,txtViewInfo,txtCount;
    private EditText editTxtGuess;
    private Button btnCheck,btnNew;
    private ImageButton imgBackBtn;

    private String currentWord;
    private int count=0;

    Random r;
    String [] dictionary = {
            "LITTLE","GREAT","ANIMAL","PIANIST","PIANO","HORSES", "SUNDAY","NIGHT","SPIDER","FRIEND","WATER","MOUSE","DANCE","MISSION","SHARE","HOUSE","BASKET",
            "MOTHER","FATHER","CLOCK","SHARK"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_advanced);

        txtViewWord=findViewById(R.id.txtViewWord);
        txtCount=findViewById(R.id.txtCount);
        txtViewInfo=findViewById(R.id.txtViewInfo);
        editTxtGuess=findViewById(R.id.editTxtGuess);
        btnCheck=findViewById(R.id.btnCheck);
        btnNew=findViewById(R.id.btnNew);
        imgBackBtn=findViewById(R.id.imgBackBtnAdv);

        r=new Random();
        newGame();

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTxtGuess.getText().toString().equalsIgnoreCase(currentWord)){
                    txtViewInfo.setText("Awesome!!!");
                    count = count+1;
                    txtCount.setText(String.valueOf(count));

                    btnCheck.setEnabled(false);
                    btnNew.setEnabled(true);
                }
                else{
                    txtViewInfo.setText("Try Again!!!");
                }
                if(count==20){
                    txtViewInfo.setText("congratulations!! You passed the Advanced level");
                }
            }
        });

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
                txtViewInfo.setText("");
            }
        });

        imgBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }

    //shuffle algorithm
    private String shuffleWord(String word){
        List<String> letters= Arrays.asList(word.split(""));
        Collections.shuffle(letters);
        String shuffled = "";
        for (String letter : letters){
            shuffled +=letter;

        }
        return shuffled;
    }

    private void newGame(){
        //get random word from the dictionary
        currentWord=dictionary[r.nextInt(dictionary.length)];

        //show the shuffles word
        txtViewWord.setText(shuffleWord(currentWord));

        //clear the text field
        editTxtGuess.setText("");

        //switch buttons
        btnNew.setEnabled(false);
        btnCheck.setEnabled(true);
    }
}