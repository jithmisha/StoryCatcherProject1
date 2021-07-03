package com.storycatcher.storycatcher;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterTabFragment extends Fragment {

    private EditText kidsname, userId, age, email, password, confpassword;
    View objectRegisterTabFragment;
    private FirebaseAuth objectFirebaseAuth;
    private Button objectButton;//Register button

    public RegisterTabFragment(){

    }

    public void createUser(){
        String kname=kidsname.getText().toString().trim();
        String uID=userId.getText().toString().trim();
        String a =age.getText().toString().trim();
        String mail=email.getText().toString().trim();
        String pass=password.getText().toString().trim();
        String conpass=confpassword.getText().toString().trim();

        if(kname.isEmpty()){
            kidsname.setError("Please enter kids name");
            kidsname.requestFocus();
            return;
        }
        if(uID.isEmpty()){
            userId.setError("Please Enter user ID");
            userId.requestFocus();
            return;
        }
        if(a.isEmpty()) {
            age.setError("Please enter kids age");
            age.requestFocus();
            return;
        }
        if(mail.isEmpty()){
            email.setError("Please enter email address");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            email.setError("Please enter valid email address");
            email.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            password.setError("Please enter password");
            password.requestFocus();
            return;
        }
        if(pass.length()<8){
            password.setError("Password length should be more than  8 characters");
            password.requestFocus();
            return;
        }
        if(!pass.equals(conpass)){
            confpassword.setError("Password Not matching");
            confpassword.requestFocus();
            return;
        }

        /*objectFirebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user=new User(kidsname.getText().toString(),userId.getText().toString(),
                                    Integer.parseInt(age.getText().toString()),email.getText().toString());
                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getContext(),"User Created",Toast.LENGTH_SHORT).show();

                                        //riderect to login layout
                                    }
                                    else{
                                        Toast.makeText(getContext(),"Failed",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(getContext(),"Failed to Create",Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/

        try{
            objectFirebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    User user=new User(kidsname.getText().toString(),userId.getText().toString(),
                                            Integer.parseInt(age.getText().toString()),email.getText().toString());
                                    FirebaseDatabase.getInstance().getReference("User")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(getContext(), "User Created", Toast.LENGTH_SHORT).show();




                                        }
                                    });
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
        }catch(Exception e){
           Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
       }

    }
    private void attachToXml(){
        try{
            kidsname=objectRegisterTabFragment.findViewById(R.id.kidsName);
            userId=objectRegisterTabFragment.findViewById(R.id.userID);
            age=objectRegisterTabFragment.findViewById(R.id.age);
            email=objectRegisterTabFragment.findViewById(R.id.email);
            password=objectRegisterTabFragment.findViewById(R.id.password);
            confpassword=objectRegisterTabFragment.findViewById(R.id.confirmpassword);
            objectButton=objectRegisterTabFragment.findViewById(R.id.register);
            objectFirebaseAuth=FirebaseAuth.getInstance();

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


