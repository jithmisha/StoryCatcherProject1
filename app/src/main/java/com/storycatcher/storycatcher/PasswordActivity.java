package com.storycatcher.storycatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordActivity extends AppCompatActivity {
    private EditText emailPassword;
    private Button resetPassword;
    private ImageButton imgButtonBack;
    FirebaseAuth auth;
    float v=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView(R.layout.activity_password);

        emailPassword = (EditText)findViewById(R.id.recoverAccountEmail);
        resetPassword = (Button)findViewById(R.id.btnresetpassword);
        auth = FirebaseAuth.getInstance();

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });

        imgButtonBack = (ImageButton)findViewById(R.id.imgBtnBack);
        imgButtonBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openLoginAdapterActivity();
            }
        });
        //animation
        // emailPassword=(EditText)findViewById(R.id.recoverAccountEmail);
        // resetPassword=(Button)findViewById(R.id.btnresetpassword);

        emailPassword.setTranslationX(800);
        resetPassword.setTranslationX(800);

        emailPassword.setAlpha(v);
        resetPassword.setAlpha(v);

        emailPassword.setTranslationX(800);
        resetPassword.setTranslationX(800);

        emailPassword.setAlpha(v);
        resetPassword.setAlpha(v);

        emailPassword.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        resetPassword.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();

        }

    private void resetPassword(){
        String email=emailPassword.getText().toString().trim();

        if(email.isEmpty()){
            emailPassword.setError("Email is required");
            emailPassword.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailPassword.setError("Please provide valid email");
            emailPassword.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(PasswordActivity.this,"Please check email to reset password",Toast.LENGTH_LONG).show();
                    emailPassword.setText("");
                }
                else{
                    Toast.makeText(PasswordActivity.this,"Try again! Something went wrong",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    //back button
    public void openLoginAdapterActivity(){
        Intent intent=new Intent(this,SignOrRegister.class);
        startActivity(intent);
    }

}