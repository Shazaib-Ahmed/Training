package com.example.sampleproject_1.sleepTracker

import android.icu.text.DateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils.currentDate
import com.example.sampleproject_1.sleepTracker.Adapter.TotalSleepAdapter
import com.example.sampleproject_1.sleepTracker.Adapter.TotalSleepAdapter.TotalSleep
import java.util.*
import kotlin.math.abs

class HomeSleepTracker : AppCompatActivity() {

    private lateinit var calendar1: Calendar
    private lateinit var calendar2: Calendar
    private lateinit var calendar3: Calendar

    private lateinit var startTime: Button
    private lateinit var stopTime: Button

    private lateinit var date1: Date
    private lateinit var date2: Date

    private lateinit var  currentDatee:String

    private lateinit var recyclerSleep: RecyclerView
    private lateinit var saveData: ArrayList<TotalSleep>
    private lateinit var adapterSleep: TotalSleepAdapter
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_sleep_tracker)
        recyclerSleep = findViewById(R.id.rec_v_time)
        saveData = ArrayList()
        startTime = findViewById(R.id.startTime)
        stopTime = findViewById(R.id.stopTime)

        calendar3 = Calendar.getInstance()
        currentDatee = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar3.time)

        buildRecyclerView()

        startTime.setOnClickListener {
            startTimeF()
            stopTime.isEnabled = true
            startTime.isEnabled = false
        }

        stopTime.setOnClickListener {
            stopTimeF()
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

        val hourDiff = (mil / (1000 * 60 * 60)).toInt()
        val minDiff = (mil / (1000 * 60) % 60).toInt()
        val secDiff = ((mil / 1000) % 60).toInt()

        val timeT = "$hourDiff : $minDiff : $secDiff"

        adapterSleep.updateData(TotalSleep(timeT,currentDatee))
        adapterSleep.notifyItemInserted(saveData.size)
    }


    private fun buildRecyclerView() {
        recyclerSleep.setHasFixedSize(true)
        recyclerSleep.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapterSleep = TotalSleepAdapter(saveData)
        recyclerSleep.adapter = adapterSleep
    }

}