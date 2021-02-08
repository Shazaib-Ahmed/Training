package com.example.sampleproject_1.WaterReminder;

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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sampleproject_1.R;
import com.example.sampleproject_1.WaterReminder.Database.EntityWaterReminder;
import com.example.sampleproject_1.WaterReminder.Database.ViewModelWaterReminder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDetailsPage extends AppCompatActivity {
    public static final String EXTRA_GENDER =
            "com.example.sampleproject_1.WaterReminder.EXTRA_GENDER";
    public static final String EXTRA_WEIGHT =
            "com.example.sampleproject_1.WaterReminder.EXTRA_WEIGHT";
    public static final String EXTRA_BEDTIME =
            "com.example.sampleproject_1.WaterReminder.EXTRA_BEDTIME";
    public static final String EXTRA_WAKEUPTIME =
            "com.example.sampleproject_1.WaterReminder.EXTRA_WAKEUPTIME";
    public static final String EXTRA_FIRST_RUN_KEY =
            "com.example.sampleproject_1.WaterReminder.EXTRA_FIRST_RUN_KEY";
    String SHARED_PREFS ="sharedPrefs";


    TextView continueTextView;
    EditText weightEditText, genderEditText;
    TextView timePickerBedTV, timePickerWakeTV;
    private List<EntityWaterReminder> entityWaterReminders = new ArrayList<>();


    private ViewModelWaterReminder viewModelWaterReminder;
    private RadioButton getMaleOption, getFemaleOption;
    private int hourPick, minutePick;
    private String sleepingTime;
    private String wakeUptime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_page);
        InitialisationFields();

        viewModelWaterReminder = new ViewModelProvider(this).get(ViewModelWaterReminder.class);

        viewModelWaterReminder.getAllNotes().observe(this, new Observer<List<EntityWaterReminder>>() {
            @Override
            public void onChanged(List<EntityWaterReminder> entityWaterReminders) {

            }
        });

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
                               /* Calendar calendar = Calendar.getInstance();

                                calendar.set(0,0,0,hourPick,minutePick);*/
                                SimpleDateFormat f24hours = new SimpleDateFormat("HH:mm");

                                try {
                                    Date date = f24hours.parse(sleepingTime);
                                    SimpleDateFormat f12hours = new SimpleDateFormat("hh:mm aa");
                                    timePickerBedTV.setText(f12hours.format(date));
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
                                    timePickerWakeTV.setText(f12hours.format(date));
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


        if (genderEditText.getText().toString().isEmpty() || weightEditText.getText().toString().isEmpty() ) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        /*String timeBed = timePickerBedTV.getText().toString();
        String timeWake = timePickerBedTV.getText().toString();*/

        int weight = Integer.parseInt(weightEditText.getText().toString());
        String gender = genderEditText.getText().toString();

        if (weight >200 || weight<20 ) {
            Toast.makeText(this, "Please enter valid weight", Toast.LENGTH_SHORT).show();
            return;
        }

        EntityWaterReminder entityWaterReminder = new EntityWaterReminder(gender, weight, sleepingTime, wakeUptime);
        viewModelWaterReminder.insert(entityWaterReminder);

        Intent data = new Intent(UserDetailsPage.this, HomePage.class);
        data.putExtra(EXTRA_WEIGHT, weight);
        data.putExtra(EXTRA_GENDER, gender);
        data.putExtra(EXTRA_BEDTIME, sleepingTime);
        data.putExtra(EXTRA_WAKEUPTIME, wakeUptime);
        data.putExtra(EXTRA_FIRST_RUN_KEY, false);

        SharedPreferences sharedPreferences =getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putBoolean(EXTRA_FIRST_RUN_KEY,false);
        editor.apply();

        startActivity(data);
        finish();
    }


    public void sendToHomePage(View view) {
        Intent intent = new Intent(UserDetailsPage.this, HomePage.class);
        startActivity(intent);
    }

    public void sendToPreviousActivity(View view) {
        onBackPressed();
    }

    private void InitialisationFields() {
        getMaleOption = findViewById(R.id.maleOption);
        getFemaleOption = findViewById(R.id.femaleOption);
        getMaleOption = findViewById(R.id.maleOption);
        getFemaleOption = findViewById(R.id.femaleOption);
        continueTextView = findViewById(R.id.bottomContinueButtonUserDetailsPage);
        weightEditText = findViewById(R.id.weightEditText);
        genderEditText = findViewById(R.id.genderEditTExt);
        timePickerBedTV = findViewById(R.id.timePickerBedTV);
        timePickerWakeTV = findViewById(R.id.timePickerWakeTV);
    }
}