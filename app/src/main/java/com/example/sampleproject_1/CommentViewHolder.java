package com.example.sampleproject_1;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class CommentViewHolder extends RecyclerView.ViewHolder {
    TextView postID,ID,Email,Body,Name;
    public CommentViewHolder(@NonNull View itemView) {
        super(itemView);

        postID=itemView.findViewById(R.id.postID_comment);
        ID=itemView.findViewById(R.id.ID_comment);
        Email=itemView.findViewById(R.id.email_comment);
        Body=itemView.findViewById(R.id.body_comment);
        Name=itemView.findViewById(R.id.name_comment);
    }
}
