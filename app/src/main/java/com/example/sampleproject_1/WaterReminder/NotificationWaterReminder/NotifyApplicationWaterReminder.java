package com.example.sampleproject_1.WaterReminder.NotificationWaterReminder;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class NotifyApplicationWaterReminder extends Application {

    public static final String CHANNEL__ID= "channel_WR";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel =new NotificationChannel(
                    CHANNEL__ID,
                    "Channel Water Reminder",
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationChannel.setDescription("This is water reminder application channel");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }

    }
}
