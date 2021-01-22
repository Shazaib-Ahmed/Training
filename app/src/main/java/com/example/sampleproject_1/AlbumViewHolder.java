package com.example.sampleproject_1;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class AlbumViewHolder extends RecyclerView.ViewHolder {
    TextView userID_album,ID_album,title_album;
    public AlbumViewHolder(@NonNull View itemView) {
        super(itemView);

        userID_album=itemView.findViewById(R.id.userID_album);
        ID_album=itemView.findViewById(R.id.ID_album);
        title_album=itemView.findViewById(R.id.title_album);

    }
}
