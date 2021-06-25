package com.example.sampleproject_1.waterTracker

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.NotificationWaterReminder.AutoStartHelper
import com.example.sampleproject_1.WaterReminder.UserDetailsPage
import org.koin.android.ext.android.inject

class IntroWaterTracker : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_water_tracker)

        autoStartNotification()

    }

    private fun autoStartNotification() {
        val autoStart = sharedPreferences.getString("autoStart", "")
        if (autoStart == "") {
            AutoStartHelper.instance.getAutoStartPermission(this)
        }
    }

    fun sendToUserDetailsPageWaterTracker(view: View) {

        val intent = Intent(this, UserDetailsPageWaterTracker::class.java)
        startActivity(intent)

    }

}