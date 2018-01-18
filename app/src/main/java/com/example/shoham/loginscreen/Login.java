package com.example.shoham.loginscreen;

/**
 * Created by shoham on 12/25/2017.
 */

public class Login {

    String email ;
    String password;

    public Login(Login login) {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Login( String email ,String password) {
       this.email = email;
        this.password = password;

    }

    public Login() {

    }
}
