package com.example.cityguide.LocationOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cityguide.R;

public class OwnerLogin extends AppCompatActivity {

    Button callSignUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_login);
        callSignUpBtn = findViewById(R.id.owner_HaveAnAccount_btn);
        callSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerLogin.this, OwnerRegistration.class);
                startActivity(intent);
            }
        });
    }
}