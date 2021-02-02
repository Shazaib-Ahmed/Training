package com.example.sampleproject_1.WaterReminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sampleproject_1.R;

public class IntroductionPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction_page);
    }
    public void sendToUserDetailsPage(View v){
        Intent intent =new Intent(IntroductionPage.this,UserDetailsPage.class);
        startActivity(intent);
    }
}