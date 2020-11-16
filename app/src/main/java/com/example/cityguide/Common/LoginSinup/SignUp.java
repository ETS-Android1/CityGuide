package com.example.cityguide.Common.LoginSinup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cityguide.Common.LoginActivity;
import com.example.cityguide.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    TextInputLayout regName, regUsername, regEmail, regPhoneno, regPassword;
    Button reg_btn,HaveanAccount;

    Button callLogin;
    Button SignUptoLogin;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth = FirebaseAuth.getInstance();
        callLogin = findViewById(R.id.HaveanAccount);
        callLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        regName = findViewById(R.id.name);
        regUsername = findViewById(R.id.username);
        regEmail = findViewById(R.id.email);
        regPhoneno = findViewById(R.id.ph);
        regPassword = findViewById(R.id.password);
        reg_btn = findViewById(R.id.reg_btn);
        HaveanAccount = findViewById(R.id.HaveanAccount);
        rootNode = FirebaseDatabase.getInstance(); //it will call the  root of the database
        reference = rootNode.getReference("users");
//        reg_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                rootNode = FirebaseDatabase.getInstance(); //it will call the  root of the database
//                reference = rootNode.getReference("users");
//
//                //get all the values
//                String name = regName.getEditText().getText().toString();
//                String username = regUsername.getEditText().getText().toString();
//                String email = regEmail.getEditText().getText().toString();
//                String phoneNo = regPhoneno.getEditText().getText().toString();
//                String password = regPassword.getEditText().getText().toString();
//
//
//                UserHelperClass helperClass = new UserHelperClass(name,username,email,phoneNo,password);
//
//                reference.child(phoneNo).setValue(helperClass);
//            }
//        });


    }

    private Boolean validateName(){
        String val = regName.getEditText().getText().toString();
        if(val.isEmpty()){
            regName.setError("Field cannot be empty");
            return false;
        }
        else{
            regName.setError(null);
            return true;
        }

    }

    private Boolean validateUsername(){
        String val = regUsername.getEditText().getText().toString();
        //String noWhiteSpace = "(?=\\s+$)";
        if(val.isEmpty()){
            regUsername.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            regUsername.setError("Username too long");
            return false;
        }
        else{
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        } else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = regPhoneno.getEditText().getText().toString();

        if (val.isEmpty()) {
            regPhoneno.setError("Field cannot be empty");
            return false;
        } else {
            regPhoneno.setError(null);
            regPhoneno.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }

    public void registerUser(View view)
    {
        if(!validateName() | !validatePassword() | !validateUsername() | !validatePhoneNo() | !validateEmail() ){
            return;
        }


        String name = regName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String phoneNo = regPhoneno.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();
        UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo,password);
        reference.child(phoneNo).setValue(helperClass);
        firebaseAuth.createUserWithEmailAndPassword(email, password);
        Toast.makeText(SignUp.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SignUp.this, LoginActivity.class);
        startActivity(intent);

    }
}