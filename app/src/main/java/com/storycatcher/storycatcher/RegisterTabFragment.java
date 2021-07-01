package com.storycatcher.storycatcher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterTabFragment extends Fragment {

    private EditText kidsname, userId, age, email, password, confpassword;
    private Button register;
    View objectRegisterTabFragment;
    private FirebaseAuth objectFirebaseAuth;
    private Button objectButton;//Register button

    public RegisterTabFragment(){

    }

    public void createUser(){
        try{
            if(!email.getText().toString(). isEmpty() && !password.getText().toString().isEmpty()){
                    objectFirebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(getContext(), "User Created", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
            }
            else{
                Toast.makeText(getContext(), "Fill the fields", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    private void attachToXml(){
        try{
            kidsname=objectRegisterTabFragment.findViewById(R.id.kidsName);
            userId=objectRegisterTabFragment.findViewById(R.id.userId);
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
        //ViewGroup root = (ViewGroup)inflater.inflate(R.layout.register_tab_fragment, container, false);
        //return root;

        objectRegisterTabFragment=inflater.inflate(R.layout.register_tab_fragment, container, false);
        attachToXml();
        return objectRegisterTabFragment;
    }

    }


