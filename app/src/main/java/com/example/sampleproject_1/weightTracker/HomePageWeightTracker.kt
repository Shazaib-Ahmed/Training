package com.example.sampleproject_1.weightTracker

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils

private lateinit var startingWeight: TextView
private lateinit var goalWeight: TextView

private var startingWeightKey = 0
private var goalWeightKey = 0

private lateinit var spinnerWeightTracker: Spinner

private lateinit var sharedPreferences: SharedPreferences

class HomePageWeightTracker : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_weight_tracker)
        initialisationFields()
        setUpSpinner()

        startingWeightKey = sharedPreferences.getInt(AppUtils.INITIAL_WEIGHT_KEY_WT.toString(), 0)

        goalWeightKey = sharedPreferences.getInt(AppUtils.FINAL_WEIGHT_KEY_WT.toString(), 0)

        startingWeight.text = "$startingWeightKey kg"
        goalWeight.text = "$goalWeightKey kg"

    }

    private fun initialisationFields() {
        sharedPreferences = this.getSharedPreferences(AppUtils.USERS_SHARED_PREF, AppUtils.PRIVATE_MODE)
        startingWeight = findViewById(R.id.starting_weightTV)
        goalWeight = findViewById(R.id.goal_weightTV)
        spinnerWeightTracker = findViewById(R.id.spinner_wt)
    }

    private fun setUpSpinner() {
        val sortBySpinnerData = resources.getStringArray(R.array.sortByspinner)
        val ad = ArrayAdapter(this.applicationContext, android.R.layout.simple_dropdown_item_1line, sortBySpinnerData)
        spinnerWeightTracker!!.adapter = ad
    }

}