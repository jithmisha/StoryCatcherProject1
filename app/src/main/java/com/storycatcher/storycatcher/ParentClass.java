package com.storycatcher.storycatcher;

public class ParentClass {
    public String emailAddress;

    public ParentClass() {
    }
    public ParentClass(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
