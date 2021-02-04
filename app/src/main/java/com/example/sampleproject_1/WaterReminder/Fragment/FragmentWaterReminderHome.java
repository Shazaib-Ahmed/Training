package com.example.sampleproject_1.WaterReminder.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.sampleproject_1.Database.DatabaseWaterReminder;
import com.example.sampleproject_1.Database.EntityWaterReminder;
import com.example.sampleproject_1.R;
import com.example.sampleproject_1.WaterReminder.UserDetailsPage;

import java.util.ArrayList;
import java.util.List;

public class FragmentWaterReminderHome extends Fragment {
    TextView userWeight, userGender;

    public FragmentWaterReminderHome() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_water_reminder_home, container, false);
        userWeight = v.findViewById(R.id.userWeightTextView);
        userGender = v.findViewById(R.id.genderTV);



        //setArguments();

       /* UserDetailsPage userDetailsPage = (UserDetailsPage) getActivity();
        String getWeight =userDetailsPage.sendUserWeight();
        String getGenderr =userDetailsPage.sendUserGender();*/

        EntityWaterReminder entityWaterReminder =new EntityWaterReminder();
        String getWeight = entityWaterReminder.getGender();
        String getGenderr =entityWaterReminder.getWeight();
        userWeight.setText(getWeight + "kg");
        userGender.setText(getGenderr + "MF");
        return v;
    }

    public void setArguments() {

            String userWeightKG = getArguments().getString("weightUserInKiloGram");
            String userGEN = getArguments().getString("genderUser");

            userWeight.setText(userWeightKG + "kg");
            userGender.setText(userGEN + "MF");

    }
}
