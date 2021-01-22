package com.example.sampleproject_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder>{
    List<CommentModelClass> commentData;
    Context context;


    public CommentAdapter(Context context, List<CommentModelClass> commentModelClasses) {
        this.commentData = commentModelClasses;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.item_comment_api_layout,parent,false);

        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {

        holder.postID.setText("Post ID: "+commentData.get(position).getPostId());
        holder.ID.setText("ID: "+commentData.get(position).getId());
        holder.Name.setText("Name: "+commentData.get(position).getName());
        holder.Email.setText("Email: "+commentData.get(position).getEmail());
        holder.Body.setText("Body: "+commentData.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return commentData.size();
    }
}
