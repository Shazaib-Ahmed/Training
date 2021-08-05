package com.example.sampleproject_1.WaterReminder.NotificationWaterReminder

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.example.sampleproject_1.WaterReminder.appModule
import com.example.sampleproject_1.WaterReminder.viewModelModule
import com.example.sampleproject_1.WaterReminder.viewModelModuleWT
import com.example.sampleproject_1.WaterReminder.viewModelModuleWT2
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NotifyApplicationWaterReminder : Application() {
    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()

        startKoin {
            androidContext(this@NotifyApplicationWaterReminder)
            modules(listOf(appModule, viewModelModule, viewModelModuleWT, viewModelModuleWT2))
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

            val notificationChannelWT2 = NotificationChannel(
                    CHANNEL__ID_WT2,
                    "Channel Weight Tracker 2",
                    NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannelWT2.description = "This is weight tracker 2 application channel"
            val managerWT2 = getSystemService(NotificationManager::class.java)
            managerWT2.createNotificationChannel(notificationChannelWT2)

        }
    }


    companion object {
        const val CHANNEL__ID = "channel_WR"
        const val CHANNEL__ID_WT = "channel_WT"
        const val CHANNEL__ID_WT2 = "channel_WT2"

    }
}
