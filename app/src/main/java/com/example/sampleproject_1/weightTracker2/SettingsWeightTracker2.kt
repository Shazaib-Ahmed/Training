package com.example.sampleproject_1.weightTracker2

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import com.example.sampleproject_1.weightTracker2.databaseWt2.ViewModelWeightTracker2
import com.example.sampleproject_1.weightTracker2.notificationWT2.TaskWorkerWT2
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

class SettingsWeightTracker2 : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by inject()
    private val viewModelWT2: ViewModelWeightTracker2 by inject()
    private lateinit var workManagerWT2: WorkManager
    private lateinit var periodicWorkRequestWT2: PeriodicWorkRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_weight_tracker2)

        periodicWorkRequestWT2 = PeriodicWorkRequest.Builder(TaskWorkerWT2::class.java, 15, TimeUnit.MINUTES)
                .setInitialDelay(15, TimeUnit.MINUTES).addTag("weightTracker2Notification")
                .build()

        val dialog = Dialog(this)

        dialog.setContentView(R.layout.dialog_two_buttons_wt2)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(true)


        val clearUserData = findViewById<TextView>(R.id.clear_user_data_wt2)
        val updateUserData = findViewById<TextView>(R.id.update_user_data_wt2)
        val switchNotification = findViewById<Switch>(R.id.switch_notification_WT2)
        val yesBtn = dialog.findViewById<TextView>(R.id.yes_TV_wt2)
        val noBtn = dialog.findViewById<TextView>(R.id.no_TV_wt2)

        val switchWT2 = sharedPreferences.getBoolean(AppUtils.NOTIFICATION_KEY_WT2, false)

        switchNotification.isChecked = switchWT2

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
                editor.putBoolean(AppUtils.NOTIFICATION_KEY_WT2, true)
                editor.apply()

            } else if (!switchNotification.isChecked) {
                cancelNotification()
                val editor = sharedPreferences.edit()
                editor.putBoolean(AppUtils.NOTIFICATION_KEY_WT2, false)
                editor.apply()
            }
        }

    }

    private fun clearAllUserData() {
        sharedPreferences.edit().clear().apply()
        viewModelWT2.deleteAllData()
        val intent = Intent(this, IntroSliderWeightTracker2::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finishAffinity()
    }

    private fun updateUserDataFun() {
        val intent = Intent(this, EnterWeight::class.java)
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    private fun scheduleNotificationChannel() {
        workManagerWT2 = WorkManager.getInstance(this)
        workManagerWT2.enqueue(periodicWorkRequestWT2)
        Toast.makeText(this, "WT2 Notification Enabled", Toast.LENGTH_SHORT).show()
    }

    private fun cancelNotification() {
        workManagerWT2 = WorkManager.getInstance(this)
        workManagerWT2.cancelAllWorkByTag("weightTracker2Notification")
        Toast.makeText(this, "WT2 Notification Disabled", Toast.LENGTH_SHORT).show()
    }

}