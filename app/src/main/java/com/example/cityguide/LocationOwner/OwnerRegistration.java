package com.example.cityguide.LocationOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cityguide.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OwnerRegistration extends AppCompatActivity {

    Button callLoginBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private EditText nameInput, phoneInput, emailInput, passwordInput, addressInput;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_registration);

        nameInput = findViewById(R.id.owner_name);
        phoneInput = findViewById(R.id.owner_phone);
        emailInput = findViewById(R.id.owner_email);
        passwordInput = findViewById(R.id.owner_password);
        addressInput = findViewById(R.id.owner_address);
        registerButton = findViewById(R.id.owner_reg_btn);
        rootNode = FirebaseDatabase.getInstance(); //it will call the  root of the database
        reference = rootNode.getReference("owners");

        callLoginBtn = findViewById(R.id.owner_already_have_account);
        callLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerRegistration.this, OwnerLogin.class);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerOwner();
            }
        });
    }

    private Boolean validateName(){
        String val = nameInput.getText().toString();
        if(val.isEmpty()){
            nameInput.setError("Field cannot be empty");
            return false;
        }
        else{
            nameInput.setError(null);
            return true;
        }

    }

    private Boolean validateAddress(){
        String val = addressInput.getText().toString();
        if(val.isEmpty()){
            addressInput.setError("Field cannot be empty");
            return false;
        }
        else{
            addressInput.setError(null);
            return true;
        }

    }

    private Boolean validateEmail() {
        String val = emailInput.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            emailInput.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            emailInput.setError("Invalid email address");
            return false;
        } else {
            emailInput.setError(null);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = phoneInput.getText().toString();

        if (val.isEmpty()) {
            phoneInput.setError("Field cannot be empty");
            return false;
        } else {
            phoneInput.setError(null);
            //phoneInput.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = passwordInput.getText().toString();
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
            passwordInput.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            passwordInput.setError("Password is too weak");
            return false;
        } else {
            passwordInput.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }

    private void registerOwner() {
        String name = nameInput.getText().toString();
        String phone = phoneInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String address = addressInput.getText().toString();

        if (!validateAddress() | !validateEmail() | !validateName() | !validatePhoneNo() | !validatePassword()){
            return;
        }

        OwnerHelperClass helperClass = new OwnerHelperClass(name, phone, email, password, address);
        reference.child(phone).setValue(helperClass);
        Toast.makeText(OwnerRegistration.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(OwnerRegistration.this, OwnerDashboard.class);
        startActivity(intent);



    }
}