package com.example.sampleproject_1.WaterReminder.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Adapter.TodayWaterIntakeAdapter.TWIViewHolder
import com.example.sampleproject_1.WaterReminder.model.WaterIntake
import java.util.*

class TodayWaterIntakeAdapter //this.data = data;
(private val data: ArrayList<WaterIntake>) : RecyclerView.Adapter<TWIViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TWIViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.item_water_inteke, parent, false)
        return TWIViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: TWIViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(s: WaterIntake) {
        data.add(s)
        notifyDataSetChanged()
    }

    class TWIViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvWaterIntakeTime: TextView = itemView.findViewById(R.id.tvTime)
        private val tvWaterQuantity: TextView = itemView.findViewById(R.id.tvWaterQuantity)
        fun bindData(s: WaterIntake) {
            tvWaterIntakeTime.text = s.time
            tvWaterQuantity.text = s.quantity.toString() + " ml"
        }

    }

}