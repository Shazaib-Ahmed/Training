package com.example.sampleproject_1.WaterReminder

import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Database.EntityWaterReminder
import com.example.sampleproject_1.WaterReminder.Database.ViewModelWaterReminder
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import org.koin.android.ext.android.inject
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class UserDetailsPage : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var sleepingTime: String
    private lateinit var wakeUptime: String
    private var weight = 0

    private lateinit var genderSpinner: Spinner

    private val sharedPreferences: SharedPreferences by inject()
    private val viewModelWaterReminder: ViewModelWaterReminder by inject()

    private var genderKey: String? = null
    private var text: String? = null
    private var weightKey = 0
    private var sleepingKey: String? = null
    private var wakeKey: String? = null

    private var gender: String? = null
    private lateinit var continueTextView: TextView
    var weightEditText: EditText? = null
    private lateinit var timePickerBedSelectTimeTV: TextView
    private lateinit var timePickerWakeSelectTimeTV: TextView

    private var hourPick = 0
    private var minutePick = 0
    private var genderValue = 0

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details_page)

        genderSpinner = findViewById(R.id.genderSpinner)
        setUpSpinner()

        genderValue = sharedPreferences.getInt("LastPosition", 0)

        initialisationFields()

        supportActionBar!!.title = "User Details"

        genderSpinner.onItemSelectedListener = this

        timePickerBedSelectTimeTV.setOnClickListener {
            val timePickerDialog = TimePickerDialog(this@UserDetailsPage,
                    android.R.style.Theme_Holo_Dialog_MinWidth,
                    OnTimeSetListener { _, hourOfDay, minute ->
                        hourPick = hourOfDay
                        minutePick = minute
                        sleepingTime = "$hourPick:$minutePick"
                        val f24hours = SimpleDateFormat("HH:mm")
                        try {
                            val date = f24hours.parse(sleepingTime)
                            val f12hours = SimpleDateFormat("hh:mm aa")
                            timePickerBedSelectTimeTV.text = f12hours.format(date)
                        } catch (e: ParseException) {
                            e.printStackTrace()
                        }
                    }, 12, 0, false
            )
            timePickerDialog.setTitle("Select Sleeping Time")
            timePickerDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            timePickerDialog.updateTime(hourPick, minutePick)
            timePickerDialog.show()
        }

        timePickerWakeSelectTimeTV.setOnClickListener {
            val timePickerDialog = TimePickerDialog(this@UserDetailsPage,
                    android.R.style.Theme_Holo_Dialog_MinWidth,
                    OnTimeSetListener { _, hourOfDay, minute ->
                        hourPick = hourOfDay
                        minutePick = minute
                        wakeUptime = "$hourPick:$minutePick"
                        val f24hours = SimpleDateFormat("HH:mm")
                        try {
                            val date = f24hours.parse(wakeUptime)
                            val f12hours = SimpleDateFormat("hh:mm aa")
                            timePickerWakeSelectTimeTV.text = f12hours.format(date)
                        } catch (e: ParseException) {
                            e.printStackTrace()
                        }
                    }, 12, 0, false
            )
            timePickerDialog.setTitle("Select Wake Up Time")
            timePickerDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            timePickerDialog.updateTime(hourPick, minutePick)
            timePickerDialog.show()
        }

        continueTextView.setOnClickListener { saveUserInfo() }
        updateDetails()

    }

    private fun saveUserInfo() {
        if (weightEditText!!.text.toString().isEmpty() || timePickerWakeSelectTimeTV!!.text.toString().isEmpty() || timePickerBedSelectTimeTV!!.text.toString().isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }
        val timeBed = timePickerBedSelectTimeTV.text.toString()
        val timeWake = timePickerWakeSelectTimeTV.text.toString()
        weight = weightEditText!!.text.toString().toInt()


        if (weight > 200 || weight < 20) {
            Toast.makeText(this, "Please enter valid weight", Toast.LENGTH_SHORT).show()
            return
        }

        val data = Intent(this@UserDetailsPage, HomePage::class.java)
        val editor = sharedPreferences.edit()
        editor.putBoolean(AppUtils.FIRST_RUN_KEY, false)
        editor.putString(AppUtils.WAKE_UP_TIME_KEY, timeWake)
        editor.putString(AppUtils.SLEEPING_TIME_KEY, timeBed)
        editor.putInt(AppUtils.WEIGHT_KEY, weight)
        editor.putInt(AppUtils.WORK_TIME_KEY, workTime)
        val totalIntake = AppUtils.calculateIntake(weight, workTime)
        val df = DecimalFormat("#")
        editor.putInt(AppUtils.TOTAL_INTAKE, df.format(totalIntake.toLong()).toInt())
        editor.apply()
        startActivity(data)
        finishAffinity()
    }

    private fun initialisationFields() {

        continueTextView = findViewById(R.id.bottomContinueButtonUserDetailsPage)
        weightEditText = findViewById(R.id.weightEditText)
        timePickerBedSelectTimeTV = findViewById(R.id.timePickerBedSelectTimeTV)
        timePickerWakeSelectTimeTV = findViewById(R.id.timePickerWakeSelectTimeTV)

    }

    companion object {
        private const val workTime = 180
    }

    private fun loaData() {
        genderKey = sharedPreferences.getString(AppUtils.GENDER_KEY, "");
        weightKey = sharedPreferences.getInt(AppUtils.WEIGHT_KEY, 0);
        sleepingKey = sharedPreferences.getString(AppUtils.SLEEPING_TIME_KEY, "");
        wakeKey = sharedPreferences.getString(AppUtils.WAKE_UP_TIME_KEY, "");
    }

    private fun updateView() {
        genderSpinner.setSelection(genderValue)
        weightEditText?.setText("$weightKey")
        timePickerBedSelectTimeTV.text = sleepingKey
        timePickerWakeSelectTimeTV.text = wakeKey

    }

    private fun updateDetails() {
        val firstRunKey = sharedPreferences.getBoolean(AppUtils.FIRST_RUN_KEY, true)

        if (!firstRunKey) {

            loaData()
            updateView()

            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setUpSpinner() {
        val genderSpinnerData = resources.getStringArray(R.array.genderSpinnerList)
        val ad = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, genderSpinnerData)
        genderSpinner!!.adapter = ad
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        text = parent?.getItemAtPosition(position).toString()
        val editor = sharedPreferences.edit()
        editor.putString(AppUtils.GENDER_KEY, text)
        editor.putInt("LastPosition", position)
        editor.apply()
    }
}