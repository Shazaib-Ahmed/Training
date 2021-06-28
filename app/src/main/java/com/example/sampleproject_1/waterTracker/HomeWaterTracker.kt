package com.example.sampleproject_1.waterTracker

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import org.koin.android.ext.android.inject

class HomeWaterTracker : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by inject()

    private lateinit var settingsButton: ImageView

    private val counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_water_tracker)

        settingsButton = findViewById(R.id.settings_btn_water_tracker)


        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsWaterTracker::class.java)
            startActivity(intent)
        }


    }
}