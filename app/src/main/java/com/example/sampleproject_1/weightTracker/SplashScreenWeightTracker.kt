package com.example.sampleproject_1.weightTracker

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.HomePage
import com.example.sampleproject_1.WaterReminder.UserDetailsPage
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import java.util.*

private lateinit var sharedPreferences: SharedPreferences

class SplashScreenWeightTracker : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_weight_tracker)
        sharedPreferences = getSharedPreferences(AppUtils.USERS_SHARED_PREF, AppUtils.PRIVATE_MODE)


        Timer().schedule(object : TimerTask() {
            override fun run() {

                if (sharedPreferences.getBoolean(AppUtils.FIRST_RUN_KEY_WEIGHT_TRACKER, true)) {
                    startActivity(Intent(applicationContext, UserDetailsWeightTracker::class.java))
                } else {
                    val intent = Intent(applicationContext, HomePageWeightTracker::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                    finishAffinity()
                }
                finish()
            }
        }, 1000)

    }
}