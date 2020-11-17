package com.example.cityguide.LocationOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cityguide.R;

public class OwnerRegistration extends AppCompatActivity {

    Button callLoginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_registration);

        callLoginBtn = findViewById(R.id.owner_already_have_account);
        callLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerRegistration.this, OwnerLogin.class);
                startActivity(intent);
            }
        });
    }
}