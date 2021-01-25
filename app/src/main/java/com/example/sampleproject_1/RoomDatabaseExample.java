package com.example.sampleproject_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;


public class RoomDatabaseExample extends AppCompatActivity {
    UserInfoViewModel userInfoViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_database_example);

        userInfoViewModel =new ViewModelProvider(this).get(UserInfoViewModel.class);


         userInfoViewModel.getAllUserInfo().observe(this, new Observer<List<UserInfo>>() {
            @Override
            public void onChanged(List<UserInfo> userInfos) {
                //update recyclerView
                Toast.makeText(RoomDatabaseExample.this, "onChanged", Toast.LENGTH_SHORT).show();
            }
        });



    }
}