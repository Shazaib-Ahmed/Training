package com.example.sampleproject_1.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

public class ViewModelWaterReminder extends AndroidViewModel {
    private RepositoryWaterReminder repository;
    private List<EntityWaterReminder> getGender;


    public ViewModelWaterReminder(@NonNull Application application) {
        super(application);
        repository = new RepositoryWaterReminder(application);
       repository.getUserGender();
    }

    public void insert(EntityWaterReminder entityWaterReminder) {
        repository.insert(entityWaterReminder);
    }

}
