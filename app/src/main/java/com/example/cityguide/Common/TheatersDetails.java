package com.example.cityguide.Common;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cityguide.Common.LoginSinup.TheatersAddReview;
import com.example.cityguide.HelperClasses.Home.restaurant;
import com.example.cityguide.R;
import com.example.cityguide.addReview;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class TheatersDetails extends AppCompatActivity {

    TextView restName;
    ImageView restImage;
    TextView address;
    TextView description;
    TextView ratings;
    TextView setCount;
    Button addReview;

    String restId ="";
    FirebaseDatabase database;
    DatabaseReference rest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theaters_details);
        database = FirebaseDatabase.getInstance();
        rest = database.getReference("Theaters");
        restName = (TextView) findViewById(R.id.restName3);
        restImage = (ImageView) findViewById(R.id.imageView3);
        address = (TextView) findViewById(R.id.address3);
        description = (TextView) findViewById(R.id.description3);
        ratings = (TextView) findViewById(R.id.rating3);
        setCount = (TextView) findViewById(R.id.setCount3);
        addReview = (Button) findViewById(R.id.addReview3);

        if(getIntent() != null)
        {
            restId = getIntent().getStringExtra("RestId");
        }
        addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), TheatersAddReview.class);
                intent.putExtra("RestId",restId);
                startActivity(intent);
            }
        });
        if(!restId.isEmpty())
        {
            getDetailRest(restId);
        }











    }
    private void getDetailRest(final String restId)
    {
        rest.child(restId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                restaurant restaurant = dataSnapshot.getValue(com.example.cityguide.HelperClasses.Home.restaurant.class);
                Picasso.get().load(restaurant.getImage()).into(restImage);
                restName.setText(restaurant.getRestName());

                address.setText(restaurant.getAddress());
                description.setText(restaurant.getDescription());
                ratings.setText(restaurant.getStarsTillNow());
                setCount.setText(restaurant.getCustomercount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}