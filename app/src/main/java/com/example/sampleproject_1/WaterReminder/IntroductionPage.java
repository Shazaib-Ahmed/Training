package com.example.sampleproject_1.WaterReminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.sampleproject_1.R;
import com.example.sampleproject_1.WaterReminder.NotificationWaterReminder.AutoStartHelper;
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils;

public class IntroductionPage extends AppCompatActivity {
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction_page);
        sharedPreferences = getSharedPreferences(AppUtils.USERS_SHARED_PREF, AppUtils.PRIVATE_MODE);
        autoStartNotification();

    }

    public void sendToUserDetailsPage(View v) {

       /* Intent intent = new Intent(IntroductionPage.this, UserDetailsPage.class);
        startActivity(intent);*/



        if (sharedPreferences.getBoolean(AppUtils.FIRST_RUN_KEY, true)) {
            startActivity(new Intent(this, UserDetailsPage.class));

        } else {
            Intent intent = new Intent(this,HomePage.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finishAffinity();
        }
        finish();

    }

    private void autoStartNotification() {
        String autoStart = sharedPreferences.getString("autoStart", "");
        if (autoStart.equals("")){
            AutoStartHelper.getInstance().getAutoStartPermission(this);
        }
    }
}