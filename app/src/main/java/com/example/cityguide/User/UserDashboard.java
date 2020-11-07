package com.example.cityguide.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityguide.Common.LoginSinup.OwnerStartupPage;
import com.example.cityguide.HelperClasses.Home.CategoriesAdapter;
import com.example.cityguide.HelperClasses.Home.CategoriesHelperClass;
import com.example.cityguide.HelperClasses.Home.FeaturedAdapter;
import com.example.cityguide.HelperClasses.Home.FeaturedHelperClass;
import com.example.cityguide.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    RecyclerView featuredRecycler;
    RecyclerView categoriesRecycler;
    RecyclerView.Adapter adapter;
    ImageView menuIcon;


    //drawer menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to take out the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_user_dashboard);

        //featured bar
        featuredRecycler = findViewById(R.id.featured_recycler);

        featuredRecycler();

        //categories bar
        categoriesRecycler = findViewById(R.id.categories_recycler);

        categoriesRecycler();

        menuIcon = findViewById(R.id.menu_id);

        //menu bar
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        //Navigation bar
        navigationDrawer();

    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);//since we are in home page,it should be checked priorly


        //to give the functionality to menu icon,when tapped should slide out the nav bar
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else
                    drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    public void onBackPressed(){
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
        super.onBackPressed();
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

    public void callOwnerScreen(View view)
    {
        startActivity(new Intent(getApplicationContext(), OwnerStartupPage.class));
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true; //we are going to handle all the onclick items in navigation bar
    }
}