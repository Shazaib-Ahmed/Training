package com.example.sampleproject_1.WaterReminder.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.sampleproject_1.WaterReminder.Fragment.FragmentWaterReminderHistory;
import com.example.sampleproject_1.WaterReminder.Fragment.FragmentWaterReminderHome;

public class TabAccessAdapter extends FragmentPagerAdapter {

    public TabAccessAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                FragmentWaterReminderHome fragmentWaterReminderHome =new FragmentWaterReminderHome();
                return fragmentWaterReminderHome;
            case 1 :
                FragmentWaterReminderHistory fragmentWaterReminderHistory =new FragmentWaterReminderHistory();
                return fragmentWaterReminderHistory;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0 :
                return "Home";
            case 1 :
                return "History";
            default:
                return null;
        }

    }
}
