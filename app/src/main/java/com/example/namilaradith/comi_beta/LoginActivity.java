package com.example.namilaradith.comi_beta;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Appclasses.FireMissilesDialogFragment;
import Appclasses.User;
import Appclasses.FireErroDialogFragment;
import Appclasses.ComiDB;


public class LoginActivity extends AppCompatActivity {


    private EditText email;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnSignIn = (Button)findViewById(R.id.btnSignIn);
        Button btnSignUp = (Button)findViewById(R.id.btnRegister);

//        //click event listener to sign in button
//        btnSignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(i);
//            }
//        });

        //click event listener to sign up button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

    }

    public void submitForm(View v) {


        email = (EditText) findViewById(R.id.txtlogEmail);
        password = (EditText) findViewById(R.id.txtlogPassword);


        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();



        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        User user = new User();
        String new_user_account = user.user_login_request(emailString, passwordString);

        int x = Integer.parseInt(new_user_account);



        String Message = "";
        if (x == 1) {

            ComiDB COMIDBhelper = new ComiDB(LoginActivity.this);
            COMIDBhelper.resetUserTbl();
            String response = user.get_all_user_details(emailString);

            String temp_name ;
            String temp_contact ;
            String temp_dob;
            String temp_email ;
            String temp_username ;



            if(response.equals("1")){

                temp_name = user.name;
                temp_contact = user.contact;
                temp_dob = user.dob;
                temp_email = user.email;
                temp_username = user.username;

                COMIDBhelper.resetUserTbl();
                COMIDBhelper.insertuser(temp_name,temp_contact,temp_email,"srilanka",temp_username,1);


            }

            Cursor c = COMIDBhelper.getAllData(1);
            c.moveToFirst();

            Message = "Welcome ";
            Message = Message.concat(c.getString(1));
            this.confirmFireMissiles(Message);


        } else {

            if (x == -2 || x == 0) {
                Message = "Please verify your credentials";
                this.ErrorFireMissiles(Message);
            }

        }



    }

    public void create_new_account_btn(View view){

        Intent goToNextActivity = new Intent(this, SignUpActivity.class);
        startActivity(goToNextActivity);
    }
    public void confirmFireMissiles(String i) {
        Intent goToNextActivity = new Intent(this, MainActivity.class);
        DialogFragment newFragment = new FireMissilesDialogFragment(i,goToNextActivity);
        newFragment.show(getSupportFragmentManager(), "missiles");
    }
    public void ErrorFireMissiles(String i) {
        DialogFragment newFragment = new FireErroDialogFragment(i);
        newFragment.show(getSupportFragmentManager(), "missiles");
    }

}
