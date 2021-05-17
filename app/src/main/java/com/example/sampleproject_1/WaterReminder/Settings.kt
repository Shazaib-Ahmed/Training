package com.example.sampleproject_1.WaterReminder

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Database.ViewModelWaterReminder
import com.example.sampleproject_1.WaterReminder.NotificationWaterReminder.TaskWorker
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

class Settings : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by inject()
    private val viewModelWaterReminder: ViewModelWaterReminder by inject()
    private lateinit var workManager: WorkManager
    private lateinit var periodicWorkRequestWR: PeriodicWorkRequest


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportActionBar!!.title = "Settings"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        periodicWorkRequestWR = PeriodicWorkRequest.Builder(TaskWorker::class.java, 15, TimeUnit.MINUTES)
                .setInitialDelay(15, TimeUnit.MINUTES).addTag("waterReminderNotification")
                .build()

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_two_buttons)
        //dialog.window?.setBackgroundDrawable(getDrawable(R.drawable.dialog_border))
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)

        val clearUserData = findViewById<TextView>(R.id.clear_user_data)
        val updateUserData = findViewById<TextView>(R.id.update_user_data)
        val switchNotification = findViewById<Switch>(R.id.switch_notification)
        val yesBtn = dialog.findViewById<TextView>(R.id.yes_TV)
        val noBtn = dialog.findViewById<TextView>(R.id.no_TV)

        val switchWR = sharedPreferences.getBoolean(AppUtils.NOTIFICATION_KEY, false)

        switchNotification.isChecked = switchWR

        clearUserData.setOnClickListener {
            dialog.show()

            yesBtn.setOnClickListener {
                clearAllUserData()
                dialog.dismiss()
            }

            noBtn.setOnClickListener {
                dialog.dismiss()
            }
        }

        updateUserData.setOnClickListener { updateUserDataFun() }

        switchNotification.setOnCheckedChangeListener { _, _ ->

            if (switchNotification.isChecked) {
                scheduleNotificationChannel()
                val editor = sharedPreferences.edit()
                editor.putBoolean(AppUtils.NOTIFICATION_KEY, true)
                editor.apply()

            } else if (!switchNotification.isChecked) {
                cancelNotification()
                val editor = sharedPreferences.edit()
                editor.putBoolean(AppUtils.NOTIFICATION_KEY, false)
                editor.apply()
            }
        }

    }

    private fun clearAllUserData() {
        sharedPreferences.edit().clear().apply()
        viewModelWaterReminder.deleteAllData()
        val intent = Intent(this, UserDetailsPage::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finishAffinity()
    }

    private fun updateUserDataFun() {
        val intent = Intent(this, UserDetailsPage::class.java)
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    private fun scheduleNotificationChannel() {

        workManager = WorkManager.getInstance(this)

        workManager.enqueue(periodicWorkRequestWR)
        // WorkManager.getInstance().enqueue(periodicWorkRequest)
        Toast.makeText(this, "WR Notification Enabled", Toast.LENGTH_SHORT).show()

    }

    private fun cancelNotification() {
        workManager = WorkManager.getInstance(this)
        workManager.cancelAllWorkByTag("waterReminderNotification")
        Toast.makeText(this, "WR Notification Disabled", Toast.LENGTH_SHORT).show()

    }


}