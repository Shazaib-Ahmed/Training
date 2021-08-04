package com.example.sampleproject_1.weightTracker2

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.aigestudio.wheelpicker.WheelPicker
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import org.koin.android.ext.android.inject
import kotlin.math.log

class EnterWeight : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by inject()
    private lateinit var nextBtn: TextView


    private var enterWeight = 25
    private var currentWeight = 0

    private var enterWeightPosition = 0

    private lateinit var enterWeightPicker: WheelPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_weight)

        enterWeightPicker = findViewById(R.id.enter_weight_picker)


        nextBtn = findViewById(R.id.next_btn_enter_weight_wt2)

        val frk = sharedPreferences.getBoolean(AppUtils.FIRST_RUN_KEY_WEIGHT_TRACKER_2,true)
      //  enterWeight = sharedPreferences.getInt(AppUtils.ENTER_WEIGHT_KEY_WT2,0)

        if (!frk){
          //  enterWeight = sharedPreferences.getInt(AppUtils.ENTER_WEIGHT_KEY_WT2,0)
            enterWeightPosition = sharedPreferences.getInt("ENTER_WEIGHT_KEY_POSITION",0)
            enterWeightPicker.selectedItemPosition = enterWeightPosition

        }



        enterWeightPicker.setOnItemSelectedListener { picker, data, position ->
            var d = data.toString()
            enterWeight = d.toInt()
            currentWeight =d.toInt()

            enterWeightPosition = position
        }




        nextBtn.setOnClickListener {
            val intent = Intent(applicationContext, GoalWeight::class.java)
            // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt(AppUtils.ENTER_WEIGHT_KEY_WT2, enterWeight)
            editor.putInt(AppUtils.CURRENT_WEIGHT_KEY_WT2, currentWeight)
            editor.putInt("ENTER_WEIGHT_KEY_POSITION", enterWeightPosition)
            editor.apply()

            startActivity(intent)
            //finishAffinity()
        }

    }
}