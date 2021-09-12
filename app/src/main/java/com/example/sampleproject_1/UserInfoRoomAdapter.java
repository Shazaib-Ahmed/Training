package com.example.sampleproject_1;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserInfoRoomAdapter extends RecyclerView.Adapter<UserInfoRoomAdapter.UserInfoRoomHolder> {
    private List<UserInfo> userInfo = new ArrayList<>();
    Context context;
    String checker = "";
    UserInfoViewModel userInfoViewModel;
    Application application;
    OnItemClickListener listener;

    public UserInfoRoomAdapter(Context context) {

        this.context = context;
    }

    @NonNull
    @Override
    public UserInfoRoomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_info_room, parent, false);
        return new UserInfoRoomHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserInfoRoomHolder holder, int position) {

        UserInfo currentUser = userInfo.get(position);
        userInfoViewModel = new UserInfoViewModel(application);
       // holder.textViewId.setText("ID: " + (currentUser.getId()));
        holder.textViewName.setText("NAME: " + currentUser.getName());
        holder.textViewAge.setText("AGE: " + (currentUser.getAge()));
        holder.textViewEmail.setText("EMAIL: " + currentUser.getEmail());
        holder.textViewMobileNumber.setText("MOBILE NUMBER: " + (currentUser.getMobileNumber()));

        holder.moreOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence options[] = new CharSequence[]
                        {
                                "Edit",
                                "Details",
                                "Delete"
                        };
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Select");

                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            Toast.makeText(context, "Edit touch", Toast.LENGTH_SHORT).show();
                        }
                        if (i == 1) {
                            Toast.makeText(context, "Details touch", Toast.LENGTH_SHORT).show();
                        }
                        if (i == 2) {
                            userInfoViewModel.delete(currentUser);
                            Toast.makeText(context, "User Deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userInfo.size();
    }

    public void setUserInfo(List<UserInfo> userInfos) {
        this.userInfo = userInfos;
        notifyDataSetChanged();
    }

    public UserInfo getUserInfoAt(int position) {
        return userInfo.get(position);
    }


    class UserInfoRoomHolder extends RecyclerView.ViewHolder {

       // private TextView textViewId;
        private TextView textViewName;
        private TextView textViewAge;
        private TextView textViewEmail;
        private TextView textViewMobileNumber;
        private ImageView moreOptions;

        public UserInfoRoomHolder(@NonNull View itemView) {
            super(itemView);
           // textViewId = itemView.findViewById(R.id.textView_id);
            textViewName = itemView.findViewById(R.id.textView_name);
            textViewAge = itemView.findViewById(R.id.textView_age);
            textViewEmail = itemView.findViewById(R.id.textView_email);
            textViewMobileNumber = itemView.findViewById(R.id.textView_mobileNumber);
            moreOptions = itemView.findViewById(R.id.more_option_room);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(userInfo.get(position));
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(UserInfo userInfo);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
