package com.example.sampleproject_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserInfoForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_form);
        setTitle("Info Form");
        EditText firstName,lastName,age,mobileNumber,address,gender;
        Button submitInfo;

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




                    firstName.setText("");
                    lastName.setText("");
                    age.setText("");
                    mobileNumber.setText("");
                    address.setText("");
                    gender.setText("");
                }
            }
        });

    }
}