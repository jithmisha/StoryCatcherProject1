package com.storycatcher.storycatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class PasswordActivity extends AppCompatActivity {
    private EditText emailPassword;
    private Button resetPassword;
    private ImageButton imgButtonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView(R.layout.activity_password);

        emailPassword=(EditText)findViewById(R.id.txtemailpass);
        resetPassword=(Button)findViewById(R.id.btnresetpassword);

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail=emailPassword.getText().toString().trim();
            }
        });

        imgButtonBack=(ImageButton)findViewById(R.id.imgBtnBack);
        imgButtonBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openLoginAdapterActivity();
            }
        });

    }
    public void openLoginAdapterActivity(){
        Intent intent=new Intent(this,SignOrRegister.class);
        startActivity(intent);
    }

}