package com.example.cityguide.Common.LoginSinup;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityguide.Common.HotelsDetails;
import com.example.cityguide.Common.RestDetails;
import com.example.cityguide.Common.RestaurantListView;
import com.example.cityguide.Common.UserDashboard;
import com.example.cityguide.HelperClasses.Home.ItemClickListener;
import com.example.cityguide.HelperClasses.Home.RestaurantViewHolder;
import com.example.cityguide.HelperClasses.Home.restaurant;
import com.example.cityguide.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class HotelsListView extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference restaurants;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels_list_view);

        database = FirebaseDatabase.getInstance();
        restaurants = database.getReference("Hotels");

        //Load Restaurants
        // Using Firebase UI to The Recycler View
        recyclerView = (RecyclerView) findViewById(R.id.hotelListView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        loadView();

    }

    public void onBackPressed()
    {
        Intent intent = new Intent(HotelsListView.this, UserDashboard.class);
        startActivity(intent);
    }

    private void loadView() {

        FirebaseRecyclerAdapter<restaurant, RestaurantViewHolder> adapter = new FirebaseRecyclerAdapter<restaurant, RestaurantViewHolder>(
                restaurant.class,R.layout.rowrestaurant,
                RestaurantViewHolder.class,restaurants
        ) {
            @Override
            protected void populateViewHolder(RestaurantViewHolder restaurantViewHolder, restaurant model, int position) {
                restaurantViewHolder.restName.setText(model.getRestName());
                Picasso.get().load(model.getImage()).into(restaurantViewHolder.rest_image);
                final restaurant clickItem = model;
                restaurantViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {


                        // Start New Activity
                        Intent restDetail = new Intent(getApplicationContext(), HotelsDetails.class);
                        restDetail.putExtra("RestId", getRef(position).getKey());
                        startActivity(restDetail);
                    }
                });

            }
        } ;
        recyclerView.setAdapter(adapter);
    }
}