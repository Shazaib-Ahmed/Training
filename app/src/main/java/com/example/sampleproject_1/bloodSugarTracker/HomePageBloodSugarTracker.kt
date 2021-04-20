package com.example.sampleproject_1.bloodSugarTracker

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleproject_1.R
import com.example.sampleproject_1.bloodSugarTracker.fragment.FragmentHome

class HomePageBloodSugarTracker : AppCompatActivity() {

    //private val settingsBtn: ImageView = findViewById(R.id.settings_btn_bst)

    private lateinit var settingsBtn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_blood_sugar_tracker)

        settingsBtn = findViewById(R.id.settings_btn_bst)

        val fragmentHome = FragmentHome()
        supportFragmentManager
                .beginTransaction().replace(R.id.blood_frame_layout, fragmentHome)
                .commit()

        settingsBtn.setOnClickListener {
            val data = Intent(this, BloodSugarTrackerSettings::class.java)
            startActivity(data)
        }
    }
}