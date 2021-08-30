package com.storycatcher.storycatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {
    private ImageButton BackBtn;
    private EditText currentPassword, newPassword, confirmNewPassword;
    private Button saveBtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        BackBtn=findViewById(R.id.imgBackBtnChange);
        currentPassword=findViewById(R.id.currentPassword);
        newPassword=findViewById(R.id.newPassword);
        confirmNewPassword=findViewById(R.id.confirmNewPassword);
        saveBtn=findViewById(R.id.btnSave);
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser currentUser=mAuth.getCurrentUser();

        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Settings.class));
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentPass=currentPassword.getText().toString().trim();
                String newPass=newPassword.getText().toString().trim();
                String confirmPass=confirmNewPassword.getText().toString().trim();
                String email=currentUser.getEmail();


                if(currentPass.isEmpty()){
                    currentPassword.setError("Please enter the current password");
                    currentPassword.requestFocus();
                    return;
                }
                if(newPass.isEmpty()){
                    newPassword.setError("Please enter new password");
                    newPassword.requestFocus();
                    return;
                }
                if(confirmPass.isEmpty()){
                    confirmNewPassword.setError("This field cannot be kept empty");
                    confirmNewPassword.requestFocus();
                    return;
                }
                if(newPass.length()<8){
                    newPassword.setError("Password length should be more than  8 characters");
                    newPassword.requestFocus();
                    return;
                }
                if(!newPass.equals(confirmPass)){
                    confirmNewPassword.setError("Password Not matching");
                    confirmNewPassword.requestFocus();
                    return;
                }

                AuthCredential credential= EmailAuthProvider.getCredential(email,currentPass);
                currentUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            currentUser.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(ChangePassword.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(ChangePassword.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else{
                            currentPassword.setError("Incorrect password entered");
                            currentPassword.requestFocus();
                            return;
                        }
                    }
                });
            }
        });







    }
}