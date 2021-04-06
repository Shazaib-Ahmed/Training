package com.example.sampleproject_1.sleepTracker.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleproject_1.R
import java.util.*

class TotalSleepAdapter
(private val data: ArrayList<TotalSleep>) : RecyclerView.Adapter<TotalSleepAdapter.TotalSleepViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TotalSleepViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.item_sleep_tracker, parent, false)
        return TotalSleepViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: TotalSleepViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(s: TotalSleep) {
        data.add(s)
        notifyDataSetChanged()
    }

    class TotalSleepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val totalTime: TextView = itemView.findViewById(R.id.totalTimeTV)
        private val todayDate: TextView = itemView.findViewById(R.id.today_date)
        private val sleepRating: TextView = itemView.findViewById(R.id.ratingTV)

        @SuppressLint("SetTextI18n")
        fun bindData(s: TotalSleep) {
            totalTime.text ="Total Sleep - " + s.totalUserTime
            todayDate.text ="Date - " + s.date
            sleepRating.text ="Sleep rating - " + s.rating+ " Star"

        }

    }
    class TotalSleep(val totalUserTime: String , val date: String, val rating:String)
}