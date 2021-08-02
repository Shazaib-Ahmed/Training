package com.example.sampleproject_1.WaterReminder

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Database.ViewModelWaterReminder
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import org.koin.android.ext.android.inject
import java.text.DecimalFormat
import java.util.*

class UserDetailsPage : AppCompatActivity() {

    private lateinit var sleepingTime: String
    private lateinit var wakeUptime: String
    private var weight = 0

    private val sharedPreferences: SharedPreferences by inject()
    private val viewModelWaterReminder: ViewModelWaterReminder by inject()

    private var radioM = true
    private var radioF = true


    private var weightKey = 0
    private var sleepingKey: String? = null
    private var wakeKey: String? = null

    private lateinit var maleOption: RadioButton
    private lateinit var femaleOption: RadioButton

    private var maleOptionISChecked = false
    private var femaleOptionISChecked = false

    private lateinit var continueTextView: TextView
    var weightEditText: EditText? = null

    private var hourPick = 0
    private var minutePick = 0

    private var genderValue = 0

    private lateinit var calender: Calendar

    private var sleepHourSelect = 0
    private var sleepMinuteSelect = 0
    private var wakeHourSelect = 0
    private var wakeMinuteSelect = 0

    private var sleepHourUpdate = 0
    private var sleepMinuteUpdate = 0
    private var wakeHourUpdate = 0
    private var wakeMinuteUpdate = 0

    private var firstRunKey = true


    private lateinit var selectedTime: String

