package com.example.newsappyc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();
        Toast.makeText(this, "Welcome Plase Wait...", Toast.LENGTH_SHORT).show();

        //Method 2
        //Parrel Threading //asysrnous Way
//
        Intent intent = new Intent(SplashScreen.this, MainActivity.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //  Intent intent=new Intent(SplashActivity.this,MainActivity.class);//for go from one activity to other
                startActivity(intent);
                finish();
            }
        },2000);

    }
}