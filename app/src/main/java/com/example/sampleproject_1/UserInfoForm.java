package com.example.sampleproject_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class UserInfoForm extends AppCompatActivity {
    EditText firstName,lastName,age,mobileNumber,address,gender;
    Button submitInfo;
    String SHARED_PREFS ="sharedPrefs";
    String FIRST_NAME ="firstName";
    String LAST_NAME ="lastNAme";
    String U_AGE ="userAge";
    String MOBILE_NUMBER ="mobilNumber";
    String ADDRESS ="ADDRESS";
    String GENDER ="gender";

    String FN,LN,AGE,MOB,ADD,GEN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_form);
        setTitle("Info Form");

        firstName=findViewById(R.id.first_name);
        lastName=findViewById(R.id.last_name);
        age=findViewById(R.id.age);
        mobileNumber=findViewById(R.id.mobile_number);
        address=findViewById(R.id.address);
        gender=findViewById(R.id.gender);
        submitInfo=findViewById(R.id.submit_info);


        submitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(firstName.getText().toString()))
                {
                    Toast.makeText(UserInfoForm.this, "Please Enter First Name", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(lastName.getText().toString()))
                {
                    Toast.makeText(UserInfoForm.this, "Please Enter Last Name", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(age.getText().toString()))
                {
                    Toast.makeText(UserInfoForm.this, "Please Enter Age", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(mobileNumber.getText().toString()))
                {
                    Toast.makeText(UserInfoForm.this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(address.getText().toString()))
                {
                    Toast.makeText(UserInfoForm.this, "Please Enter Address", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(gender.getText().toString()))
                {
                    Toast.makeText(UserInfoForm.this, "Please Enter Gender", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String FirstN = firstName.getText().toString();
                    String LastN = lastName.getText().toString();
                    String Age = age.getText().toString();
                    String MobileN = mobileNumber.getText().toString();
                    String Add = address.getText().toString();
                    String Gender = gender.getText().toString();

                    Intent intent =new Intent(UserInfoForm.this,ShowUserInfo.class);
                    intent.putExtra("keyFirstName",FirstN);
                    intent.putExtra("keyLastName",LastN);
                    intent.putExtra("keyAddress",Add);
                    intent.putExtra("keyAge",Age);
                    intent.putExtra("keyMobileN",MobileN);
                    intent.putExtra("keyGender",Gender);
                    startActivity(intent);

                    saveData();

                    firstName.setText("");
                    lastName.setText("");
                    age.setText("");
                    mobileNumber.setText("");
                    address.setText("");
                    gender.setText("");
                }
            }



        });

        loadData();

        updateViews();

    }
    public void saveData()
    {

        SharedPreferences sharedPreferences =getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString(FIRST_NAME,firstName.getText().toString());
        editor.putString(LAST_NAME,lastName.getText().toString());
        editor.putString(U_AGE,age.getText().toString());
        editor.putString(MOBILE_NUMBER,mobileNumber.getText().toString());
        editor.putString(ADDRESS,address.getText().toString());
        editor.putString(GENDER,gender.getText().toString());
        editor.apply();
        Toast.makeText(this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();
    }
    public void loadData()
    {
        SharedPreferences sharedPreferences =getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        FN =sharedPreferences.getString(FIRST_NAME,"");
        LN =sharedPreferences.getString(LAST_NAME,"");
        AGE =sharedPreferences.getString(U_AGE,"");
        MOB =sharedPreferences.getString(MOBILE_NUMBER,"");
        ADD =sharedPreferences.getString(ADDRESS,"");
        GEN =sharedPreferences.getString(GENDER,"");
    }
    public void updateViews()
    {
        firstName.setText(FN);
        lastName.setText(LN);
        age.setText(AGE);
        mobileNumber.setText(MOB);
        address.setText(ADD);
        gender.setText(GEN);
        
    }
}