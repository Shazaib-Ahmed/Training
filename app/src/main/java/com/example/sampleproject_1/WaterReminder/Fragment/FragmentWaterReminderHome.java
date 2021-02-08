package com.example.sampleproject_1.WaterReminder.Fragment;

import android.content.ClipData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.sampleproject_1.R;
import com.example.sampleproject_1.WaterReminder.Database.EntityWaterReminder;
import com.example.sampleproject_1.WaterReminder.Database.ViewModelWaterReminder;


import java.util.ArrayList;
import java.util.List;

public class FragmentWaterReminderHome extends Fragment {
    TextView userWeight, userGender;
    private List<EntityWaterReminder> entityWaterReminders =new ArrayList<>();
    private ViewModelWaterReminder viewModelWaterReminder;
    public FragmentWaterReminderHome() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_water_reminder_home, container, false);
        userWeight = v.findViewById(R.id.userWeightTextView);
        userGender = v.findViewById(R.id.genderTV);




        return v;
    }

    public void setUser(List<EntityWaterReminder> entityWaterReminders){
        this.entityWaterReminders =entityWaterReminders;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        viewModelWaterReminder = new ViewModelProvider(this).get(ViewModelWaterReminder.class);

        viewModelWaterReminder.getAllNotes().observe(getActivity(), new Observer<List<EntityWaterReminder>>() {
            @Override
            public void onChanged(List<EntityWaterReminder> entityWaterReminders) {
              /*  int weight = entityWaterReminders.get().getWeight();
                String gender = entityWaterReminders.get().getGender();
                String bedTime = entityWaterReminders.get().getBedTime();
                String wakeUpTime = entityWaterReminders.get().getWakeUpTime();

                EntityWaterReminder entityWaterReminder = new EntityWaterReminder(gender,weight,bedTime,wakeUpTime);

                userWeight.setText(weight+"");
                userGender.setText(gender);*/


            }
        });
    }
    public class SharedViewModel extends ViewModel{
        private final MutableLiveData<ClipData.Item> selected =new MutableLiveData<>();

        public void select (ClipData.Item item)
        {
            selected.setValue(item);
        }
        public LiveData<ClipData.Item> getSelected(){
            return selected;
        }


    }
}
