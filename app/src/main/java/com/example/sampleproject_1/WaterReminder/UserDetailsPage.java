package com.example.sampleproject_1.WaterReminder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sampleproject_1.R;

public class UserDetailsPage extends AppCompatActivity {
RadioButton maleOption,femaleOption;
SeekBar seekBar;
TextView textViewWeightInKg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_page);



        maleOption=findViewById(R.id.maleOption);
        femaleOption=findViewById(R.id.femaleOption);
seekBar=findViewById(R.id.weightSeekBar);
        textViewWeightInKg=findViewById(R.id.textViewWeightInKg);
seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int i=progress*500/500;
        textViewWeightInKg.setText(""+i+" Kg");
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
        Intent intent =new Intent(UserDetailsPage.this,HomePage.class);
        startActivity(intent);
    }

    public void sendToPreviousActivity(View view) {
        onBackPressed();
    }
}