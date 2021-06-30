package com.example.demotaco.security;

import lombok.Data;
import com.example.demotaco.domain.User;

@Data
public class Registration {
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;

    public User convertUser() {
        return new User(username, password, fullname, street, city, state, zip, phoneNumber);
    }
}
