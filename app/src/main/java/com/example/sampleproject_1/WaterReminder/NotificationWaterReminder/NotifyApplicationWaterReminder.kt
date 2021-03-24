package com.example.sampleproject_1.WaterReminder.NotificationWaterReminder

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.example.sampleproject_1.WaterReminder.appModule
import com.example.sampleproject_1.WaterReminder.viewModelModule
import com.example.sampleproject_1.WaterReminder.viewModelModuleWT
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NotifyApplicationWaterReminder : Application() {
    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()

        startKoin {
            androidContext(this@NotifyApplicationWaterReminder)
            modules(listOf(appModule, viewModelModule, viewModelModuleWT))
        }


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


            val notificationChannelWT = NotificationChannel(
                    CHANNEL__ID_WT,
                    "Channel Weight Tracker",
                    NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannelWT.description = "This is weight tracker application channel"
            val managerWT = getSystemService(NotificationManager::class.java)
            managerWT.createNotificationChannel(notificationChannelWT)

        }
    }


    companion object {
        const val CHANNEL__ID = "channel_WR"
        const val CHANNEL__ID_WT = "channel_WT"

    }
}
