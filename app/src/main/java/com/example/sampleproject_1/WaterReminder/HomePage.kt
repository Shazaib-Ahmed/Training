package com.example.sampleproject_1.WaterReminder

import android.content.Intent
import android.content.SharedPreferences
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
            R.id.settings -> {
                val data = Intent(this, Settings::class.java)
                startActivity(data)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        supportActionBar!!.title = "Home Page"
        viewPager = findViewById<View>(R.id.main_tabs_pager) as ViewPager
        tabsAccessAdapter = TabAccessAdapter(supportFragmentManager)
        viewPager!!.adapter = tabsAccessAdapter
        tabLayout = findViewById<View>(R.id.main_tabs) as TabLayout
        tabLayout!!.setupWithViewPager(viewPager)
        sharedPreferences = getSharedPreferences(AppUtils.USERS_SHARED_PREF, AppUtils.PRIVATE_MODE)
        editor = sharedPreferences.edit()
    }


    private fun scheduleNotificationChannel() {

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