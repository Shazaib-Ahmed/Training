package com.example.sampleproject_1.WaterReminder.NotificationWaterReminder

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class NotifyApplicationWaterReminder : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                    CHANNEL__ID,
                    "Channel Water Reminder",
                    NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.description = "This is water reminder application channel"
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(notificationChannel)
        }
    }

    companion object {
        const val CHANNEL__ID = "channel_WR"
    }
}