package com.example.cityguide.Common;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cityguide.R;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIMER = 5000;
    //variables
    ImageView background_image;

    //Animations
    Animation sideAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        //hooks
        background_image = findViewById(R.id.splashscreen);

        //Animations //from the xml file
        sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim);

        //set animations on elements
        background_image.setAnimation(sideAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                                                //from current activity //to dashboard activity
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();//you should see the splashscreen only when u open the application but not when you go back
            }
        },SPLASH_TIMER);//this is basically to change to user dashboard after specified time i.e,5000ms
    }

    public static class RestDetails extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_rest_details);
        }
    }
}