package com.storycatcher.storycatcher;

public class UserClass {
    public String emailAddress;

    public UserClass() {
    }
    public UserClass(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
