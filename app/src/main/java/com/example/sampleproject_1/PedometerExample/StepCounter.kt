package com.example.sampleproject_1.PedometerExample

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.sampleproject_1.R

class StepCounter : AppCompatActivity(), SensorEventListener {

    private var sensorManager: SensorManager? = null

    private var ACT_REC_PERMISSION_CODE = 1

    private var running = false

    private var totalSteps = 0f
    private var previousTotalSteps = 0f

    private lateinit var tv_stepsTaken: TextView
    private lateinit var tv_totalMax: TextView

    private var currentSteps = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step_counter)
        if (ContextCompat.checkSelfPermission(
                        this.applicationContext,
                        android.Manifest.permission.ACTIVITY_RECOGNITION
                ) == PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(
                    this,
                    "You have already granted this permission!",
                    Toast.LENGTH_SHORT
            )
                    .show()
        } else {
            requestStoragePermission()
        }

        tv_stepsTaken = findViewById(R.id.tv_stepsTaken)
        tv_totalMax = findViewById(R.id.tv_totalMax)

        loadData()
        resetSteps()
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

    }

    override fun onResume() {
        super.onResume()
        running = true
        val stepSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (stepSensor == null) {
            Toast.makeText(this, "No sensor detected on this device", Toast.LENGTH_SHORT).show()
        } else {
            sensorManager?.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (running) {
            totalSteps = event!!.values[0]
            currentSteps = totalSteps.toInt() - previousTotalSteps.toInt()

            tv_stepsTaken.text = ("$currentSteps")

        }
    }

    private fun resetSteps() {
        tv_stepsTaken.setOnClickListener {
            Toast.makeText(this, "Long tap to reset steps", Toast.LENGTH_SHORT).show()
        }

        tv_stepsTaken.setOnLongClickListener {

            previousTotalSteps = totalSteps
            tv_stepsTaken.text = 0.toString()
            saveData()
            true
        }
    }

    private fun saveData() {

        val sharedPreferences: SharedPreferences =
                getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putFloat("key1", previousTotalSteps)
        editor.apply()
    }

    private fun loadData() {
        val sharedPreferences: SharedPreferences =
                getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val savedNumber = sharedPreferences.getFloat("key1", 0f)
        Log.e("MAIN", "$savedNumber")
        previousTotalSteps = savedNumber
    }

    private fun requestStoragePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        android.Manifest.permission.ACTIVITY_RECOGNITION
                )
        ) {
            AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission needed because of . . . .  ")
                    .setPositiveButton("ok") { dialog, which ->
                        ActivityCompat.requestPermissions(
                                this@StepCounter,
                                arrayOf(android.Manifest.permission.ACTIVITY_RECOGNITION),
                                ACT_REC_PERMISSION_CODE
                        )
                    }
                    .setNegativeButton("cancel") { dialog, which -> dialog.dismiss() }.create().show()
        } else {
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.ACTIVITY_RECOGNITION),
                    ACT_REC_PERMISSION_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray
    ) {
        if (requestCode == ACT_REC_PERMISSION_CODE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show()
            }
        }
    }

}