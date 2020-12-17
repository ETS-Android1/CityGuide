package com.example.cityguide.Common;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityguide.BangalorePalace_feat;
import com.example.cityguide.Common.LoginSinup.HotelsListView;
import com.example.cityguide.Common.LoginSinup.OwnerStartupPage;
import com.example.cityguide.Common.LoginSinup.ShopsListView;
import com.example.cityguide.Common.LoginSinup.TheatersListView;
import com.example.cityguide.HelperClasses.Home.CategoriesAdapter;
import com.example.cityguide.HelperClasses.Home.CategoriesHelperClass;
import com.example.cityguide.HelperClasses.Home.FeaturedAdapter;
import com.example.cityguide.HelperClasses.Home.FeaturedHelperClass;
import com.example.cityguide.Lalbagh_feat;
import com.example.cityguide.R;
import com.example.cityguide.User.UserProfile;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    RecyclerView featuredRecycler;
    RecyclerView categoriesRecycler;
    RecyclerView.Adapter cadapter;
    FeaturedAdapter adapter;
    ImageView menuIcon;


    //drawer menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ClipData.Item logout;


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
        {

        }
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

        cadapter = new CategoriesAdapter(categories);
        categoriesRecycler.setAdapter(cadapter);

    }

    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        //for the horizontal sliding
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        final ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(R.drawable.wonderla,4,"Wonderla","Wonderla is one of the largest amusement parks in the entire country with more than 60 exhilarating rides." ));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.bangalore_palace,4,"Bangalore Palace","Bangalore Palace is a true example of sheer architectural beauty." ));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.lalbagh,3,"Lalbagh Garden ","Lalbagh is one of the oldest botanical garden in south india." ));

        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new FeaturedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //Toast.makeText(UserDashboard.this, position, Toast.LENGTH_SHORT).show();
                if(position == 0)
                {
                    //Toast.makeText(UserDashboard.this, "wonderla", Toast.LENGTH_SHORT).show();
                    Intent wonderla = new Intent(UserDashboard.this, Wonderla_feat.class);
                    startActivity(wonderla);
                }
                if(position == 1)
                {
                    //Toast.makeText(UserDashboard.this, "wonderla", Toast.LENGTH_SHORT).show();
                    Intent bangalorePalace = new Intent(UserDashboard.this, BangalorePalace_feat.class);
                    startActivity(bangalorePalace);
                }
                if(position == 2)
                {
                    //Toast.makeText(UserDashboard.this, "wonderla", Toast.LENGTH_SHORT).show();
                    Intent lalbagh = new Intent(UserDashboard.this, Lalbagh_feat.class);
                    startActivity(lalbagh);
                }

            }
        });

    }

    public void callOwnerScreen(View view)
    {
        startActivity(new Intent(getApplicationContext(), OwnerStartupPage.class));
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //we are going to handle all the onclick items in navigation bar
        switch (item.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_restaurants:
                Intent intent1 = new Intent(UserDashboard.this, RestaurantListView.class);
                startActivity(intent1);
                break;
            case R.id.nav_profile:
                Intent intent = getIntent();
//                String user_username = intent.getStringExtra("username");
//                String user_name = intent.getStringExtra("name");
//                String user_email = intent.getStringExtra("email");
//                String user_phoneNo = intent.getStringExtra("phoneNo");
                //String user_password = intent.getStringExtra("password");
                Intent intent2 = new Intent(UserDashboard.this, UserProfile.class);
//                intent2.putExtra("name", user_username);
//                intent2.putExtra("username", user_name);
//                intent2.putExtra("email", user_email);
//                intent2.putExtra("phoneNo", user_phoneNo);
                startActivity(intent2);
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(UserDashboard.this, LoginActivity.class));
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    public void callRestListView(View view)
    {

        startActivity(new Intent(UserDashboard.this,RestaurantListView.class));

    }
    public void callHotelListView(View view)
    {

        startActivity(new Intent(UserDashboard.this, HotelsListView.class));

    }
    public void callShopsListView(View view)
    {

        startActivity(new Intent(UserDashboard.this, ShopsListView.class));

    }
    public void callTheatersListView(View view)
    {

        startActivity(new Intent(UserDashboard.this, TheatersListView.class));

    }
}