package com.storycatcher.storycatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameIntermediate extends AppCompatActivity {
    private TextView txtViewWord,txtViewInfo,txtCount;
    private EditText editTxtGuess;
    private Button btnCheck,btnNew;

    private String currentWord;
    private int count=0;

    Random r;
    String [] dictionary = {
            "STAR","HIGH","NINE","DUCK","FIVE","PLAY","HILL","RAIN","HOME","FARM","LION","SONG","KING","TRIP","WOOD","WALK","STAY",
            "LAZY","TREE","FOOD","FIRE"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_intermediate);

        txtViewWord=findViewById(R.id.txtViewWord);
        txtCount=findViewById(R.id.txtCount);
        txtViewInfo=findViewById(R.id.txtViewInfo);
        editTxtGuess=findViewById(R.id.editTxtGuess);
        btnCheck=findViewById(R.id.btnCheck);
        btnNew=findViewById(R.id.btnNew);

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
                if(count==2){
                    txtViewInfo.setText("congratulations!! You passed the Intermediate level");
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