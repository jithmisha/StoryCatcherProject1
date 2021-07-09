package com.storycatcher.storycatcher;

import android.app.Fragment;

public class UserClass {
    public  String kidsName, userID, email;
    public int age;

    public UserClass(){
    }
    public UserClass(String kName, String uID, int age, String mail){
        kidsName=kName;
        userID=uID;
        this.age=age;
        email=mail;
    }

    /*public String getKidsName() {
        return kidsName;
    }

    public void setKidsName(String kidsName) {
        this.kidsName = kidsName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

     */
}
