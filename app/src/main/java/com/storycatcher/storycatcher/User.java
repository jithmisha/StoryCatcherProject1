package com.storycatcher.storycatcher;

import android.app.Fragment;

public class User{
    public  String kidsName, userID, email;
    public int age;

    public User(){

    }
    public User(String kName,String uID,int age,String mail){
        kidsName=kName;
        userID=uID;
        this.age=age;
        email=mail;
    }
}
