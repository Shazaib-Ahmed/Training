package com.example.sampleproject_1.weightTracker

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.NotificationWaterReminder.TaskWorker
import com.example.sampleproject_1.WaterReminder.UserDetailsPage
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import com.example.sampleproject_1.weightTracker.DatabaseWT.ViewModelWeightTracker
import com.example.sampleproject_1.weightTracker.NotificationWT.TaskWorkerWT
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

private lateinit var viewModelWeightTracker: ViewModelWeightTracker

class SettingsWeightTracker : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by inject()
    private lateinit var workManager: WorkManager
    private lateinit var periodicWorkRequest: PeriodicWorkRequest


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_weight_tracker)



        

        viewModelWeightTracker = ViewModelProvider(this).get(ViewModelWeightTracker::class.java)
        supportActionBar!!.title = "Settings Weight Tracker"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_two_btn_weight_tracker)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)

        val switchNotification = findViewById<Switch>(R.id.switch_notification_WT)

        val switchWT = sharedPreferences.getBoolean(AppUtils.NOTIFICATION_KEY_WT, false)

        val clearUserDataWT = findViewById<TextView>(R.id.clear_user_data_wt)
        val updateUserDataWT = findViewById<TextView>(R.id.update_user_data_wt)

        val yesBtnWT = dialog.findViewById<TextView>(R.id.yes_TV_WT)
        val noBtnWT = dialog.findViewById<TextView>(R.id.no_TV_WT)

        switchNotification.isChecked = switchWT


        clearUserDataWT.setOnClickListener {
            dialog.show()

            yesBtnWT.setOnClickListener {
                clearAllUserData()
                dialog.dismiss()
            }

            noBtnWT.setOnClickListener {
                dialog.dismiss()
            }
        }
        updateUserDataWT.setOnClickListener { updateUserDataFun() }

        switchNotification.setOnCheckedChangeListener { _, _ ->

            if (switchNotification.isChecked) {
                scheduleNotificationChannelWT()
                val editor = sharedPreferences.edit()
                editor.putBoolean(AppUtils.NOTIFICATION_KEY_WT, true)
                editor.apply()

            } else if (!switchNotification.isChecked) {
                cancelNotificationWT()
                val editor = sharedPreferences.edit()
                editor.putBoolean(AppUtils.NOTIFICATION_KEY_WT, false)
                editor.apply()
            }
        }

    }

    private fun clearAllUserData() {
        sharedPreferences.edit().clear().apply()
        viewModelWeightTracker.deleteAllData()
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finishAffinity()
    }

    private fun updateUserDataFun() {
        val intent = Intent(this, UserDetailsWeightTracker::class.java)
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    private fun scheduleNotificationChannelWT() {

        workManager = WorkManager.getInstance(this)
        periodicWorkRequest = PeriodicWorkRequest.Builder(TaskWorkerWT::class.java, 15, TimeUnit.MINUTES)
                .setInitialDelay(15, TimeUnit.MINUTES)
                .build()

        workManager.enqueue(periodicWorkRequest)
        // WorkManager.getInstance().enqueue(periodicWorkRequest)
        Toast.makeText(this, "WT Notification Enabled", Toast.LENGTH_SHORT).show()

    }

    private fun cancelNotificationWT() {
        // workManager.enqueue(request)
       // workManager.cancelWorkById(periodicWorkRequest.id)
        workManager = WorkManager.getInstance(this)
        workManager.cancelWorkById(periodicWorkRequest.id)
       // workManager.cancelAllWork()
        Toast.makeText(this, "WT Notification Disabled", Toast.LENGTH_SHORT).show()
    }
}