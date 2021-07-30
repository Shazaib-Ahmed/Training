package com.example.sampleproject_1.weightTracker2

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

class SplashScreenWeightTracker2 : AppCompatActivity() {
    private val sharedPreferences: SharedPreferences by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_weight_tracker2)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                val intent = Intent(applicationContext, IntroSliderWeightTracker2::class.java)
                startActivity(intent)
               /* if (sharedPreferences.getBoolean(AppUtils.FIRST_RUN_KEY_WEIGHT_TRACKER_2, true)) {
                    startActivity(Intent(applicationContext, IntroSliderWeightTracker2::class.java))
                } else {
                    val intent = Intent(applicationContext, HomePageWeightTracker2::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                    finishAffinity()
                }*/
                finish()
            }
        }, 1000)

    }
}