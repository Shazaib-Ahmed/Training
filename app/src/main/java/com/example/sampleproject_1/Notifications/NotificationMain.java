package com.example.sampleproject_1.Notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sampleproject_1.R;

import java.util.concurrent.TimeUnit;



public class NotificationMain extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_main);
        //UploadTask.context=NotificationMain.this;

                }



    public void scheduleChannel (View v){
        PeriodicWorkRequest periodicWorkRequest =new PeriodicWorkRequest.Builder(UploadTask.class,15, TimeUnit.MINUTES).build();
        WorkManager.getInstance().enqueue(periodicWorkRequest);
    }
    public void cancelNotification(View v){
        WorkManager.getInstance().cancelAllWork();
        Toast.makeText(this, "Notification Canceled", Toast.LENGTH_SHORT).show();
    }


}