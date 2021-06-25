package com.example.cityguide.Common;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cityguide.Common.LoginSinup.HotelsListView;
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

public class HotelsDetails extends AppCompatActivity {

    TextView restName;
    ImageView restImage;
    TextView address;
    TextView description;
    TextView ratings;
    TextView setCount;
    Button addReview;
    Button nav_btn;
    String restId ="";
    FirebaseDatabase database;
    DatabaseReference rest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels_details);
        database = FirebaseDatabase.getInstance();
        rest = database.getReference("Hotels");
        restName = (TextView) findViewById(R.id.restName1);
        restImage = (ImageView) findViewById(R.id.imageView1);
        address = (TextView) findViewById(R.id.address1);
        description = (TextView) findViewById(R.id.description1);
        ratings = (TextView) findViewById(R.id.rating1);
        setCount = (TextView) findViewById(R.id.setCount1);
        addReview = (Button) findViewById(R.id.addReview1);
        nav_btn = (Button) findViewById(R.id.navigationButtonhotels);
        if(getIntent() != null)
        {
            restId = getIntent().getStringExtra("RestId");
        }
        addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(HotelsDetails.this, HotelsAddReview.class);
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
        Intent intent = new Intent(HotelsDetails.this, HotelsListView.class);
        startActivity(intent);
    }

    private void getDetailRest(final String restId)
    {
        rest.child(restId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final restaurant restaurant = dataSnapshot.getValue(com.example.cityguide.HelperClasses.Home.restaurant.class);
                Picasso.get().load(restaurant.getImage()).into(restImage);
                restName.setText(restaurant.getRestName());

                address.setText(restaurant.getAddress());
                nav_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("google.navigation:q=" + Uri.encode(restaurant.getRestName() )));
                        intent.setPackage("com.google.android.apps.maps");
                        startActivity(intent);
                    }
                });
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