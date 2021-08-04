package com.example.sampleproject_1.weightTracker2


import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.icu.text.DateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils

import com.example.sampleproject_1.weightTracker.calendar

import com.example.sampleproject_1.weightTracker2.adapter.QuotesAdapter
import com.example.sampleproject_1.weightTracker2.databaseWt2.EntityWeightTracker2
import com.example.sampleproject_1.weightTracker2.databaseWt2.ViewModelWeightTracker2
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.marvel999.acr.ArcProgress
import me.bastanfar.semicirclearcprogressbar.SemiCircleArcProgressBar
import org.koin.android.ext.android.inject
import java.util.*

class HomeWeightTracker2 : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by inject()
    private lateinit var viewModelWeightTracker2: ViewModelWeightTracker2

    private val mNames: ArrayList<String> = ArrayList()
    private val mImageUrls: ArrayList<String> = ArrayList()

    private lateinit var spinnerWeightTracker2: Spinner
    private lateinit var editWeightBtn: RelativeLayout
    private lateinit var updateWeightBtn: TextView

    private lateinit var enterWeightTv: TextView
    private lateinit var droppedtv: TextView
    private lateinit var goalWeightTv: TextView
    private lateinit var currentWeightTv: TextView
    private lateinit var droppedWeightTv: TextView

    private lateinit var progressBar: ArcProgress

    private lateinit var lineChartWT2: LineChart
    private lateinit var calendar: Calendar


    private lateinit var lineEntriesWT2: ArrayList<Entry>
    private lateinit var labelsNamesWT2: ArrayList<String>

    private lateinit var dialog: Dialog
    private lateinit var weightInputEditText: EditText


    private var enterWeight = 0
    private var goalWeight = 0
    private var currentWeight = 0
    private var droppedWeight = 0


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_weight_tracker2)

        initialisingFields()
        viewModelWeightTracker2 = ViewModelProvider(this).get(ViewModelWeightTracker2::class.java)
        images

        calendar = Calendar.getInstance()
        dialog = Dialog(this)
        openDialog()
        weightInputEditText = dialog.findViewById(R.id.input_weight_et)

        setUpSpinner()

        val currentDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.time)

        val closeBtn = dialog.findViewById<ImageView>(R.id.dialog_cancel_btn)
        val saveBtn = dialog.findViewById<TextView>(R.id.save_btn_wt2)


        enterWeight = sharedPreferences.getInt(AppUtils.ENTER_WEIGHT_KEY_WT2, 0)
        goalWeight = sharedPreferences.getInt(AppUtils.GOAL_WEIGHT_KEY_WT2, 0)
        currentWeight = sharedPreferences.getInt(AppUtils.CURRENT_WEIGHT_KEY_WT2, 0)
        droppedWeight = sharedPreferences.getInt(AppUtils.DROPPED_WEIGHT_KEY_WT2, 0)



        if (enterWeight < goalWeight) {
            droppedtv.text = "Gain : "
        }


        enterWeightTv.text = "$enterWeight"
        goalWeightTv.text = "$goalWeight"
        currentWeightTv.text = "$currentWeight kg"
        droppedWeightTv.text = "$droppedWeight kg"


        editWeightBtn.setOnClickListener {
            val intent = Intent(applicationContext, EnterWeight::class.java)
            startActivity(intent)
        }

        updateWeightBtn.setOnClickListener {
            dialog.show()
            weightInputEditText.setText("$currentWeight")
            weightInputEditText.requestFocus()
            weightInputEditText.setSelectAllOnFocus(true)
            weightInputEditText.selectAll()
            dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
            dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

            saveBtn.setOnClickListener {

                if (weightInputEditText.text.toString().isEmpty()) {

                    Toast.makeText(this, "Please enter weight", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                } else if (weightInputEditText.text.toString().toFloat() > 200 || weightInputEditText.text.toString().toFloat() < 20) {
                    Toast.makeText(this, "Please enter valid weight", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val weight = weightInputEditText.text.toString().toFloat()

                currentWeight = weight.toInt()



                currentWeightTv.text = "$currentWeight kg"
                droppedWeight = enterWeight - currentWeight
                droppedWeightTv.text = "$droppedWeight kg"

                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putInt(AppUtils.CURRENT_WEIGHT_KEY_WT2, currentWeight)
                editor.putInt(AppUtils.DROPPED_WEIGHT_KEY_WT2, droppedWeight)
                editor.apply()

                viewModelWeightTracker2.insert(entityWeightTracker2 = EntityWeightTracker2(0, enterWeight, goalWeight, currentWeight, currentDate))


                dialog.dismiss()
            }



            closeBtn.setOnClickListener {
                weightInputEditText.clearFocus()
                weightInputEditText.requestFocus()
                dialog.dismiss()
            }
        }


        val xAxis = lineChartWT2.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        lineChartWT2.isDragEnabled = true
        lineChartWT2.setScaleEnabled(false)
        lineChartWT2.axisRight.isEnabled = false
        lineChartWT2.setBackgroundColor(Color.WHITE)
        lineChartWT2.description.text = "Time"
        lineChartWT2.setVisibleXRangeMaximum(10f)
        lineChartWT2.animateXY(3000, 3000)

        viewModelWeightTracker2.getAllUserWT2.observe(this, androidx.lifecycle.Observer { weightTracker2 ->
            lineEntriesWT2 = ArrayList()
            labelsNamesWT2 = ArrayList()


            for (i in weightTracker2!!.indices) {
                val userCurrentWeight = weightTracker2[i].key_current_weight.toFloat()
                val userFinalWeight = weightTracker2[i].key_final_weight.toFloat()
                val userInitialWeight = weightTracker2[i].key_initial_weight
                val currentDateKey = weightTracker2[i].key_current_date

                Log.e("data", "$userFinalWeight  ===DATA===  $userCurrentWeight  ===DATA===  $currentDateKey")

                lineEntriesWT2.add(Entry(i.toFloat(), userCurrentWeight))
                labelsNamesWT2.add(currentDateKey)

            }

            val lineDataSet = LineDataSet(lineEntriesWT2, "WEIGHT")
            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(lineDataSet)


            val data = LineData(dataSets)
            lineChartWT2.data = data
            lineChartWT2.invalidate()

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
            xAxis.labelCount = labelsNamesWT2.size
            xAxis.valueFormatter = IndexAxisValueFormatter(labelsNamesWT2)
            xAxis.labelRotationAngle = 270f

            val description = Description()
            description.text = "user_weight"
            description.textSize = 1f
            lineChartWT2.description = description
            lineChartWT2.setPinchZoom(false)


        }

        )


    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //val recyclerView = findViewById<RecyclerView>(R.id.home_recycler_view)
        // recyclerView.layoutManager = layoutManager
        val adapter = QuotesAdapter(this, mNames, mImageUrls)
        // recyclerView.adapter = adapter

        /*  val snapHelper: SnapHelper = LinearSnapHelper()
          snapHelper.attachToRecyclerView(recyclerView)*/
    }

    companion object {
        private const val TAG = "MainActivity"
    }

    private fun setUpSpinner() {
        /*  val sortBySpinnerData = resources.getStringArray(R.array.sortByspinner)
          / val ad = ArrayAdapter(this.applicationContext, android.R.layout.simple_dropdown_item_1line, sortBySpinnerData)
            spinnerWeightTracker2.adapter = ad
  */
        val ad = ArrayAdapter.createFromResource(
                this, R.array.chart_spinner, R.layout.spinner_layout
        )
        ad.setDropDownViewResource(R.layout.spinner_layout)
        spinnerWeightTracker2.adapter = ad
    }

    private val images: Unit
        get() {
            mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg")
            mNames.add("“To change your body you must first change your mind.”")
            mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg")
            mNames.add("“The only bad workout is the one that didn’t happen.”")
            mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg")
            mNames.add("“I don’t stop when I’m tired,I stop when I’m DONE!”")
            mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg")
            mNames.add("“Happiness is the highest form of health.”")
            mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg")
            mNames.add("“Three months from now, you will thanks yourself”")
            initRecyclerView()
        }

    private fun initialisingFields() {

        enterWeightTv = findViewById(R.id.enter_weight_tv)
        goalWeightTv = findViewById(R.id.goal_weight_tv)
        currentWeightTv = findViewById(R.id.current_weight_tv)
        droppedWeightTv = findViewById(R.id.dropped_weight_tv)
        spinnerWeightTracker2 = findViewById(R.id.spinner_wt2)
        editWeightBtn = findViewById(R.id.edit_weight_rl)
        updateWeightBtn = findViewById(R.id.next_btn_goal_weight_wt2)
        droppedtv = findViewById(R.id.dropped_tv)
        lineChartWT2 = findViewById(R.id.MPLineChartWT2)
        progressBar = findViewById(R.id.arc_progressbar)


    }

    private fun openDialog() {

        dialog.setContentView(R.layout.dialog_input_weight_wt2)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(true)
    }


}


