package com.example.cityguide.User;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cityguide.Prevalent.Prevalent;
import com.example.cityguide.R;
import com.google.android.material.textfield.TextInputLayout;

import io.paperdb.Paper;

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
        String UserEmailkey = Paper.book().read(Prevalent.UserEmailKey);
        String UserNamekey = Paper.book().read(Prevalent.UserNameKey);
        String UserPhonekey = Paper.book().read(Prevalent.UserPhoneKey);
        String UserUsernamekey = Paper.book().read(Prevalent.UserUsernameKey);

        fullNameLabel.setText(UserNamekey);
        usernameLabel.setText(UserUsernamekey);
        username.setText(UserUsernamekey);
        fullName.setText(UserNamekey);
        email.setText(UserEmailkey);
        phoneNo.setText(UserPhonekey);
        //password.getEditText().setText(user_password);


    }
}