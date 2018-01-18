package com.recyclerView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.shoham.loginscreen.Login;
import com.example.shoham.loginscreen.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class user_Report_Mangent extends AppCompatActivity {


    private static final String TAG = "PostDetailActivity";
    private DatabaseReference mPostReference;
ArrayList<Login> logArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__report_mangent);



        // Initialize Database
        mPostReference = FirebaseDatabase.getInstance().getReference()
                .child("login");

logArr = new ArrayList<>();


        mPostReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Login login = dataSnapshot.getValue(Login.class);
                    logArr.add(login);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
    }
}
