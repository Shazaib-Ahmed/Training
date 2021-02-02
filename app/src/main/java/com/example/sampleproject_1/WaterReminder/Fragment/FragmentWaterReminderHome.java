package com.example.sampleproject_1.WaterReminder.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.sampleproject_1.R;

public class FragmentWaterReminderHome extends Fragment {
    public FragmentWaterReminderHome() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_water_reminder_home, container, false);
        return v;
    }
}
