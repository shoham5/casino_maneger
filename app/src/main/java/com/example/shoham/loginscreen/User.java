package com.example.shoham.loginscreen;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shoham on 12/23/2017.
 */

@IgnoreExtraProperties
public class User {

        public String firstName;
        public String lastName;
        public String address;
        public String city;
        public String email;
        public String phone;


        public User(User user) {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String firstName, String lastName, String address, String city, String email ,String phone) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.city = city;
            this.email = email;
            this.phone = phone;

        }

    public User() {

    }

    @Exclude
        public Map<String, Object> toMap() {
            HashMap<String, Object> result = new HashMap<>();
            result.put("first name", firstName);
            result.put("last name", lastName);
            result.put("title", address);
            result.put("city", city);
            result.put("email", email);
            result.put("phone", phone);

            return result;
        }

    }



