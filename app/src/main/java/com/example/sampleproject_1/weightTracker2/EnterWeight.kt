package com.example.sampleproject_1.weightTracker2

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import org.koin.android.ext.android.inject
import kotlin.math.log

class EnterWeight : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by inject()
    private lateinit var nextBtn: TextView

    private val enterWeight = 80

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_weight)

        nextBtn = findViewById(R.id.next_btn_enter_weight_wt2)
        nextBtn.setOnClickListener {
            val intent = Intent(applicationContext, GoalWeight::class.java)
            // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt(AppUtils.ENTER_WEIGHT_KEY_WT2, enterWeight)
            editor.apply()

            startActivity(intent)
            //finishAffinity()
        }

    }
}