package com.example.sampleproject_1.weightTracker


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Database.ViewModelWaterReminder
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import com.example.sampleproject_1.weightTracker.DatabaseWT.EntityWeightTracker
import com.example.sampleproject_1.weightTracker.DatabaseWT.ViewModelWeightTracker
import org.koin.android.ext.android.inject

private lateinit var radioGroup: RadioGroup
private lateinit var kgOption: RadioButton
private lateinit var lbOption: RadioButton

private var radioKG = true
private var radioLB = true

private var kkkk = false
private var lllll = false

private var weightInitial = 0
private var weightGoal = 0

private var currentWeightKey = 0
private var goalWeightKey = 0

private lateinit var continueBtnWt: TextView
private lateinit var currentWeightET: EditText
private lateinit var goalWeightET: EditText

private lateinit var viewModelWeightTracker: ViewModelWeightTracker

class UserDetailsWeightTracker : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by inject()

    //private val viewModelWeightTracker: ViewModelWeightTracker by inject()
    private val firstRunKey =
        sharedPreferences.getBoolean(AppUtils.FIRST_RUN_KEY_WEIGHT_TRACKER, true)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details_weight_tracker)

        initialisationFields()

        viewModelWeightTracker = ViewModelProvider(this).get(ViewModelWeightTracker::class.java)


        if (firstRunKey) {
            kgOption.isChecked = true

            if (kgOption.isChecked) {
                //saveRadioData("KG_CHECKED", true)
                kkkk = true

            } else if (lbOption.isChecked) {
                //saveRadioData("LB_CHECKED", true)

                lllll = true
            }

            lbOption.setOnCheckedChangeListener { _, lbIsChecked ->

                saveRadioData("LB_CHECKED", lbIsChecked)
                lllll = lbIsChecked

            }

            kgOption.setOnCheckedChangeListener { _, kgIsChecked ->

                saveRadioData("KG_CHECKED", kgIsChecked)

                kkkk = kgIsChecked
            }

        }


        if (!firstRunKey) {

/*
            radioGroup.setOnCheckedChangeListener { group, checkedId ->

                if (checkedId == R.id.kg_option){

                    kkkk = true
                    Toast.makeText(this, "Converted to kg checkedListener", Toast.LENGTH_SHORT).show()
                }

                if (checkedId == R.id.lb_option){
                    Toast.makeText(this, "Converted to lb checkedListener", Toast.LENGTH_SHORT).show()

                    lllll = true
                }

            }
*/
            kgOption.setOnCheckedChangeListener { _, kgIsChecked ->
                kkkk = kgIsChecked
            }

            lbOption.setOnCheckedChangeListener { _, lbIsChecked ->
                lllll = lbIsChecked
            }


            /*  kgOption.setOnClickListener {
                  val weightInitial1 = currentWeightET.text.toString().toInt()
                  val weightGoal1 = goalWeightET.text.toString().toInt()

                  AppUtils.covertToKg(weightInitial1)
                  AppUtils.covertToKg(weightGoal1)

                  currentWeightET.setText("$weightInitial1")
                  goalWeightET.setText("$weightGoal1")
              }

              lbOption.setOnClickListener {
                  val weightInitial1 = currentWeightET.text.toString().toInt()
                  val weightGoal1 = goalWeightET.text.toString().toInt()

                  AppUtils.covertToLb(weightInitial1)
                  AppUtils.covertToLb(weightGoal1)
                  Toast.makeText(this, "Converted to lb", Toast.LENGTH_SHORT).show()
                  currentWeightET.setText("$weightInitial1")
                  goalWeightET.setText("$weightGoal1")

              }*/


        }


/*
        if (!firstRunKey){

            if (kgCheckedd || lbCheckedd)

            {
                if (kgCheckedd){
                    lbOption.setOnClickListener {
                        val weightInitial1 = currentWeightET.text.toString().toInt()
                        val weightGoal1 = goalWeightET.text.toString().toInt()

                        AppUtils.covertToKg(weightInitial1)
                        AppUtils.covertToKg(weightGoal1)
                        Toast.makeText(this, "Converted to lb", Toast.LENGTH_SHORT).show()
                        currentWeightET.setText("$weightInitial1")
                        goalWeightET.setText("$weightGoal1")

                    }

                }
                else if (lbCheckedd){
                    kgOption.setOnClickListener {
                        val weightInitial1 = currentWeightET.text.toString().toInt()
                        val weightGoal1 = goalWeightET.text.toString().toInt()

                        AppUtils.covertToLb(weightInitial1)
                        AppUtils.covertToLb(weightGoal1)

                        currentWeightET.setText("$weightInitial1")
                        goalWeightET.setText("$weightGoal1")
                    }

                }
            }

        */
