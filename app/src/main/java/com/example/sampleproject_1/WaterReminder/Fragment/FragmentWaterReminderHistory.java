package com.example.sampleproject_1.WaterReminder.Fragment;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.service.autofill.Dataset;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.load.resource.bitmap.LazyBitmapDrawableResource;
import com.example.sampleproject_1.R;
import com.example.sampleproject_1.WaterReminder.Database.EntityWaterReminder;
import com.example.sampleproject_1.WaterReminder.Database.ViewModelWaterReminder;
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils;
import com.example.sampleproject_1.WaterReminder.Utils.DailyWaterIntakeData;
import com.example.sampleproject_1.WaterReminder.model.WaterIntake;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FragmentWaterReminderHistory extends Fragment {

    private ViewModelWaterReminder viewModelWaterReminder;

    private BarChart barChart;
    //ArrayList<BarEntry> barEntries;
    //ArrayList<String> labelsNames;
    private Spinner sortBySpinner;

    //ArrayList<DailyWaterIntakeData> dailyWaterIntakeData = new ArrayList<>();

    //private float totalPercentage = 0f;
    //private float totalGlasses = 0f;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_water_reminder_history, container, false);
        barChart = v.findViewById(R.id.chart);
        sortBySpinner = v.findViewById(R.id.sortBySpinner);
        setUpSpinner();


        //sharedPreferences = this.getActivity().getSharedPreferences(AppUtils.USERS_SHARED_PREF, AppUtils.PRIVATE_MODE);

        viewModelWaterReminder = new ViewModelProvider(this).get(ViewModelWaterReminder.class);
        viewModelWaterReminder.getAllData();
        viewModelWaterReminder.getAllData().observe(this.getActivity(), new Observer<List<Entry>>() {
            @Override
            public void onChanged(List<Entry> entries) {

                
               // BarDataSet barDataSet = new BarDataSet(entries, "Days");

            }
        });

        //barEntries = new ArrayList<>();
        //labelsNames = new ArrayList<>();
        //fillData();

       /* for (int i = 0; i < dailyWaterIntakeData.size(); i++) {
            String day = dailyWaterIntakeData.get(i).getDays();
            float percentage = dailyWaterIntakeData.get(i).getPercentage();

            barEntries.add(new BarEntry(i, percentage));
            labelsNames.add(day);
        }*/

       /* BarDataSet barDataSet = new BarDataSet(barEntries, "Daily Water Intake");
        barDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        Description description = new Description();
        description.setText("Days");
        description.setTextSize(1f);

        barChart.setDescription(description);
        barChart.setPinchZoom(false);
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.9f);
        barChart.setData(barData);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labelsNames));

        YAxis yAxis = barChart.getAxisLeft();
        yAxis.mAxisMinimum = 0;

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(labelsNames.size());
        xAxis.setLabelRotationAngle(270);

        barChart.animateY(3000);
        barChart.invalidate();*/

        return v;
    }

    private void setUpSpinner() {
        String sortBySpinnerData[] = getResources().getStringArray(R.array.sortByspinner);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(getContext().getApplicationContext(), android.R.layout.simple_dropdown_item_1line, sortBySpinnerData);
        sortBySpinner.setAdapter(ad);
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
