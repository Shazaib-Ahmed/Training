package com.example.sampleproject_1.weightTracker2

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import com.example.sampleproject_1.waterTracker.HomeWaterTracker
import org.koin.android.ext.android.inject

class GoalWeight : AppCompatActivity() {

    private lateinit var  nextBtn: TextView
    private val sharedPreferences: SharedPreferences by inject()
    private val goalWeight = 60
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_weight)

        nextBtn = findViewById(R.id.next_btn_goal_weight_wt2)
        nextBtn.setOnClickListener{
            val intent = Intent(applicationContext, HomeWeightTracker2::class.java)

            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt(AppUtils.GOAL_WEIGHT_KEY_WT2, goalWeight)
            editor.putBoolean(AppUtils.FIRST_RUN_KEY_WEIGHT_TRACKER_2, false)
            editor.apply()
            // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finishAffinity()
        }
    }
}