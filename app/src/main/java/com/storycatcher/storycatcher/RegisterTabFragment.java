package com.storycatcher.storycatcher;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class RegisterTabFragment extends Fragment {

    //variables
    private EditText  registerEmail, registerPassword, confpassword;
    View objectRegisterTabFragment;
    FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    private Button objectButton;//Register button
    private ProgressBar objectProgressBar;
    float v=0;
    String userID;
//    private FirebaseDatabase rootNode;
//    private DatabaseReference reference;

    public RegisterTabFragment(){
    }

    public void createUser(){
        String mail=registerEmail.getText().toString().trim();
        String pass=registerPassword.getText().toString().trim();
        String conpass=confpassword.getText().toString().trim();
        String noWhiteSpace="\\A\\w{4,20}\\z";


        if(mail.isEmpty()){
            registerEmail.setError("Please enter email address");
            registerEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            registerEmail.setError("Please enter valid email address");
            registerEmail.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            registerPassword.setError("Please enter password");
            registerPassword.requestFocus();
            return;
        }
        if(pass.length()<8){
            registerPassword.setError("Password length should be more than  8 characters");
            registerPassword.requestFocus();
            return;
        }
        if(!pass.equals(conpass)){
            confpassword.setError("Password Not matching");
            confpassword.requestFocus();
            return;
        }

        try{
            objectProgressBar.setVisibility(View.VISIBLE);
            objectButton.setEnabled(false);


            mAuth.createUserWithEmailAndPassword(mail, pass)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                        UserClass userInfo= new UserClass(mail);
                                        userID=mAuth.getCurrentUser().getUid();
                                        DocumentReference documentReference=fstore.collection("Users").document(userID);
                                        documentReference.set(userInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(getContext(), "User Created", Toast.LENGTH_SHORT).show();
                                            objectProgressBar.setVisibility(View.INVISIBLE);
                                            objectButton.setEnabled(true);
                                            registerEmail.setText("");
                                            registerPassword.setText("");
                                            confpassword.setText("");
                                            Intent in= new Intent(getContext(),SignOrRegister.class);
                                            startActivity(in);
                                        }
                                    });

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    objectProgressBar.setVisibility(View.INVISIBLE);
                                    objectButton.setEnabled(true);
                                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
        }catch(Exception e){
           Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
       }

    }
    private void attachToXml(){
        try{
            registerEmail=objectRegisterTabFragment.findViewById(R.id.registerEmail);
            registerPassword=objectRegisterTabFragment.findViewById(R.id.registerPassword);
            confpassword=objectRegisterTabFragment.findViewById(R.id.confirmpassword);
            objectButton=objectRegisterTabFragment.findViewById(R.id.register);
            mAuth =FirebaseAuth.getInstance();
            fstore=FirebaseFirestore.getInstance();
            objectProgressBar=objectRegisterTabFragment.findViewById(R.id.progressBarRegister);

            objectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createUser();
                }
            });
        }
        catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        objectRegisterTabFragment=inflater.inflate(R.layout.register_tab_fragment, container, false);
        attachToXml();
        return objectRegisterTabFragment;
    }

    }


