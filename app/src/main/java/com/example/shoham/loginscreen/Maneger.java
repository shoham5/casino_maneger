package com.example.shoham.loginscreen;

/**
 * Created by shoham on 12/25/2017.
 */

public class Maneger extends User{


    String code;
    public Maneger(Maneger maneger) {
        super();
        // Default constructor required for calls to DataSnapshot.getValue(Maneger.class)
    }

    public Maneger(User user,String mPass) {
       super(user);
       this.code=mPass;

    }

}
