package com.example.sampleproject_1.weightTracker.NotificationWT

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.NotificationWaterReminder.NotifyApplicationWaterReminder
import com.example.sampleproject_1.weightTracker.HomePageWeightTracker

class TaskWorkerWT (var context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {


        val intentWT = Intent(context, HomePageWeightTracker::class.java)
        val pendingIntentWT = PendingIntent.getActivity(context, 3, intentWT, PendingIntent.FLAG_UPDATE_CURRENT)
        val notificationWT = NotificationCompat.Builder(context, NotifyApplicationWaterReminder.CHANNEL__ID_WT)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentText("Enter your weight")
                .setContentTitle("REMINDER")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntentWT)
        val managerWT = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        managerWT.notify(2, notificationWT.build())

        return Result.success()
    }
}