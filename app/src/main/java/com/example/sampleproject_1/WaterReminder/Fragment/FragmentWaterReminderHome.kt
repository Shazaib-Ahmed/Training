package com.example.sampleproject_1.WaterReminder.Fragment

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Adapter.TodayWaterIntakeAdapter
import com.example.sampleproject_1.WaterReminder.Database.EntityWaterReminder
import com.example.sampleproject_1.WaterReminder.Database.ViewModelWaterReminder
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import com.example.sampleproject_1.WaterReminder.model.WaterIntake
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*

class FragmentWaterReminderHome : Fragment() {
    private var userWeight: TextView? = null
    private var totalIntakeTV: TextView? = null
    private var addWater: TextView? = null
    private lateinit var remainingWater: TextView

    // private var viewModelWaterReminder: ViewModelWaterReminder? = null
    //private lateinit var sharedPreferences: SharedPreferences

    private var entityWaterReminder: EntityWaterReminder? = null
    private var progress = 0
    private var inTook = 0
    private var totalIntake = 0
    var totalInTook = 0

    private var timeIntakeWaterListRV: RecyclerView? = null
    private var saveDailyData: ArrayList<WaterIntake>? = null
    private var adapterTime: TodayWaterIntakeAdapter? = null

    private val viewModelWR: ViewModelWaterReminder by inject()
    private val sharedPreferences: SharedPreferences by inject()

    //var context: Context3

    //private lateinit var waveLoadingView: WaveLoadingView
    private val calendar = Calendar.getInstance()
    private val simpleDateFormat = SimpleDateFormat("hh:mm:ss a")
    private val currentTime = simpleDateFormat.format(calendar.time)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // sharedPreferences = this.activity!!.getSharedPreferences(AppUtils.USERS_SHARED_PREF, AppUtils.PRIVATE_MODE)

        val v = inflater.inflate(R.layout.fragment_water_reminder_home, container, false)
        userWeight = v.findViewById(R.id.userWeightTextView)
        totalIntakeTV = v.findViewById(R.id.totalIntakeTV)
        //waveLoadingView = v.findViewById(R.id.progress_bar)

        // addWater = v.findViewById(R.id.add_water)

        addWater = v.findViewById(R.id.add_water) as TextView
        remainingWater = v.findViewById(R.id.remainingWater)
        timeIntakeWaterListRV = v.findViewById(R.id.time_intake_water_list)

        //viewModelWaterReminder = ViewModelProvider(this).get(ViewModelWaterReminder::class.java)

        val w = sharedPreferences.getInt(AppUtils.WEIGHT_KEY, 0)
        val g = sharedPreferences.getString(AppUtils.GENDER_KEY, "")
        totalIntake = sharedPreferences.getInt(AppUtils.TOTAL_INTAKE, 0)
        val wakeTime = sharedPreferences.getString(AppUtils.WAKE_UP_TIME_KEY, "")
        //context = v.context.applicationContext
        remainingWater.text = "Remaining Water"
        loadData()
        buildRecyclerView()
        progress = sharedPreferences.getInt("PRO", 0)
        totalInTook = sharedPreferences.getInt("TOI", 0)
        //waveLoadingView.progressValue = progress
      //  waveLoadingView.centerTitle = "$progress %"
        remainingWater.text = "$totalInTook/$totalIntake ml"

        addWater!!.setOnClickListener(View.OnClickListener {
            inTook = 10 * totalIntake / 100
            totalInTook += inTook
            if (totalInTook < totalIntake && progress < 100) {
                setWaterLevel(inTook, totalIntake)
                updateTimeChart()
                saveData()
            } else {
                Toast.makeText(
                    requireContext().applicationContext,
                    "You are done for the day",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })


/*
        addWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog =new BottomSheetDialog();
                bottomSheetDialog.show(getChildFragmentManager(),"ModalBottomSheet");
            }
        });
*/

        userWeight!!.text = "$w kg"
        var totalIntakeInLitreTV = (totalIntake / 1000).toString()
        totalIntakeTV!!.text = "$totalIntakeInLitreTV L"
        return v
    }

    private fun updateTimeChart() {
        adapterTime!!.updateData(WaterIntake(currentTime, inTook))
        adapterTime!!.notifyItemInserted(saveDailyData!!.size)
        // entityWaterReminder = EntityWaterReminder(0,currentTime, inTook, totalIntake, totalInTook)
        // viewModelWaterReminder?.insert(entityWaterReminder!!)
        viewModelWR.insert(
            entityWaterReminder = EntityWaterReminder(
                0,
                currentTime,
                inTook,
                totalIntake,
                totalInTook
            )
        )

    }

    private fun setWaterLevel(inTook: Int, totalIntake: Int) {
        if (inTook * 100 / totalIntake > 140) {
            Toast.makeText(
                requireContext().applicationContext,
                "You are done for the day",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            progress += inTook * 100 / totalIntake
           // waveLoadingView!!.progressValue = progress
           // waveLoadingView!!.centerTitle = "$progress %"
            val editor = sharedPreferences!!.edit()
            editor.putInt("PRO", progress)
            editor.putInt("TOI", totalInTook)
            editor.apply()
        }
        remainingWater!!.text = "$totalInTook/$totalIntake ml"
    }

    @SuppressLint("WrongConstant")
    private fun saveData() {
        val sharedPreferences =
            this.requireActivity().getSharedPreferences(AppUtils.USERS_SHARED_PREF, AppUtils.PRIVATE_MODE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val jsonData = gson.toJson(saveDailyData)
        editor.putString("myJson", jsonData)
        editor.apply()
    }

    @SuppressLint("WrongConstant")
    private fun loadData() {
        val sharedPreferences =
            this.requireActivity().getSharedPreferences(AppUtils.USERS_SHARED_PREF, AppUtils.PRIVATE_MODE)
        val gson = Gson()
        val jsonData = sharedPreferences.getString("myJson", null)
        val type = object : TypeToken<ArrayList<WaterIntake?>?>() {}.type
        saveDailyData = gson.fromJson<ArrayList<WaterIntake>>(jsonData, type)
        if (saveDailyData == null) {
            saveDailyData = ArrayList()
        }
    }

    private fun buildRecyclerView() {
        timeIntakeWaterListRV!!.setHasFixedSize(true)
        timeIntakeWaterListRV!!.layoutManager =
            LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)
        adapterTime = TodayWaterIntakeAdapter(saveDailyData!!)
        timeIntakeWaterListRV!!.adapter = adapterTime
    }

}