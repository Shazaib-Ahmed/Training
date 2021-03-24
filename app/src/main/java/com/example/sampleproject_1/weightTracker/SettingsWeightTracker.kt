package com.example.sampleproject_1.weightTracker

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.NotificationWaterReminder.TaskWorker
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import com.example.sampleproject_1.weightTracker.NotificationWT.TaskWorkerWT
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

class SettingsWeightTracker : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by inject()

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_weight_tracker)


        supportActionBar!!.title = "Settings Weight Tracker"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val switchNotification = findViewById<Switch>(R.id.switch_notification_WT)

        val switchWT = sharedPreferences.getBoolean(AppUtils.NOTIFICATION_KEY_WT, false)

        switchNotification.isChecked = switchWT

        switchNotification.setOnCheckedChangeListener { _, _ ->

            if (switchNotification.isChecked) {
                scheduleNotificationChannel()
                val editor = sharedPreferences.edit()
                editor.putBoolean(AppUtils.NOTIFICATION_KEY_WT, true)
                editor.apply()

            } else if (!switchNotification.isChecked) {
                cancelNotification()
                val editor = sharedPreferences.edit()
                editor.putBoolean(AppUtils.NOTIFICATION_KEY_WT, false)
                editor.apply()
            }
        }

    }

    private fun scheduleNotificationChannel() {

        val periodicWorkRequest = PeriodicWorkRequest.Builder(TaskWorkerWT::class.java, 15, TimeUnit.MINUTES)
                .setInitialDelay(15, TimeUnit.MINUTES)
                .build()

        WorkManager.getInstance().enqueue(periodicWorkRequest)
        Toast.makeText(this, "WT Notification Enabled", Toast.LENGTH_SHORT).show()

    }

    private fun cancelNotification() {
        WorkManager.getInstance().cancelAllWork()
        Toast.makeText(this, "WT Notification Disabled", Toast.LENGTH_SHORT).show()
    }
}