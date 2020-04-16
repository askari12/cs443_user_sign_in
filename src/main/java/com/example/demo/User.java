package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Indexed;

@Document(collection = "User")
public class User
{

    @Id
    private String userId;
    private String firstName;
    private String lastName;


    private String email;
    private String passwordHash;
    private String passwordSalt;
    private String password;

    public User(String firstName, String lastName, String email, String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userId = email;
        this.password = "";

        PasswordManager passwordManager = new PasswordManager();
        String[] arr = passwordManager.encodePassword(password);

        this.passwordSalt = arr[0];
        this.passwordHash = arr[1];
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordHash() {return  this.passwordHash;}

    public void setPasswordHash (String passwordHash) {this.passwordHash = passwordHash;};

    public String getPasswordSalt() {return  this.passwordSalt;}

    public void setPasswordSalt (String passwordSalt) {this.passwordSalt = passwordSalt;};
}
