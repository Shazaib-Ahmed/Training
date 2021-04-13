package com.example.sampleproject_1.sleepTracker

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.icu.text.DateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Settings
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils.currentDate
import com.example.sampleproject_1.WaterReminder.model.WaterIntake
import com.example.sampleproject_1.sleepTracker.Adapter.TotalSleepAdapter
import com.example.sampleproject_1.sleepTracker.Adapter.TotalSleepAdapter.TotalSleep
import com.example.sampleproject_1.weightTracker.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.android.ext.android.inject
import java.util.*
import kotlin.math.abs

class HomeSleepTracker : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by inject()

    private lateinit var calendar1: Calendar
    private lateinit var calendar2: Calendar
    private lateinit var calendar3: Calendar

    private lateinit var startTime: Button
    private lateinit var stopTime: Button

    private lateinit var date1: Date
    private lateinit var date2: Date


    private lateinit var recyclerSleep: RecyclerView
    private var saveData: ArrayList<TotalSleep>? = null
    private lateinit var adapterSleep: TotalSleepAdapter

    private lateinit var ratingSleepTracker: RatingBar

    private var timeT: String = ""
    private lateinit var currentDatee: String
    private var rate: String = ""

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_sleep_tracker, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.clear_data_ST -> {
                sharedPreferences.edit().clear().apply()
                val intent = Intent(this, HomeSleepTracker::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                finishAffinity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_sleep_tracker)

        supportActionBar!!.title = "Sleep Tracker"



        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_sleep_tacker)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)

        val submitRatingBtn = dialog.findViewById<TextView>(R.id.submitRatingBtn)
        ratingSleepTracker = dialog.findViewById(R.id.ratingSleepTracker)

        recyclerSleep = findViewById(R.id.rec_v_time)
        saveData = ArrayList()
        startTime = findViewById(R.id.startTime)
        stopTime = findViewById(R.id.stopTime)

        calendar3 = Calendar.getInstance()
        currentDatee = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar3.time)


        loadDataST()
        buildRecyclerView()


        startTime.setOnClickListener {
            startTimeF()
            stopTime.isEnabled = true
            startTime.isEnabled = false
        }

        stopTime.setOnClickListener {
            dialog.show()
            submitRatingBtn.setOnClickListener {
                rate = ratingSleepTracker.rating.toString()
                Toast.makeText(this, "$rate Star", Toast.LENGTH_SHORT).show()
                stopTimeF()
                dialog.dismiss()
            }
            stopTime.isEnabled = false
            startTime.isEnabled = true
        }

    }

    private fun startTimeF() {
        calendar1 = Calendar.getInstance()
        date1 = calendar1.time
    }

    private fun stopTimeF() {
        calendar2 = Calendar.getInstance()
        date2 = calendar2.time
        val mils: Long = date2.time - date1.time
        val mil: Long = abs(mils)

       //for hour
        val hourDiff = (mil / (1000 * 60 * 60)).toInt()

        //for minute
        val minDiff = (mil / (1000 * 60) % 60).toInt()

        //for second
        val secDiff = ((mil / 1000) % 60).toInt()

        timeT = "$hourDiff : $minDiff : $secDiff"

        adapterSleep.updateData(TotalSleep(timeT, currentDatee, rate))
        saveDataST()
        adapterSleep.notifyItemInserted(saveData!!.size)
    }


    private fun buildRecyclerView() {
        recyclerSleep.setHasFixedSize(true)
        recyclerSleep.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapterSleep = TotalSleepAdapter(saveData!!)
        recyclerSleep.adapter = adapterSleep
    }

    private fun saveDataST() {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val jsonData = gson.toJson(saveData)
        editor.putString("myJsonST", jsonData)
        editor.apply()
    }
    private fun loadDataST() {
        val gson = Gson()
        val jsonData = sharedPreferences.getString("myJsonST", null)
        val type = object : TypeToken<ArrayList<TotalSleep?>?>() {}.type
        saveData = gson.fromJson<ArrayList<TotalSleep>>(jsonData, type)
        if (saveData == null) {
            saveData = ArrayList()
        }
    }

}