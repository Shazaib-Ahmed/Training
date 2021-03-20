package com.example.sampleproject_1.weightTracker

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import com.example.sampleproject_1.weightTracker.DatabaseWT.EntityWeightTracker
import com.example.sampleproject_1.weightTracker.DatabaseWT.ViewModelWeightTracker

private lateinit var startingWeight: TextView
private lateinit var goalWeight: TextView
private lateinit var currentWeight: TextView


private var startingWeightKey = 0
private var goalWeightKey = 0

private var currentWeightInput = 0
private lateinit var spinnerWeightTracker: Spinner

private lateinit var sharedPreferences: SharedPreferences
private lateinit var viewModelWeightTracker: ViewModelWeightTracker

class HomePageWeightTracker : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_weight_tracker)
        initialisationFields()
        setUpSpinner()

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_input_weight_wt)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)

        viewModelWeightTracker = ViewModelProvider(this).get(ViewModelWeightTracker::class.java)

        startingWeightKey = sharedPreferences.getInt(AppUtils.INITIAL_WEIGHT_KEY_WT.toString(), 0)

        goalWeightKey = sharedPreferences.getInt(AppUtils.FINAL_WEIGHT_KEY_WT.toString(), 0)

        currentWeightInput = sharedPreferences.getInt("CURRENT_WEIGHT_KEY", 0)

        val firstRunKeyWT = sharedPreferences.getBoolean(AppUtils.FIRST_RUN_KEY_WEIGHT_TRACKER, true)

        startingWeight.text = "$startingWeightKey kg"
        goalWeight.text = "$goalWeightKey kg"
        currentWeight.text = "$currentWeightInput kg"

        if (!firstRunKeyWT) {
            currentWeight.text = "$currentWeightInput kg"
        }


        val yesBtn = dialog.findViewById<TextView>(R.id.dialog_done_wt)
        val noBtn = dialog.findViewById<TextView>(R.id.dialog_cancel_wt)
        val currentInputET = dialog.findViewById<EditText>(R.id.weight_current_wt)


        currentWeight.setOnClickListener {
            dialog.show()
            yesBtn.setOnClickListener {
                currentWeightInput = currentInputET!!.text.toString().toInt()

                if (currentWeightInput > startingWeightKey) {
                    Toast.makeText(this, "Enter weight is more than initial weight. Please update weight in settings", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }


                currentWeight.text = "$currentWeightInput kg"

                val editor = sharedPreferences.edit()
                editor.putInt("CURRENT_WEIGHT_KEY", currentWeightInput)
                editor.apply()
                viewModelWeightTracker.insert(entityWeightTracker = EntityWeightTracker(0, startingWeightKey, goalWeightKey, currentWeightInput))
                dialog.dismiss()

            }

            noBtn.setOnClickListener {
                dialog.dismiss()
            }
        }

    }

    private fun initialisationFields() {
        sharedPreferences = this.getSharedPreferences(AppUtils.USERS_SHARED_PREF, AppUtils.PRIVATE_MODE)
        startingWeight = findViewById(R.id.starting_weightTV)
        goalWeight = findViewById(R.id.goal_weightTV)
        spinnerWeightTracker = findViewById(R.id.spinner_wt)
        currentWeight = findViewById(R.id.current_weightTV)
    }

    private fun setUpSpinner() {
        val sortBySpinnerData = resources.getStringArray(R.array.sortByspinner)
        val ad = ArrayAdapter(this.applicationContext, android.R.layout.simple_dropdown_item_1line, sortBySpinnerData)
        spinnerWeightTracker.adapter = ad
    }

}