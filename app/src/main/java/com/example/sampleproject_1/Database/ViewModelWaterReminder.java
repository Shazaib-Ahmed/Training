package com.example.sampleproject_1.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sampleproject_1.UserInfo;

import java.util.List;

public class ViewModelWaterReminder extends AndroidViewModel {
    private RepositoryWaterReminder repository;
    private List<UserInfo> allUserInfo;


    public ViewModelWaterReminder(@NonNull Application application) {
        super(application);
        repository = new RepositoryWaterReminder(application);
        allUserInfo =repository.getAllUserInfo();
    }

    public void insert(EntityWaterReminder entityWaterReminder) {
        repository.insert(entityWaterReminder);
    }

}
