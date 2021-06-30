package com.storycatcher.storycatcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class LoginTabFragment extends Fragment {

    TextView userId;
    TextView password;
    TextView forgotPass;
    Button loginbtn;
    float v=0;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

         userId= root.findViewById(R.id.userId);
         password= root.findViewById(R.id.password);
         forgotPass= root.findViewById(R.id.forgotPassword);
         loginbtn= root.findViewById(R.id.loginbtn);

         userId.setTranslationX(800);
         password.setTranslationX(800);
         forgotPass.setTranslationX(800);
         loginbtn.setTranslationX(800);

         userId.setAlpha(v);
         password.setAlpha(v);
         forgotPass.setAlpha(v);
         loginbtn.setAlpha(v);

         userId.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
         password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
         forgotPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
         loginbtn.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

         TextView forgotpass= (TextView) root.findViewById(R.id.forgotPassword);
         forgotpass.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View v){
                 Intent in = new Intent(getActivity(), PasswordActivity.class);

                 startActivity(in);
             }

         });



        return root;


    }
}
