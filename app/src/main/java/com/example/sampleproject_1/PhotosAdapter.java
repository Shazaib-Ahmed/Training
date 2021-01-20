package com.example.sampleproject_1;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class PhotosAdapter extends RecyclerView.Adapter<PhotoViewHolder>{

    ArrayList<PhotoModelClass> data= new ArrayList<>();

    public PhotosAdapter(ArrayList<PhotoModelClass> data) {
        this.data = data;
    }

    public PhotosAdapter(Context context, List<PhotoModelClass> photoModelClasses)
    {
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.item_photos_api_layout,parent,false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        holder.albumId.setText(data.get(position).getAlbumId());
        holder.idd.setText(data.get(position).getId());
        holder.title.setText(data.get(position).getTitle());



    }

    @Override
    public int getItemCount() {
        return data.size();
  }
}
