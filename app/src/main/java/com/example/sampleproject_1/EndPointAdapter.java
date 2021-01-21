package com.example.sampleproject_1;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;


import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


class EndPointAdapter extends RecyclerView.Adapter<ViewHolderApiEnds> {
    List<EndPointModel> data1;
    JsonPlaceHolderApi jsonPlaceHolderApi;

 public EndPointAdapter(List<EndPointModel> data1,JsonPlaceHolderApi activity) {
        this.data1 = data1;
        this.jsonPlaceHolderApi=activity;
    }

    @NonNull
    @Override
    public ViewHolderApiEnds onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_endpoints_api_layout,parent,false);
        return new ViewHolderApiEnds(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderApiEnds holder, int position) {

        holder.endPointText.setText(data1.get(position).getEndpointText());

         holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {

                Toast.makeText(jsonPlaceHolderApi, "Endpoints", Toast.LENGTH_SHORT).show();

                //JsonPlaceHolderApi activity = (JsonPlaceHolderApi)v.getContext();
                Fragment fragment =new Fragment();
                jsonPlaceHolderApi.getSupportFragmentManager()
                        .beginTransaction().replace(R.id.frame_layout123,fragment)
                        .commit();
            }
        });
    }
@Override
    public int getItemCount() {
        return data1.size();
    }
}
