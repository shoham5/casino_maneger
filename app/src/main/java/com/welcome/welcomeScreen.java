package com.welcome;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shoham.loginscreen.FIreBaseApp;
import com.example.shoham.loginscreen.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import com.google.firebase.analytics.FirebaseAnalytics;


public class welcomeScreen extends AppCompatActivity {



    //DECLARE FIELDS

    Button logoutBtn,profileBtn,connectAsManegerBtn,showBtn,findMyLocBtn,mapbu;

    //FIREBASE AUTHENTICATION FILEDS
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;


    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        //ASSIGN ID
        logoutBtn = (Button) findViewById(R.id.bLogOut);
        profileBtn = (Button) findViewById(R.id.bEditProfile);
        connectAsManegerBtn = (Button) findViewById(R.id.bManegerScreen);
        showBtn = (Button) findViewById(R.id.bShowInfo);
        findMyLocBtn = (Button) findViewById(R.id.bFindMyLocation);
        mapbu= (Button) findViewById(R.id.mapbu);


        //ASSIGN INSTANCES
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                } else {
                  //  startActivity(new Intent(welcomeScreen.this, com.welcome.welcomeScreen.class));
                }
            }
        };



        findMyLocBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(welcomeScreen.this, com.example.shoham.loginscreen.Main2Activity.class));
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(welcomeScreen.this, FIreBaseApp.class));
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(welcomeScreen.this,com.example.shoham.loginscreen.informationActivity.class));
            }
        });


        connectAsManegerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(welcomeScreen.this, com.example.shoham.loginscreen.manegerScreenLogin.class));
            }
        });

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(welcomeScreen.this, com.example.shoham.loginscreen.showUserInfo.class));
            }
        });
        mapbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(welcomeScreen.this,com.example.shoham.loginscreen.MapsActivity.class));
            }
        });



        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        String name="sho";
        String text = "updateProfile";
String s =  "bEditProfile";

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID,s );
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

       //////////////// mFirebaseAnalytics.setUserId(mAuth.getCurrentUser().getUid());


       // params.putString("IMAGE NAME",name);
        //params.putString("FULL_Text",text);
      //  mFirebaseAnalytics.logEvent("SELECT_CONTENT",bundle);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }

}
