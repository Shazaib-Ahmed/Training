package com.example.sampleproject_1.weightTracker


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import com.example.sampleproject_1.weightTracker.DatabaseWT.EntityWeightTracker
import com.example.sampleproject_1.weightTracker.DatabaseWT.ViewModelWeightTracker

private lateinit var kgOption: RadioButton
private lateinit var lbOption: RadioButton

private var radioKG = true
private var radioLB = true

private var weightInitial = 0
private var weightGoal = 0
//private var weightCurrent = 0

private var currentWeightKey = 0
private var goalWeightKey = 0

private lateinit var continueBtnWt: TextView
private lateinit var currentWeightET: EditText
private lateinit var goalWeightET: EditText

private lateinit var viewModelWeightTracker: ViewModelWeightTracker

//private val sharedPreferences: SharedPreferences by inject()
//private val viewModelWR: ViewModelWaterReminder by inject()


private lateinit var sharedPreferences: SharedPreferences


class UserDetailsWeightTracker : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details_weight_tracker)

        initialisationFields()

        viewModelWeightTracker = ViewModelProvider(this).get(ViewModelWeightTracker::class.java)


        kgOption.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, kgIsChecked ->
            saveData("KG_CHECKED", kgIsChecked)
        }
        )

        lbOption.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, lbIsChecked ->
            saveData("LB_CHECKED", lbIsChecked)
        }
        )

        continueBtnWt.setOnClickListener { saveUserInfo() }
        //updateDetails()
    }

    private fun initialisationFields() {
        sharedPreferences = this.getSharedPreferences(AppUtils.USERS_SHARED_PREF, AppUtils.PRIVATE_MODE)
        kgOption = findViewById(R.id.kg_option)
        currentWeightET = findViewById(R.id.current_weight_ET)
        goalWeightET = findViewById(R.id.goal_weight_ET)
        lbOption = findViewById(R.id.lb_option)
        continueBtnWt = findViewById(R.id.bottomContinueButtonWT)

    }

    private fun saveUserInfo() {


        if (currentWeightET.text.toString().isEmpty() || goalWeightET.text.toString().isEmpty()) {
            Toast.makeText(this, "Please enter weight", Toast.LENGTH_SHORT).show()
            return
        } else if (!kgOption.isChecked && !lbOption.isChecked) {
            Toast.makeText(this, "Please select the unit", Toast.LENGTH_SHORT).show()
            return
        }

        weightInitial = currentWeightET.text.toString().toInt()
        weightGoal = goalWeightET.text.toString().toInt()

        if (weightInitial > 200 || weightInitial < 20) {
            Toast.makeText(this, "Please enter valid weight", Toast.LENGTH_SHORT).show()
            return

        } else if (weightGoal > 200 || weightGoal < 20) {
            Toast.makeText(this, "Please enter valid weight", Toast.LENGTH_SHORT).show()
            return

        }


        val data = Intent(this@UserDetailsWeightTracker, HomePageWeightTracker::class.java)
        val editor = sharedPreferences.edit()
        editor.putBoolean(AppUtils.FIRST_RUN_KEY_WEIGHT_TRACKER, false)
        editor.putBoolean("KG_CHECKED", radioKG)
        editor.putBoolean("LB_CHECKED", radioLB)
        editor.putInt(AppUtils.INITIAL_WEIGHT_KEY_WT, weightInitial)
        editor.putInt(AppUtils.FINAL_WEIGHT_KEY_WT, weightGoal)
        var weightCurrent = weightInitial
        editor.putInt("CURRENT_WEIGHT_KEY", weightCurrent)
        editor.apply()
        viewModelWeightTracker.insert(entityWeightTracker = EntityWeightTracker(0, weightInitial, weightGoal, weightCurrent))

        startActivity(data)
        finishAffinity()
    }

    private fun saveData(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    private fun loaData() {
        radioKG = updateRadio("KG_CHECKED")
        radioLB = updateRadio("LB_CHECKED")
    }

    private fun updateView() {
        kgOption.isChecked = radioKG
        lbOption.isChecked = radioLB
        currentWeightKey = sharedPreferences.getInt(AppUtils.INITIAL_WEIGHT_KEY_WT, 0)
        goalWeightKey = sharedPreferences.getInt(AppUtils.FINAL_WEIGHT_KEY_WT, 0)
    }

    private fun updateRadio(key: String):
            Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    private fun updateDetails() {
        val firstRunKey = sharedPreferences.getBoolean(AppUtils.FIRST_RUN_KEY_WEIGHT_TRACKER, true)

        if (!firstRunKey) {

            loaData()
            updateView()

        }
    }

}