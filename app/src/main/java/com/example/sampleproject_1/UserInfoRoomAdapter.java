package com.example.sampleproject_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserInfoRoomAdapter extends RecyclerView.Adapter<UserInfoRoomAdapter.UserInfoRoomHolder> {
    private List<UserInfo> userInfo =new ArrayList<>();

    @NonNull
    @Override
    public UserInfoRoomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_info_room,parent,false);
        return new UserInfoRoomHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserInfoRoomHolder holder, int position) {

        UserInfo currentUser = userInfo.get(position);

        holder.textViewId.setText(String.valueOf(currentUser.getId()));
        holder.textViewName.setText(currentUser.getName());
        holder.textViewAge.setText(""+currentUser.getAge());
        holder.textViewEmail.setText(currentUser.getEmail());
        holder.textViewMobileNumber.setText(""+currentUser.getMobileNumber());


    }

    @Override
    public int getItemCount() {
        return userInfo.size();
    }

    public void setUserInfo(List<UserInfo>userInfos){
        this.userInfo = userInfos;
        notifyDataSetChanged();
    }

    public UserInfo getUserInfoAt(int position){
        return userInfo.get(position);
    }


    class UserInfoRoomHolder extends RecyclerView.ViewHolder{
        private TextView textViewId;
        private TextView textViewName;
        private TextView textViewAge;
        private TextView textViewEmail;
        private TextView textViewMobileNumber;

        public UserInfoRoomHolder(@NonNull View itemView) {
            super(itemView);
            textViewId =itemView.findViewById(R.id.textView_id);
            textViewName =itemView.findViewById(R.id.textView_name);
            textViewAge =itemView.findViewById(R.id.textView_age);
            textViewEmail =itemView.findViewById(R.id.textView_email);
            textViewMobileNumber =itemView.findViewById(R.id.textView_mobileNumber);


        }
    }
}
