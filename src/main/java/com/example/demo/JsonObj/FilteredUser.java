package com.example.demo.JsonObj;

public class FilteredUser {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;

    public FilteredUser(String userId, String firstName, String lastName, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    public FilteredUser() {}

    public String getUserId() {return this.userId;}

    public String getFirstName() {return this.firstName;}

    public String getLastName() {return this.lastName;}

    public String getEmail() {return this.email;}


    public void setUserId(String id) {this.userId = id;}

    public void setFirstName(String name) {this.firstName = name;}

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
