package com.example.sampleproject_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;



public class AddEditUserInfo extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.sampleproject_1.EXTRA_ID";
    public static final String EXTRA_NAME = "com.example.sampleproject_1.EXTRA_NAME";
    public static final String EXTRA_AGE = "com.example.sampleproject_1.EXTRA_AGE";
    public static final String EXTRA_EMAIL = "com.example.sampleproject_1.EXTRA_EMAIL";
    public static final String EXTRA_MOBILE = "com.example.sampleproject_1.EXTRA_MOBILE";
    //public static final Pattern AGE = Pattern.compile("[1-9]{2}");


    EditText editTextName, editTextAge, editTextEmail, editTextMobileNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_info);
        editTextName = findViewById(R.id.editText_name);
        editTextAge = findViewById(R.id.editText_age);
        editTextEmail = findViewById(R.id.editText_email);
        editTextMobileNumber = findViewById(R.id.editText_mobileNumber);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            editTextName.setText(intent.getStringExtra(EXTRA_NAME));
            editTextAge.setText("" + intent.getIntExtra(EXTRA_AGE, 0));
            editTextEmail.setText(intent.getStringExtra(EXTRA_EMAIL));
            editTextMobileNumber.setText(intent.getStringExtra(EXTRA_MOBILE));

        } else {

            setTitle("Add User Info");
        }

    }

    private void saveUserInfo() {
        String name = editTextName.getText().toString();
        String age = editTextAge.getText().toString();
        String email = editTextEmail.getText().toString();
        String mobileNumber = editTextMobileNumber.getText().toString();

        if (name.trim().isEmpty() || age.trim().isEmpty() || email.trim().isEmpty() || mobileNumber.trim().isEmpty()) {
            Toast.makeText(this, "Please fill all information", Toast.LENGTH_SHORT).show();
            return;
        } else if (editTextAge.getText().toString().equals("0") || editTextAge.getText().toString().equals("00")) {
            editTextAge.setError("Please enter a valid age");
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email address");
            return;
        } else if (!Patterns.PHONE.matcher(mobileNumber).matches() || editTextMobileNumber.getText().toString().length() < 10 || editTextMobileNumber.getText().toString().length() > 10) {
            editTextMobileNumber.setError("Please enter a valid mobile number");
            return;

        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_AGE, age);
        data.putExtra(EXTRA_EMAIL, email);
        data.putExtra(EXTRA_MOBILE, mobileNumber);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }


        setResult(RESULT_OK, data);
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