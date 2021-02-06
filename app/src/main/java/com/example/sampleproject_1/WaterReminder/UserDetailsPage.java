package com.example.sampleproject_1.WaterReminder;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sampleproject_1.Database.EntityWaterReminder;
import com.example.sampleproject_1.Database.ViewModelWaterReminder;
import com.example.sampleproject_1.R;

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
    EditText weightEditText, genderEditText;
    String buttonOption = "DEMO";
    private List<EntityWaterReminder> entityWaterReminders = new ArrayList<>();


    private ViewModelWaterReminder viewModelWaterReminder;
    private RadioButton getMaleOption, getFemaleOption;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_page);
        InitialisationFields();
        SeekBarListener();

        viewModelWaterReminder = new ViewModelProvider(this).get(ViewModelWaterReminder.class);

        viewModelWaterReminder.getAllNotes().observe(this, new Observer<List<EntityWaterReminder>>() {
            @Override
            public void onChanged(List<EntityWaterReminder> entityWaterReminders) {
                
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
        int weight = Integer.parseInt(weightEditText.getText().toString());
        String gender = genderEditText.getText().toString();

        if (weight <20 || gender.trim().isEmpty())
        {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent(UserDetailsPage.this,HomePage.class);
        data.putExtra(EXTRA_WEIGHT,weight);
        data.putExtra(EXTRA_GENDER,gender);
        setResult(RESULT_OK,data);
        startActivityForResult(data,1);
        finish();


    }

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
        genderEditText = findViewById(R.id.genderEditTExt);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK){
            int weight = data.getIntExtra(UserDetailsPage.EXTRA_WEIGHT,21);
            String gender = data.getStringExtra(UserDetailsPage.EXTRA_GENDER);

            EntityWaterReminder entityWaterReminder = new EntityWaterReminder(gender,weight,"BEDTIME","WAKEUPTIME");
            viewModelWaterReminder.insert(entityWaterReminder);
            Toast.makeText(this, "WELCOME", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, ". . . . .", Toast.LENGTH_SHORT).show();
        }
    }
}