package com.example.sampleproject_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddUserInfo extends AppCompatActivity {
    public static final String EXTRA_NAME = "com.example.sampleproject_1.EXTRA_NAME";
    public static final String EXTRA_AGE = "com.example.sampleproject_1.EXTRA_AGE";
    public static final String EXTRA_EMAIL = "com.example.sampleproject_1.EXTRA_EMAIL";
    public static final String EXTRA_MOBILE = "com.example.sampleproject_1.EXTRA_MOBILE";

    EditText editTextName, editTextAge, editTextEmail, editTextMobileNumber;
    TextView editTextId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_info);
        editTextName = findViewById(R.id.editText_name);
        editTextAge = findViewById(R.id.editText_age);
        editTextEmail = findViewById(R.id.editText_email);
        editTextMobileNumber = findViewById(R.id.editText_mobileNumber);
        editTextId = findViewById(R.id.editText_Id);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add User Info");
    }
    private void saveUserInfo(){
        String name = editTextName.getText().toString();
        String age =editTextAge.getText().toString();
        String email =editTextEmail.getText().toString();
        String mobileNumber =editTextMobileNumber.getText().toString();

        if (name.trim().isEmpty() || age.trim().isEmpty() || email.trim().isEmpty()|| mobileNumber.trim().isEmpty() ){
            Toast.makeText(this, "Please fill all information", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data =new Intent();
        data.putExtra(EXTRA_NAME,name);
        data.putExtra(EXTRA_AGE,age);
        data.putExtra(EXTRA_EMAIL,email);
        data.putExtra(EXTRA_MOBILE,mobileNumber);

        setResult(RESULT_OK,data);
        finish();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_user_info_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_userInfo:
                saveUserInfo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}