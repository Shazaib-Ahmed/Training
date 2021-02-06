package com.example.sampleproject_1.WaterReminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sampleproject_1.Database.EntityWaterReminder;
import com.example.sampleproject_1.Database.ViewModelWaterReminder;
import com.example.sampleproject_1.R;
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


    TextView continueTextView;
    EditText weightEditText, genderEditText;
    TextView timePickerBedTV, timePickerWakeTV;
    private List<EntityWaterReminder> entityWaterReminders = new ArrayList<>();


    private ViewModelWaterReminder viewModelWaterReminder;
    private RadioButton getMaleOption, getFemaleOption;
    private int hourPick, minutePick;


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

                                String time = hourPick + ":" + minutePick;
                               /* Calendar calendar = Calendar.getInstance();

                                calendar.set(0,0,0,hourPick,minutePick);*/
                                SimpleDateFormat f24hours = new SimpleDateFormat("HH:mm");

                                try {
                                    Date date = f24hours.parse(time);
                                    SimpleDateFormat f12hours = new SimpleDateFormat("hh:mm aa");
                                    timePickerBedTV.setText(f12hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0, false
                );
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

                                String time = hourPick + ":" + minutePick;

                                SimpleDateFormat f24hours = new SimpleDateFormat("HH:mm");

                                try {
                                    Date date = f24hours.parse(time);
                                    SimpleDateFormat f12hours = new SimpleDateFormat("hh:mm aa");
                                    timePickerWakeTV.setText(f12hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0, false
                );
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

        int weight = Integer.parseInt(weightEditText.getText().toString());
        String gender = genderEditText.getText().toString();
        String timeBed = timePickerBedTV.getText().toString();
        String timeWake = timePickerBedTV.getText().toString();

        EntityWaterReminder entityWaterReminder = new EntityWaterReminder(gender, weight, timeBed, timeWake);
        viewModelWaterReminder.insert(entityWaterReminder);

        Intent data = new Intent(UserDetailsPage.this, HomePage.class);
        data.putExtra(EXTRA_WEIGHT, weight);
        data.putExtra(EXTRA_GENDER, gender);
        data.putExtra(EXTRA_BEDTIME, timeBed);
        data.putExtra(EXTRA_WAKEUPTIME, timeWake);
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