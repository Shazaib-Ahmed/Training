package com.example.sampleproject_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ShowUserInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_info);

        TextView firstLastN,userAge,userMobileNumber,userAddress,userGender;
        firstLastN =findViewById(R.id.first_last_name);
        userAge =findViewById(R.id.user_age);
        userMobileNumber =findViewById(R.id.user_mobile);
        userAddress =findViewById(R.id.user_address);
        userGender =findViewById(R.id.user_gender);


        String firstName = getIntent().getStringExtra("keyFirstName");
        String lastName = getIntent().getStringExtra("keyLastName");
        String UAge = getIntent().getStringExtra("keyAge");
        String UMobile = getIntent().getStringExtra("keyMobileN");
        String UAdd = getIntent().getStringExtra("keyAddress");
        String UGender = getIntent().getStringExtra("keyGender");

        firstLastN.setText("Name -- " + firstName +" "+lastName);
        userAge.setText("Age -- "+ UAge);
        userMobileNumber.setText("Mobile Number -- "+UMobile);
        userAddress.setText("Address -- "+UAdd);
        userGender.setText("Gender -- "+UGender);


    }
}