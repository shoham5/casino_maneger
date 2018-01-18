package com.example.shoham.loginscreen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FIreBaseApp extends AppCompatActivity {
    public String  ss;

    final int PASS_LEN = 6;
    String userId;
    String userEmailString, userPassString;


    // views and fields
    Button createtoUser, loginBtn;
    EditText userEmailEditTxt, userPasswordEditTxt;

            //=findViewById(R.id.LogintextView);
    // FIREBASE AUTHENTICATION FIELDS
    FirebaseAuth mAuth;
    FirebaseAuth exampleAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    // FIREBASE DATABASE REF
    private DatabaseReference mDatabase;
    boolean passwordVal(String s){
    if(s.length()<6)return false;
    else return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base_app);


        //ASSIGN iD
        createtoUser = (Button) findViewById(R.id.bcreateUser);
        loginBtn = (Button) findViewById(R.id.bmoveToUser);
        userEmailEditTxt = (EditText) findViewById(R.id.emailEditTxtFiled);
        userPasswordEditTxt = (EditText) findViewById(R.id.passwordEditTxtFiled);

        final String[] currUserId = new String[1];

        // init the database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //ASSIGN AUTHTICATION INSTANCES
        mAuth = FirebaseAuth.getInstance();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    startActivity(new Intent(FIreBaseApp.this, com.welcome.welcomeScreen.class));


                } else {
                    //Toast.makeText(FIreBaseApp.this , "IN else on authstatechanged ", Toast.LENGTH_LONG).show();
                    ss="not";
                }
            }
        };


        //MOVE TO LOGIN
        loginBtn.setOnClickListener(new View.OnClickListener()

            {

                @Override
                public void onClick (View view){

                        /// login opertion
                        String userEmailString, userPassString;
                        userEmailString = userEmailEditTxt.getText().toString().trim();
                        userPassString = userPasswordEditTxt.getText().toString().trim();

                        if(!TextUtils.isEmpty(userEmailString) && !TextUtils.isEmpty(userPassString))
                        {
                            mAuth.signInWithEmailAndPassword(userEmailString,userPassString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        TextView t =(findViewById(R.id.LogintextView));
                                        t.setText("Welcome");
                                        startActivity(new Intent(FIreBaseApp.this, com.welcome.welcomeScreen.class));
                                    } else {
                                        Toast.makeText(FIreBaseApp.this, "LOGIN faild-incoorect user name or password", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }

                    }

            });


        //ACT CREATE USER BTUUN LISTENER
        createtoUser.setOnClickListener(new View.OnClickListener()

            {

                @Override
                public void onClick (View view) {

                    userEmailString = userEmailEditTxt.getText().toString().trim();
                    userPassString = userPasswordEditTxt.getText().toString().trim();
                    if (!TextUtils.isEmpty(userEmailString) && !TextUtils.isEmpty(userPassString)) {
                        if (userPassString.length() >= PASS_LEN) {
                            mAuth.createUserWithEmailAndPassword(userEmailString, userPassString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        Toast.makeText(FIreBaseApp.this, " user Account created", Toast.LENGTH_LONG).show();
                                        TextView t =(findViewById(R.id.LogintextView));
                                        t.setText("Welcome");
                                        createLoginAct(userEmailString, userPassString);
                                      //  Toast.makeText(FIreBaseApp.this, "Faild to create", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(FIreBaseApp.this, com.welcome.welcomeScreen.class));
                                    } else {
                                        Toast.makeText(FIreBaseApp.this, "Faild to create", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(FIreBaseApp.this, "Faild to create-the PASSWORD is short", Toast.LENGTH_LONG).show();

                        }
                    }
                }
            });

        }



        //WHEN THE CREATE USER BUTTUN START
        @Override

    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        //Toast.makeText(FIreBaseApp.this , "onstart ", Toast.LENGTH_LONG).show();
    }

    //WHEN THE CREATE USER BUTTUN STOP
    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }

    //CREATE OBJECT LOGIN WITH IS FIELD
    public void createLoginAct(String email , String password)
    {
        Login login = new Login(email,password);
        userId=FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabase.child("login").child(userId).setValue(login);

    }
}

