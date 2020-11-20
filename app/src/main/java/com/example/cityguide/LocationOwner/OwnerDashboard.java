package com.example.cityguide.LocationOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cityguide.R;
import com.example.cityguide.User.OwnerAddNewActivity;

public class OwnerDashboard extends AppCompatActivity {

    private ImageView restaurants,hotels,theaters,shops;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_dashboard);

        restaurants = (ImageView) findViewById(R.id.restaurants);
        hotels = (ImageView) findViewById(R.id.hotels);
        theaters = (ImageView) findViewById(R.id.theaters);
        shops = (ImageView) findViewById(R.id.shops);

        restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerDashboard.this, OwnerAddNewActivity.class);
                intent.putExtra("category","restaurants");
                startActivity(intent);
            }
        });

        hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerDashboard.this, OwnerAddNewActivity.class);
                intent.putExtra("category","hotels");
                startActivity(intent);
            }
        });

        theaters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerDashboard.this, OwnerAddNewActivity.class);
                intent.putExtra("category","theaters");
                startActivity(intent);
            }
        });

        shops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerDashboard.this, OwnerAddNewActivity.class);
                intent.putExtra("category","shops");
                startActivity(intent);
            }
        });
    }
}