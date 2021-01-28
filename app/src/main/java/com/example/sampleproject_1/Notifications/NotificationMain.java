package com.example.sampleproject_1.Notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.sampleproject_1.R;

import static com.example.sampleproject_1.Notifications.NotificationApplication.CHANNEL_ID;

public class NotificationMain extends AppCompatActivity {
    NotificationManagerCompat managerCompat;
    EditText editTextTitle,editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_main);

        managerCompat =NotificationManagerCompat.from(this);
        editTextTitle=findViewById(R.id.editText_title);
        editTextMessage=findViewById(R.id.editText_message);

    }

    public void sendOnChannel (View v){
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        managerCompat.notify(1,notification);
    }

}