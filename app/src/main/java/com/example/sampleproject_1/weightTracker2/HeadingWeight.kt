package com.example.sampleproject_1.weightTracker2

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import org.koin.android.ext.android.inject

class HeadingWeight : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by inject()

    private var currentWeight = 0

    private var goalWeight = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heading_weight)

        currentWeight = sharedPreferences.getInt(AppUtils.CURRENT_WEIGHT_KEY_WT2,0)
        goalWeight = sharedPreferences.getInt(AppUtils.GOAL_WEIGHT_KEY_WT2,0)

    }
}