/*     lbOption.setOnCheckedChangeListener { buttonView, isChecked ->
                 Toast.makeText(this, "Converted to lb", Toast.LENGTH_SHORT).show()
             }

            kgOption.setOnCheckedChangeListener { buttonView, isChecked ->
                Toast.makeText(this, "Converted to kg", Toast.LENGTH_SHORT).show()
            }

            lbOption.setOnClickListener {
                val weightInitial1 = currentWeightET.text.toString().toInt()
                val weightGoal1 = goalWeightET.text.toString().toInt()

                AppUtils.covertToKg(weightInitial1)
                AppUtils.covertToKg(weightGoal1)

                currentWeightET.setText("$weightInitial1")
                goalWeightET.setText("$weightGoal1")

            }

                kgOption.setOnClickListener {
                    val weightInitial1 = currentWeightET.text.toString().toInt()
                    val weightGoal1 = goalWeightET.text.toString().toInt()

                    AppUtils.covertToLb(weightInitial1)
                    AppUtils.covertToLb(weightGoal1)

                    currentWeightET.setText("$weightInitial1")
                    goalWeightET.setText("$weightGoal1")
                }*//*

        }
*/

        kgOption.setOnClickListener {

            currentWeightET.hint = "Enter Weight in Kg"
            goalWeightET.hint = "Enter Weight in Kg"
        }

        lbOption.setOnClickListener {

            currentWeightET.hint = "Enter Weight in Lb"
            goalWeightET.hint = "Enter Weight in Lb"
        }


        continueBtnWt.setOnClickListener { saveUserInfo() }
        updateDetails()
    }

    private fun initialisationFields() {
        kgOption = findViewById(R.id.kg_option)
        currentWeightET = findViewById(R.id.current_weight_ET)
        goalWeightET = findViewById(R.id.goal_weight_ET)
        lbOption = findViewById(R.id.lb_option)
        continueBtnWt = findViewById(R.id.bottomContinueButtonWT)

        radioGroup = findViewById(R.id.radioGroup)

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

        if (kgOption.isChecked) {

            if (weightInitial > 200 || weightInitial < 20) {
                Toast.makeText(this, "Please enter valid weight", Toast.LENGTH_SHORT).show()
                return

            } else if (weightGoal > 200 || weightGoal < 20) {
                Toast.makeText(this, "Please enter valid weight", Toast.LENGTH_SHORT).show()
                return

            }
        } else if (lbOption.isChecked) {


            if (weightInitial > 440 || weightInitial < 44) {
                Toast.makeText(this, "Please enter valid weight", Toast.LENGTH_SHORT).show()
                return

            } else if (weightGoal > 440 || weightGoal < 44) {
                Toast.makeText(this, "Please enter valid weight", Toast.LENGTH_SHORT).show()
                return

            }

        }

        /*if (weightInitial > 200 || weightInitial < 20) {
            Toast.makeText(this, "Please enter valid weight", Toast.LENGTH_SHORT).show()
            return

        } else if (weightGoal > 200 || weightGoal < 20) {
            Toast.makeText(this, "Please enter valid weight", Toast.LENGTH_SHORT).show()
            return

        }*/

        saveRadioData("KG_CHECKED", kkkk)
        saveRadioData("LB_CHECKED", lllll)

        val data = Intent(this@UserDetailsWeightTracker, HomePageWeightTracker::class.java)
        val editor = sharedPreferences.edit()
        editor.putBoolean(AppUtils.FIRST_RUN_KEY_WEIGHT_TRACKER, false)
        editor.putInt(AppUtils.INITIAL_WEIGHT_KEY_WT, weightInitial)
        editor.putInt(AppUtils.FINAL_WEIGHT_KEY_WT, weightGoal)

        val weightCurrent = weightInitial
        editor.putInt("CURRENT_WEIGHT_KEY", weightCurrent)
        editor.apply()
        //viewModelWeightTracker.insert(entityWeightTracker = EntityWeightTracker(0, weightInitial, weightGoal, weightCurrent))

        startActivity(data)
        finishAffinity()
    }

    private fun saveRadioData(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    private fun loaData() {
        radioKG = updateRadio("KG_CHECKED")
        radioLB = updateRadio("LB_CHECKED")
        currentWeightKey = sharedPreferences.getInt(AppUtils.INITIAL_WEIGHT_KEY_WT, 0)
        goalWeightKey = sharedPreferences.getInt(AppUtils.FINAL_WEIGHT_KEY_WT, 0)
    }

    private fun updateView() {
        kgOption.isChecked = radioKG
        lbOption.isChecked = radioLB
        currentWeightET.setText("$currentWeightKey")
        goalWeightET.setText("$goalWeightKey")
    }

    private fun updateRadio(key: String):
            Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    private fun updateDetails() {


        if (!firstRunKey) {

            loaData()
            updateView()

        }
    }

}