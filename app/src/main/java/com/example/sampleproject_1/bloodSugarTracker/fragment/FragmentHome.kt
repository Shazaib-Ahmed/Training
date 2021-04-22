package com.example.sampleproject_1.bloodSugarTracker.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.sampleproject_1.R

class FragmentHome : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_blood, container, false)
        val statisticsFragBtn: ImageView = view.findViewById(R.id.statistics_iv_btn)

        val averageFrag: LinearLayout = view.findViewById(R.id.average_linear_layout_bst)

        statisticsFragBtn.setOnClickListener {

            statisticsFragmentView()
        }

        averageFrag.setOnClickListener {

            averageFragmentView()
        }

        return view
    }


    private fun statisticsFragmentView() {
        val fragmentStatistics = FragmentStatistics()
        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.blood_frame_layout, fragmentStatistics)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun averageFragmentView() {
        val fragmentAverage = FragmentAverage()
        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.blood_frame_layout, fragmentAverage)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}