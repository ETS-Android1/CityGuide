package com.example.cityguide.Common;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cityguide.Common.LoginSinup.SignUp;
import com.example.cityguide.LocationOwner.OwnerLogin;
import com.example.cityguide.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    
    private EditText email;
    private EditText password;
    private Button login;
    private TextView callSignUp,forgotPassword, callOwner;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.inputEmail);
        password = (EditText) findViewById(R.id.inputPassword);
        login = findViewById(R.id.loginButton);
        callSignUp = findViewById(R.id.newUser);
        callOwner = findViewById(R.id.callOwnerPage);
        forgotPassword = findViewById(R.id.ForgotPassword);
        auth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtEmail = email.getText().toString();
                String txtPassword = password.getText().toString();
                loginUser(txtEmail,txtPassword);
            }
        });

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

        callOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, OwnerLogin.class);
                startActivity(intent);
            }
        });

    }

    public String removeSpecialCharacters(String str)
    {
        String resultStr="";

        for (int i=0;i<str.length();i++)
        {
            if (str.charAt(i)>64 && str.charAt(i)<=122)
            {

                resultStr=resultStr+str.charAt(i);
            }
        }
        return resultStr;
    }

    public void onBackPressed() {
        finishAffinity();
    }

    private void loginUser(String e,String p)
    {
        String val = email.getText().toString();
        String val2 = password.getText().toString();
        if(val.isEmpty()){
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(val).matches()){
            email.setError("Please enter a valid email!");
            email.requestFocus();
            return;
        }

        if(val2.isEmpty()){
            password.setError("Password is required!");
            password.requestFocus();
            return;
        }

        auth.signInWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    final String userEnteredEmail = email.getText().toString();
                    String userEnteredPassword = password.getText().toString();
                    final String modifiedEmail = removeSpecialCharacters(userEnteredEmail);

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

                    Query checkUser = reference.orderByChild("email").equalTo(userEnteredEmail);

                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String nameFromDB = dataSnapshot.child(modifiedEmail).child("name").getValue(String.class);
                            String usernameFromDB = dataSnapshot.child(modifiedEmail).child("username").getValue(String.class);
                            String phoneNoFromDB = dataSnapshot.child(modifiedEmail).child("phoneNo").getValue(String.class);
                            String emailFromDB = dataSnapshot.child(modifiedEmail).child("email").getValue(String.class);
                            Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
                            intent.putExtra("name", nameFromDB);
                            intent.putExtra("username", usernameFromDB);
                            intent.putExtra("email", emailFromDB);
                            intent.putExtra("phoneNo", phoneNoFromDB);
//                            Toast.makeText(LoginActivity.this,nameFromDB,Toast.LENGTH_SHORT).show();
//                            Toast.makeText(LoginActivity.this,phoneNoFromDB,Toast.LENGTH_SHORT).show();

                            startActivity(intent);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

//                    Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(LoginActivity.this, UserDashboard.class));

                }else{
                    Toast.makeText(LoginActivity.this,"Failed to login! Please check your credentials",Toast.LENGTH_LONG).show();
                }
            }
        });
//        auth.signInWithEmailAndPassword(e,p).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//            @Override
//            public void onSuccess(AuthResult authResult) {
//                Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(LoginActivity.this, UserDashboard.class));
//                finish();
//            }
//        });
    }
}