 /*   private lateinit var sleepHourPicker: WheelPicker
    private lateinit var sleepMinutePicker: WheelPicker
    private lateinit var wakeHourPicker: WheelPicker
    private lateinit var wakeMinutePicker: WheelPicker*/


    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details_page)

        genderValue = sharedPreferences.getInt("LastPosition", 0)
        firstRunKey = sharedPreferences.getBoolean(AppUtils.FIRST_RUN_KEY, true)


        initialisationFields()

        supportActionBar!!.title = "User Details"

        /*sleepHourPicker.setOnItemSelectedListener { picker, data, position ->
            sleepHourSelect = position
            val editor = sharedPreferences.edit()
            editor.putInt("SLEEP_HOUR_KEY", sleepHourSelect)
            editor.apply()

        }

        sleepMinutePicker.setOnItemSelectedListener { picker, data, position ->
            sleepMinuteSelect = position
            val editor = sharedPreferences.edit()
            editor.putInt("SLEEP_MINUTE_KEY", sleepMinuteSelect)
            editor.apply()

        }

        wakeHourPicker.setOnItemSelectedListener { picker, data, position ->
            wakeHourSelect = position
            val editor = sharedPreferences.edit()
            editor.putInt("WAKE_HOUR_KEY", wakeHourSelect)
            editor.apply()

        }

        wakeMinutePicker.setOnItemSelectedListener { picker, data, position ->
            wakeMinuteSelect = position
            val editor = sharedPreferences.edit()
            editor.putInt("WAKE_MINUTE_KEY", wakeMinuteSelect)
            editor.apply()
            Toast.makeText(this, "Selected : $wakeMinuteSelect", Toast.LENGTH_SHORT).show()

        }
*/
        if (firstRunKey) {
            maleOption.isChecked = true

            if (maleOption.isChecked) {
                //saveRadioData("KG_CHECKED", true)
                maleOptionISChecked = true

            } else if (femaleOption.isChecked) {
                //saveRadioData("LB_CHECKED", true)
                femaleOptionISChecked = true
            }

            femaleOption.setOnCheckedChangeListener { _, femaleIsChecked ->

                saveData("FEMALE_CHECKED", femaleIsChecked)
                maleOptionISChecked = femaleIsChecked

            }

            maleOption.setOnCheckedChangeListener { _, maleIsChecked ->

                saveData("MALE_CHECKED", maleIsChecked)
                maleOptionISChecked = maleIsChecked

            }

        }

        if (!firstRunKey) {

            maleOption.setOnCheckedChangeListener { _, maleIsChecked ->
                maleOptionISChecked = maleIsChecked
            }

            femaleOption.setOnCheckedChangeListener { _, femaleIsChecked ->
                femaleOptionISChecked = femaleIsChecked
            }
        }


        /*     maleOption.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, maleIsChecked ->

                 saveData("MALE_CHECKED", maleIsChecked)
             }
             )

             femaleOption.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, femaleIsChecked ->

                 saveData("FEMALE_CHECKED", femaleIsChecked)
             }
             )*/


        continueTextView.setOnClickListener { saveUserInfo() }
        updateDetails()

    }

    private fun saveUserInfo() {


        if (weightEditText!!.text.toString()
                .isEmpty() || !maleOption.isChecked && !femaleOption.isChecked
        ) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }
        /* val timeBed = timePickerBedSelectTimeTV.text.toString()
         val timeWake = timePickerWakeSelectTimeTV.text.toString()*/
        weight = weightEditText!!.text.toString().toInt()


        if (weight > 200 || weight < 20) {
            Toast.makeText(this, "Please enter valid weight", Toast.LENGTH_SHORT).show()
            return
        }


        saveData("MALE_CHECKED", maleOptionISChecked)
        saveData("FEMALE_CHECKED", femaleOptionISChecked)


        val data = Intent(this@UserDetailsPage, HomePage::class.java)
        val editor = sharedPreferences.edit()
        editor.putBoolean(AppUtils.FIRST_RUN_KEY, false)
        /*editor.putString(AppUtils.WAKE_UP_TIME_KEY, timeWake)
        editor.putString(AppUtils.SLEEPING_TIME_KEY, timeBed)*/
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


        maleOption = findViewById(R.id.male_option)
        femaleOption = findViewById(R.id.female_option)

        /*sleepHourPicker = findViewById(R.id.sleepHourPicker)
        sleepMinutePicker = findViewById(R.id.sleepMinutePicker)
        wakeHourPicker = findViewById(R.id.wakeHourPicker)
        wakeMinutePicker = findViewById(R.id.wakeMinutePicker)*/

    }

    companion object {
        private const val workTime = 180
    }

    private fun loaData() {
        radioM = updateRadio("MALE_CHECKED")
        radioF = updateRadio("FEMALE_CHECKED")

        weightKey = sharedPreferences.getInt(AppUtils.WEIGHT_KEY, 0);
        sleepingKey = sharedPreferences.getString(AppUtils.SLEEPING_TIME_KEY, "")
        wakeKey = sharedPreferences.getString(AppUtils.WAKE_UP_TIME_KEY, "")

        sleepHourUpdate = sharedPreferences.getInt("SLEEP_HOUR_KEY", 0)

        sleepMinuteUpdate = sharedPreferences.getInt("SLEEP_MINUTE_KEY", 0)

        wakeHourUpdate = sharedPreferences.getInt("WAKE_HOUR_KEY", 0)

        wakeMinuteUpdate = sharedPreferences.getInt("WAKE_MINUTE_KEY", 0)
    }

    private fun updateView() {
        maleOption.isChecked = radioM
        femaleOption.isChecked = radioF
        weightEditText?.setText("$weightKey")
        /*sleepHourPicker.selectedItemPosition = sleepHourUpdate
        sleepMinutePicker.selectedItemPosition = sleepMinuteUpdate
        wakeMinutePicker.selectedItemPosition = wakeHourUpdate
        wakeHourPicker.selectedItemPosition = wakeMinuteUpdate*/

    }

    private fun updateDetails() {
        val firstRunKey = sharedPreferences.getBoolean(AppUtils.FIRST_RUN_KEY, true)

        if (!firstRunKey) {

            loaData()
            updateView()

            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun saveData(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    private fun updateRadio(key: String):
            Boolean {
        return sharedPreferences.getBoolean(key, false)
    }


    /* override fun onItemSelected(picker: WheelPicker?, data: Any?, position: Int) {


         if (picker != null) {
             when (picker.id) {
                 R.id.sleepHourPicker -> {
                     val editor = sharedPreferences.edit()
                     editor.putInt("SLEEP_HOUR_KEY", position)
                     editor.apply()
                     Toast.makeText(this,"  + $data",Toast.LENGTH_SHORT).show()


                     *//*val sleepTimePickerHourData = resources.getStringArray(R.array.hour_select)
                                       val adapter = ArrayAdapter(this.applicationContext, android.R.layout.simple_dropdown_item_1line, sleepTimePickerHourData)*//*

                }
                R.id.sleepMinutePicker -> {
                    val editor = sharedPreferences.edit()
                    editor.putInt("SLEEP_MINUTE_KEY", sleepMinuteSelect)
                    editor.apply()

                }
                R.id.wakeHourPicker -> {

                    val editor = sharedPreferences.edit()
                    editor.putInt("WAKE_HOUR_KEY", position)
                    editor.apply()

                }
                R.id.wakeMinutePicker -> {
                    val editor = sharedPreferences.edit()
                    editor.putInt("WAKE_MINUTE_KEY", position)
                    editor.apply()
                }
            }
        }

    }*/

    private fun updatePickerDetails() {
        /*val firstRunKey = sharedPreferences.getBoolean(AppUtils.FIRST_RUN_KEY, true)

        if (!firstRunKey) {*/

        /*sleepHourPicker.selectedItemPosition = sleepHourUpdate
        sleepMinutePicker.selectedItemPosition = sleepMinuteUpdate
        wakeMinutePicker.selectedItemPosition = wakeHourUpdate
        wakeHourPicker.selectedItemPosition = wakeMinuteUpdate*/

        Log.e("MY === VALUE", " =========  $sleepHourUpdate")
        Log.e("MY === VALUE", " =========  $sleepMinuteUpdate")
        Log.e("MY === VALUE", " =========  $wakeHourUpdate")
        Log.e("MY === VALUE", " =========  $wakeMinuteUpdate")

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        // }

    }

}



