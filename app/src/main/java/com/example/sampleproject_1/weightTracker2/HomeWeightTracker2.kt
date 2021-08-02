package com.example.sampleproject_1.weightTracker2


import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import com.example.sampleproject_1.weightTracker2.adapter.QuotesAdapter
import com.example.sampleproject_1.weightTracker2.databaseWt2.ViewModelWeightTracker2
import org.koin.android.ext.android.inject
import java.util.ArrayList

class HomeWeightTracker2 : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by inject()
    private lateinit var viewModelWeightTracker2: ViewModelWeightTracker2

    private val mNames: ArrayList<String> = ArrayList()
    private val mImageUrls: ArrayList<String> = ArrayList()

    private lateinit var spinnerWeightTracker2: Spinner
    private lateinit var editWeightBtn: RelativeLayout

    private lateinit var enterWeightTv: TextView
    private lateinit var goalWeightTv: TextView
    private lateinit var currentWeightTv: TextView
    private lateinit var droppedWeightTv: TextView


    private var enterWeight = 0
    private var goalWeight = 0
    private var currentWeight = 0
    private var droppedWeight = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_weight_tracker2)
     //  viewModelWeightTracker2 = ViewModelProvider(this).get(ViewModelWeightTracker2::class.java)
        images

        initialisingFields()
        setUpSpinner()



        enterWeight = sharedPreferences.getInt(AppUtils.ENTER_WEIGHT_KEY_WT2, 0)
        goalWeight = sharedPreferences.getInt(AppUtils.GOAL_WEIGHT_KEY_WT2, 0)

        currentWeight = enterWeight
        droppedWeight = enterWeight - currentWeight

        editWeightBtn.setOnClickListener {
            val intent = Intent(applicationContext, HeadingWeight::class.java)
            startActivity(intent)
        }

        enterWeightTv.text = enterWeight.toString()
        goalWeightTv.text = goalWeight.toString()
        currentWeightTv.text = "$currentWeight kg"
        droppedWeightTv.text = "$droppedWeight kg"

    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val recyclerView = findViewById<RecyclerView>(R.id.home_recycler_view)
        recyclerView.layoutManager = layoutManager
        val adapter = QuotesAdapter(this, mNames, mImageUrls)
        recyclerView.adapter = adapter

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
    }

    companion object {
        private const val TAG = "MainActivity"
    }

    private fun setUpSpinner() {
        val sortBySpinnerData = resources.getStringArray(R.array.sortByspinner)
        /*  val ad = ArrayAdapter(this.applicationContext, android.R.layout.simple_dropdown_item_1line, sortBySpinnerData)
          spinnerWeightTracker2.adapter = ad*/

        val ad = ArrayAdapter.createFromResource(
            this, R.array.sortByspinner, R.layout.spinner_layout
        )
        ad.setDropDownViewResource(R.layout.spinner_layout)
        spinnerWeightTracker2.adapter = ad
    }

    private val images: Unit
        get() {
            mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg")
            mNames.add("“To change your body you must first change your mind.”")
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

    private fun initialisingFields() {

        enterWeightTv = findViewById(R.id.enter_weight_tv)
        goalWeightTv = findViewById(R.id.goal_weight_tv)
        currentWeightTv = findViewById(R.id.current_weight_tv)
        droppedWeightTv = findViewById(R.id.dropped_weight_tv)
        spinnerWeightTracker2 = findViewById(R.id.spinner_wt2)
        editWeightBtn = findViewById(R.id.edit_weight_rl)


    }


}


