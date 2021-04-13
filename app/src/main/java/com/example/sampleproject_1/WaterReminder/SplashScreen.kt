package com.example.sampleproject_1.WaterReminder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sampleproject_1.R
import android.content.Intent
import android.content.SharedPreferences
import com.example.sampleproject_1.WaterReminder.IntroductionPage
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import org.koin.android.ext.android.inject
import java.util.*

class SplashScreen : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                if (sharedPreferences.getBoolean(AppUtils.FIRST_RUN_KEY, true)) {
                    startActivity(Intent(applicationContext, IntroductionPage::class.java))
                } else {
                    val intent = Intent(applicationContext, HomePage::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                    finishAffinity()
                }
                finish()
            }
        }, 1000)
    }
}