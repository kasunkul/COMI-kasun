package com.example.namilaradith.comi_beta;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.DialogFragment;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import Appclasses.FireErroDialogFragment;
import Appclasses.FireMissilesDialogFragment;
import Appclasses.User;

public class SignUpActivity extends AppCompatActivity {

    private EditText userName;
    private EditText fname;
    private EditText lname;
    private EditText email;
    private EditText mobile;
    private EditText password;
    private EditText cnPassword;
    private EditText birthDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setElements();

    }

    public void onStart(){
        super.onStart();
        birthDate = (EditText) findViewById(R.id.txtBdate);
        birthDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DialogFragment newFragment = new DatePickerFragment(v);
                    newFragment.show(getSupportFragmentManager(), "datePicker");
                }
            }
        });

    }

    public void setElements(){
        userName = (EditText) findViewById(R.id.txtUserName);
        fname = (EditText) findViewById(R.id.txtFname);
        lname = (EditText) findViewById(R.id.txtLname);
        email = (EditText) findViewById(R.id.txtlogEmail);
        password = (EditText) findViewById(R.id.txtlogPassword);
        cnPassword = (EditText) findViewById(R.id.txtConfirmPassword);
        mobile = (EditText) findViewById(R.id.txtMobile);
    }

    public void submitForm(View v){
        userName = (EditText) findViewById(R.id.txtUserName);
        fname = (EditText) findViewById(R.id.txtFname);
        lname = (EditText) findViewById(R.id.txtLname);
        email = (EditText) findViewById(R.id.txtlogEmail);
        password = (EditText) findViewById(R.id.txtlogPassword);
        cnPassword = (EditText) findViewById(R.id.txtConfirmPassword);
        mobile = (EditText) findViewById(R.id.txtMobile);
        birthDate = (EditText) findViewById(R.id.txtBdate);


        String userNameString = userName.getText().toString();
        String fnameString = fname.getText().toString();
        String lnameString = lname.getText().toString();
        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();
        String mobileString = mobile.getText().toString();
        String birthDateString = birthDate.getText().toString();


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        User user = new User();
        String new_user_account = user.create_new_user_account(userNameString, fnameString, lnameString, emailString, birthDateString, passwordString, mobileString);
        int x =Integer.parseInt(new_user_account);

        String Message= "";
        if(x == 1){
            Message = "Your Account has been created Sucessfully.";
            this.confirmFireMissiles(Message);
        }
        else{


           if(x == -1){

               Message = "Please verify recheck your mail and username";
           }
           else if(x == 0){

               Message = "Server Error Please try again later";
            }
                this.ErrorFireMissiles(Message);

        }

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
