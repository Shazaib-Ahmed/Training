package com.example.sampleproject_1.WaterReminder.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Database.EntityWaterReminder
import com.example.sampleproject_1.WaterReminder.Database.ViewModelWaterReminder
import com.example.sampleproject_1.WaterReminder.viewModelModule
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class FragmentWaterReminderHistory : Fragment() {
    private lateinit var viewModelWaterReminder: ViewModelWaterReminder

    //private val viewModelWaterReminder by viewModel<ViewModelWaterReminder>()

    //  private val viewModelWaterReminder:ViewModelWaterReminder by viewModel()

    private lateinit var barChart: BarChart
    var barEntries: ArrayList<BarEntry>? = null
    var labelsNames: ArrayList<String>? = null
    private var sortBySpinner: Spinner? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_water_reminder_history, container, false)
        barChart = v.findViewById(R.id.chart)
        sortBySpinner = v.findViewById(R.id.sortBySpinner)

        setUpSpinner()

        viewModelWaterReminder = ViewModelProvider(this).get(ViewModelWaterReminder::class.java)

        viewModelWaterReminder.getAllUser.observe(viewLifecycleOwner, Observer<List<EntityWaterReminder>> { dailyWaterIntakeData ->
            barEntries = ArrayList()
            labelsNames = ArrayList()
            //fillData();
            for (i in dailyWaterIntakeData!!.indices) {
                val day = dailyWaterIntakeData[i].kEY_DATE
                val percentage = dailyWaterIntakeData[i].kEY_INTOOK.toFloat()
                Log.e("data", "$day  ===DAILY_DATA===  $percentage")
                if (day != null) {
                    barEntries!!.add(BarEntry(i.toFloat(), percentage))
                    labelsNames!!.add(day)
                }
            }
            // BarDataSet barDataSet = new BarDataSet(entries, "Days");
            val barDataSet = BarDataSet(barEntries, "Daily Water Intake")
            barDataSet.setColors(*ColorTemplate.LIBERTY_COLORS)
            val description = Description()
            description.text = "Days"
            description.textSize = 1f
            barChart.description = description
            barChart.setPinchZoom(false)
            val barData = BarData(barDataSet)
            barData.barWidth = 0.9f
            barChart.data = barData
            val xAxis = barChart.xAxis
            xAxis.valueFormatter = IndexAxisValueFormatter(labelsNames)
            val yAxis = barChart.axisLeft
            yAxis.mAxisMinimum = 0f
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            xAxis.setDrawAxisLine(false)
            xAxis.granularity = 1f
            xAxis.labelCount = labelsNames!!.size
            xAxis.labelRotationAngle = 270f
            barChart.animateY(3000)
            barChart.invalidate()
        })

//       BarDataSet barDataSet = new BarDataSet(barEntries, "Daily Water Intake");
//        barDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
//        Description description = new Description();
//        description.setText("Days");
//        description.setTextSize(1f);
//
//        barChart.setDescription(description);
//        barChart.setPinchZoom(false);
//        BarData barData = new BarData(barDataSet);
//        barData.setBarWidth(0.9f);
//        barChart.setData(barData);
//
//        XAxis xAxis = barChart.getXAxis();
//        xAxis.setValueFormatter(new IndexAxisValueFormatter(labelsNames));
//
//        YAxis yAxis = barChart.getAxisLeft();
//        yAxis.mAxisMinimum = 0;
//
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setDrawGridLines(false);
//        xAxis.setDrawAxisLine(false);
//        xAxis.setGranularity(1f);
//        xAxis.setLabelCount(labelsNames.size());
//        xAxis.setLabelRotationAngle(270);
//
//        barChart.animateY(3000);
//        barChart.invalidate();
        return v
    }

    private fun setUpSpinner() {
        val sortBySpinnerData = resources.getStringArray(R.array.sortByspinner)
        val ad = ArrayAdapter(context!!.applicationContext, android.R.layout.simple_dropdown_item_1line, sortBySpinnerData)
        sortBySpinner!!.adapter = ad
    }

    /*private void fillData() {
        dailyWaterIntakeData.add(new DailyWaterIntakeData("1", 60f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("2", 70f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("3", 50f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("4", 60f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("5", 40f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("6", 90f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("7", 20f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("8", 100f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("9", 50f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("10", 60f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("11", 60f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("12", 100f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("13", 50f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("14", 60f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("15", 40f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("16", 90f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("17", 20f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("18", 100f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("19", 50f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("20", 60f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("21", 60f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("22", 70f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("23", 50f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("24", 60f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("25", 40f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("26", 90f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("27", 20f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("28", 100f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("29", 50f));
        dailyWaterIntakeData.add(new DailyWaterIntakeData("30", 60f));
    }*/

}