package com.example.sampleproject_1;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ViewHolderApiEnds extends RecyclerView.ViewHolder
{
    TextView endPointText;
    public ViewHolderApiEnds(@NonNull View itemView) {
        super(itemView);
        endPointText=itemView.findViewById(R.id.end_points_text);
    }
}
