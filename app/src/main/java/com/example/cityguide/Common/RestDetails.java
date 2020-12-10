package com.example.cityguide.Common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cityguide.HelperClasses.Home.restaurant;
import com.example.cityguide.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class RestDetails extends AppCompatActivity {

    TextView restName;
    ImageView restImage;
    TextView address;
    TextView description;

    String restId ="";
    FirebaseDatabase database;
    DatabaseReference rest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_details);
        database = FirebaseDatabase.getInstance();
        rest = database.getReference("Restaurant");
        restName = (TextView) findViewById(R.id.restName);
        restImage = (ImageView) findViewById(R.id.imageView);
        address = (TextView) findViewById(R.id.address);
        description = (TextView) findViewById(R.id.description);




        if(getIntent() != null)
        {
            restId = getIntent().getStringExtra("RestId");
        }
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


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}