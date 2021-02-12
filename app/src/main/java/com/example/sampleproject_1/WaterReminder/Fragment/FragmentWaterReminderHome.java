package com.example.sampleproject_1.WaterReminder.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleproject_1.R;
import com.example.sampleproject_1.WaterReminder.Adapter.TodayWaterIntakeAdapter;
import com.example.sampleproject_1.WaterReminder.Database.EntityWaterReminder;
import com.example.sampleproject_1.WaterReminder.Database.ViewModelWaterReminder;
import com.example.sampleproject_1.WaterReminder.Dialog.BottomSheetDialog;
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils;
import com.example.sampleproject_1.WaterReminder.model.WaterIntake;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.itangqi.waveloadingview.WaveLoadingView;

public class FragmentWaterReminderHome extends Fragment {

    TextView userWeight, userGender, addWater, remainingWater;
    //private List<EntityWaterReminder> entityWaterReminders = new ArrayList<>();
    private ViewModelWaterReminder viewModelWaterReminder;
    private SharedPreferences sharedPreferences;
    private EntityWaterReminder entityWaterReminder;
    private int progress;
    private int inTook = 0;
    private int totalIntake = 0;
    private String dateNow;
    int totalIntook = 0;


    RecyclerView timeIntakeWaterListRV;
    ArrayList<WaterIntake> itemList1;
    TodayWaterIntakeAdapter adapterTime;
    Context context;
    WaveLoadingView waveLoadingView;

    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
    String currentTime = simpleDateFormat.format(calendar.getTime());

    Gson gson;

    public FragmentWaterReminderHome() {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPreferences = this.getActivity().getSharedPreferences(AppUtils.USERS_SHARED_PREF, AppUtils.PRIVATE_MODE);

        View v = inflater.inflate(R.layout.fragment_water_reminder_home, container, false);
        userWeight = v.findViewById(R.id.userWeightTextView);
        userGender = v.findViewById(R.id.genderTV);
        waveLoadingView = v.findViewById(R.id.progress_bar);
        addWater = v.findViewById(R.id.add_water);
        remainingWater = v.findViewById(R.id.remainingWater);

        timeIntakeWaterListRV = v.findViewById(R.id.time_intake_water_list);

        viewModelWaterReminder = new ViewModelProvider(this).get(ViewModelWaterReminder.class);

       /* entityWaterReminder = new EntityWaterReminder(dateNow, 0, totalIntake);
        viewModelWaterReminder.insert(entityWaterReminder);*/

        int w = sharedPreferences.getInt(AppUtils.WEIGHT_KEY, 0);
        String g = sharedPreferences.getString(AppUtils.GENDER_KEY, "");
        totalIntake = sharedPreferences.getInt(AppUtils.TOTAL_INTAKE, 0);
        String wakeTime = sharedPreferences.getString(AppUtils.WAKE_UP_TIME_KEY, "");

        context = v.getContext().getApplicationContext();

        remainingWater.setText("Remaining Water");
        loadData();

        buildRecyclerView();

        addWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inTook = 10 * totalIntake / 100;
                totalIntook += inTook;
                if (totalIntook < totalIntake && progress < 100) {

                    setWaterLevel(inTook, totalIntake);

                    saveData();

                    adapterTime.updateData(new WaterIntake(currentTime, inTook));
                    adapterTime.notifyItemInserted(itemList1.size());

                    //updateTimeChart();
                } else {
                    Toast.makeText(getContext().getApplicationContext(), "You are done for the day", Toast.LENGTH_SHORT).show();
                }

            }

        });

/*
        addWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog =new BottomSheetDialog();
                bottomSheetDialog.show(getChildFragmentManager(),"ModalBottomSheet");
            }
        });
*/

        userWeight.setText(w + " kg");
        userGender.setText(totalIntake / 1000 + " L");

        return v;
    }


    private void updateTimeChart() {

        adapterTime.updateData(new WaterIntake(currentTime, inTook));
        adapterTime.notifyItemInserted(itemList1.size());

        entityWaterReminder = new EntityWaterReminder(currentTime, inTook, totalIntake);
        viewModelWaterReminder.insert(entityWaterReminder);
    }


    private void setWaterLevel(int inTook, int totalIntake) {

        if ((inTook * 100 / totalIntake) > 140) {
            Toast.makeText(getContext().getApplicationContext(), "You are done for the day", Toast.LENGTH_SHORT).show();
        } else {
            progress += inTook * 100 / totalIntake;
            waveLoadingView.setProgressValue(progress);
            waveLoadingView.setCenterTitle(progress + " %");
        }
        remainingWater.setText(totalIntook + "/" + totalIntake + " ml");
    }

//    private void setWaterLevel(int inTook, int totalIntake) {
//
//        Log.e("intook",inTook+"    "+totalIntake);
//        if (((inTook * 100) / totalIntake) > 140) {
//            Toast.makeText(getContext().getApplicationContext(), "You are done for the day", Toast.LENGTH_SHORT).show();
//        } else {
//            progress = ((inTook / totalIntake) * 100);
//            waveLoadingView.setProgressValue(progress);
//            waveLoadingView.setCenterTitle(progress + " %");
//        }
//        remainingWater.setText(inTook + "/" + totalIntake + " ml");
//    }


    private void updateValues() {
        //totalIntake = sharedPreferences.getInt(AppUtils.TOTAL_INTAKE,0);
        //entityWaterReminder =new EntityWaterReminder(dateNow,inTook,totalIntake);
        inTook = entityWaterReminder.getKEY_INTOOK(dateNow);
//        setWaterLevel(inTook, totalIntake);

    }

/*
    public void addWater(int inTook){
       // inTook = 200;

        if (totalIntook < totalIntake) {
            totalIntook += inTook;
            progress += inTook * 100 / totalIntake;
            if (progress < 100) {
                //progress = ((200/totalIntake)*100);
                // progress=(int)((double) 200/totalIntake*100);

                remainingWater.setText(totalIntook + "/" + totalIntake + " ml");
                waveLoadingView.setProgressValue(progress);
                waveLoadingView.setCenterTitle(progress + " %");
                updateTimeChart();
            }
        } else {

            Toast.makeText(getContext().getApplicationContext(), "You are done for the day", Toast.LENGTH_SHORT).show();
        }

    }
*/

    private void saveData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        gson = new Gson();
        String jsonData = gson.toJson(itemList1);
        editor.putString("myJson", jsonData);
        editor.apply();
    }

    private void loadData() {
        gson = new Gson();
        String jsonData = sharedPreferences.getString("myJson", null);
        Type type = new TypeToken<ArrayList<WaterIntake>>() {
        }.getType();
        itemList1 = gson.fromJson(jsonData, type);

        if (itemList1 == null) {
            itemList1 = new ArrayList<>();
        }
    }

    private void buildRecyclerView() {
        timeIntakeWaterListRV.setHasFixedSize(true);
        timeIntakeWaterListRV.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false));
        adapterTime = new TodayWaterIntakeAdapter(itemList1);
        timeIntakeWaterListRV.setAdapter(adapterTime);
    }

}
