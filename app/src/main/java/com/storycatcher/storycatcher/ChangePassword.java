package com.storycatcher.storycatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {
    private ImageButton BackBtn;
    private EditText currentPassword, newPassword, confirmNewPassword;
    private Button saveBtn;
    FirebaseAuth mAuth;
    TextInputLayout confirmPasswordTxLayout, currentPasswordTxLayout, newPasswordTxLayout;
    private ProgressBar ProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        BackBtn = findViewById(R.id.imgBackBtnChange);
        currentPassword = findViewById(R.id.currentPassword);
        newPassword = findViewById(R.id.newPassword);
        confirmNewPassword = findViewById(R.id.confirmNewPassword);
        saveBtn = findViewById(R.id.btnSave);
        ProgressBar = findViewById(R.id.progressBarChangePassword);
        confirmPasswordTxLayout = findViewById(R.id.textInputLayout2);
        currentPasswordTxLayout = findViewById(R.id.textInputLayout3);
        newPasswordTxLayout = findViewById(R.id.textInputLayout);

        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });
    }
    public void changePassword(){
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String currentPass = currentPassword.getText().toString().trim();
        String newPass = newPassword.getText().toString().trim();
        String confirmPass = confirmNewPassword.getText().toString().trim();
        String email = currentUser.getEmail();

        if(currentPass.isEmpty()){
            currentPasswordTxLayout.setEndIconVisible(false);
            currentPassword.setError("Please enter the current password");
            currentPassword.requestFocus();
            currentPassword.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    currentPasswordTxLayout.setEndIconVisible(true);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            return;
        }
        if(newPass.isEmpty()){
            newPasswordTxLayout.setEndIconVisible(false);
            newPassword.setError("Please enter new password");
            newPassword.requestFocus();
            newPassword.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    newPasswordTxLayout.setEndIconVisible(true);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            return;
        }
        if(newPass.length()<8){
            newPasswordTxLayout.setEndIconVisible(false);
            newPassword.setError("Password length should be more than  8 characters");
            newPassword.requestFocus();
            newPassword.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    newPasswordTxLayout.setEndIconVisible(true);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            return;
        }
        if(confirmPass.isEmpty()){
            confirmPasswordTxLayout.setEndIconVisible(false);
            confirmNewPassword.setError("This field cannot be kept empty");
            confirmNewPassword.requestFocus();
            confirmNewPassword.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    confirmPasswordTxLayout.setEndIconVisible(true);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            return;
        }
        if(!newPass.equals(confirmPass)){
            confirmPasswordTxLayout.setEndIconVisible(false);
            confirmNewPassword.setError("Password Not matching");
            confirmNewPassword.requestFocus();
            confirmNewPassword.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    confirmPasswordTxLayout.setEndIconVisible(true);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            return;
        }

        AuthCredential credential = EmailAuthProvider.getCredential(email,currentPass);
        currentUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                ProgressBar.setVisibility(View.VISIBLE);
                if(task.isSuccessful()){
                    currentUser.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                ProgressBar.setVisibility(View.INVISIBLE);
                                saveBtn.setEnabled(true);
                                currentPassword.setText("");
                                newPassword.setText("");
                                confirmNewPassword.setText("");
                                Toast.makeText(ChangePassword.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(ChangePassword.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    currentPasswordTxLayout.setEndIconVisible(false);
                    currentPassword.setError("Incorrect password entered");
                    ProgressBar.setVisibility(View.INVISIBLE);
                    currentPassword.requestFocus();
                    currentPassword.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            currentPasswordTxLayout.setEndIconVisible(true);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    return;
                }
            }
        });
    }
}