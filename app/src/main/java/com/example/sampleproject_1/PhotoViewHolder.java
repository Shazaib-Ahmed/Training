package com.example.sampleproject_1;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class PhotoViewHolder extends RecyclerView.ViewHolder {
    ImageView url,thumbnailUrl;
    TextView idd,albumId,title;
            public PhotoViewHolder(@NonNull View itemView) {

                super(itemView);

                url=itemView.findViewById(R.id.url);
                thumbnailUrl=itemView.findViewById(R.id.thumbnailUrl);
                albumId=itemView.findViewById(R.id.album_id);
                idd=itemView.findViewById(R.id.idd);
                thumbnailUrl=itemView.findViewById(R.id.thumbnailUrl);

            }
        }
