package com.example.sampleproject_1.WaterReminder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sampleproject_1.R;
import com.example.sampleproject_1.WaterReminder.Database.EntityWaterReminder;
import com.example.sampleproject_1.WaterReminder.Database.ViewModelWaterReminder;
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDetailsPage extends AppCompatActivity  {

    private String sleepingTime;
    private String wakeUptime;
    private int weight;
    private static int workTime = 180;
    private SharedPreferences sharedPreferences;
    private String gender;


    TextView continueTextView;
    EditText genderEditText;
    EditText weightEditText;
    TextView timePickerBedTV, timePickerWakeTV;
    private List<EntityWaterReminder> entityWaterReminders = new ArrayList<>();
    TextView timePickerBedTV1, timePickerWakeTV1;
    ActionBar actionBar;

    private ViewModelWaterReminder viewModelWaterReminder;
    private RadioButton getMaleOption, getFemaleOption;
    private int hourPick, minutePick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_page);
        InitialisationFields();

        actionBar = getSupportActionBar();
        actionBar.setTitle("Home Page");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#3F51B5"));
        actionBar.setBackgroundDrawable(colorDrawable);


        viewModelWaterReminder = new ViewModelProvider(this).get(ViewModelWaterReminder.class);


        timePickerBedTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(UserDetailsPage.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                hourPick = hourOfDay;
                                minutePick = minute;

                                sleepingTime = hourPick + ":" + minutePick;

                                SimpleDateFormat f24hours = new SimpleDateFormat("HH:mm");

                                try {
                                    Date date = f24hours.parse(sleepingTime);
                                    SimpleDateFormat f12hours = new SimpleDateFormat("hh:mm aa");
                                    timePickerBedTV1.setText(f12hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0, false
                );
                timePickerDialog.setTitle("Select Sleeping Time");
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(hourPick, minutePick);
                timePickerDialog.show();
            }
        });

        timePickerWakeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(UserDetailsPage.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                hourPick = hourOfDay;
                                minutePick = minute;

                                wakeUptime = hourPick + ":" + minutePick;

                                SimpleDateFormat f24hours = new SimpleDateFormat("HH:mm");

                                try {
                                    Date date = f24hours.parse(wakeUptime);
                                    SimpleDateFormat f12hours = new SimpleDateFormat("hh:mm aa");
                                    timePickerWakeTV1.setText(f12hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0, false
                );
                timePickerDialog.setTitle("Select Wake Up Time");
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(hourPick, minutePick);
                timePickerDialog.show();
            }
        });

        continueTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInfo();
            }
        });



    }

    private void saveUserInfo() {

        if (genderEditText.getText().toString().isEmpty() ||  weightEditText.getText().toString().isEmpty() || timePickerWakeTV1.getText().toString().isEmpty() || timePickerBedTV1.getText().toString().isEmpty() ) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String timeBed = timePickerBedTV1.getText().toString();
        String timeWake = timePickerWakeTV1.getText().toString();

        weight = Integer.parseInt(weightEditText.getText().toString());
       // gender = genderEditText.getText().toString();

        if (weight > 200 || weight < 20) {
            Toast.makeText(this, "Please enter valid weight", Toast.LENGTH_SHORT).show();
            return;
        }

        /*EntityWaterReminder entityWaterReminder = new EntityWaterReminder();
        viewModelWaterReminder.insert(entityWaterReminder);*/

        Intent data = new Intent(UserDetailsPage.this, HomePage.class);

        sharedPreferences = getSharedPreferences(AppUtils.USERS_SHARED_PREF,AppUtils.PRIVATE_MODE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putBoolean(AppUtils.FIRST_RUN_KEY,false);
        editor.putString(AppUtils.WAKE_UP_TIME_KEY,timeWake);
        editor.putString(AppUtils.SLEEPING_TIME_KEY,timeBed);
        editor.putInt(AppUtils.WEIGHT_KEY,weight);
        editor.putString(AppUtils.GENDER_KEY,gender);
        editor.putInt(AppUtils.WORK_TIME_KEY,workTime);

        int totalIntake = AppUtils.calculateIntake(weight,workTime);
        DecimalFormat df = new DecimalFormat("#");
        editor.putInt(AppUtils.TOTAL_INTAKE, Integer.parseInt(df.format(totalIntake)));

        editor.apply();
        startActivity(data);
        finishAffinity();
    }


    private void InitialisationFields() {
        getMaleOption = findViewById(R.id.maleOption);
        getFemaleOption = findViewById(R.id.femaleOption);
        getMaleOption = findViewById(R.id.maleOption);
        getFemaleOption = findViewById(R.id.femaleOption);
        continueTextView = findViewById(R.id.bottomContinueButtonUserDetailsPage);
        weightEditText = findViewById(R.id.weightEditText);
        genderEditText = findViewById(R.id.genderEditText);
        timePickerBedTV = findViewById(R.id.timePickerBedTV);
        timePickerWakeTV = findViewById(R.id.timePickerWakeTV);
        timePickerBedTV1 = findViewById(R.id.timePickerBedTV1);
        timePickerWakeTV1 = findViewById(R.id.timePickerWakeTV1);
    }
}