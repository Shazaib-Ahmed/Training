package com.example.sampleproject_1.weightTracker2


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.NotificationWaterReminder.AutoStartHelper
import com.example.sampleproject_1.weightTracker.HomePageWeightTracker
import com.example.sampleproject_1.weightTracker2.adapter.QuotesAdapter
import org.koin.android.ext.android.inject
import java.util.*


class IntroSliderWeightTracker2 : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by inject()

    private val mNames: ArrayList<String> = ArrayList()
    private val mImageUrls: ArrayList<String> = ArrayList()
    private lateinit var  startBtn:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_slider_weight_tracker2)
        autoStartNotification()
        images
        startBtn = findViewById(R.id.start_btn_wt2)
        startBtn.setOnClickListener{
            val intent = Intent(applicationContext, EnterWeight::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finishAffinity()
        }
    }

    private val images: Unit
        get() {
            mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg")
            mNames.add("“To change your body you must  first change your mind.”")
            mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg")
            mNames.add("“The only bad workout is the one that didn’t happen.”")
            mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg")
            mNames.add("“I don’t stop when I’m tired,I stop when I’m DONE!”")
            mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg")
            mNames.add("“Happiness is the highest form of health.”")
            mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg")
            mNames.add("“Three months from now, you will thanks yourself”")


            initRecyclerView()
        }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val recyclerView = findViewById<RecyclerView>(R.id.welcome_recycler_view)
        recyclerView.layoutManager = layoutManager
        val adapter = QuotesAdapter(this, mNames, mImageUrls)
        recyclerView.adapter = adapter

        val snapHelper:SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
    }

    companion object {
        private const val TAG = "MainActivity"
    }

    private fun autoStartNotification() {
        val autoStart = sharedPreferences.getString("autoStart", "")
        if (autoStart == "") {
            AutoStartHelper.instance.getAutoStartPermission(this)
        }
    }
}


