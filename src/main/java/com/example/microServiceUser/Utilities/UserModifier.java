package com.example.microServiceUser.Utilities;

public class UserModifier {

    public UserModifier(){}

    private String mail;

    private String newMail;

    private String newPassword;

    private String newName;

    private String newSurName;

    public String getMail() {
        return mail;
    }

    public String getNewMail() {
        return newMail;
    }

    public String getNewName() {
        return newName;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getNewSurName() {
        return newSurName;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public void setNewMail(String newMail) {
        this.newMail = newMail;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setNewSurName(String newSurName) {
        this.newSurName = newSurName;
    }
}
