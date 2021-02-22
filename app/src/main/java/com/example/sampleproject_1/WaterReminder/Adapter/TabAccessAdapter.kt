package com.example.sampleproject_1.WaterReminder.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.sampleproject_1.WaterReminder.Fragment.FragmentWaterReminderHistory
import com.example.sampleproject_1.WaterReminder.Fragment.FragmentWaterReminderHome
import java.lang.IllegalStateException

class TabAccessAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FragmentWaterReminderHome()
            }
            1 -> {
                FragmentWaterReminderHistory()
            }

            else -> {
                throw IllegalStateException("$position is illegal")
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Home"
            1 -> "History"
            else -> null
        }
    }
}