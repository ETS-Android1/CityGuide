package com.example.cityguide.Common.LoginSinup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cityguide.Common.HotelsDetails;
import com.example.cityguide.Common.RestDetails;
import com.example.cityguide.Common.RestaurantListView;
import com.example.cityguide.HelperClasses.Home.restaurant;
import com.example.cityguide.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class ShopsAddReview extends AppCompatActivity {
    TextView rateCount,showRating;
    EditText review;
    Button submit;
    RatingBar ratingBar;
    float rateValue;String temp;
    String restId ="";
    FirebaseDatabase database;
    DatabaseReference rest;
    String newStarsTillNow;
    String newCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops_add_review);
        rateCount = findViewById(R.id.ratingCount2);
        ratingBar = findViewById(R.id.ratingBar2);
        review = findViewById(R.id.writeReview2);
        submit = findViewById(R.id.submitButton2);
        showRating = findViewById(R.id.showRating2);
        database = FirebaseDatabase.getInstance();
        rest = database.getReference("Shops");
        if(getIntent() != null)
        {
            restId = getIntent().getStringExtra("RestId");
        }
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rateValue = ratingBar.getRating();
                if(rateValue<=1 && rateValue>0)
                {
                    rateCount.setText("Bad "+rateValue+"/5");
                }
                else if(rateValue<=2 && rateValue>0)
                {
                    rateCount.setText("Ok "+rateValue+"/5");
                }
                else if(rateValue<=3 && rateValue>0)
                {
                    rateCount.setText("Good "+rateValue+"/5");
                }
                else if(rateValue<=4 && rateValue>0)
                {
                    rateCount.setText("Very Good "+rateValue+"/5");
                }
                else if(rateValue<=5 && rateValue>0)
                {
                    rateCount.setText("Exceptional "+rateValue+"/5");
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                temp = rateCount.getText().toString();
//                showRating.setText("Your Rating : "+temp+" ");


                rest.child(restId).addValueEventListener(new ValueEventListener() {
                    int i=0;
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        restaurant restaurant = dataSnapshot.getValue(com.example.cityguide.HelperClasses.Home.restaurant.class);

                        String starsTillNow = restaurant.getStarsTillNow();
                        double count1 = Double.valueOf(starsTillNow);
                        String reviewCount = restaurant.getCustomercount();
                        int count = Integer.parseInt(reviewCount);
                        double back = count1*count;
                        back +=(rateValue);
                        count +=1;
                        back /=(count);
                        double newRating = back;
                        String send = String.format("%.2f",newRating);

                        String send2 = Integer.toString(count);
                        newStarsTillNow = new String(send);
                        newCount = new String(send2);
                        if(i==0) {
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("Customercount", newCount);
                            map.put("starsTillNow", newStarsTillNow);

                            rest.child(restId).updateChildren(map).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Toast.makeText(getApplicationContext(), "Your Rating Successfully Noted", Toast.LENGTH_SHORT).show();
                                    Intent restDetail = new Intent(getApplicationContext(), ShopsDetails.class);
                                    restDetail.putExtra("RestId",restId);
                                    startActivity(restDetail);
                                    finish();
                                }
                            });
                            i=1;
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




            }
        });
    }
}