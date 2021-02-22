package com.example.sampleproject_1.WaterReminder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sampleproject_1.R
import android.content.Intent
import com.example.sampleproject_1.WaterReminder.IntroductionPage
import java.util.*

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                startActivity(Intent(applicationContext, IntroductionPage::class.java))
                finishAffinity()
            }
        }, 1000)
    }
}