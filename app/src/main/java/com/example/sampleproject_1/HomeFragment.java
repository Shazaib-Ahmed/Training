package com.example.sampleproject_1;


import android.content.Context;
import android.icu.text.TimeZoneFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class HomeFragment extends Fragment
{
    Button addWater;
    ProgressBar progressBar;
    TextView progressText;
    ListView timeIntakeWaterList,litreIntakeWaterList;
    int progress=0;
    ArrayList<String> itemList1,itemList2;
    ArrayAdapter<String> adapterTime,adapterLitre;
    Context context;


    public HomeFragment()
    {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        addWater =v.findViewById(R.id.add_water);
        progressText = v.findViewById(R.id.progress_text);
        progressBar=v.findViewById(R.id.progress_bar);

        timeIntakeWaterList=v.findViewById(R.id.time_intake_water_list);
        litreIntakeWaterList=v.findViewById(R.id.litre_intake_water_list);

        context=v.getContext().getApplicationContext();

        itemList1=new ArrayList<String>();
        adapterTime=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,itemList1);
        timeIntakeWaterList.setAdapter(adapterTime);

        itemList2=new ArrayList<String>();
        adapterLitre=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,itemList2);
        litreIntakeWaterList.setAdapter(adapterLitre);

        addWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (progress<=90)
                {
                    progress+=10;
                    updateProgressBar();

                    updateTimeChart();
                    updateLitreChart();

                }
           }
        });
        return v;

    }

    private void updateLitreChart()
    {
        itemList2.add("200ml");
        adapterLitre.notifyDataSetChanged();
    }

    private void updateTimeChart()
    {
        Calendar calendar =Calendar.getInstance();
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("hh:mm:ss a");
        String currentTime =simpleDateFormat.format(calendar.getTime());
        itemList1.add(currentTime);
        adapterTime.notifyDataSetChanged();
    }

    private void updateProgressBar()
{
    progressBar.setProgress(progress);
    progressText.setText(""+progress+"%");
}
}
