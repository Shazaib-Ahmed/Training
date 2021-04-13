package com.example.sampleproject_1.WaterReminder

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.NotificationWaterReminder.AutoStartHelper
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import org.koin.android.ext.android.inject

class IntroductionPage : AppCompatActivity() {
    //private lateinit var sharedPreferences: SharedPreferences

    private val sharedPreferences: SharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction_page)
        //sharedPreferences = getSharedPreferences(AppUtils.USERS_SHARED_PREF, AppUtils.PRIVATE_MODE)
        autoStartNotification()
    }

/*
    fun sendToUserDetailsPage(v: View?) {

        if (sharedPreferences.getBoolean(AppUtils.FIRST_RUN_KEY, true)) {
            startActivity(Intent(this, UserDetailsPage::class.java))
        } else {
            val intent = Intent(this, HomePage::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finishAffinity()
        }
        finish()
    }
*/

    private fun autoStartNotification() {
        val autoStart = sharedPreferences!!.getString("autoStart", "")
        if (autoStart == "") {
            AutoStartHelper.instance.getAutoStartPermission(this)
        }
    }

    fun sendToUserDetailsPage(view: View) {
        val intent = Intent(this, UserDetailsPage::class.java)
        // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        //finishAffinity()
        //finish()
    }
}