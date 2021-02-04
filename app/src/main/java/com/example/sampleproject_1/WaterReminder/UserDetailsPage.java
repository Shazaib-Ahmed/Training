package com.example.sampleproject_1.WaterReminder;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sampleproject_1.Database.DataAccessObjectWaterReminder;
import com.example.sampleproject_1.Database.DatabaseWaterReminder;
import com.example.sampleproject_1.Database.EntityWaterReminder;
import com.example.sampleproject_1.R;
import com.example.sampleproject_1.WaterReminder.Fragment.FragmentWaterReminderHome;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsPage extends AppCompatActivity {
    public static final String EXTRA_GENDER =
            "com.example.sampleproject_1.WaterReminder.EXTRA_GENDER";
    public static final String EXTRA_WEIGHT =
            "com.example.sampleproject_1.WaterReminder.EXTRA_WEIGHT";

    SeekBar seekBar;
    TextView textViewWeightInKg;
    TextView continueTextView;
    EditText weightEditText;
    String buttonOption = "DEMO";
    // ViewModelWaterReminder viewModelWaterReminder;

    //private ViewModelWaterReminder viewModelWaterReminder;
    private RadioButton getMaleOption, getFemaleOption;


    private List<EntityWaterReminder> entityWaterReminders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_page);

        //viewModelWaterReminder = new ViewModelProvider(this).get(ViewModelWaterReminder.class);
        InitialisationFields();
        SeekBarListener();


        if (getMaleOption.isChecked()) {
            buttonOption = "MALE";
        } else if (getFemaleOption.isChecked()) {
            buttonOption = "FEMALE";
        }

        continueTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EntityWaterReminder entityWaterReminder = new EntityWaterReminder();
                entityWaterReminder.setWeight(weightEditText.getText().toString());
                entityWaterReminder.setGender(buttonOption);

                if (validateInput(entityWaterReminder)) {
                    DatabaseWaterReminder databaseWaterReminder = DatabaseWaterReminder.getUserDatabase(getApplicationContext());
                    DataAccessObjectWaterReminder dataAccessObjectWaterReminder = databaseWaterReminder.dataAccessObjectWaterReminder();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            dataAccessObjectWaterReminder.insertUserDetail(entityWaterReminder);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String weightUSER = entityWaterReminder.getWeight();
                                    String genderUSER = entityWaterReminder.getGender();


                                    Intent intent = new Intent(UserDetailsPage.this, HomePage.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("weightUserInKiloGram", weightUSER);
                                    bundle.putString("genderUser", genderUSER);
                                    FragmentWaterReminderHome fragmentWaterReminderHome = new FragmentWaterReminderHome();
                                    fragmentWaterReminderHome.setArguments(bundle);
                                    intent.putExtras(bundle);
                                    startActivity(intent);

                                    Toast.makeText(UserDetailsPage.this, "Operation Successful", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();
                } else {
                    Toast.makeText(UserDetailsPage.this, "Fill All Fields", Toast.LENGTH_SHORT).show();
                }


                /*.putExtra("weight_user",weightUSER)
                .putExtra("gender_user",genderUSER));*/

                // saveUserDetails();
               /* String weightInKiloGram = weightEditText.getText().toString();
                if (buttonOption.trim().isEmpty() || weightInKiloGram.trim().isEmpty()) {
                    Toast.makeText(UserDetailsPage.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                    return;
                }
              *//*  entityWaterReminder.setGender(buttonOption);
                entityWaterReminder.setWeight(weightInKiloGram);*//*
                Intent intent = new Intent(UserDetailsPage.this, HomePage.class);
                EntityWaterReminder entityWaterReminder = new EntityWaterReminder(weightInKiloGram, buttonOption, "", "");
                viewModelWaterReminder.insert(entityWaterReminder);
                startActivity(intent);*/
                //DatabaseWaterReminder.getInstance(getApplicationContext()).dataAccessObjectWaterReminder().insert(entityWaterReminder);
            }
        });
    }

    private boolean validateInput(EntityWaterReminder entityWaterReminder) {
        if (entityWaterReminder.getWeight().isEmpty() || entityWaterReminder.getGender().isEmpty()) {
            return false;
        }
        return true;
    }
    /*private void saveUserDetails() {
        String weighttt = weightEditText.getText().toString();

        if (buttonOption.trim().isEmpty() || weighttt.trim().isEmpty()) {
            Toast.makeText(this, "Please Enter All Details", Toast.LENGTH_SHORT).show();
            return;
        }

        EntityWaterReminder entityWaterReminder = new EntityWaterReminder();

        entityWaterReminder.setWeight(weighttt);
        entityWaterReminder.setGender(buttonOption);

        DatabaseWaterReminder.getInstance(getApplicationContext()).dataAccessObjectWaterReminder().insert(entityWaterReminder);
        Intent intent = new Intent(UserDetailsPage.this, HomePage.class);
        startActivity(intent);
        finishAffinity();
    }*/

    private void SeekBarListener() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int i = progress * 500 / 500;
                textViewWeightInKg.setText("" + i + " Kg");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

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
        seekBar = findViewById(R.id.weightSeekBar);
        textViewWeightInKg = findViewById(R.id.textViewWeightInKg);
        getMaleOption = findViewById(R.id.maleOption);
        getFemaleOption = findViewById(R.id.femaleOption);
        continueTextView = findViewById(R.id.bottomContinueButtonUserDetailsPage);
        weightEditText = findViewById(R.id.weightEditText);
    }

    public String sendUserWeight() {
        return weightEditText.getText().toString();
    }

    public String sendUserGender() {
        return buttonOption;
    }


}