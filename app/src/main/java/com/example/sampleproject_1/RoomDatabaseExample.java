package com.example.sampleproject_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;


public class RoomDatabaseExample extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    UserInfoViewModel userInfoViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_database_example);

        Button addUserInfoButton = findViewById(R.id.add_userInfo_button);
        addUserInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoomDatabaseExample.this, AddEditUserInfo.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });

        RecyclerView recyclerViewRoomDatabase = findViewById(R.id.rv_room_database);
        recyclerViewRoomDatabase.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewRoomDatabase.setHasFixedSize(true);

        UserInfoRoomAdapter userInfoRoomAdapter = new UserInfoRoomAdapter(this);
        recyclerViewRoomDatabase.setAdapter(userInfoRoomAdapter);

        userInfoViewModel = new ViewModelProvider(this).get(UserInfoViewModel.class);


        userInfoViewModel.getAllUserInfo().observe(this, new Observer<List<com.example.sampleproject_1.UserInfo>>() {
            @Override
            public void onChanged(List<com.example.sampleproject_1.UserInfo> userInfos) {
                userInfoRoomAdapter.setUserInfo(userInfos);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                userInfoViewModel.delete(userInfoRoomAdapter.getUserInfoAt(viewHolder.getAdapterPosition()));
                Toast.makeText(RoomDatabaseExample.this, "User Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerViewRoomDatabase);

        userInfoRoomAdapter.setOnItemClickListener(new UserInfoRoomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(UserInfo userInfo) {
                Intent intent = new Intent(RoomDatabaseExample.this, AddEditUserInfo.class);
                intent.putExtra(AddEditUserInfo.EXTRA_ID, userInfo.getId());
                intent.putExtra(AddEditUserInfo.EXTRA_NAME, userInfo.getName());
                intent.putExtra(AddEditUserInfo.EXTRA_AGE, userInfo.getAge());
                intent.putExtra(AddEditUserInfo.EXTRA_EMAIL, userInfo.getEmail());
                intent.putExtra(AddEditUserInfo.EXTRA_MOBILE, userInfo.getMobileNumber());
                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String name = data.getStringExtra(AddEditUserInfo.EXTRA_NAME);
            int age = data.getIntExtra(AddEditUserInfo.EXTRA_AGE, 0);
            String email = data.getStringExtra(AddEditUserInfo.EXTRA_EMAIL);
            String mobileNumber = data.getStringExtra(AddEditUserInfo.EXTRA_MOBILE);

            UserInfo userInfo = new UserInfo(name, age, email, mobileNumber);
            userInfoViewModel.insert(userInfo);

            Toast.makeText(this, "User info saved", Toast.LENGTH_SHORT).show();

        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditUserInfo.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                return;
            }
            String name = data.getStringExtra(AddEditUserInfo.EXTRA_NAME);
            int age = data.getIntExtra(AddEditUserInfo.EXTRA_AGE, 0);
            String email = data.getStringExtra(AddEditUserInfo.EXTRA_EMAIL);
            String mobileNumber = data.getStringExtra(AddEditUserInfo.EXTRA_MOBILE);

            UserInfo userInfo = new UserInfo(name, age, email, mobileNumber);
            userInfo.setId(id);
            userInfoViewModel.update(userInfo);

            Toast.makeText(this, "User info Updated", Toast.LENGTH_SHORT).show();


        } else {
            Toast.makeText(this, "User info cannot be updated", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.room_database_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_users:
                userInfoViewModel.deleteAllUserInfo();
                Toast.makeText(this, "All users deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}