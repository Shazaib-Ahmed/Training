package com.example.sampleproject_1.sleepTracker

import android.app.Dialog
import android.content.SharedPreferences
import android.icu.text.DateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils.currentDate
import com.example.sampleproject_1.sleepTracker.Adapter.TotalSleepAdapter
import com.example.sampleproject_1.sleepTracker.Adapter.TotalSleepAdapter.TotalSleep
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

    private lateinit var currentDatee: String

    private lateinit var recyclerSleep: RecyclerView
    private lateinit var saveData: ArrayList<TotalSleep>
    private lateinit var adapterSleep: TotalSleepAdapter

    private lateinit var ratingSleepTracker: RatingBar

    private var rate: String = ""

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

        val hourDiff = (mil / (1000 * 60 * 60)).toInt()
        val minDiff = (mil / (1000 * 60) % 60).toInt()
        val secDiff = ((mil / 1000) % 60).toInt()

        val timeT = "$hourDiff : $minDiff : $secDiff"

        adapterSleep.updateData(TotalSleep(timeT, currentDatee, rate))
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