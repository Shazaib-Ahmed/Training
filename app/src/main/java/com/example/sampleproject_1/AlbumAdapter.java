package com.example.sampleproject_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumViewHolder>{
    List<AlbumModelClass> albumData;
    Context context;


    public AlbumAdapter(Context context, List<AlbumModelClass> albumModelClasses) {
        this.albumData = albumModelClasses;
        this.context = context;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.item_album_api,parent,false);

        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {

        holder.userID_album.setText("User ID: "+albumData.get(position).getUserId());
        holder.ID_album.setText("ID: "+albumData.get(position).getId());
        holder.title_album.setText("Title: "+albumData.get(position).getTitle());


    }

    @Override
    public int getItemCount() {
        return albumData.size();
    }
}
