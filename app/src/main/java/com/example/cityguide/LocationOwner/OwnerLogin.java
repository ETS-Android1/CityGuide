package com.example.cityguide.LocationOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cityguide.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class OwnerLogin extends AppCompatActivity {

    Button callSignUpBtn;
    EditText phone,password;
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

        phone = findViewById(R.id.owner_login_phone);
        password = findViewById(R.id.owner_login_password);
    }

    public void letTheOwnerLoggedIn(View view)
    {
        if(!validateFields()){
            return;
        }
        final String _phone = phone.getText().toString();
        final String _password = password.getText().toString();

        //final DatabaseReference RootRef;
        //RootRef = FirebaseDatabase.getInstance().getReference();
        //Database

        Query checkUser = FirebaseDatabase.getInstance().getReference("owners").orderByChild("phone").equalTo((_phone));

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    phone.setError(null);

                    String systemPassword = dataSnapshot.child("phone").child("password").getValue(String.class);
                    if(systemPassword.equals(_password)){
                        password.setError(null);

                        Toast.makeText(OwnerLogin.this,"Logged in  Successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(OwnerLogin.this, OwnerDashboard.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(OwnerLogin.this, "Password does not match!", Toast.LENGTH_SHORT).show();

                    }
                }
                else{
                    Toast.makeText(OwnerLogin.this, "Data does not exist!", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(OwnerLogin.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateFields(){
        String _phone = phone.getText().toString().trim();
        String _password = password.getText().toString().trim();

        if(_phone.isEmpty()){
            phone.setError("Phone Number cannot be empty!");
            phone.requestFocus();
            return false;
        } else if (_password.isEmpty()) {
            password.setError("Password cannot be empty!");
            password.requestFocus();
            return false;
        } else return true;

    }
}