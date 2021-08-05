package com.example.sampleproject_1.weightTracker2.notificationWT2

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.NotificationWaterReminder.NotifyApplicationWaterReminder
import com.example.sampleproject_1.weightTracker2.HomeWeightTracker2

class TaskWorkerWT2 (var context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {


        val intentWT2 = Intent(context, HomeWeightTracker2::class.java)
        val pendingIntentWT2 = PendingIntent.getActivity(context, 3, intentWT2, PendingIntent.FLAG_UPDATE_CURRENT)
        val notificationWT2 = NotificationCompat.Builder(context, NotifyApplicationWaterReminder.CHANNEL__ID_WT)
                .setSmallIcon(R.drawable.ic_notification_wt2)
                .setContentText("Enter your weight")
                .setContentTitle("REMINDER")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntentWT2)
        val managerWT2 = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        managerWT2.notify(2, notificationWT2.build())

        return Result.success()
    }
}