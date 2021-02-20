package com.example.sampleproject_1.WaterReminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.sampleproject_1.R;
import com.example.sampleproject_1.WaterReminder.Adapter.TabAccessAdapter;
import com.example.sampleproject_1.WaterReminder.Dialog.BottomSheetDialog;
import com.example.sampleproject_1.WaterReminder.NotificationWaterReminder.TaskWorker;
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils;
import com.google.android.material.tabs.TabLayout;

import java.util.concurrent.TimeUnit;

public class HomePage extends AppCompatActivity implements BottomSheetDialog.BottomSheetListener {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabAccessAdapter tabsAccessAdapter;
    private ActionBar actionBar;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_water_reminder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {

            case R.id.enableNotification:
                    Toast.makeText(this, "Notification Scheduled", Toast.LENGTH_SHORT).show();
                    scheduleNotificationChannel();


                return true;

            case R.id.disableNotificationn:
                    cancelNotification();
                    Toast.makeText(this, "Notification Disabled", Toast.LENGTH_SHORT).show();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        actionBar = getSupportActionBar();
        actionBar.setTitle("Home Page");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#3F51B5"));
        actionBar.setBackgroundDrawable(colorDrawable);

        viewPager = (ViewPager) findViewById(R.id.main_tabs_pager);
        tabsAccessAdapter = new TabAccessAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabsAccessAdapter);

        tabLayout = (TabLayout) findViewById(R.id.main_tabs);
        tabLayout.setupWithViewPager(viewPager);

        sharedPreferences = this.getSharedPreferences(AppUtils.USERS_SHARED_PREF, AppUtils.PRIVATE_MODE);
        editor = sharedPreferences.edit();


    }

    @Override
    public void onButtonClicked(String text) {

    }

    private void scheduleNotificationChannel() {
        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(TaskWorker.class, 15, TimeUnit.MINUTES).build();
        WorkManager.getInstance().enqueue(periodicWorkRequest);
    }

    private void cancelNotification() {
        WorkManager.getInstance().cancelAllWork();
        Toast.makeText(this, "Notification Canceled", Toast.LENGTH_SHORT).show();
    }

}