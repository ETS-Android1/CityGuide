package com.example.cityguide.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.cityguide.HelperClasses.Home.CategoriesAdapter;
import com.example.cityguide.HelperClasses.Home.CategoriesHelperClass;
import com.example.cityguide.HelperClasses.Home.FeaturedAdapter;
import com.example.cityguide.HelperClasses.Home.FeaturedHelperClass;
import com.example.cityguide.R;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity {

    RecyclerView featuredRecycler;
    RecyclerView categoriesRecycler;
    RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to take out the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_user_dashboard);

        featuredRecycler = findViewById(R.id.featured_recycler);

        featuredRecycler();

        categoriesRecycler = findViewById(R.id.categories_recycler);

        categoriesRecycler();


    }

    private void categoriesRecycler() {

        categoriesRecycler.setHasFixedSize(true);
        //for the horizontal sliding
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<CategoriesHelperClass> categories = new ArrayList<>();

        categories.add(new CategoriesHelperClass(R.drawable.restaurant_image,"Restaurant"));
        categories.add(new CategoriesHelperClass(R.drawable.restaurant_image,"Hotels"));
        categories.add(new CategoriesHelperClass(R.drawable.restaurant_image,"Theaters"));
        categories.add(new CategoriesHelperClass(R.drawable.restaurant_image,"Shops"));

        adapter = new CategoriesAdapter(categories);
        categoriesRecycler.setAdapter(adapter);

    }

    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        //for the horizontal sliding
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(R.drawable.wonderla,4,"Wonderla","Wonderla is one of the largest amusement parks in the entire country with more than 60 exhilarating rides." ));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.bangalore_palace,4,"Bangalore Palace","Bangalore Palace is a true example of sheer architectural beauty." ));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.brigade_road,3,"Brigade Road ","Brigade road is considered a shopping paradise for the city dwellers." ));

        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);

    }
}