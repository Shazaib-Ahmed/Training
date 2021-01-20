package com.example.sampleproject_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;


public class PhotosAdapter extends RecyclerView.Adapter<PhotoViewHolder>{

    List<PhotoModelClass> data;
    Context context;


    public PhotosAdapter(Context context, List<PhotoModelClass> photoModelClasses)
    {
        this.data=photoModelClasses;
        this.context=context;
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

        holder.albumId.setText("ALBUM ID: "+data.get(position).getAlbumId());
        holder.idd.setText("ID: "+data.get(position).getId());
        holder.title.setText("TITLE: "+data.get(position).getTitle());

        String url = data.get(position).getThumbnailUrl();
        Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.scene1)
                .into(holder.thumbnailUrl);



    }

    @Override
    public int getItemCount() {
        return data.size();
  }
}
