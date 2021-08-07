package com.example.sampleproject_1.weightTracker2


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.NotificationWaterReminder.AutoStartHelper
import com.example.sampleproject_1.weightTracker2.adapter.Quotes
import com.example.sampleproject_1.weightTracker2.adapter.QuotesAdapter
import com.github.islamkhsh.CardSliderViewPager
import org.koin.android.ext.android.inject


class IntroSliderWeightTracker2 : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by inject()


    private lateinit var startBtn: TextView

    private lateinit var sliderViewPager: CardSliderViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_slider_weight_tracker2)
        autoStartNotification()

        startBtn = findViewById(R.id.start_btn_wt2)
        sliderViewPager =  findViewById(R.id.viewPagerIntro)

        val quotes = arrayListOf<Quotes>()
        val posters = resources.obtainTypedArray(R.array.images)
        for (i in resources.getStringArray(R.array.quotes).indices) {
            quotes.add(
                    Quotes(
                            posters.getResourceId(i, -1),
                            resources.getStringArray(R.array.quotes)[i],

                            )
            )
        }
        posters.recycle()
        sliderViewPager.adapter = QuotesAdapter(quotes)

        startBtn.setOnClickListener {
            val intent = Intent(applicationContext, EnterWeight::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finishAffinity()
        }
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


