package com.example.shoham.loginscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.recyclerView.user_Report_Mangent;

public class manegerScreenLogin extends AppCompatActivity {

    Button loginAsManeger, bOnOff;
    EditText userEmailEditTxt,userCodeEditTxt;
    String userEmailString, userCodeString="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maneger_screen_login);

        bOnOff = (Button) findViewById(R.id.onBtn);
        loginAsManeger = (Button) findViewById(R.id.bLoginManeger);
        loginAsManeger.setVisibility(View.GONE);
        userCodeEditTxt = findViewById(R.id.etEmailMng);
        userCodeEditTxt = findViewById(R.id.etCodeMng);

        userCodeString = userCodeEditTxt.getText().toString().trim();







        bOnOff.setOnClickListener(new View.OnClickListener()
    {

        @Override
        public void onClick(View view) {

            userCodeString = userCodeEditTxt.getText().toString().trim();
            //Toast.makeText(manegerScreenLogin.this, userCodeString, Toast.LENGTH_LONG).show();

            if (userCodeString.compareTo("7777777")==0) {
            //    Toast.makeText(manegerScreenLogin.this, "******", Toast.LENGTH_LONG).show();
                loginAsManeger.setVisibility(View.VISIBLE);

            }
        }




    });


        //MOVE TO MANEGER SCREEN
        loginAsManeger.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(manegerScreenLogin.this, Main2Activity.class));

            }

        });

        }
    }



