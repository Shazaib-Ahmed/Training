package com.example.sampleproject_1.weightTracker

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.SharedPreferences
import android.graphics.Color
import android.icu.text.DateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
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
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import org.koin.android.ext.android.inject
import java.util.*
import kotlin.collections.ArrayList

private lateinit var startingWeight: TextView
private lateinit var goalWeight: TextView
private lateinit var currentWeight: TextView

private lateinit var lostWeightTV: TextView
private var lostWeight = 0
private var lostWeightkey = 0

private var startingWeightKey = 0
private var goalWeightKey = 0
private var currentWeightInput = 0
private lateinit var spinnerWeightTracker: Spinner
private lateinit var viewModelWeightTracker: ViewModelWeightTracker
private lateinit var lineChart: LineChart
//private var lineEntries: ArrayList<Entry>? = null

private lateinit var calendar: Calendar


private lateinit var lineEntries: ArrayList<Entry>
private lateinit var labelsNames: ArrayList<String>

class HomePageWeightTracker : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by inject()

    // private val viewModelWeightTracker: ViewModelWeightTracker by inject()

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_weight_tracker)
        initialisationFields()
        setUpSpinner()
        calendar = Calendar.getInstance()


        val currentDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.time)

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_input_weight_wt)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)

        viewModelWeightTracker = ViewModelProvider(this).get(ViewModelWeightTracker::class.java)

        startingWeightKey = sharedPreferences.getInt(AppUtils.INITIAL_WEIGHT_KEY_WT.toString(), 0)

        goalWeightKey = sharedPreferences.getInt(AppUtils.FINAL_WEIGHT_KEY_WT.toString(), 0)

        currentWeightInput = sharedPreferences.getInt("CURRENT_WEIGHT_KEY", 0)

        lostWeightkey = sharedPreferences.getInt("LOST_WEIGHT_KEY", 0)

        val firstRunKeyWT = sharedPreferences.getBoolean(AppUtils.FIRST_RUN_KEY_WEIGHT_TRACKER, true)

        startingWeight.text = "$startingWeightKey kg"
        goalWeight.text = "$goalWeightKey kg"

        currentWeight.text = "$currentWeightInput kg"
        lostWeightTV.text = "-$lostWeightkey kg"

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

                if (currentWeightInput == goalWeightKey) {
                    Toast.makeText(this, "You achieved your goal", Toast.LENGTH_SHORT).show()
                }

                if (currentWeightInput == (goalWeightKey - 1) || currentWeightInput < goalWeightKey) {
                    Toast.makeText(this, "You achieved your goal. Update goal in settings", Toast.LENGTH_SHORT).show()

                }

                if (currentWeightInput > startingWeightKey) {
                    Toast.makeText(this, "Enter weight is more than initial weight. Please update weight in settings", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                currentWeight.text = "$currentWeightInput kg"
                lostWeightkey = startingWeightKey - currentWeightInput

                val editor = sharedPreferences.edit()
                editor.putInt("CURRENT_WEIGHT_KEY", currentWeightInput)
                editor.putInt("LOST_WEIGHT_KEY", lostWeightkey)
                editor.apply()



                viewModelWeightTracker.insert(entityWeightTracker = EntityWeightTracker(0, startingWeightKey, goalWeightKey, currentWeightInput, currentDate))
                lostWeightTV.text = "-$lostWeightkey kg"
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

            lineEntries = ArrayList()
            labelsNames = ArrayList()

            for (i in weightTracker!!.indices) {
                val userCurrentWeight = weightTracker[i].key_current_weight.toFloat()
                val userFinalWeight = weightTracker[i].key_final_weight.toFloat()
                val userInitialWeight = weightTracker[i].key_initial_weight
                val currentDateKey = weightTracker[i].key_current_date

                Log.e("data", "$userFinalWeight  ===DATA===  $userCurrentWeight  ===DATA===  $currentDateKey")

                lineEntries.add(Entry(i.toFloat(), userCurrentWeight))
                labelsNames.add(currentDateKey)

            }

            val lineDataSet = LineDataSet(lineEntries, "WEIGHT")
            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(lineDataSet)


            val data = LineData(dataSets)
            lineChart.data = data
            lineChart.invalidate()

            lineDataSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
            lineDataSet.cubicIntensity = 0.2f
            lineDataSet.lineWidth = 1f
            lineDataSet.color = Color.GRAY
            lineDataSet.fillAlpha = 10
            lineDataSet.setCircleColor(Color.DKGRAY)
            lineDataSet.circleHoleColor = Color.BLACK

            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            xAxis.setDrawAxisLine(false)
            xAxis.granularity = 1f
            xAxis.labelCount = labelsNames!!.size
            xAxis.valueFormatter = IndexAxisValueFormatter(labelsNames)
            xAxis.labelRotationAngle = 270f

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
        lostWeightTV = findViewById(R.id.lost_weight_TV)
    }

    private fun setUpSpinner() {
        val sortBySpinnerData = resources.getStringArray(R.array.sortByspinner)
        val ad = ArrayAdapter(this.applicationContext, android.R.layout.simple_dropdown_item_1line, sortBySpinnerData)
        spinnerWeightTracker.adapter = ad
    }

}