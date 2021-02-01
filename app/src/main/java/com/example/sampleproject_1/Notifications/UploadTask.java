package com.example.sampleproject_1.Notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.sampleproject_1.R;

public class UploadTask extends Worker {
   public Context context;
    public UploadTask(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context=context;
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.e("scheduleTask","WORKING");
        Intent intent =new Intent(context,NotificationMain.class);
       // PendingIntent pendingIntent =PendingIntent.getActivity(context,2,intent,0);
        PendingIntent pendingIntent =PendingIntent.getActivity(context,2,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context,NotificationApplication.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentText("Message")
                .setContentTitle("title")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);
        NotificationManager manager =(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1,notification.build());
        return Result.success();
    }
}
