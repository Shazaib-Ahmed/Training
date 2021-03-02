package com.example.sampleproject_1.WaterReminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.sampleproject_1.R;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}