package com.example.sampleproject_1.weightTracker

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.SharedPreferences
import android.graphics.Color
import android.icu.text.UnicodeSet
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import com.example.sampleproject_1.weightTracker.DatabaseWT.EntityWeightTracker
import com.example.sampleproject_1.weightTracker.DatabaseWT.ViewModelWeightTracker
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import org.koin.android.ext.android.inject
import java.util.ArrayList

private lateinit var startingWeight: TextView
private lateinit var goalWeight: TextView
private lateinit var currentWeight: TextView


private var startingWeightKey = 0
private var goalWeightKey = 0
private var currentWeightInput = 0
private lateinit var spinnerWeightTracker: Spinner
private lateinit var viewModelWeightTracker: ViewModelWeightTracker
private lateinit var lineChart: LineChart
private var lineEntries: ArrayList<Entry>? = null

//private lateinit var lineEntries: ArrayList<Entry>
private lateinit var labelsNames: ArrayList<String>

class HomePageWeightTracker : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by inject()

    // private val viewModelWeightTracker: ViewModelWeightTracker by inject()

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

        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        lineChart.isDragEnabled = true
        lineChart.setScaleEnabled(false)
        lineChart.axisRight.isEnabled = false
        lineChart.setBackgroundColor(Color.WHITE)
        lineChart.description.text = "Time"
        lineChart.setVisibleXRangeMaximum(10f)
        lineChart.animateXY(3000, 3000)

        viewModelWeightTracker.getAllUserWT.observe(this, Observer<List<EntityWeightTracker>> { weightTracker ->

            for (i in weightTracker!!.indices) {
                lineEntries = ArrayList()

                val userCurrentWeight = weightTracker[i].key_current_weight.toFloat()
                val userFinalWeight = weightTracker[i].key_final_weight.toFloat()
                val userInitialWeight = weightTracker[i].key_initial_weight
                Log.e("data", "$userFinalWeight  ===DAILY_DATA===  $userCurrentWeight")
                lineEntries!!.add(Entry(i.toFloat(), userCurrentWeight))
            }

            val lineDataSet = LineDataSet(lineEntries, "WEIGHT")
            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(lineDataSet)

            val data = LineData(dataSets)
            lineChart.data = data
            lineChart.invalidate()

            val description = Description()
            description.text = "user_weight"
            description.textSize = 1f
            lineChart.description = description
            lineChart.setPinchZoom(false)


        })
    }

    private fun initialisationFields() {
        startingWeight = findViewById(R.id.starting_weightTV)
        goalWeight = findViewById(R.id.goal_weightTV)
        spinnerWeightTracker = findViewById(R.id.spinner_wt)
        currentWeight = findViewById(R.id.current_weightTV)
        lineChart = findViewById(R.id.MPLineChart)
    }

    private fun setUpSpinner() {
        val sortBySpinnerData = resources.getStringArray(R.array.sortByspinner)
        val ad = ArrayAdapter(this.applicationContext, android.R.layout.simple_dropdown_item_1line, sortBySpinnerData)
        spinnerWeightTracker.adapter = ad
    }

}