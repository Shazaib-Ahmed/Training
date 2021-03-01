package com.example.sampleproject_1.WaterReminder

import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
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

class UserDetailsPage : AppCompatActivity() {

    private lateinit var sleepingTime: String
    private lateinit var wakeUptime: String
    private var weight = 0
    //private lateinit var sharedPreferences: SharedPreferences

    private val sharedPreferences: SharedPreferences by inject()
    private val viewModelWaterReminder: ViewModelWaterReminder by inject()

    private val gender: String? = null
    private lateinit var continueTextView: TextView
    var genderEditText: EditText? = null
    var weightEditText: EditText? = null
    private lateinit var timePickerBedSelectTimeTV: TextView
    private lateinit var timePickerWakeSelectTimeTV: TextView

    // private val entityWaterReminders: List<EntityWaterReminder> = ArrayList()
    var timePickerBedTV: TextView? = null
    var timePickerWakeTV: TextView? = null
    var actionBar: ActionBar? = null
    //private lateinit var viewModelWaterReminder: ViewModelWaterReminder
    private var getMaleOption: RadioButton? = null
    private var getFemaleOption: RadioButton? = null
    private var hourPick = 0
    private var minutePick = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details_page)

        initialisationFields()

        actionBar = supportActionBar
        actionBar!!.title = "Home Page"
        val colorDrawable = ColorDrawable(Color.parseColor("#3F51B5"))
        actionBar!!.setBackgroundDrawable(colorDrawable)

        //viewModelWaterReminder = ViewModelProvider(this).get(ViewModelWaterReminder::class.java)

        timePickerBedSelectTimeTV!!.setOnClickListener {
            val timePickerDialog = TimePickerDialog(this@UserDetailsPage,
                    android.R.style.Theme_Holo_Dialog_MinWidth,
                    OnTimeSetListener { view, hourOfDay, minute ->
                        hourPick = hourOfDay
                        minutePick = minute
                        sleepingTime = "$hourPick:$minutePick"
                        val f24hours = SimpleDateFormat("HH:mm")
                        try {
                            val date = f24hours.parse(sleepingTime)
                            val f12hours = SimpleDateFormat("hh:mm aa")
                            timePickerBedTV!!.text = f12hours.format(date)
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

        timePickerWakeSelectTimeTV!!.setOnClickListener {
            val timePickerDialog = TimePickerDialog(this@UserDetailsPage,
                    android.R.style.Theme_Holo_Dialog_MinWidth,
                    OnTimeSetListener { view, hourOfDay, minute ->
                        hourPick = hourOfDay
                        minutePick = minute
                        wakeUptime = "$hourPick:$minutePick"
                        val f24hours = SimpleDateFormat("HH:mm")
                        try {
                            val date = f24hours.parse(wakeUptime)
                            val f12hours = SimpleDateFormat("hh:mm aa")
                            timePickerWakeTV!!.text = f12hours.format(date)
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
        continueTextView!!.setOnClickListener { saveUserInfo() }
    }

    private fun saveUserInfo() {
        if (genderEditText!!.text.toString().isEmpty() || weightEditText!!.text.toString().isEmpty() || timePickerWakeTV!!.text.toString().isEmpty() || timePickerBedTV!!.text.toString().isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }
        val timeBed = timePickerBedTV!!.text.toString()
        val timeWake = timePickerWakeTV!!.text.toString()
        weight = weightEditText!!.text.toString().toInt()

        if (weight > 200 || weight < 20) {
            Toast.makeText(this, "Please enter valid weight", Toast.LENGTH_SHORT).show()
            return
        }

        val data = Intent(this@UserDetailsPage, HomePage::class.java)
        //sharedPreferences = getSharedPreferences(AppUtils.USERS_SHARED_PREF, AppUtils.PRIVATE_MODE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(AppUtils.FIRST_RUN_KEY, false)
        editor.putString(AppUtils.WAKE_UP_TIME_KEY, timeWake)
        editor.putString(AppUtils.SLEEPING_TIME_KEY, timeBed)
        editor.putInt(AppUtils.WEIGHT_KEY, weight)
        editor.putString(AppUtils.GENDER_KEY, gender)
        editor.putInt(AppUtils.WORK_TIME_KEY, workTime)
        val totalIntake = AppUtils.calculateIntake(weight, workTime)
        val df = DecimalFormat("#")
        editor.putInt(AppUtils.TOTAL_INTAKE, df.format(totalIntake.toLong()).toInt())
        editor.apply()
        startActivity(data)
        finishAffinity()
    }

    private fun initialisationFields() {
        getMaleOption = findViewById(R.id.maleOption)
        getFemaleOption = findViewById(R.id.femaleOption)
        getMaleOption = findViewById(R.id.maleOption)
        getFemaleOption = findViewById(R.id.femaleOption)
        continueTextView = findViewById(R.id.bottomContinueButtonUserDetailsPage)
        weightEditText = findViewById(R.id.weightEditText)
        genderEditText = findViewById(R.id.genderEditText)
        timePickerBedSelectTimeTV = findViewById(R.id.timePickerBedSelectTimeTV)
        timePickerWakeSelectTimeTV = findViewById(R.id.timePickerWakeSelectTimeTV)
        timePickerBedTV = findViewById(R.id.timePickerBedTV)
        timePickerWakeTV = findViewById(R.id.timePickerWakeTV)
    }

    companion object {
        private const val workTime = 180
    }
}