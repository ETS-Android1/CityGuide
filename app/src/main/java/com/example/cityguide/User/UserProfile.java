package com.example.cityguide.User;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cityguide.R;
import com.google.android.material.textfield.TextInputLayout;

public class UserProfile extends AppCompatActivity {

    TextView fullName,email,phoneNo,username;
    TextView fullNameLabel,usernameLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //hooks
        fullName = findViewById(R.id.full_name_profile);
        email = findViewById(R.id.email_profile);
        phoneNo = findViewById(R.id.phone_no_profile);
        //password = findViewById(R.id.password_profile);
        username = findViewById(R.id.user_nane_profile);
        fullNameLabel = findViewById(R.id.full_name);
        usernameLabel = findViewById(R.id.username_profile);

        //Show All Data

        showAllUserData();

    }

    private void showAllUserData() {

        Intent intent = getIntent();
        String user_username = intent.getStringExtra("username");
        String user_name = intent.getStringExtra("name");
        String user_email = intent.getStringExtra("email");
        String user_phoneNo = intent.getStringExtra("phoneNo");
        //String user_password = intent.getStringExtra("password");
        //Toast.makeText(UserProfile.this,user_phoneNo,Toast.LENGTH_SHORT).show();
        fullNameLabel.setText(user_username);
        usernameLabel.setText(user_name);
        username.setText(user_name);
        fullName.setText(user_username);
        email.setText(user_email);
        phoneNo.setText(user_phoneNo);
        //password.getEditText().setText(user_password);


    }
}