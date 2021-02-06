package com.example.sampleproject_1.WaterReminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sampleproject_1.R;

public class IntroductionPage extends AppCompatActivity {
    Boolean FIRST_RUN_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction_page);
    }
    public void sendToUserDetailsPage(View v){


        Intent intent =new Intent(IntroductionPage.this,UserDetailsPage.class);
        startActivity(intent);

     /*   if (FIRST_RUN_KEY= true)
        {
            Intent intent =new Intent(IntroductionPage.this,UserDetailsPage.class);
            startActivity(intent);
        }
        else {
            Intent intent =new Intent(IntroductionPage.this,HomePage.class);
            startActivity(intent);
        }*/

    }
}