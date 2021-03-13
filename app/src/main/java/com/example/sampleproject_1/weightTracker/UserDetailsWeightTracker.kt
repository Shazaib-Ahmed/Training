package com.example.sampleproject_1.weightTracker


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleproject_1.R
import org.koin.android.ext.android.inject

private lateinit var kgOption: RadioButton
private lateinit var lbOption: RadioButton

private lateinit var continueBtnWt: TextView




class UserDetailsWeightTracker : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details_weight_tracker)

        initialisationFields()

         val sharedPreferences: SharedPreferences by inject()


        kgOption.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, maleIsChecked ->

            Toast.makeText(this, "KG option selected $maleIsChecked", Toast.LENGTH_SHORT).show()
        }
        )

        lbOption.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, femaleIsChecked ->
            Toast.makeText(this, "LB option selected $femaleIsChecked", Toast.LENGTH_SHORT).show()
        }
        )

        continueBtnWt.setOnClickListener { saveUserInfo() }

    }

    private fun initialisationFields() {
        kgOption = findViewById(R.id.kg_option)
        lbOption = findViewById(R.id.lb_option)
        continueBtnWt = findViewById(R.id.bottomContinueButtonWT)

    }

    private fun saveUserInfo() {
        val data = Intent(this@UserDetailsWeightTracker, HomePageWeightTracker::class.java)
        startActivity(data)
        finishAffinity()
    }
}