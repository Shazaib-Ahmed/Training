package com.example.sampleproject_1.WaterReminder.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.sampleproject_1.Database.EntityWaterReminder;
import com.example.sampleproject_1.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentWaterReminderHome extends Fragment {
    TextView userWeight, userGender;
    EntityWaterReminder entityWaterReminder;
   // List<EntityWaterReminder> entityWaterReminderList = new ArrayList<>();

    public FragmentWaterReminderHome() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_water_reminder_home, container, false);
        userWeight = v.findViewById(R.id.userWeightTextView);
        userGender=v.findViewById(R.id.genderTV);
        entityWaterReminder = new EntityWaterReminder();



        String weightKilogram = entityWaterReminder.getWeight();
        String genderrrr = entityWaterReminder.getGender();
        userWeight.setText("" +weightKilogram);
        userGender.setText("" +genderrrr);

        return v;
    }

}
