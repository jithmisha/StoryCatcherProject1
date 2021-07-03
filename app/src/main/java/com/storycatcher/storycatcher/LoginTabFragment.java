package com.storycatcher.storycatcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginTabFragment extends Fragment {

    TextView logInmail;
    TextView logInpassword;
    TextView logInforgotPass;

    float v=0;

    private View objectLoginTabFragment;
    private FirebaseAuth objectFirebaseAuth;
    private EditText loginEmail,logInPassWord;
    private Button loginButton;//login button

    private void initializeVariable(){
        try{
            objectFirebaseAuth=FirebaseAuth.getInstance();
            loginEmail=objectLoginTabFragment.findViewById(R.id.logInEmail);
            logInPassWord=objectLoginTabFragment.findViewById(R.id.logInPassword);
            loginButton=objectLoginTabFragment.findViewById(R.id.loginbtn);

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    logInUser();
                }
            });
        }catch(Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void logInUser(){
        try{
            if(!loginEmail.getText().toString().isEmpty() && !logInPassWord.getText().toString().isEmpty()){
                objectFirebaseAuth.signInWithEmailAndPassword(loginEmail.getText().toString(),logInPassWord.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                startActivity(new Intent(getActivity().getApplicationContext(),Profile.class));
                                getActivity().finish();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(),"Invalid email or password", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
            else{
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        objectLoginTabFragment=(ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);


        logInmail= objectLoginTabFragment.findViewById(R.id.logInEmail);
        logInpassword= objectLoginTabFragment.findViewById(R.id.logInPassword);
        logInforgotPass= objectLoginTabFragment.findViewById(R.id.logInForgotPassword);
        loginButton=objectLoginTabFragment.findViewById(R.id.loginbtn);

        logInmail.setTranslationX(800);
        logInpassword.setTranslationX(800);
        logInforgotPass.setTranslationX(800);
        loginButton.setTranslationX(800);

        logInmail.setAlpha(v);
        logInpassword.setAlpha(v);
        logInforgotPass.setAlpha(v);
        loginButton.setAlpha(v);

         logInmail.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
         logInpassword.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
         logInforgotPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
         loginButton.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

         //TextView forgotpass= (TextView) root.findViewById(R.id.forgotPassword);
        TextView forgotpass= (TextView) objectLoginTabFragment.findViewById(R.id.logInForgotPassword);
        forgotpass.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View v){
                 Intent in = new Intent(getActivity(), PasswordActivity.class);

                 startActivity(in);
             }

         });
        //return root;
        initializeVariable();
        return objectLoginTabFragment;


    }
}
