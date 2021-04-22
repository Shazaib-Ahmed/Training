package com.example.sampleproject_1.bloodSugarTracker

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleproject_1.R
import com.example.sampleproject_1.bloodSugarTracker.fragment.FragmentAverage
import com.example.sampleproject_1.bloodSugarTracker.fragment.FragmentHome
import com.example.sampleproject_1.bloodSugarTracker.fragment.FragmentStatistics

class HomePageBloodSugarTracker : AppCompatActivity() {



    private lateinit var settingsBtn: ImageView
    private lateinit var notificationBtn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_blood_sugar_tracker)

        settingsBtn = findViewById(R.id.settings_btn_bst)
        notificationBtn = findViewById(R.id.notification_btn_bst)

        val fragmentHome = FragmentHome()
        supportFragmentManager
                .beginTransaction().replace(R.id.blood_frame_layout, fragmentHome)
                .commit()



        settingsBtn.setOnClickListener {
            val data = Intent(this, BloodSugarTrackerSettings::class.java)
            startActivity(data)
        }

        notificationBtn.setOnClickListener {
            updateFragmentForComments()
        }

    }


    private fun updateFragmentForComments() {
       /* val fragmentReminderAlert = FragmentReminderAlert()
        supportFragmentManager
                .beginTransaction().replace(R.id.blood_frame_layout, fragmentReminderAlert)
                .commit()*/
    }

}