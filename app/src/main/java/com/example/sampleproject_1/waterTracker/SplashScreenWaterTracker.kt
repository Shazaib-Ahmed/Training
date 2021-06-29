package com.example.sampleproject_1.waterTracker


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import org.koin.android.ext.android.inject
import java.util.*

class SplashScreenWaterTracker : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by inject()

    private lateinit var splashTv: TextView
    private lateinit var bodyIv: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_water_tracker)

        splashTv =findViewById(R.id.splash_tv)
        bodyIv =findViewById(R.id.bodyWeightIv)

        YoYo.with(Techniques. BounceIn)
            .duration(1000)
            .repeat(0)
            .playOn(splashTv)

        YoYo.with(Techniques. BounceIn)
            .duration(1000)
            .repeat(0)
            .playOn(bodyIv)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                if (sharedPreferences.getBoolean(AppUtils.FIRST_RUN_KEY_WATER_TRACKER, true)) {
                    startActivity(Intent(applicationContext, IntroWaterTracker::class.java))
                } else {
                    val intent = Intent(applicationContext, HomeWaterTracker::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                    finishAffinity()
                }
                finish()
            }
        }, 1250)
    }
}