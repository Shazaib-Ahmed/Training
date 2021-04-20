package com.example.sampleproject_1.bloodSugarTracker.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.sampleproject_1.R

class FragmentStatistics : Fragment() {

    private var bloodSpinner: Spinner? = null
    private var bloodSpinnerFilter: Spinner? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_statistics, container, false)

        bloodSpinner = view.findViewById(R.id.blood_spinner)
        bloodSpinnerFilter = view.findViewById(R.id.blood_spinner_filter)

        setUpSpinner()
        setUpSpinnerFilter()
                return view
    }

    private fun setUpSpinner() {
        val sortBySpinnerData = resources.getStringArray(R.array.sortByspinner)
        val ad = ArrayAdapter(context!!.applicationContext, android.R.layout.simple_dropdown_item_1line, sortBySpinnerData)
        bloodSpinner!!.adapter = ad
    }

    private fun setUpSpinnerFilter() {
        val sortBySpinnerData = resources.getStringArray(R.array.sortByspinnerFilter)
        val ad = ArrayAdapter(context!!.applicationContext, android.R.layout.simple_dropdown_item_1line, sortBySpinnerData)
        bloodSpinnerFilter!!.adapter = ad
    }
}