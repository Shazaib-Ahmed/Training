package com.example.sampleproject_1.WaterReminder

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Adapter.TabAccessAdapter
import com.example.sampleproject_1.WaterReminder.Dialog.BottomSheetDialog.BottomSheetListener
import com.example.sampleproject_1.WaterReminder.NotificationWaterReminder.TaskWorker
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import com.google.android.material.tabs.TabLayout
import java.util.concurrent.TimeUnit

class HomePage : AppCompatActivity() {
    private var viewPager: ViewPager? = null
    private var tabLayout: TabLayout? = null
    private var tabsAccessAdapter: TabAccessAdapter? = null
    private var actionBar: ActionBar? = null
    private lateinit var sharedPreferences: SharedPreferences
    private var editor: SharedPreferences.Editor? = null
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_water_reminder, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.enableNotification -> {
                Toast.makeText(this, "Notification Scheduled", Toast.LENGTH_SHORT).show()
                scheduleNotificationChannel()
                //item.setVisible(false);
                true
            }
            R.id.disableNotification -> {
                cancelNotification()
                Toast.makeText(this, "Notification Disabled", Toast.LENGTH_SHORT).show()
                //item.setVisible(false);
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        actionBar = supportActionBar
        actionBar!!.title = "Home Page"
        val colorDrawable = ColorDrawable(Color.parseColor("#3F51B5"))
        actionBar!!.setBackgroundDrawable(colorDrawable)
        viewPager = findViewById<View>(R.id.main_tabs_pager) as ViewPager
        tabsAccessAdapter = TabAccessAdapter(supportFragmentManager)
        viewPager!!.adapter = tabsAccessAdapter
        tabLayout = findViewById<View>(R.id.main_tabs) as TabLayout
        tabLayout!!.setupWithViewPager(viewPager)
        sharedPreferences = getSharedPreferences(AppUtils.USERS_SHARED_PREF, AppUtils.PRIVATE_MODE)
        editor = sharedPreferences.edit()
    }


    private fun scheduleNotificationChannel() {
        //val periodicWorkRequest = PeriodicWorkRequest.Builder(TaskWorker::class.java, 15, TimeUnit.MINUTES).build()

        val periodicWorkRequest = PeriodicWorkRequest.Builder(TaskWorker::class.java, 15, TimeUnit.MINUTES)
                .setInitialDelay(15,TimeUnit.MINUTES)
                .build()

        WorkManager.getInstance().enqueue(periodicWorkRequest)

    }

    private fun cancelNotification() {
        WorkManager.getInstance().cancelAllWork()
        
        Toast.makeText(this, "Notification Canceled", Toast.LENGTH_SHORT).show()
    }


}