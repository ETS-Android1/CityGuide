package com.example.cityguide.Common.LoginSinup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cityguide.Common.HotelsAddReview;
import com.example.cityguide.Common.RestDetails;
import com.example.cityguide.Common.RestaurantListView;
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

public class ShopsDetails extends AppCompatActivity {

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
        setContentView(R.layout.activity_shops_details);
        database = FirebaseDatabase.getInstance();
        rest = database.getReference("Shops");
        restName = (TextView) findViewById(R.id.restName2);
        restImage = (ImageView) findViewById(R.id.imageView2);
        address = (TextView) findViewById(R.id.address2);
        description = (TextView) findViewById(R.id.description2);
        ratings = (TextView) findViewById(R.id.rating2);
        setCount = (TextView) findViewById(R.id.setCount2);
        addReview = (Button) findViewById(R.id.addReview2);

        if(getIntent() != null)
        {
            restId = getIntent().getStringExtra("RestId");
        }
        addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), ShopsAddReview.class);
                intent.putExtra("RestId",restId);
                startActivity(intent);
            }
        });
        if(!restId.isEmpty())
        {
            getDetailRest(restId);
        }


    }

    public void onBackPressed()
    {
        Intent intent = new Intent(ShopsDetails.this, ShopsListView.class);
        startActivity(intent);
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