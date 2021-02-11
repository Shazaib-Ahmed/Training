package com.example.sampleproject_1.WaterReminder.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleproject_1.R;
import com.example.sampleproject_1.WaterReminder.model.WaterIntake;

import java.util.ArrayList;

public class TodayWaterIntakeAdapter extends RecyclerView.Adapter<TodayWaterIntakeAdapter.TWIViewHolder> {


    private final ArrayList<WaterIntake> data;

    public TodayWaterIntakeAdapter(ArrayList<WaterIntake> data) {
        this.data = data;
    }


    @NonNull
    @Override
    public TWIViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_water_inteke, parent, false);
        return new TWIViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TWIViewHolder holder, int position) {
        holder.bindData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateData(WaterIntake s) {
        data.add(s);
        notifyDataSetChanged();
    }

    static class TWIViewHolder extends RecyclerView.ViewHolder {
        private TextView tvWaterIntakeTime;
        private TextView tvWaterQuantity;

        public TWIViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWaterIntakeTime = itemView.findViewById(R.id.tvTime);
            tvWaterQuantity = itemView.findViewById(R.id.tvWaterQuantity);

        }

        public void bindData(WaterIntake s) {
            tvWaterIntakeTime.setText(s.time);
            tvWaterQuantity.setText(s.quantity + "");
        }
    }
}
