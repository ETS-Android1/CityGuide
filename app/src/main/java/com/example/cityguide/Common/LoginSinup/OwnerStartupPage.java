package com.example.cityguide.Common.LoginSinup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cityguide.LocationOwner.OwnerLogin;
import com.example.cityguide.LocationOwner.OwnerRegistration;
import com.example.cityguide.R;

public class OwnerStartupPage extends AppCompatActivity {

    Button OwnerStartUpLogin;
    Button OwnerStartUpSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_owner_startup_page);
        OwnerStartUpLogin = findViewById(R.id.owner_startupPage_login_btn);

        OwnerStartUpLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerStartupPage.this, OwnerLogin.class);
                startActivity(intent);
            }
        });
        OwnerStartUpSignup = findViewById(R.id.owner_startupPage_signup_btn);
        OwnerStartUpSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerStartupPage.this, OwnerRegistration.class);
                startActivity(intent);
            }
        });

    }
}