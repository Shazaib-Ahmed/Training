package com.example.sampleproject_1.bloodSugarTracker

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import com.example.sampleproject_1.weightTracker.HomePageWeightTracker
import com.example.sampleproject_1.weightTracker.UserDetailsWeightTracker
import org.koin.android.ext.android.inject
import java.util.*

class SplashScreenBloodSugarTracker : AppCompatActivity() {
    private val sharedPreferences: SharedPreferences by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_blood_sugar_tracker)

        Timer().schedule(object : TimerTask() {
            override fun run() {

              /*  if (sharedPreferences.getBoolean(AppUtils.FIRST_RUN_KEY_WEIGHT_TRACKER, true)) {
                    startActivity(Intent(applicationContext, UserDetailsWeightTracker::class.java))
                } else {
                    val intent = Intent(applicationContext, HomePageWeightTracker::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                    finishAffinity()
                }
                finish()*/

                val intent = Intent(applicationContext, HomePageBloodSugarTracker::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                finishAffinity()
            }
        }, 1000)

    }
}