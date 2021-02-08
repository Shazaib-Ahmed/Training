package com.example.sampleproject_1.WaterReminder.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sampleproject_1.R;
import com.example.sampleproject_1.WaterReminder.Database.EntityWaterReminder;
import com.example.sampleproject_1.WaterReminder.Database.ViewModelWaterReminder;
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.itangqi.waveloadingview.WaveLoadingView;

public class FragmentWaterReminderHome extends Fragment {
    TextView userWeight, userGender, addWater, remainingWater;
    private List<EntityWaterReminder> entityWaterReminders = new ArrayList<>();
    private ViewModelWaterReminder viewModelWaterReminder;
    private SharedPreferences sharedPreferences;
    private int progress, intook = 0;

    ListView timeIntakeWaterList;
    ArrayList<String> itemList1;
    ArrayAdapter<String> adapterTime;
    Context context;
    WaveLoadingView waveLoadingView;

    public FragmentWaterReminderHome() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_water_reminder_home, container, false);
        userWeight = v.findViewById(R.id.userWeightTextView);
        userGender = v.findViewById(R.id.genderTV);
        waveLoadingView = v.findViewById(R.id.progress_bar);
        addWater = v.findViewById(R.id.add_water);
        remainingWater = v.findViewById(R.id.remainingWater);

        timeIntakeWaterList = v.findViewById(R.id.time_intake_water_list);


        context = v.getContext().getApplicationContext();

        itemList1 = new ArrayList<String>();
        adapterTime = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, itemList1);
        timeIntakeWaterList.setAdapter(adapterTime);


        sharedPreferences = this.getActivity().getSharedPreferences(AppUtils.USERS_SHARED_PREF, AppUtils.PRIVATE_MODE);
        int w = sharedPreferences.getInt(AppUtils.WEIGHT_KEY, 0);
        String g = sharedPreferences.getString(AppUtils.GENDER_KEY, "");
        int totalintaka = sharedPreferences.getInt(AppUtils.TOTAL_INTAKE, 1);
        String waketime = sharedPreferences.getString(AppUtils.WAKE_UP_TIME_KEY, "");


        addWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (progress > 90) {

                    Toast.makeText(getContext().getApplicationContext(), "You are done for the day", Toast.LENGTH_SHORT).show();

                } else if (progress <= 90) {
                    progress += 10;

                    //intook = 1/10 * totalintaka;
                    intook += 237;
                    waveLoadingView.setProgressValue(progress);
                    waveLoadingView.setCenterTitle((progress + " %"));
                    remainingWater.setText(intook + "/" + totalintaka + " ml");
                    updateTimeChart();
                }

            }
        });

        userWeight.setText(w + " kg");
        userGender.setText(totalintaka / 1000 + " L");

        return v;
    }


    private void updateTimeChart() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
        String currentTime = simpleDateFormat.format(calendar.getTime());
        itemList1.add(currentTime + " 200ml");
        adapterTime.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

  /*      viewModelWaterReminder = new ViewModelProvider(this).get(ViewModelWaterReminder.class);

        viewModelWaterReminder.getAllNotes().observe(getActivity(), new Observer<List<EntityWaterReminder>>() {
            @Override
            public void onChanged(List<EntityWaterReminder> entityWaterReminders) {
              *//*  int weight = entityWaterReminders.get().getWeight();
                String gender = entityWaterReminders.get().getGender();
                String bedTime = entityWaterReminders.get().getBedTime();
                String wakeUpTime = entityWaterReminders.get().getWakeUpTime();

                EntityWaterReminder entityWaterReminder = new EntityWaterReminder(gender,weight,bedTime,wakeUpTime);

                userWeight.setText(weight+"");
                userGender.setText(gender);*//*


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
        }*/

    }

}
