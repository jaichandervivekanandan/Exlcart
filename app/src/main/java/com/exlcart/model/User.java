package com.exlcart.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Saravanan on 7/4/2015.
 */
public class User {

    @SerializedName("user_id")
    public String userID;
    public String email;
    public String firstname;
    public String lastname;
    public String telephone;

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getTelephone() {
        return telephone;
    }
}
