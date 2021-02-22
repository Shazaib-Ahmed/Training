package com.example.sampleproject_1.WaterReminder.NotificationWaterReminder

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.HomePage

class TaskWorker(var context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
       // Log.e("scheduleTask", "WORKING")
        val intent = Intent(context, HomePage::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val notification = NotificationCompat.Builder(context, NotifyApplicationWaterReminder.CHANNEL__ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentText("Drink Water")
                .setContentTitle("REMINDER")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(1, notification.build())
        return Result.success()
    }

}